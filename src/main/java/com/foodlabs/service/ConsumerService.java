package com.foodlabs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodlabs.dto.response.product.ProductCatalogResponse;
import com.foodlabs.factory.ProductFactory;
import com.foodlabs.model.Category;
import com.foodlabs.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final ProductService productService;
    private final ProductFactory productFactory;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload) {
        log.info("CatalogService::consume: {}", payload.value());
        createCatalogFile();
    }

    @SneakyThrows
    private void createCatalogFile() {

        Map<Category, List<Product>> productsByCategory = productService.getAllProducts()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        Map<String, List<ProductCatalogResponse>> productsMap = productsByCategory.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> entry.getValue().stream()
                                .map(productFactory::createProductCatalogResponse)
                                .toList()
                ));


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productsMap);

        FileWriter fileWriter = new FileWriter("catalog.json");
        fileWriter.write(json);
        fileWriter.close();

        log.info("ConsumerService::createCatalogFile: {}", json);
    }

}

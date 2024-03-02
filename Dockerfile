# Usa uma imagem base com Maven para construir o projeto
FROM maven:3.8.4-openjdk-17 AS builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos de configuração do projeto Maven
COPY pom.xml .
COPY src ./src

# Executa o comando Maven para construir o projeto
RUN mvn clean install -DskipTests

# Usa uma imagem base do OpenJDK 17 para a execução da aplicação
FROM openjdk:17-jdk-slim-buster

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR da aplicação construído na etapa anterior
COPY --from=builder /app/target/foodlabs-0.0.1-SNAPSHOT.jar /app

# Expõe a porta 8080 para acesso à aplicação
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "foodlabs-0.0.1-SNAPSHOT.jar"]

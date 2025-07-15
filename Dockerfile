# Estágio 1: Build - Usa uma imagem com Maven e Java para compilar o projeto
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Estágio 2: Run - Usa uma imagem leve apenas com Java para rodar a aplicação
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
# Copia o arquivo .jar gerado no estágio anterior
COPY --from=builder /app/target/arealogin-0.0.1-SNAPSHOT.jar ./app.jar
# Expõe a porta que o Spring Boot usa
EXPOSE 8080
# Comando para iniciar a aplicação quando o container rodar
CMD ["java", "-jar", "app.jar"]
# Estágio de build
FROM maven:3.8.4-openjdk-17 AS build
COPY /src /app/src
COPY /pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

# Estágio de produção
FROM openjdk:17
EXPOSE 8080

# Copia o JAR gerado do estágio anterior (build) para o diretório /target no container
COPY --from=build /app/target/api-codando-0.0.1-SNAPSHOT.jar /app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
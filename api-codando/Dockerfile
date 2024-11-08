FROM maven:3.8.4-openjdk-17-alpine AS build
COPY /src /app/src
COPY /pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip
FROM openjdk:17-jre-alpine
EXPOSE 8080
COPY --from=build /app/target/api-codando-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM maven:3.6.1-jdk-8-slim AS build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn -f /app/pom.xml clean package

FROM gcr.io/distroless/java
COPY --from=build /app/target/restful-web-services-0.0.1-SNAPSHOT.jar /usr/local/lib/backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/backend.jar"]
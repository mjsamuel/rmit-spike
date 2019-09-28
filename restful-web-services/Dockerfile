FROM maven:3.6.1-jdk-8-slim AS build
WORKDIR /app

ARG HOST_DB_VAR
ARG PASSWD_DB_VAR

ENV HOST_DB=${HOST_DB_VAR}
ENV PASSWD_DB=${PASSWD_DB_VAR}

COPY src ./src
COPY pom.xml .
COPY install.config.sh .
RUN ./install.config.sh

RUN mvn -f /app/pom.xml clean package

FROM gcr.io/distroless/java
COPY --from=build /app/target/restful-web-services-0.0.1-SNAPSHOT.jar /usr/local/lib/backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/backend.jar"]
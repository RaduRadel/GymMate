# Stage 1: build with Maven + Temurin JDK
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY src/ ./src
RUN mvn clean package -DskipTests

# Stage 2: run on a slim JRE
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

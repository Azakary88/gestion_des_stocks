FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copier pom.xml uniquement
COPY pom.xml .
RUN mvn -B -U -e -DskipTests dependency:resolve

# Copier le code source
COPY src ./src
RUN mvn -B -U -DskipTests package

# Image finale
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/inventory-backend-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

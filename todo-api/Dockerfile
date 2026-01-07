# Estágio 1: Build (Usando Maven com Temurin)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Usando apenas a JRE para ser mais leve)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# O nome do jar gerado pelo Maven costuma ser demo-0.0.1-SNAPSHOT.jar 
# ou o nome definido no pom.xml. O asterisco ajuda a copiar sem erro de nome exato.
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
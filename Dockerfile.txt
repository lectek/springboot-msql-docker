# Etapa 1: build da aplicação
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem final para rodar
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/EmbalandoApp-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
HEALTHCHECK CMD curl --fail http://localhost:8080/ || exit 1
ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]

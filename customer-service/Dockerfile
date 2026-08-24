# Build stage
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY customer-api-1.0.3-SNAPSHOT.jar .

RUN chmod +x mvnw

RUN ./mvnw install:install-file \
    -Dfile=customer-api-1.0.3-SNAPSHOT.jar \
    -DgroupId=com.carlosgil \
    -DartifactId=customer-api \
    -Dversion=1.0.3-SNAPSHOT \
    -Dpackaging=jar

COPY src ./src

RUN ./mvnw clean package -DskipTests


# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
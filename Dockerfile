FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN ./gradlew clean build
RUN cp build/libs/productsmanagement-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

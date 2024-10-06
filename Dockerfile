FROM openjdk:17-jdk-slim
COPY target/carshop.jar /app/carshop.jar
ENTRYPOINT ["java", "-jar", "/app/carshop.jar"]

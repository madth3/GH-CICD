FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

VOLUME /app
COPY target/*.jar tallerspring.jar
ENTRYPOINT ["java", "-jar", "tallerspring.jar"]
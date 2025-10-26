FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/carpincho-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_carpincho.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_carpincho.jar"]
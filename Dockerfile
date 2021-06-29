FROM openjdk:11.0.11-jre-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 4040

ENTRYPOINT ["java","-jar","/app.jar"]

# For Java 8
FROM openjdk:8-jdk-alpine

# For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/basic-transaction-api-v1-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} basic-transaction-api-v1.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","basic-transaction-api-v1.jar"]
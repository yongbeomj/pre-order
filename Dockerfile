FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=build/libs/pre-order-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Seoul
WORKDIR usr/src/app
ENTRYPOINT ["java", "-jar", "/app.jar"]
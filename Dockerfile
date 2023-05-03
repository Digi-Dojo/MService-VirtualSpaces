FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/virtualspaces-0.0.1-SNAPSHOT-plain.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
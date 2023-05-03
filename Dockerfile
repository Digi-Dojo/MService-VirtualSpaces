
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./gradlew bootjar --no-daemon




FROM openjdk:17-jdk
EXPOSE 8888
COPY --from=build /build/libs/virtualspaces-0.0.1-SNAPSHOT.jar dockerapp.jar
ENTRYPOINT ["java","-jar","dockerapp.jar"]
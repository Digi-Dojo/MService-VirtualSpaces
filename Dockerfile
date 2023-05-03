
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon




FROM openjdk:17-jdk
EXPOSE 8080
COPY --from=build /build/libs/dpg-ch94qfjhp8u0vh9tkm00-a-1.jar dockerapp.jar
ENTRYPOINT ["java","-jar","dockerapp.jar"]
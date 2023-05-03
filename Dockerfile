FROM openjdk:17-jdk
ADD build/libs/virtualspaces-0.0.1-SNAPSHOT.jar dockerapp.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","dockerapp.jar"]
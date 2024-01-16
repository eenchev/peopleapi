FROM openjdk:21-ea-28-jdk-bullseye
COPY target/peopleapi-*-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar"]
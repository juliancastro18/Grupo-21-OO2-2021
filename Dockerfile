FROM amazoncorretto:11-alpine-jdk
MAINTAINER grupo21
COPY target/grupo21-0.0.1-SNAPSHOT.jar grupo21-app.jar
ENTRYPOINT ["java","-jar","/grupo21-app.jar"]
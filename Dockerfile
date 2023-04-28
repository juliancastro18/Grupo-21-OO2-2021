#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-11-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:19
COPY --from=build /target/grupo21-0.0.1-SNAPSHOT.jar grupo21-app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","grupo21-app.jar"]
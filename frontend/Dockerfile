FROM gradle:7.5.1-jdk17 AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build 

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

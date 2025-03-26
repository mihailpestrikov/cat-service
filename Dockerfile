FROM gradle:8.5-jdk20 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

FROM eclipse-temurin:20.0.2_9-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/banking-system-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

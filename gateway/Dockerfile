FROM amazoncorretto:17-alpine3.23

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8888

LABEL authors="grazielle"

ENTRYPOINT ["java", "-jar", "app.jar"]
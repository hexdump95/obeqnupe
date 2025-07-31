FROM amazoncorretto:21-alpine3.22

WORKDIR /app
LABEL org.opencontainers.image.source="https://github.com/hexdump95/obeqnupe"
LABEL org.opencontainers.image.authors="Sergio Villanueva <sergiovillanueva@protonmail.com>"
EXPOSE 8080

COPY ./build/libs/obeqnupe-0.0.1-SNAPSHOT.jar ./app.jar

ENV SPRING_PROFILES_ACTIVE=prod

CMD ["java", "-jar", "app.jar"]

FROM openjdk:13-alpine
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw install -DskipTests

FROM openjdk:13-alpine
VOLUME /tmp
COPY target/JUMIA.jar JUMIA.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/JUMIA.jar"]
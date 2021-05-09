#### Stage 1: Build the application
FROM openjdk:8-jdk-alpine as build

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY src src

# Package the application
RUN ./mvnw install -DskipTests

FROM tomcat:9.0.24
ARG DEPENDENCY=/app/target
COPY --from=build ${DEPENDENCY}/CardTrading-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/CardTrading-0.0.1-SNAPSHOT.war
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=docker -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/CardTrading-0.0.1-SNAPSHOT.war" ]
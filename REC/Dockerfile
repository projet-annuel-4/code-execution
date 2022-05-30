FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn clean
COPY src ./src
RUN mvn package

FROM openjdk:17-alpine

COPY --from=build /home/app/target/*.jar /usr/local/lib/build.jar
EXPOSE 3001

ENTRYPOINT ["java","-jar","/usr/local/lib/build.jar"]


# docker build -t test .  */
# docker run -d --network host --name producer test */
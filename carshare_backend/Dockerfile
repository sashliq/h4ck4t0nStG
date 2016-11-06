FROM openjdk:8-jre-alpine

RUN mkdir -p /usr/src/myapp
WORKDIR /usr/src/myapp
COPY target/carshare-0.0.1-SNAPSHOT.jar /usr/src/myapp
CMD ["java", "-jar", "/usr/src/myapp/carshare-0.0.1-SNAPSHOT.jar"]

FROM openjdk:21

LABEL mentainer ="pk356639@gmail.com"

WORKDIR /app

COPY target/BusBooking-0.0.1-SNAPSHOT.jar /app/BusBooking-docker.jar

ENTRYPOINT ["java","-jar","BusBooking-docker.jar"]



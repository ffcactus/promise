FROM openjdk:11
MAINTAINER Bai, Bin <ffcactus@me.com>

ARG JAR_FILE
ARG PORT
ADD ./target/${JAR_FILE} /app/application.jar
CMD ["java", "-Xmx200m", "-jar", "/app/application.jar"]

EXPOSE ${PORT}
FROM openjdk:11-jre
MAINTAINER Bai, Bin <ffcactus@me.com>

ARG JAR_FILE
ADD ./target/${JAR_FILE} /app/application.jar
CMD ["java", "-Xmx200m", "-jar", "/app/application.jar"]

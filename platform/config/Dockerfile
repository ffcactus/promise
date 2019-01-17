FROM openjdk:11-jre
MAINTAINER Bai, Bin <ffcactus@me.com>

ARG JAR_FILE

ADD ./target/${JAR_FILE} /app/application.jar
CMD ["java", "-Xmx200m", "-jar", "/app/application.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8888
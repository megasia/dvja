FROM eu.gcr.io/google-containers/cos-kernel-headers:latest
MAINTAINER Nobody <nobody@mainteiner.this>

RUN apt-get update && apt-get install -y maven default-mysql-client default-jdk

WORKDIR /app
COPY pom.xml pom.xml
RUN mvn dependency:resolve

COPY . .
RUN mvn clean package
RUN chmod 755 /app/scripts/start.sh

EXPOSE 8080
CMD ["sh", "-c", "/app/scripts/start.sh"]


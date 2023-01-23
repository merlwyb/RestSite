FROM maven AS MAVEN_BUILD

MAINTAINER Radmir Yanubekov

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/rest-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "rest-0.0.1-SNAPSHOT.jar"]

# docker image build -t docker-rest-test .
# docker container run -p 8888:8888 docker-rest-test

# docker save -o docker-rest-test.tar docker-rest-test
# docker load -i docker-rest-test.tar


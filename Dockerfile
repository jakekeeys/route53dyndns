FROM java:8

COPY ./target/dyndns.jar /usr/src/dyndns.jar

WORKDIR /usr/src

ENTRYPOINT ["java", "-jar", "dyndns.jar"]
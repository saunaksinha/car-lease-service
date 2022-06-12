FROM quantics/jdk-11
MAINTAINER saunak
COPY target/car-lease-service-0.0.1-SNAPSHOT.jar car-lease-service.jar
EXPOSE 6379
ENTRYPOINT ["java","-jar","/car-lease-service.jar"]

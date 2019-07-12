FROM anapsix/alpine-java:8

ADD target/RestClient-0.0.1-SNAPSHOT.jar RestClient.jar

ENTRYPOINT ["java","-jar","/RestClient.jar"]

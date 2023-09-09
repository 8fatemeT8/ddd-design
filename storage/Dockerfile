FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/storage-0.0.1-SNAPSHOT.jar storage.jar
ENTRYPOINT ["java","-jar","/storage.jar"]
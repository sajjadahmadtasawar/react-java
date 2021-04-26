FROM openjdk:11-jre-slim
COPY /target/sivadmin-0.0.1-SNAPSHOT.jar /home/sivadmin.jar
CMD ["java", "-jar", "/home/sivadmin.jar"]
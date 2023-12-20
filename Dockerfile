FROM openjdk:17-jdk

WORKDIR /app

COPY target/test_springboot1.jar /app/test_springboot1.jar

EXPOSE 8082

CMD ["java", "-jar", "test_springboot1.jar"]
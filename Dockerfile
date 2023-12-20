FROM openjdk:17-jdk

WORKDIR /app

COPY your-app.jar /app/your-app.jar

EXPOSE 8080

CMD ["java", "-jar", "your-app.jar"]
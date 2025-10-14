FROM openjdk:17
COPY target/subscription-service-twitterlike-0.0.1-SNAPSHOT.jar subscription-service-twitterlike-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "subscription-service-twitterlike-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

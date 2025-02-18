# Base image
FROM openjdk:21-jdk-slim

# Information around who maintains the image
LABEL author=ngleanhvu.com

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Excute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]


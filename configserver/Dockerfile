FROM openjdk:21-jdk-slim

LABEL author=ngleanhvu.com

# Cài đặt curl
RUN apt-get update && apt-get install -y curl

# Copy ứng dụng vào container
COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

# Đặt ENTRYPOINT để chạy ứng dụng Java
ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]

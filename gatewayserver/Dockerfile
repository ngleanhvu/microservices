FROM openjdk:21-jdk-slim

LABEL author=ngleanhvu.com

# Cài đặt curl
RUN apt-get update && apt-get install -y curl

# Copy ứng dụng vào container
COPY target/gatewayserver-0.0.1-SNAPSHOT.jar gatewayserver-0.0.1-SNAPSHOT.jar

# Đặt ENTRYPOINT để chạy ứng dụng Java
ENTRYPOINT ["java", "-jar", "gatewayserver-0.0.1-SNAPSHOT.jar"]

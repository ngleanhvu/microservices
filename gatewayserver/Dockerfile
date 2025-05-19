# Base image
FROM openjdk:21-jdk-slim

LABEL author=ngleanhvu.com
RUN apt-get update && apt-get install -y wget && apt-get clean
# Tạo thư mục chứa agent
RUN mkdir -p /app/libs

# Tải OpenTelemetry Java Agent
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.32.0/opentelemetry-javaagent.jar /app/libs/opentelemetry-javaagent-1.32.0.jar

# Làm việc trong thư mục /app
WORKDIR /app

# Copy file .jar vào đúng thư mục
COPY target/gatewayserver-0.0.1-SNAPSHOT.jar /app/gatewayserver-0.0.1-SNAPSHOT.jar

# Chạy ứng dụng với agent
ENTRYPOINT ["java", "-javaagent:/app/libs/opentelemetry-javaagent-1.32.0.jar", "-jar", "gatewayserver-0.0.1-SNAPSHOT.jar"]

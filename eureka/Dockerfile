# ---------- Stage 1: Build ----------
FROM openjdk:21-jdk-slim AS build
WORKDIR /app

# Copy toàn bộ project vào image
COPY . .

# Fix lỗi permission cho file mvnw
RUN chmod +x mvnw

# Build project (bỏ qua test)
RUN ./mvnw clean package -DskipTests

# ---------- Stage 2: Runtime ----------
FROM gcr.io/distroless/java21:nonroot

# Tạo thư mục làm việc
WORKDIR /app

# Copy file JAR từ stage build sang
COPY --from=build /app/target/eureka-0.0.1-SNAPSHOT.jar app.jar

# Khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

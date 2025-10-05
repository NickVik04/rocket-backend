# 1️⃣ Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# 2️⃣ Set working directory inside the container
WORKDIR /app

# 3️⃣ Copy everything from your project into the container
COPY . .

# 4️⃣ Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# 5️⃣ Expose port (Render assigns one dynamically)
EXPOSE 8080

# 6️⃣ Start the Spring Boot JAR
CMD ["java", "-jar", "target/rocket-backend-0.0.1-SNAPSHOT.jar"]

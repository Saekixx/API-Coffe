# ==========================================
# ETAPA 1: Compilación y Generación del JAR
# ==========================================
FROM eclipse-temurin:25-jdk-alpine AS builder

WORKDIR /app

# 1. Copiar wrapper y pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Descargar dependencias
RUN ./mvnw dependency:go-offline -B

# 2. Copiar código y compilar
COPY src src
RUN ./mvnw clean package -DskipTests

# ==========================================
# ETAPA 2: Imagen Final de Producción (Runtime)
# ==========================================
FROM eclipse-temurin:25-jre-alpine AS runner

WORKDIR /app

# Crear usuario sin privilegios
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el JAR compilado desde la etapa builder
COPY --from=builder --chown=spring:spring /app/target/*.jar app.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
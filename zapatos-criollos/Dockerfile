# Imagen con Java y Maven
FROM maven:3.9-eclipse-temurin-17

# Copia el proyecto
WORKDIR /app
COPY . .

# Crea el archivo .jar
RUN mvn -q package -DskipTests

# Ejecuta el programa
CMD ["java", "-jar", "target/zapatos-criollos-1.0.jar"]

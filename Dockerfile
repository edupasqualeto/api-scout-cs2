# Usa Java 17 (ajusta se precisar)
FROM eclipse-temurin:17-jdk-alpine

# Diretório dentro do container
WORKDIR /app

# Copia o jar gerado
COPY target/*.jar app.jar

# Comando para rodar
ENTRYPOINT ["java", "-jar", "app.jar"]

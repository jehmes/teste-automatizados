# Define a imagem base a ser usada
FROM openjdk:11-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o jar gerado pelo Maven para o diretório de trabalho
COPY ./target*.jar app.jar

# Expõe a porta 8080 para que possa ser acessada de fora do container
EXPOSE 8080

# Comando a ser executado quando o container for iniciado
CMD ["java", "-jar", "app.jar"]


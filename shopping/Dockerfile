#Etapa 1: Construccion del jar
#DockerFile está dividido en 2 capas
FROM maven:3.8.5-openjdk-17

#ENV DATABASE_URL r2dbc:mysql://localhost:3306/shoppingdb r2dbc:mysql://db:3306/shoppingdb?createDatabaseIfNotExist=true&serverTimezone=UTC
#ENV DATABASE_USERNAME root
#ENV DATABASE_PASSWORD 123456
#WORKDIR /app

#Antes realizar un mvn clean - package
COPY target/shopping-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

#docker build -t shopping-image:1 .
#docker build -t springboot-shopping-image:1 .
#docker run -p 8080:8080 springboot-shopping


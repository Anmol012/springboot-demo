#!/bin/bash

APP_NAME="spring-app"
MYSQL_CONTAINER="mysql-db"
NETWORK="springnet"
MYSQL_ROOT_PASSWORD="rootpass"
MYSQL_DATABASE="testdb"

echo "==== Building Spring Boot JAR ===="
mvn clean package -DskipTests

echo "==== Creating Docker Network ===="
docker network create $NETWORK || echo "Network already exists"

echo "==== Starting MySQL Container ===="
docker rm -f $MYSQL_CONTAINER 2>/dev/null

docker run -d \
  --name $MYSQL_CONTAINER \
  --network $NETWORK \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -v $(pwd)/init.sql:/docker-entrypoint-initdb.d/init.sql \
  -p 3301:3306 \
  mysql:latest

echo "Waiting for MySQL to initialize..."
sleep 20

echo "==== Building Spring App Docker Image ===="
docker build -t $APP_NAME .

echo "==== Starting Spring Boot Container ===="
docker rm -f $APP_NAME 2>/dev/null
docker run -d \
  --name $APP_NAME \
  --network $NETWORK \
  -p 8087:8080 \
  $APP_NAME

echo "==== DONE ===="
echo "MySQL running on port 3306"
echo "Spring Boot running on port 8087"
echo "App URL: http://localhost:8080"

#!/bin/bash

echo "Gerando build do projeto..."
./mvnw clean package -DskipTests

echo ""
echo "Subindo containers Docker..."
docker compose up --build

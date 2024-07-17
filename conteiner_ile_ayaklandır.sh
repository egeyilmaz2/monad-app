#!/bin/bash

# Docker'ın yüklü olup olmadığını kontrol et
if ! command -v docker &> /dev/null
then
    echo "Docker yüklü değil. Lütfen Docker Desktop'ı yükleyin ve tekrar deneyin."
    exit 1
fi

# Docker Compose'un yüklü olup olmadığını kontrol et
if ! command -v docker-compose &> /dev/null
then
    echo "Docker Compose yüklü değil. Lütfen Docker Compose'u indirip kurun."
    exit 1
fi

# Backend ve Frontend image'larının mevcut olup olmadığını kontrol et
backend_image_exists=$(docker images -q backend-app:latest)
frontend_image_exists=$(docker images -q frontend-app:latest)

# Eğer backend image mevcut değilse, backend'i build et ve image oluştur
if [ -z "$backend_image_exists" ]; then
    echo "Backend image bulunamadı. Backend'i build ediyor ve Docker image oluşturuyor..."
    cd backend/main-app
    ./gradlew clean build
    docker build -t backend-app:latest .
    cd ../..
else
    echo "Backend image zaten mevcut."
fi

# Eğer frontend image mevcut değilse, frontend'i build et ve Docker image oluştur
if [ -z "$frontend_image_exists" ]; then
    echo "Frontend image bulunamadı. Frontend'i build ediyor ve Docker image oluşturuyor..."
    cd frontend
    ./gradlew clean 
	echo "Docker image oluşturuluyor..."
    docker build -t frontend-app:latest . --progress=plain
    cd ..
else
    echo "Frontend image zaten mevcut."
fi

# Image'ları tar dosyası olarak devops/images klasörüne kopyala
mkdir -p devops/images
docker save backend-app:latest -o devops/images/backend-app.tar
docker save frontend-app:latest -o devops/images/frontend-app.tar

# Docker Compose ile container'ları başlat
echo "Docker Compose ile container'ları başlatıyor..."
cd devops
docker-compose down
docker-compose up --build -d

echo "Frontend ve Backend Docker container'ları başlatıldı."

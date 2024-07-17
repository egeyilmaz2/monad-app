#!/bin/bash


echo "Backend'i ayaklandırılıyor..."
cd backend/main-app
./gradlew clean build
nohup ./gradlew bootRun &


echo "Frontend'i ayaklandırılıyor..."
cd ../../frontend
./gradlew clean build
nohup ./gradlew bootRun &

echo "Frontend ve Backend ayaklandırıldı."

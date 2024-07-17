cd devops
docker-compose -f  docker-compose-db.yml up -d

cd ../frontend
./gradlew clean bootRun &

cd ../backend/main-app
./gradlew clean bootRun &
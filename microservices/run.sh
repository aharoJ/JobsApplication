#!/bin/bash

# Function to kill processes on specified ports
cleanup() {
	echo "Stopping all microservices..."
	lsof -ti:8080,8081,8082,8083 | xargs kill
}

# Trap SIGINT (Ctrl-C) and SIGTERM (termination signal)
trap cleanup SIGINT SIGTERM

# Start companyms microservice
cd /Users/aharo/desk/jobapplication/microservices/company || exit
./mvnw spring-boot:run &

# Start jobms microservice
cd /Users/aharo/desk/jobapplication/microservices/job || exit
./mvnw spring-boot:run &

# Start reviewms microservice
cd /Users/aharo/desk/jobapplication/microservices/review || exit
./mvnw spring-boot:run &

# Wait for all to terminate (they won't until you manually stop them)
wait

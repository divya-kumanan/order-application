# Building and Running order-application in Docker
## Prerequisites
* Docker
* JDK 11
* Maven
## Building the application
1. Clone the repository to your local machine:
```
git clone https://github.com/divya-kumanan/order-application.git
```
2. Navigate to the root directory of the project:
```
cd order-application
```
3. Build the application using Maven:
```
mvn clean install
```
This will build the application and create a JAR file in the `target/` directory.

## Building the Docker image
1. Build the Docker image:
```
docker build -t order-application .
```
This will build a Docker image named `order-application` using the Dockerfile in the root directory of the project.

## Running the Docker container
1. Run the Docker container:
```
docker run -p 8080:8080 order-application
```
This will start the container and map port 8080 in the container to port 8080 on your host machine.

2. Access the application in your web browser:
```
http://localhost:8080
```
This will bring up the home page of your Spring Boot application.

## Stopping the Docker container
1. To stop the Docker container, open a new terminal window and run the following command to list all running containers:
```
docker ps
```
2. Find the container ID for `order-application` in the output.
3. Stop the container by running the following command:
```
docker stop container_id
```
Replace `container_id` with the actual container ID for `order-application`.
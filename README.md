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

2. Access the order-application in your web browser:
```
http://localhost:8080/actuator/health
```
This will bring up the health status of your Spring Boot application.

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

## Running the Regression Test in Postman
1. Open the Postman App 
2. Import the collection `OrderApplication-Vodafone.postman_collection.json` and `Local.postman_collection.json`  from the root folder of order-application.
3. Click on Runner and drag the folder `Regression Test for OrderApi` to the Runner
4. Select the environment `Local`
5. Start Run

## View Open-Api Specification(Swagger) for order-application
1. Access the swagger document [openapi yaml file]
```
http://localhost:8080/v3/api-docs
```
2. Access the swagger document in UI
```
http://localhost:8080/swagger-ui/index.html
```
# Spring Microservices Working Demo

This is a minimal Spring Boot microservices demo with Eureka service discovery and Feign inter-service calls.

## Overview
- Two microservices: `user-service` and `task-service`
- Eureka server for service discovery
- Feign client in task-service to validate users
- H2 in-memory databases per service

## Services and Ports
- Eureka Server: `http://localhost:8761`
- User Service: `http://localhost:8082`
- Task Service: `http://localhost:8083` (changed from 8081 due to port conflict)

To change the task-service port back to 8081, edit:
`task-service/src/main/resources/application.properties`

## Tech Stack
- Java 17+ (tested with JDK 23)
- Spring Boot 3.2
- Spring Cloud 2023
- Eureka Server/Client
- OpenFeign
- H2 Database
- Maven

## How to Run (Terminal)
Start services in this order:

```bash
cd discovery-server
mvn spring-boot:run
```

```bash
cd ../user-service
mvn spring-boot:run
```

```bash
cd ../task-service
mvn spring-boot:run
```

## How to Run (IntelliJ)
Open the project folder, then run these main classes:
- `discovery-server/src/main/java/com/example/discoveryserver/DiscoveryServerApplication.java`
- `user-service/src/main/java/com/example/userservice/UserServiceApplication.java`
- `task-service/src/main/java/com/example/taskservice/TaskServiceApplication.java`

## Verify Registration
Open the Eureka dashboard:
- `http://localhost:8761`
You should see `USER-SERVICE` and `TASK-SERVICE` as UP.

## Postman Requests
Use these requests to demonstrate the flow.

1. Create User
- Method: `POST`
- URL: `http://localhost:8082/api/users`
- Headers: `Content-Type: application/json`
- Body:
```json
{"name":"John Doe","email":"john@example.com"}
```

2. Create Task (valid assignee)
- Method: `POST`
- URL: `http://localhost:8083/api/tasks`
- Headers: `Content-Type: application/json`
- Body:
```json
{"title":"Learn Eureka","description":"Test Feign call","assigneeId":1}
```

3. Get All Tasks
- Method: `GET`
- URL: `http://localhost:8083/api/tasks`

4. Create Task (invalid assignee)
- Method: `POST`
- URL: `http://localhost:8083/api/tasks`
- Headers: `Content-Type: application/json`
- Body:
```json
{"title":"Invalid","description":"No user","assigneeId":999}
```
- Expected: `400 Bad Request` (assignee does not exist)

## What this proves
- Eureka registers and discovers services dynamically
- Feign resolves `user-service` by name
- Task creation is validated against user-service


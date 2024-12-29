## Spring Boot Microservices

This repository contains an up-to-date codebase demonstrating how to build and
deploy microservices with Spring Boot.

## Services Overview

- Product Service
- Order Service
- Inventory Service
- Notification Service
- API Gateway (Spring Cloud Gateway MVC)

## Tech Stack

Technologies utilized in this project include:

- Spring Boot
- MongoDB
- MySQL
- Kafka
- Keycloak
- Testcontainers with WireMock
- Grafana Stack (Prometheus, Grafana, Loki, and Tempo)
- Kubernetes

## Building the Backend Services

Use the following command to build and package the backend services into a
Docker image:

```bash
mvn spring-boot:build-image -DdockerPassword=<your-docker-account-password>
```

This command creates a Docker image for each service and pushes them to your
Docker Hub account.

---

## Running the Backend Services

Make sure the following are installed:

- Java 21
- Docker
- Kind (Kubernetes in Docker):
  [Kind Quick Start Guide](https://kind.sigs.k8s.io/docs/user/quick-start/#installation)

### Start the Kind Cluster

Execute the script to create a Kind Kubernetes cluster:

```bash
./k8s/kind/create-kind-cluster.sh
```

This command creates a Kind cluster and pre-loads the necessary Docker images,
reducing deployment time later.

### Deploy the Infrastructure

Apply the infrastructure configuration:

```bash
kubectl apply -f k8s/manifests/infrastructure
```

### Deploy the Services

Apply the service definitions:

```bash
kubectl apply -f k8s/manifests/applications
```

### Access the API Gateway

Use port forwarding to expose the API Gateway locally:

```bash
kubectl port-forward svc/api-gateway 9000:9000
```

### Access the Keycloak Admin Console

Use port forwarding to expose the Keycloak admin console locally:

```bash
kubectl port-forward svc/keycloak 8080:8080
```

### Access the Grafana Dashboards

Use port forwarding to expose the Grafana dashboard locally:

```bash
kubectl port-forward svc/grafana 3000:3000
```

### Access the mysql workbench

Use port forwarding to expose the mysql dashboard locally:

```bash
kubectl port-forward svc/mysql 3307:3306
```
### Access the MongoDb Compass

Use port forwarding to expose the mongodb dashboard locally:

```bash
kubectl port-forward svc/mongodb 27017:27017  
```

### Access the Kafka Broker

Use port forwarding to expose the kafka broker locally:

```bash
kubectl port-forward svc/broker 29092:29092 
```

### Access the Kafka UI

Use port forwarding to expose the kafka-ui dashboard locally:

```bash
kubectl port-forward svc/kafka-ui 8082:8080  
```

# ECommerce Spring Boot Microservices 

This project is a **Spring Boot–based microservices system** designed to demonstrate a scalable, event-driven architecture using modern backend technologies.

## Architecture Overview

The system consists of multiple independent microservices that communicate via **REST APIs** and **Apache Kafka**, with centralized configuration and service discovery.

### Microservices

- **User Service**
  - Manages user registration and user-related data
  - Exposes REST APIs for user operations

- **Product Service**
  - Handles product catalog management
  - Provides APIs for creating and retrieving products

- **Order Service**
  - Manages order creation and order processing
  - Communicates with User and Product services
  - Publishes order events to Kafka

- **Notification Service**
  - Listens to Kafka topics for events (e.g., order placed)
  - Sends notifications based on consumed events

## Key Technologies

- **Spring Boot** – Microservice development
- **Spring Cloud Netflix Eureka** – Service discovery
- **Spring Cloud Config Server** – Centralized configuration management
- **Apache Kafka** – Asynchronous event-driven communication
- **Spring Cloud Gateway** (optional) – API gateway
- **RESTful APIs** – Synchronous inter-service communication
- **Maven** – Build and dependency management

## Service Discovery

All microservices register with the **Eureka Server**, enabling dynamic service discovery and load balancing without hardcoded service URLs.

## Configuration Management

Configuration is centralized using **Spring Cloud Config Server**, allowing:
- Externalized configuration
- Environment-specific configurations
- Dynamic configuration updates

## Event-Driven Communication

Apache Kafka is used for asynchronous communication:
- The **Order Service** produces events (e.g., order created)
- The **Notification Service** consumes events and processes notifications

## Project Structure


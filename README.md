# ShopVerse Services
Shopverse is composed of independent Spring Boot microservices managed via a service registry, gateway, and asynchronous messaging. 
Each service is container-ready, observable, and follows best practices for cloud deployment.

## Services

- api-gateway: API Gateway using Spring Cloud Gateway, with OAuth2 security and Redis-backed rate limiting.
- discovery-server: Eureka-based service discovery for dynamic service registration and resolution.
- product-service: Product catalog (MongoDB), service registration, actuator endpoints, tracing.
- order-service: Manages placing and querying orders (PostgreSQL, JPA/WebFlux).
- inventory-service: Manages inventory data (PostgreSQL, JPA).
- notification-service: Kafka consumer for sending notifications in reaction to order events.

## Tech Stack

- Spring Boot 3, Spring Cloud
- Spring Cloud Gateway (API Gateway)
- Eureka (Service Discovery)
- Kafka (Async Messaging for events)
- MongoDB (Product data)
- PostgreSQL (Order, Inventory)
- Redis (Rate limiting)
- Zipkin (Distributed tracing, micrometer/Brave)


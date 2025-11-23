# ShopVerse Services
ShopVerse is a collection of Spring Boot microservices (API Gateway, service discovery, and several domain services). Each service is container-ready and instrumented for observability. This repository contains multi-module Maven projects and a `docker-compose.yml` that brings up infrastructure and application services (including Prometheus and Grafana).

**Quick overview:**
- **Service discovery:** `discovery-server` (Eureka)
- **Gateway:** `api-gateway` (Spring Cloud Gateway)
- **Domain services:** `product-service`, `order-service`, `inventory-service`, `notification-service`
- **Messaging & infra:** Kafka, PostgreSQL, MongoDB, Redis
- **Observability:** Zipkin, Prometheus, Grafana

**This README** contains quick start instructions to build the service images (via Maven + Jib) and run everything locally using Docker Compose.

## Prerequisites
- **Docker & Docker Compose**: Ensure Docker Desktop is installed and running.
- **Maven**: Required to build the Java modules and produce Docker images via Jib.
- Recommended: at least 8GB RAM available to Docker when running the full stack.

## Build (create Docker images)
Run from the repository root in a terminal (PowerShell on Windows):

```
mvn compile jib:dockerBuild
```

This uses Jib to build Docker images for the Maven modules. Depending on your network and system resources this may take a few minutes.

If you prefer to build images per-module you can `cd` into a module (for example `api-gateway`) and run the same `mvn compile jib:dockerBuild` there.

## Run (Docker Compose)
Start the full stack with Docker Compose from the repository root:

```
docker compose up
```

To run in detached mode:

```
docker compose up -d
```

To stop and remove containers:

```
docker compose down
```

## Services & Default Ports
When the stack is up the main endpoints are exposed on the following ports (host -> container):

- **PostgreSQL:** `5432`
- **MongoDB:** `27017`
- **Redis:** `6379`
- **Kafka (host):** `9092`
- **Zipkin:** `9411`
- **Eureka (discovery-server):** `8761`
- **API Gateway:** `8090`
- **Prometheus:** `9090`
- **Grafana:** `3000`

Access example:
- API gateway: http://localhost:8090
- Eureka UI: http://localhost:8761

Test the order service via the API gateway:

- Order API (via gateway): `http://localhost:8090/api/order` — use `POST` to create orders on the service endpoints.

## Prometheus & Grafana
Prometheus and Grafana are included in `docker-compose.yml` and are mounted with local configuration/data:

- Prometheus config: `./prometheus/prometheus.yml` → Prometheus server at http://localhost:9090
- Grafana data/config: `./grafana` volume → Grafana UI at http://localhost:3000

Default Grafana credentials (configured in `docker-compose.yml` environment)

You can import or configure dashboards in Grafana to consume Prometheus metrics. The included `prometheus.yml` should already scrape the instrumented services configured in the stack.

## Notes & Troubleshooting
- If you get port conflicts adjust the host-side port mappings in `docker-compose.yml`.
- Ensure Docker has sufficient memory; Kafka and multiple DBs can be memory hungry.
- If images are not updated after local code changes, rebuild with Jib: `mvn compile jib:dockerBuild` and then `docker compose up --force-recreate --build` (or down/up).
- For Windows PowerShell use `;` to chain commands on one line if needed.

## Next steps
- Run `mvn compile jib:dockerBuild` then `docker compose up` to bring up the local stack.
- Open Grafana at `http://localhost:3000` (admin/password) and Prometheus at `http://localhost:9090`.

If you'd like, I can also:
- run the Maven and Docker Compose commands here (if you want me to execute them), or
- add example Grafana dashboards and a minimal `prometheus.yml` scrape config for the services.

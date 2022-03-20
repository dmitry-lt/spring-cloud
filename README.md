# spring-cloud

This sample demonstrates Spring Cloud microservice architecture:
* Eureka service registry
* Eureka clients
* RESTful communication between microservices 
* Circuit breaker
* Sleuth tracing and Zipkin collector service

### Prerequisites

Must be installed locally

* Java 17
* Docker

### Local run

#### Eureka service registry

```
cd service-registry
gradlew bootRun
```

Eureka console will be available at http://localhost:8761

#### Dog name microservice

```
cd dog-name-service
gradlew bootRun
```

#### Dog breed microservice

```
cd dog-breed-service
gradlew bootRun
```

#### Dog microservice

```
cd dog-service
gradlew bootRun
```

Dog microservice will display a random dog name and a random dog breed obtained from `dog-name-service` and `dog-breed-service` respectively:
http://localhost:8080/api/random

If `dog-name-service` or `dog-breed-service` is not running, the circuit breaker will fall back to the default dog name and breed

#### Zipkin collector service

```
docker run -d -p 9411:9411 openzipkin/zipkin
```

Zipkin console will be available at http://localhost:9411

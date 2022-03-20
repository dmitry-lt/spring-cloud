# spring-cloud

This sample demonstrates Spring Cloud microservice architecture:
* Eureka service registry
* Eureka clients
* RESTful load balanced communication between microservices 
* Circuit breaker
* Sleuth tracing and Zipkin collector service
* Performance optimization: `dog-service` calls `dog-name-service` and `dog-breed-service` in parallel

## Prerequisites

Must be installed locally

* Java 17
* Docker

## Local run

### Eureka service registry

```
cd service-registry
gradlew bootRun
```

Eureka console is available at http://localhost:8761

### Dog name microservice

```
cd dog-name-service
gradlew bootRun
```

Returns a random dog name

### Dog breed microservice

```
cd dog-breed-service
gradlew bootRun
```

Returns a random dog breed

### Dog microservice

```
cd dog-service
gradlew bootRun
```

Dog microservice displays a random dog name and a random dog breed obtained from `dog-name-service` and `dog-breed-service` respectively:
http://localhost:8080/api/random

![Dog service endpoint screenshot](screenshots/dog-service.png?raw=true "Dog service")

If `dog-name-service` or `dog-breed-service` is not running, the circuit breaker falls back to the default dog name and breed

### Zipkin collector service

```
docker run -d -p 9411:9411 openzipkin/zipkin
```

Zipkin console is available at http://localhost:9411

![Zipkin screenshot](screenshots/zipkin-trace-call-services-in-parallel-2.png?raw=true "Zipkin console")

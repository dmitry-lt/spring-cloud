# spring-cloud

This sample demonstrates Spring Cloud applications

### Prerequisites

Must be installed locally

* Java 17

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
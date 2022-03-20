start /d service-registry gradlew bootRun
start /d dog-name-service gradlew bootRun
start /d dog-breed-service gradlew bootRun
start /d dog-service gradlew bootRun
docker run -d -p 9411:9411 openzipkin/zipkin



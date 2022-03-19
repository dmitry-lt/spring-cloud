package demo.sc.dog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DogService {
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public String getRandomDog() {
        return getDogName() + " " + getDogBreed();
    }

    public String getDogName() {
        return circuitBreakerFactory
                .create("getDogName")
                .run(() -> restTemplate.getForObject("http://dog-name-service/api/random", String.class),
                        throwable -> "defaultDogName");
    }

    public String getDogBreed() {
        return circuitBreakerFactory
                .create("getDogBreed")
                .run(() -> restTemplate.getForObject("http://dog-breed-service/api/random", String.class),
                        throwable -> "defaultDogBreed");
    }
}
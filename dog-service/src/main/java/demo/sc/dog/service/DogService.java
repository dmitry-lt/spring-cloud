package demo.sc.dog.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class DogService {
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final BeanFactory beanFactory;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Data
    class Dog {
        private String name;
        private String breed;
    }

    @SneakyThrows
    public String getRandomDog() {
        final Dog dog = new Dog();

        Runnable getName = () -> dog.setName(requestDogName());
        Runnable getBreed = () -> dog.setBreed(requestDogBreed());

        // call name and breed services in parallel
        // Stream.of(getName, getBreed).parallel().forEach(Runnable::run);

        // Sleuth does not work with parallelStream() out of the box.
        // If you want to have the tracing information propagated through the stream,
        // you have to use the approach with supplyAsync(...)

        var traceableExecutorService = new TraceableExecutorService(beanFactory, executorService);
        CompletableFuture<Void> nameFuture = CompletableFuture.runAsync(getName, traceableExecutorService);
        CompletableFuture<Void> breedFuture = CompletableFuture.runAsync(getBreed, traceableExecutorService);

        CompletableFuture<Void> combined = CompletableFuture.allOf(nameFuture, breedFuture);

        combined.get();

        return dog.getName() + " " + dog.getBreed();
    }

    private String requestDogName() {
        return circuitBreakerFactory
                .create("requestDogName")
                .run(() -> restTemplate.getForObject("http://dog-name-service/api/random", String.class),
                        throwable -> "defaultDogName");
    }

    private String requestDogBreed() {
        return circuitBreakerFactory
                .create("requestDogBreed")
                .run(() -> restTemplate.getForObject("http://dog-breed-service/api/random", String.class),
                        throwable -> "defaultDogBreed");
    }
}
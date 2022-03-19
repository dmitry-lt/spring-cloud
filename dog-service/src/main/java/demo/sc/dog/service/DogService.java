package demo.sc.dog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DogService {
    private final RestTemplate restTemplate;

    public String getRandomDog() {
        String name = restTemplate.getForObject("http://dog-name-service/api/random", String.class);
        String breed = restTemplate.getForObject("http://dog-breed-service/api/random", String.class);

        return name + " " + breed;
    }
}
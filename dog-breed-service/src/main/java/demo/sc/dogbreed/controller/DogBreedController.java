package demo.sc.dogbreed.controller;

import demo.sc.dogbreed.service.DogBreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DogBreedController {
    private final DogBreedService dogBreedService;

    @GetMapping("/random")
    public String getRandomBreed() {
        return dogBreedService.getRandomBreed();
    }
}

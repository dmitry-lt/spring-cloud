package demo.sc.dog.controller;

import demo.sc.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DogController {
    private final DogService dogService;

    @GetMapping("/random")
    public String getRandom() {
        return dogService.getRandomDog();
    }
}

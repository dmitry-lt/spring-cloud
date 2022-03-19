package demo.sc.dogname.controller;

import demo.sc.dogname.service.DogNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DogNameController {
    private final DogNameService dogNameService;

    @GetMapping("/random")
    public String getRandomName() {
        return dogNameService.getRandomName();
    }
}

package demo.sc.dogname.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class DogNameService {

    List<String> names = Arrays.asList(
            "Bella",
            "Max",
            "Luna",
            "Charlie",
            "Lucy",
            "Cooper",
            "Daisy",
            "Milo",
            "Zoey",
            "Rocky");

    private Random random = new Random();

    public String getRandomName() {
        return names.get(random.nextInt(names.size()));
    }
}

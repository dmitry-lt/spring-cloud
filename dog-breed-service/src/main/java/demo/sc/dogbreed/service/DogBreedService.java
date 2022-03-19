package demo.sc.dogbreed.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Random;

@Service
public class DogBreedService {

    private List<DogBreed> breeds;

    private Random random = new Random();

    public String getRandomBreed() {
        return breeds.get(random.nextInt(breeds.size())).getName();
    }

    @PostConstruct
    void init() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:breeds/breeds.csv");
        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            CsvToBean<DogBreed> csvToBean = new CsvToBeanBuilder<DogBreed>(reader)
                    .withType(DogBreed.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            breeds = csvToBean.parse();
        }
    }
}
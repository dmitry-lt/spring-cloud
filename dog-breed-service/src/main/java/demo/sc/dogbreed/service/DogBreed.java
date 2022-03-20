package demo.sc.dogbreed.service;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DogBreed {
    @CsvBindByName
    private String name;
}

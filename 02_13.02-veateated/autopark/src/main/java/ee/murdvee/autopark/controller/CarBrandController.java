package ee.murdvee.autopark.controller;

import ee.murdvee.autopark.entity.CarBrand;
import ee.murdvee.autopark.repository.CarBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarBrandController {

    private CarBrandRepository carBrandRepository;

    @GetMapping("brands")
    public List<CarBrand> getBrands() {
        return carBrandRepository.findAll();
    }

    @PostMapping("brands")
    public List<CarBrand> addBrand(@RequestBody CarBrand brand) {
        if (brand.getId() != null) {
            throw new RuntimeException("Brändi lisamisel ei tohi ID olla määratud");
        }
        if (brand.getName() == null || brand.getName().isBlank()) {
            throw new RuntimeException("Brändi nimi ei tohi olla tühi");
        }
        carBrandRepository.save(brand);
        return carBrandRepository.findAll();
    }
}

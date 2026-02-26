package ee.murdvee.autopark.controller;

import ee.murdvee.autopark.entity.Car;
import ee.murdvee.autopark.repository.CarRepository;
import ee.murdvee.autopark.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarController {

    private CarRepository carRepository;
    private CarService carService;

    @GetMapping("cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("cars/{id}")
    public Car getOneCar(@PathVariable Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Autot ID-ga " + id + " ei leitud")
        );
    }

    @PostMapping("cars")
    public List<Car> addCar(@RequestBody Car car) {
        carService.validate(car); // valideerime enne salvestamist
        carRepository.save(car);
        return carRepository.findAll();
    }
}

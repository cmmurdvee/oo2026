package ee.murdvee.autopark.service;

import ee.murdvee.autopark.entity.Car;
import ee.murdvee.autopark.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;

    public void validate(Car car) {

        //ei tohi auto lisamisel olla
        if (car.getId() != null) {
            throw new RuntimeException("Auto lisamisel ei tohi ID olla määratud");
        }

        //Mark ei tohi olla tühi
        if (car.getMake() == null || car.getMake().isBlank()) {
            throw new RuntimeException("Auto mark ei tohi olla tühi");
        }

        //Mudel ei tohi olla tühi
        if (car.getModel() == null || car.getModel().isBlank()) {
            throw new RuntimeException("Auto mudel ei tohi olla tühi");
        }

        //Aasta peab olema vahemikus 1886 kuni praegune aasta
        int currentYear = Year.now().getValue();
        if (car.getYear() == null) {
            throw new RuntimeException("Auto aasta ei tohi olla tühi");
        }
        if (car.getYear() < 1886 || car.getYear() > currentYear) {
            throw new RuntimeException("Auto aasta peab olema vahemikus 1886 kuni " + currentYear);
        }

        //Hind peab olema positiivne arv
        if (car.getPrice() == null || car.getPrice() <= 0) {
            throw new RuntimeException("Auto hind peab olema positiivne arv");
        }


    }
}

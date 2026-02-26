package ee.murdvee.autopark.repository;

import ee.murdvee.autopark.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
}

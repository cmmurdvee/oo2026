package ee.murdvee.proovikontrolltoo.repository;

import ee.murdvee.proovikontrolltoo.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

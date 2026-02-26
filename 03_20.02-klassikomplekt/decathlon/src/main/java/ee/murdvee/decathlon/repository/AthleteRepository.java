package ee.murdvee.decathlon.repository;

import ee.murdvee.decathlon.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}

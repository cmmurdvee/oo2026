package ee.murdvee.decathlon.repository;

import ee.murdvee.decathlon.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}

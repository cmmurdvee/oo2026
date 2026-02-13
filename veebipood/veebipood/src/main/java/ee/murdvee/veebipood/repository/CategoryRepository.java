package ee.murdvee.veebipood.repository;

import ee.murdvee.veebipood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//non-null type argument is expected
//Spring Boot 4.0

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package ee.murdvee.veebipood.repository;

import ee.murdvee.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//  repository --> andmebaasiga suhtlemiseks, tema sees on kõige funktsioonid, mida on võimalik andmebaasiga teha.

public interface ProductRepository extends JpaRepository<Product, Long> {
}

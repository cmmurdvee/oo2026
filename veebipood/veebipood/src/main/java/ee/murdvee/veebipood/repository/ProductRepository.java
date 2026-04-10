package ee.murdvee.veebipood.repository;

import ee.murdvee.veebipood.entity.Product;
import org.springframework.data.domain.Page; //!!!!!!
import org.springframework.data.domain.Pageable; //!!!!!!
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//  repository --> andmebaasiga suhtlemiseks, tema sees on kõige funktsioonid, mida on võimalik andmebaasiga teha.

public interface ProductRepository extends JpaRepository<Product, Long> {
    // igas Repository-s on voimalik seda klassi (tabeli kirjet) k2tte saada
    // listinda voi yksikuna
    // siin on voimalik:
    // list<Product>
    // Product
    // int
    // boolean
    // void
    Page<Product> findAllByCategoryId(Pageable pageable, Long categoryId);
}

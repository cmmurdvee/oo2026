package ee.murdvee.veebipood.repository;

import ee.murdvee.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

//CrudRepository --> minimaalsed vajalikud (standardsed) funktsioonid
//PagingAndSortingRepository --> funktsioonid lehekylge andmete valjstamine ja sorteerimiseks
//JpaRepository --> koikvoimalikud funktsioonid

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
}

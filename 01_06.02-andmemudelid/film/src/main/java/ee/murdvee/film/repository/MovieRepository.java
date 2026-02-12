package ee.murdvee.film.repository;

import ee.murdvee.film.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

//repository > andmebaasiga suhtlemiseks, tema sees on koik funktsioonid, mida on voimalik andmebaasiga teha

public interface MovieRepository extends JpaRepository<Movie,Long> {

}

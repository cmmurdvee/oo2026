package ee.murdvee.film.controller;

import ee.murdvee.film.entity.Movie;
import ee.murdvee.film.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    //localhost:8080
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    @DeleteMapping("movies/{id}")
    public List<Movie> deleteMovies(@PathVariable Long id){
        movieRepository.deleteById(id); //kustutan
        return movieRepository.findAll(); //uuenenud seis
    }

    @PostMapping("movies")
    public List<Movie> addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie); // salvestan
        return movieRepository.findAll(); // uuenenud seis
    }
}

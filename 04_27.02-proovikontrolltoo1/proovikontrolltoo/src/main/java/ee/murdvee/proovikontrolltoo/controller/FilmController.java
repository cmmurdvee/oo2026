package ee.murdvee.proovikontrolltoo.controller;

import ee.murdvee.proovikontrolltoo.dto.FilmSaveDto;
import ee.murdvee.proovikontrolltoo.entity.Film;
import ee.murdvee.proovikontrolltoo.entity.FilmType;
import ee.murdvee.proovikontrolltoo.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmRepository filmRepository;

    @PostMapping("films")
    public Film saveFilm(@RequestBody FilmSaveDto filmSaveDto){
        Film film = new Film();
        film.setTitle(filmSaveDto.title());
        film.setType(filmSaveDto.type());
        film.setDays(0); // <--- selle nimel tegime Recordi
        return filmRepository.save(film);
    }

    @DeleteMapping("films/{id}")
    public void deleteFilm(@PathVariable Long id){
        filmRepository.deleteById(id);
    }

    // PUT - kogu entity muutmise voimekus
    // PATCH - booking voetud, tellimus makstud. kogus yhe vorra v2hendatud.
    @PatchMapping("films/type")
    public Film changeFilmType(@RequestParam Long id, @RequestParam FilmType filmType){
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(filmType);
        return filmRepository.save(film);
    }

    @GetMapping("films")
    public List<Film> findAll(){
        return filmRepository.findAll();
    }

    @GetMapping("films/available")
    public List<Film> findAllAvailable(){
        return filmRepository.findByDays(0);
    }
}
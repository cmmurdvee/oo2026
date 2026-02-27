package ee.murdvee.proovikontrolltoo.controller;


import ee.murdvee.proovikontrolltoo.dto.FilmRentalDto;
import ee.murdvee.proovikontrolltoo.entity.Film;
import ee.murdvee.proovikontrolltoo.entity.Rental;
import ee.murdvee.proovikontrolltoo.repository.FilmRepository;
import ee.murdvee.proovikontrolltoo.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalRepository rentalRepository;
    private final FilmRepository filmRepository;
    private double premiumPrice = 4;
    private double basicPrice = 3;

    @GetMapping("rentals")
    public List<Rental> findAll(){
        return rentalRepository.findAll();
    }

    // DTO --> mis film(ID), mitmeks p2evaks votan
    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<FilmRentalDto> filmRentalDto){

        Rental rental = new Rental(); //{id: null, initialFee:null, lateFee: null}
        Rental dbRental = rentalRepository.save(rental);

        double  sum = 0;

        for (FilmRentalDto filmrentalDto : filmRentalDto) {
            Film dbFilm = filmRepository.findById(filmrentalDto.filmId()).orElseThrow();
            dbFilm.setRental(dbRental);
            dbFilm.setDays(filmrentalDto.days());
            switch (dbFilm.getType()) {
                case NEW -> sum += premiumPrice * filmrentalDto.days();
                case REGULAR -> {
                    if (filmrentalDto.days() <= 3) {
                        sum += basicPrice;
                    } else  {
                        sum += basicPrice + basicPrice * (filmrentalDto.days() - 3);
                    }
                }
                case OLD -> {
                   if (filmrentalDto.days() <= 5) {
                       sum += basicPrice;
                   } else {
                       sum  += basicPrice + basicPrice * (filmrentalDto.days() - 5);
                   }
                }
            }
            filmRepository.save(dbFilm);
        }

        dbRental.setInitialFee(sum);
        return rentalRepository.save(dbRental);
    }

    //DTO --> mis film(ID), mitu p2eva tegelikult rendis oli
    @PostMapping("end-rental")
    public double endRental(@RequestBody List<FilmRentalDto> filmRentalDto) {

        double sum = 0;
        for (FilmRentalDto filmrentalDto : filmRentalDto) {
            Film dbFilm = filmRepository.findById(filmrentalDto.filmId()).orElseThrow();
            Rental rental = dbFilm.getRental();
            // switch case --> filmi_summa arvutamine + summale juurde liitmine
            rental.setLateFee(rental.getLateFee() + FILMI_SUMMA); // <-- voib olla switchi sees
            rentalRepository.save(rental);

            dbFilm.setRental(null);
            dbFilm.setDays(0);
            filmRepository.save(dbFilm);
        }
        return sum; //maksmisele lahev summa (voib tulla erinevatest rentalitest)
    }
}


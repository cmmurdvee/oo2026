package ee.murdvee.decathlon.controller;

import ee.murdvee.decathlon.entity.Athlete;
import ee.murdvee.decathlon.entity.Result;
import ee.murdvee.decathlon.repository.AthleteRepository;
import ee.murdvee.decathlon.service.AthleteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AthleteController {

    private AthleteRepository athleteRepository;
    private AthleteService athleteService;

    @GetMapping("athletes")
    public List<Athlete> getAthletes() {
        return athleteRepository.findAll();
    }

    @PostMapping("athletes")
    public List<Athlete> addAthlete(@RequestBody Athlete athlete) {
        athleteService.validateAthlete(athlete);
        athleteRepository.save(athlete);
        return athleteRepository.findAll();
    }

    @PostMapping("athletes/{id}/results")
    public Athlete addResult(@PathVariable Long id, @RequestBody Result result) {
        return athleteService.addResult(id, result);
    }

    @GetMapping("athletes/{id}/total")
    public Athlete getTotalPoints(@PathVariable Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sportlast ID-ga " + id + " ei leitud"));
    }
}

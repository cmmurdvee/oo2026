package ee.murdvee.decathlon.service;

import ee.murdvee.decathlon.entity.Athlete;
import ee.murdvee.decathlon.entity.Result;
import ee.murdvee.decathlon.repository.AthleteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AthleteService {

    private AthleteRepository athleteRepository;
    private DecathlonScoringService scoringService;

    public void validateAthlete(Athlete athlete) {
        if (athlete.getId() != null) {
            throw new RuntimeException("Sportlase lisamisel ei tohi ID olla määratud");
        }
        if (athlete.getFirstName() == null || athlete.getFirstName().isBlank()) {
            throw new RuntimeException("Eesnimi ei tohi olla tühi");
        }
        if (athlete.getLastName() == null || athlete.getLastName().isBlank()) {
            throw new RuntimeException("Perekonnanimi ei tohi olla tühi");
        }
        if (athlete.getCountry() == null || athlete.getCountry().isBlank()) {
            throw new RuntimeException("Riik ei tohi olla tühi");
        }
    }

    public void validateResult(Result result) {
        if (result.getDiscipline() == null || result.getDiscipline().isBlank()) {
            throw new RuntimeException("Spordiala ei tohi olla tühi");
        }
        if (result.getPerformance() <= 0) {
            throw new RuntimeException("Tulemus peab olema positiivne arv");
        }
    }

    public Athlete addResult(Long athleteId, Result result) {
        validateResult(result);
        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new RuntimeException("Sportlast ID-ga " + athleteId + " ei leitud"));

        int points = scoringService.calculatePoints(result.getDiscipline(), result.getPerformance());
        result.setPoints(points);

        athlete.getResults().add(result);
        return athleteRepository.save(athlete);
    }
}

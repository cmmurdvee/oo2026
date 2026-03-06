package ee.murdvee.kontrolltoo.service;

import ee.murdvee.kontrolltoo.dto.KiirusSaveDto;
import ee.murdvee.kontrolltoo.entity.Kiirus;
import ee.murdvee.kontrolltoo.repository.KiirusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KiirusService {

    private KiirusRepository kiirusRepository;

    public Kiirus save(KiirusSaveDto dto) {
        // arv peab olema positvne
        if (dto.arv() <= 0) {
            throw new RuntimeException("Kiirus peab olema positiivne arv (suurem kui 0)");
        }
        // arv ei tohi olla yle 1000
        if (dto.arv() > 1000) {
            throw new RuntimeException("Kiirus ei tohi yletada 1000 km/h");
        }

        Kiirus kiirus = new Kiirus();
        kiirus.setArv(dto.arv());
        return kiirusRepository.save(kiirus);
    }

    public List<Kiirus> findAll() {
        return kiirusRepository.findAll();
    }

    //  Arvutab koigi kiiruste keskmise
    public double getKeskmine() {
        List<Kiirus> kiirused = kiirusRepository.findAll();
        if (kiirused.isEmpty()) {
            throw new RuntimeException("Andmebaasis pole yhtegi kirjet");
        }
        return kiirused.stream()
                .mapToInt(Kiirus::getArv)
                .average()
                .orElseThrow();
    }

    // km/h teinsendamine mph
    public List<Double> getMiilidesse() {
        return kiirusRepository.findAll().stream()
                .map(k -> k.getArv() * 0.621371)
                .toList();
    }
}

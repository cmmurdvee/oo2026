package ee.murdvee.kontrolltoo.controller;

import ee.murdvee.kontrolltoo.dto.KiirusSaveDto;
import ee.murdvee.kontrolltoo.entity.Kiirus;
import ee.murdvee.kontrolltoo.service.KiirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KiirusController {

    @Autowired
    private KiirusService kiirusService;

    @PostMapping("kiirused")
    public Kiirus save(@RequestBody KiirusSaveDto dto) {
        return kiirusService.save(dto);
    }

    @GetMapping("kiirused")
    public List<Kiirus> findAll() {
        return kiirusService.findAll();
    }

    @GetMapping("kiirused/keskmine")
    public double getKeskmine() {
        return kiirusService.getKeskmine();
    }

    @GetMapping("kiirused/miilidesse")
    public List<Double> getMiilidesse() {
        return kiirusService.getMiilidesse();
    }
}

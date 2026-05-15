package ee.kontrolltoo.backend.controller;

import ee.kontrolltoo.backend.dto.MarineRegionTypeDto;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ShopController {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String MARINE_REGIONS_URL = "https://marineregions.org/rest/getGazetteerTypes.json";

    @GetMapping("shops")
    public List<MarineRegionTypeDto> getShops() {
        MarineRegionTypeDto[] response = restTemplate.exchange(
                MARINE_REGIONS_URL, HttpMethod.GET, null, MarineRegionTypeDto[].class
        ).getBody();
        return Arrays.asList(response);
    }
}

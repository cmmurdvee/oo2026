package com.library.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.library.dto.BibleDto;

@RestController
@RequestMapping("/api/bibles")
@CrossOrigin(origins = "http://localhost:3000")
public class BibleController {

    private RestTemplate restTemplate = new RestTemplate();

    private static final String BIBLES_API_URL = "https://holy-bible-api.com/bibles";

    @GetMapping
    public List<BibleDto> getBibles() {
        BibleDto[] response = restTemplate.exchange(
                BIBLES_API_URL, HttpMethod.GET, null, BibleDto[].class
        ).getBody();
        return Arrays.asList(response);
    }
}

package com.b2w.starwar.infrastructure.integration;

import com.b2w.starwar.domain.wrapper.PlanetaWrapper;
import org.springframework.web.client.RestTemplate;

/**
 * Classe criada por mpinho na data 02/03/19
 * E-mail: marcosjava2008@gmail.com
 */
public class PlanetaSw {

    private RestTemplate restTemplate;

    public PlanetaSw() {
        this.restTemplate = Swapi.initialiaze();
    }

    public PlanetaWrapper call(Long id) {
        return this.restTemplate.getForObject("https://swapi.co/api/planets/{id}", PlanetaWrapper.class, id);
    }

}

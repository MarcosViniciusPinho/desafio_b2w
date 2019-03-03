package com.b2w.starwar.infrastructure.service.impl;

import com.b2w.starwar.infrastructure.service.PlanetaSwService;
import com.b2w.starwar.infrastructure.service.Swapi;
import com.b2w.starwar.infrastructure.service.wrapper.PlanetaWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Classe criada por mpinho na data 02/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@Service
public class PlanetaSwServiceImpl implements PlanetaSwService {

    private RestTemplate restTemplate;

    public PlanetaSwServiceImpl() {
        this.restTemplate = Swapi.initialiaze();
    }

    public PlanetaWrapper call(Long id) {
        return this.restTemplate.getForObject("https://swapi.co/api/planets/{id}", PlanetaWrapper.class, id);
    }

}

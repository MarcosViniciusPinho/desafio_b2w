package com.b2w.starwar.infrastructure.service.impl;

import com.b2w.starwar.infrastructure.service.PlanetaSwService;
import com.b2w.starwar.infrastructure.service.Swapi;
import com.b2w.starwar.infrastructure.service.wrapper.PlanetaWrapper;
import com.b2w.starwar.infrastructure.service.wrapper.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<PlanetaWrapper> call(Long page) {
        Optional<Wrapper> wrapper = Optional.of(
                this.restTemplate.getForObject(String.format("https://swapi.co/api/planets?page=%d", page), Wrapper.class)
        );
        if(wrapper.isPresent()) {
            return wrapper.get().getPlanetaWrappers();
        }
        return new ArrayList<>();
    }

}

package com.b2w.starwar.infrastructure.service.impl;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.infrastructure.service.PlanetaSwService;
import com.b2w.starwar.infrastructure.service.Swapi;
import com.b2w.starwar.infrastructure.service.wrapper.PlanetaWrapper;
import com.b2w.starwar.infrastructure.service.wrapper.Wrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
                this.restTemplate.getForObject(
                        String.format("https://swapi.co/api/planets?page=%d", page), Wrapper.class
                )
        );
        if(wrapper.isPresent()) {
            return wrapper.get().getPlanetaWrappers();
        }
        return new ArrayList<>();
    }

    @Override
    public Integer getTotalDeFilmesPorPlaneta(Planeta planeta) {
        Long cont = 1L;
        Optional<PlanetaWrapper> planetaWrapper = Optional.empty();

        if(StringUtils.isEmpty(planeta.getNome())) {
            return null;
        }

        do {

            List<PlanetaWrapper> wrappers = this.call(cont);

            if(CollectionUtils.isNotEmpty(wrappers)) {
                planetaWrapper = wrappers.stream()
                        .filter(w -> w.getName().equals(planeta.getNome())).findFirst();
                cont++;
            }

            if(wrappers.size() < 10 || planetaWrapper.isPresent()){
                cont = 0L;
            }

        } while(cont > 0);

        return planetaWrapper.isPresent() ? planetaWrapper.get().getFilms().size() : null;
    }

}

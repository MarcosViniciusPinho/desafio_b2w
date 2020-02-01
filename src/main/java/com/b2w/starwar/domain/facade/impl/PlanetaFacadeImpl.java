package com.b2w.starwar.domain.facade.impl;

import com.b2w.starwar.domain.dto.PlanetaDto;
import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.domain.facade.PlanetaFacade;
import com.b2w.starwar.domain.service.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Service
public class PlanetaFacadeImpl implements PlanetaFacade {

    @Autowired
    private PlanetaService service;

    @Override
    public PlanetaDto save(PlanetaDto dto) {
        Planeta planeta = this.service.save(new Planeta(dto.getNome(), dto.getClima(), dto.getTerreno()));
        return PlanetaDto.builder().id(planeta.getId().toHexString()).nome(planeta.getNome())
                .clima(planeta.getClima()).terreno(planeta.getTerreno())
                .totalDeAparicoesEmFilmes(planeta.getTotalDeAparicoesEmFilmes())
                .build();
    }

    @Override
    public void delete(String id) {
        this.service.delete(id);
    }

    @Override
    public Optional<List<PlanetaDto>> findAll(String nome) {
        Optional<List<Planeta>> planetas = this.service.findAll(nome);
        if(planetas.isPresent()) {
            List<PlanetaDto> dtos = new ArrayList<>();
            planetas.get().forEach(it -> dtos.add(PlanetaDto.builder().id(it.getId().toHexString())
                    .nome(it.getNome()).clima(it.getClima()).terreno(it.getTerreno())
                    .totalDeAparicoesEmFilmes(it.getTotalDeAparicoesEmFilmes())
                    .build()));
            return Optional.of(dtos);
        }
        return Optional.empty();
    }

    @Override
    public Optional<PlanetaDto> findById(String id) {
        Optional<Planeta> planeta = this.service.findById(id);
        if(planeta.isPresent()) {
            Planeta planetaOther = planeta.get();
            return Optional.of(PlanetaDto.builder().id(planetaOther.getId().toHexString())
                    .nome(planetaOther.getNome()).clima(planetaOther.getClima())
                    .terreno(planetaOther.getTerreno())
                    .totalDeAparicoesEmFilmes(planetaOther.getTotalDeAparicoesEmFilmes())
                    .build());
        }
        return Optional.empty();
    }
}

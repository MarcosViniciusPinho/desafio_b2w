package com.b2w.starwar.domain.service.impl;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.infrastructure.repository.PlanetaRepository;
import com.b2w.starwar.domain.service.PlanetaService;
import com.b2w.starwar.infrastructure.service.PlanetaSwService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@Service
public class PlanetaServiceImpl implements PlanetaService {

    @Autowired
    private PlanetaRepository repository;

    @Autowired
    private PlanetaSwService service;

    @Override
    public Planeta save(Planeta planeta) {
        Planeta planetaCadastrado = this.repository.save(planeta);
        planetaCadastrado.setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(planeta)
        );
        return planetaCadastrado;
    }

    @Override
    public void delete(ObjectId id) {
        Planeta planeta = new Planeta();
        planeta.setId(id);
        this.repository.delete(planeta);
    }

    @Override
    public List<Planeta> findAll() {
        List<Planeta> planetas = this.repository.findAll();
        planetas.forEach(planeta -> {
            planeta.setTotalDeAparicoesEmFilmes(
                    this.service.getTotalDeFilmesPorPlaneta(planeta)
            );
        });
        return planetas;
    }

    @Override
    public Planeta find(ObjectId id, String nome) {
        Planeta planeta = this.repository.findByIdOrNome(id, nome);
        if(planeta != null) {
            planeta.setTotalDeAparicoesEmFilmes(this.service.getTotalDeFilmesPorPlaneta(planeta));
        }
        return planeta;
    }

}

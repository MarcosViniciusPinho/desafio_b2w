package com.b2w.starwar.domain.service.impl;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.infrastructure.repository.PlanetaRepository;
import com.b2w.starwar.domain.service.PlanetaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@Service
public class PlanetaServiceImpl implements PlanetaService {

    @Autowired
    private PlanetaRepository repository;

    @Override
    public Planeta save(Planeta planeta) {
        return this.repository.save(planeta);
    }

    @Override
    public void delete(ObjectId id) {
        Planeta planeta = new Planeta();
        planeta.setId(id);
        this.repository.delete(planeta);
    }
}

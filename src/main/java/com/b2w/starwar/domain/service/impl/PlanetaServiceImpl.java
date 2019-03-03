package com.b2w.starwar.domain.service.impl;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.infrastructure.repository.PlanetaRepository;
import com.b2w.starwar.infrastructure.repository.SequenceRepository;
import com.b2w.starwar.domain.service.PlanetaService;
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

    @Autowired
    private SequenceRepository sequenceRepository;

    @Override
    public Planeta save(Planeta planeta) {
        planeta.setId(this.sequenceRepository.getNextSequence("planetas"));
        return this.repository.save(planeta);
    }
}

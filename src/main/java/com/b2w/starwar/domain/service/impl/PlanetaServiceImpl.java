package com.b2w.starwar.domain.service.impl;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.domain.service.PlanetaService;
import com.b2w.starwar.infrastructure.handler.exception.RecurseNotFoundException;
import com.b2w.starwar.infrastructure.repository.PlanetaRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe criada por mpinho
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
    public void delete(String id) {
        Optional<Planeta> planeta = this.repository.findById(new ObjectId(id));
        this.validate(planeta);
        this.repository.delete(planeta.get());
    }

    @Override
    public Optional<List<Planeta>> findAll(String nome) {
        return this.repository.findAllByNomeIsLike(StringUtils.isEmpty(nome) ? "" : nome);
    }

    @Override
    public Optional<Planeta> findById(String id) {
        return this.repository.findById(new ObjectId(id));
    }

    private void validate(Optional<Planeta> planeta) {
        if(!planeta.isPresent()) {
            throw new RecurseNotFoundException("Não foi(foram) encontrado(s) planeta(s) com os dados informados",
                    "A API do desafio não retornou nenhuma informação!");
        }
    }

}

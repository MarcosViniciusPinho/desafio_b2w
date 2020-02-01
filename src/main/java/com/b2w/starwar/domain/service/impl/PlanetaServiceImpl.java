package com.b2w.starwar.domain.service.impl;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.domain.service.PlanetaService;
import com.b2w.starwar.infrastructure.handler.exception.RecurseNotFoundException;
import com.b2w.starwar.infrastructure.handler.exception.UniqueException;
import com.b2w.starwar.infrastructure.repository.PlanetaRepository;
import com.b2w.starwar.infrastructure.service.PlanetaSwService;
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

    @Autowired
    private PlanetaSwService service;

    @Override
    public Planeta save(Planeta planeta) {
        this.validarNomeRepetido(planeta.getNome());
        Planeta planetaCadastrado = this.repository.save(planeta);
        planetaCadastrado.setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(planeta)
        );
        return planetaCadastrado;
    }

    @Override
    public void delete(String id) {
        Optional<Planeta> planeta = this.repository.findById(new ObjectId(id));
        this.validarRecurso(planeta);
        this.repository.delete(planeta.get());
    }

    @Override
    public Optional<List<Planeta>> findAll(String nome) {
        Optional<List<Planeta>> planetas = this.repository.findAllByNomeIsLike(StringUtils.isEmpty(nome) ? "" : nome);
        planetas.ifPresent(it -> it.forEach(planeta -> {
            planeta.setTotalDeAparicoesEmFilmes(
                    this.service.getTotalDeFilmesPorPlaneta(planeta)
            );
        }));
        return planetas;
    }

    @Override
    public Optional<Planeta> findById(String id) {
        Optional<Planeta> planeta = this.repository.findById(new ObjectId(id));
        planeta.ifPresent(it -> it.setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(it)
        ));
        return planeta;
    }

    private void validarRecurso(Optional<Planeta> planeta) {
        if(!planeta.isPresent()) {
            throw new RecurseNotFoundException("Não foi(foram) encontrado(s) planeta(s) com os dados informados",
                    "A API do desafio não retornou nenhuma informação!");
        }
    }

    private void validarNomeRepetido(String nome) {
        Planeta planeta = this.repository.findByNome(nome);
        if(planeta != null) {
            throw new UniqueException("Já existe um planeta cadastrado com este nome",
                    "Planeta já existente na base de dados!");
        }
    }

}

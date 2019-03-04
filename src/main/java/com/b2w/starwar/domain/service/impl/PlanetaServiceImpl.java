package com.b2w.starwar.domain.service.impl;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.infrastructure.handler.exception.RecurseNotFoundException;
import com.b2w.starwar.infrastructure.handler.exception.UniqueException;
import com.b2w.starwar.infrastructure.repository.PlanetaRepository;
import com.b2w.starwar.domain.service.PlanetaService;
import com.b2w.starwar.infrastructure.service.PlanetaSwService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        this.validarNomeRepetido(planeta.getNome());
        Planeta planetaCadastrado = this.repository.save(planeta);
        planetaCadastrado.setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(planeta)
        );
        return planetaCadastrado;
    }

    @Override
    public void delete(ObjectId id) {
        Optional<Planeta> planeta = this.repository.findById(id);
        this.validarRecurso(planeta);
        this.repository.delete(planeta.get());
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
    public Optional<Planeta> find(ObjectId id, String nome) {
        Optional<Planeta> planeta = this.repository.findByIdOrNome(id, nome);
        this.validarRecurso(planeta);
        planeta.get().setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(planeta.get())
        );
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

package com.b2w.starwar.infrastructure.service;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.infrastructure.service.wrapper.PlanetaWrapper;

import java.util.List;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
public interface PlanetaSwService {

    /**
     * Método responsável por buscar todos os planetas na API https://swapi.co/api/planets
     * @param page page responsavel pela busca por demanda.
     * @return List<PlanetaWrapper> classe que transforma as informações vindas da API para este objeto <code>PlanetaWrapper</code>
     */
    List<PlanetaWrapper> call(Long page);

    /**
     * Método responsável por obter o total de filmes por planeta disponibilizados na API deste desafio.
     * @param planeta planeta
     * @return Integer Retorna a quantidade de aparições em filmes caso o planeta fornecido exista
     * na API deste desafio, caso contrário retorna null
     */
    Integer getTotalDeFilmesPorPlaneta(Planeta planeta);

}

package com.b2w.starwar.infrastructure.service;

import com.b2w.starwar.infrastructure.service.wrapper.PlanetaWrapper;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
public interface PlanetaSwService {

    /**
     * Método responsável por buscar um planeta na API https://swapi.co/api/planets/{id} por seu respectivo id
     * @param id id fornecido para ser pesquisado
     * @return PlanetaWrapper classe que transforma as informações vindas da API para este objeto <code>PlanetaWrapper</code>
     */
    PlanetaWrapper call(Long id);

}

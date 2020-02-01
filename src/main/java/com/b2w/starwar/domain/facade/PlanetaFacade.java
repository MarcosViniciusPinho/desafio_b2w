package com.b2w.starwar.domain.facade;

import com.b2w.starwar.domain.dto.PlanetaDto;

import java.util.List;
import java.util.Optional;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
public interface PlanetaFacade {

    /**
     * Método para salvar um planeta
     * @param dto dto
     * @return PlanetaDto
     */
    PlanetaDto save(PlanetaDto dto);

    /**
     * Método que exclui um determinado planeta por seu id.
     * @param id id
     */
    void delete(String id);

    /**
     * Listar todos os planetas, porém caso passe o filtro nome irá retornar apenas informações relativas ao mesmo.
     * @return Optional<List<PlanetaDto>>
     */
    Optional<List<PlanetaDto>> findAll(String nome);

    /**
     * Método usado para buscar um planeta a partir de seu id
     * @param id id
     * @return PlanetaDto
     */
    Optional<PlanetaDto> findById(String id);
}

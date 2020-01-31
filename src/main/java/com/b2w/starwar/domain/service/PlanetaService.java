package com.b2w.starwar.domain.service;

import com.b2w.starwar.domain.entity.Planeta;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
public interface PlanetaService {

    /**
     * Método para salvar um planeta
     * @param planeta planeta
     * @return Planeta
     */
    Planeta save(Planeta planeta);

    /**
     * Método que exclui um determinado planeta por seu id.
     * @param id id
     */
    void delete(ObjectId id);

    /**
     * Listar todos os planetas, porém caso passe o filtro nome irá retornar apenas informações relativas ao mesmo.
     * @return Optional<List<Planeta>>
     */
    Optional<List<Planeta>> findAll(String nome);

    /**
     * Método usado para buscar um planeta a partir de seu id ou nome
     * @param id id
     * @param nome nome
     * @return Planeta
     */
    Optional<Planeta> find(ObjectId id, String nome);
}

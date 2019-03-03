package com.b2w.starwar.domain.service;

import com.b2w.starwar.domain.entity.Planeta;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Classe criada por mpinho na data 03/03/19
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
     * Listar todos os planetas
     * @return List<Planeta>
     */
    List<Planeta> findAll();

    /**
     * Método usado para buscar um planeta a partir de seu id ou nome
     * @param id id
     * @param nome nome
     * @return Planeta
     */
    Planeta find(ObjectId id, String nome);
}

package com.b2w.starwar.infrastructure.repository;

import com.b2w.starwar.domain.entity.Planeta;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, ObjectId> {

    /**
     * Método que busca um planeta por seu respectivo id ou nome.
     * @param id id
     * @param nome nome
     * @return Planeta
     */
    Optional<Planeta> findByIdOrNome(ObjectId id, String nome);

    /**
     * Método que busca um planeta a partir de seu nome
     * @param nome nome do planeta a ser pesquisado
     * @return Planeta
     */
    Planeta findByNome(String nome);

}

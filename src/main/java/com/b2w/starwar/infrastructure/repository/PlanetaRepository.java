package com.b2w.starwar.infrastructure.repository;

import com.b2w.starwar.domain.entity.Planeta;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe criada por mpinho na data 03/03/19
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
    Planeta findByIdOrNome(ObjectId id, String nome);

}

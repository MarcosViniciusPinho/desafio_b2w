package com.b2w.starwar.infrastructure.repository;

import com.b2w.starwar.infrastructure.repository.exception.SequenceException;
import org.springframework.stereotype.Repository;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@Repository
public interface SequenceRepository {

    Long getNextSequence(String key) throws SequenceException;

}

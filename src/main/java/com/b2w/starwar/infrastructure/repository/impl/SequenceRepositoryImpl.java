package com.b2w.starwar.infrastructure.repository.impl;

import com.b2w.starwar.infrastructure.repository.SequenceRepository;
import com.b2w.starwar.infrastructure.repository.exception.SequenceException;
import com.b2w.starwar.infrastructure.repository.model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
public class SequenceRepositoryImpl implements SequenceRepository {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public Long getNextSequence(String key) throws SequenceException {
        Query query = new Query(Criteria.where("_id").is(key));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        Sequence sequence =
                mongoOperation.findAndModify(query, update, options, Sequence.class);

        if (sequence == null) {
            throw new SequenceException("Erro ao tentar gerar o proximo id");
        }

        return sequence.getSeq();
    }
}

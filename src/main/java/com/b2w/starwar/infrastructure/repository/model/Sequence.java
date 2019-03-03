package com.b2w.starwar.infrastructure.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "sequences")
public class Sequence {

    @Id
    private String id;

    private long seq;

}

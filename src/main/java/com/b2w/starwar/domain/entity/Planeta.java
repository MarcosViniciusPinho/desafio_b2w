package com.b2w.starwar.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe criada por mpinho na data 02/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"nome", "clima", "terreno", "totalDeAparicoesEmFilmes"})
@NoArgsConstructor
@Document(collection = "planetas")
public class Planeta {

    @JsonIgnore
    @Id
    private ObjectId id;

    private String nome;

    private String clima;

    private String terreno;

    private Long totalDeAparicoesEmFilmes;

}

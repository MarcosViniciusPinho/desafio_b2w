package com.b2w.starwar.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "planetas")
public class Planeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    private ObjectId id;

    private String nome;

    private String clima;

    private String terreno;

    private Integer totalDeAparicoesEmFilmes;

    public Planeta(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }
}

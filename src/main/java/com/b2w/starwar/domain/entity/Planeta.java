package com.b2w.starwar.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Id
    private Long id;

    private String nome;

    private String clima;

    private String terreno;

    private Long totalDeAparicoesEmFilmes;

}

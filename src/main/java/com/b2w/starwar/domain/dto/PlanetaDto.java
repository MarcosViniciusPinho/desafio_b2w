package com.b2w.starwar.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
@Setter
@Builder
public class PlanetaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String nome;

    private String clima;

    private String terreno;

    private Integer totalDeAparicoesEmFilmes;

}

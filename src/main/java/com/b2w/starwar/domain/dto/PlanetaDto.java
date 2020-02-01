package com.b2w.starwar.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clima;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String terreno;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalDeAparicoesEmFilmes;

}

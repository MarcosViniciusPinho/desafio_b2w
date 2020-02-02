package com.b2w.starwar.infrastructure.handler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
@AllArgsConstructor
public class ResponseError {

    private Integer codigo;
    private String mensagem;

}

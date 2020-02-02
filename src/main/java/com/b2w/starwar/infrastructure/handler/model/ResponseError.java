package com.b2w.starwar.infrastructure.handler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
@AllArgsConstructor
@ToString
public class ResponseError {

    private Integer codigo;
    private String mensagem;

}

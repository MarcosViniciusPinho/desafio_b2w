package com.b2w.starwar.infrastructure.handler.exception;

import lombok.Getter;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
public class RecurseNotFoundException extends RuntimeException {

    private final Integer codigo;
    private final String mensagem;

    public RecurseNotFoundException(Integer codigo, String mensagem) {
        super("Recurso informado não existe");
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

}

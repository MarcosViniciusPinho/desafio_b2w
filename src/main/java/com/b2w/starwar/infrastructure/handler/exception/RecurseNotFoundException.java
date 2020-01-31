package com.b2w.starwar.infrastructure.handler.exception;

import lombok.Getter;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
public class RecurseNotFoundException extends RuntimeException {

    private final String mensagemClient;

    public RecurseNotFoundException(String mensagemClient, String mensagemException) {
        super(mensagemException);
        this.mensagemClient = mensagemClient;
    }

}

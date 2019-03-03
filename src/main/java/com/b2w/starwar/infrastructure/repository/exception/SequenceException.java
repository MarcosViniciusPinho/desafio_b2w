package com.b2w.starwar.infrastructure.repository.exception;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
public class SequenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SequenceException(String errMsg) {
        super(errMsg);
    }
}

package com.b2w.starwar.infrastructure.handler;

import com.b2w.starwar.Constante;
import com.b2w.starwar.infrastructure.handler.model.ResponseError;
import com.b2w.starwar.infrastructure.handler.exception.RecurseNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@Slf4j
@ControllerAdvice
public class StarwarHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RecurseNotFoundException.class })
    public ResponseEntity<Object> handleRecurseNotFoundException(RecurseNotFoundException ex, WebRequest request) {
        return this.throwException(ex, ex.getCodigo(), ex.getMensagem(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UnknownHostException.class, SocketException.class })
    public ResponseEntity<Object> handleUnknownHostException(IOException ex, WebRequest request) {
        String mensagem = "Verifique sua conexão com a internet e tente novamente, caso esteja conectado favor comunicar ao administrador do sistema!";
        return this.throwException(ex instanceof UnknownHostException ? (UnknownHostException) ex : (SocketException) ex,
                Constante.ErrorCode.INDISPONIBILIDADE, mensagem, request, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<Object> throwException(Exception ex, Integer codigo, String message, WebRequest request, HttpStatus status) {
        List<ResponseError> erros = Collections.singletonList(new ResponseError(codigo, message));
        log.debug(new ResponseError(codigo, ExceptionUtils.getRootCauseMessage(ex)).toString());
        return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
    }

}

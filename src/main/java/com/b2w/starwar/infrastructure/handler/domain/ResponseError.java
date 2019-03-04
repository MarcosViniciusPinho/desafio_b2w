package com.b2w.starwar.infrastructure.handler.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseError {

    private String erro;
    private String exception;

}

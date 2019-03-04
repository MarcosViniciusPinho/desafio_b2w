package com.b2w.starwar.infrastructure.service.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
public class Wrapper {

    private Long count;
    private String next;
    private String previous;
    @JsonProperty("results")
    private List<PlanetaWrapper> planetaWrappers;

}

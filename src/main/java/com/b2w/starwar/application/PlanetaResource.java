package com.b2w.starwar.application;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.domain.service.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Classe criada por mpinho na data 03/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@RestController
@RequestMapping("/planetas")
public class PlanetaResource {

    @Autowired
    private PlanetaService service;

    /**
     * Método que adiciona uma planeta
     * @param planeta planeta
     * @return ResponseEntity<Planeta>
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planeta> create(@Valid @RequestBody Planeta planeta){
        Planeta planetaSalvo = this.service.save(planeta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(planetaSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(planetaSalvo);
    }

}

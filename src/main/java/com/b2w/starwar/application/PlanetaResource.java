package com.b2w.starwar.application;

import com.b2w.starwar.domain.dto.PlanetaDto;
import com.b2w.starwar.domain.facade.PlanetaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Classe criada por mpinho
 * E-mail: marcosjava2008@gmail.com
 */
@RestController
@RequestMapping("/planetas")
public class PlanetaResource {

    @Autowired
    private PlanetaFacade facade;

    /**
     * Método que adiciona uma planeta
     * @param dto dto
     * @return ResponseEntity<Planeta>
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetaDto> create(@RequestBody PlanetaDto dto){
        PlanetaDto planetaSalvo = this.facade.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(planetaSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(planetaSalvo);
    }

    /**
     * Método que exclui um planeta por seu id
     * @param id id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id){
        this.facade.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método que lista todos os planetas, caso deseje filtrar um planeta basta passar seu nome.
     * @return ResponseEntity<List<Planeta>>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<List<PlanetaDto>>> findAll(@RequestParam(value = "nome", required = false) String nome){
        return ResponseEntity.ok().body(this.facade.findAll(nome));
    }

    /**
     * Método que busca um planeta por seu id
     * @param  id id
     * @return ResponseEntity<Planeta>
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PlanetaDto>> findById(@PathVariable(value = "id") String id){
        return ResponseEntity.ok().body(this.facade.findById(id));
    }

}

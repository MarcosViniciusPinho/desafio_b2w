package com.b2w.starwar.application;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.domain.service.PlanetaService;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    /**
     * Método que exclui um planeta por seu id
     * @param id id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id){
        this.service.delete(new ObjectId(id));
        return ResponseEntity.noContent().build();
    }

    /**
     * Método que lista todos os planetas, caso deseje filtrar um planeta basta passar seu nome.
     * @return ResponseEntity<List<Planeta>>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<List<Planeta>>> findAll(@RequestParam(value = "nome", required = false) String nome){
        return ResponseEntity.ok().body(this.service.findAll(nome));
    }

    /**
     * Método que busca um planeta por seu id ou por seu nome
     * @param  id id
     * @param  nome nome
     * @return ResponseEntity<Planeta>
     */
    @GetMapping(value = "/param", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Planeta>> find(@RequestParam(value = "id", required = false) String id,
                                                 @RequestParam(value = "nome", required = false) String nome){
        val planeta = this.service.find(StringUtils.isNotEmpty(id) ? new ObjectId(id)
                : new ObjectId(), nome);
        return ResponseEntity.ok(planeta);
    }

}

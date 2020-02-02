package com.b2w.starwar.unit;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.domain.service.impl.PlanetaServiceImpl;
import com.b2w.starwar.infrastructure.handler.exception.RecurseNotFoundException;
import com.b2w.starwar.infrastructure.repository.PlanetaRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetaServiceImplTest {

    @InjectMocks
    private PlanetaServiceImpl service;

    @Mock
    private PlanetaRepository repository;

    @Test
    public void deveSalvarUmPlanetaComSucesso() {
        String id = "5e35e10f9498c60f6c8cacea";

        Planeta planeta = new Planeta("Aldebaran", "temperate", "desert");
        Planeta planetaSalvo = new Planeta(new ObjectId(id), "Aldebaran",
                "temperate", "desert");

        Mockito.when(this.repository.save(planeta)).thenReturn(planetaSalvo);
        Planeta planetaAtual = this.service.save(planeta);

        Assert.assertNotNull(planetaAtual.getId());
        Assert.assertEquals(id, planetaAtual.getId().toHexString());

    }

    @Test
    public void deveExcluirUmPlanetaComSucesso() {
        String id = "5e35e10f9498c60f6c8cacea";

        Planeta planetaEncontrado = new Planeta(new ObjectId(id), "Aldebaran",
                "temperate", "desert");

        Mockito.when(this.repository.findById(new ObjectId(id))).thenReturn(Optional.of(planetaEncontrado));

        this.service.delete(id);
    }

    @Test(expected = RecurseNotFoundException.class)
    public void deveTentarExcluirUmPlaneta() {
        String id = "5e35e10f9498c60f6c8cacea";

        Mockito.when(this.repository.findById(new ObjectId(id))).thenReturn(Optional.empty());

        this.service.delete(id);
    }

    @Test
    public void deveListarTodosOsPlanetasComSucesso() {

        int quantidadeDePlanetasEsperados = 3;
        String nome = "";

        Mockito.when(this.repository.findAllByNomeIsLike(nome)).thenReturn(
                Optional.of(
                        Arrays.asList(new Planeta(), new Planeta(), new Planeta())
                )
        );

        Optional<List<Planeta>> planetas = this.service.findAll(nome);
        Assert.assertEquals(quantidadeDePlanetasEsperados, planetas.get().size());

    }

    @Test
    public void deveListarOsPlanetasPorNomeComSucesso() {

        int quantidadeDePlanetasEsperados = 1;
        String nome = "Aldebaran";

        Mockito.when(this.repository.findAllByNomeIsLike(nome)).thenReturn(
                Optional.of(
                        Arrays.asList(new Planeta())
                )
        );

        Optional<List<Planeta>> planetas = this.service.findAll(nome);
        Assert.assertEquals(quantidadeDePlanetasEsperados, planetas.get().size());

    }

    @Test
    public void deveEncontrarNenhumPlaneta() {

        int quantidadeDePlanetasEsperados = 0;
        String nome = "";

        Mockito.when(this.repository.findAllByNomeIsLike(nome)).thenReturn(
                Optional.of(new ArrayList<>())
        );

        Optional<List<Planeta>> planetas = this.service.findAll(nome);
        Assert.assertEquals(quantidadeDePlanetasEsperados, planetas.get().size());

    }

    @Test
    public void deveEncontrarUmPlanetaPorSeuId() {
        String id = "5e35e10f9498c60f6c8cacea";

        Mockito.when(this.repository.findById(new ObjectId(id))).thenReturn(Optional.of(
                new Planeta(new ObjectId(id), "Aldebaran", "Desert", "Baldio")
        ));

        Optional<Planeta> planeta = this.service.findById(id);
        Assert.assertEquals(id, planeta.get().getId().toHexString());
        Assert.assertEquals("Aldebaran", planeta.get().getNome());
        Assert.assertEquals("Desert", planeta.get().getClima());
        Assert.assertEquals("Baldio", planeta.get().getTerreno());
    }

    @Test
    public void deveTentarEncontrarUmPlanetaComIdInexistente() {
        String id = "5e35e10f9498c60f6c8cacea";

        Mockito.when(this.repository.findById(new ObjectId(id))).thenReturn(Optional.empty());

        Optional<Planeta> planeta = this.service.findById(id);
        Assert.assertFalse(planeta.isPresent());
    }

}

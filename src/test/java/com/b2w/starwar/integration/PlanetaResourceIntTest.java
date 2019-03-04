package com.b2w.starwar.integration;

import com.b2w.starwar.domain.entity.Planeta;
import com.b2w.starwar.domain.service.PlanetaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe criada por mpinho na data 04/03/19
 * E-mail: marcosjava2008@gmail.com
 */
@RunWith(SpringRunner.class)
public class PlanetaResourceIntTest extends IntegrationSource {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlanetaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MongoOperations operations;

    @Before
    public void setup() {
        this.operations.dropCollection("planetas");
    }

    @Test
    public void deveSalvarUmPlanetaQueNaoExistaNaApiDoDesafio() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(post("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Planeta("Aldebaran", "temperate", "desert"))))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Aldebaran"))
                .andExpect(jsonPath("$.clima").value("temperate"))
                .andExpect(jsonPath("$.terreno").value("desert"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").isEmpty())
                .andReturn().getResponse();

        Assert.assertTrue(response.getHeader("Location").contains("nome=Aldebaran"));
    }

    @Test
    public void deveSalvarUmPlanetaQueExistaNaApiDoDesafio() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(post("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Planeta("Coruscant", "temperate", "cityscape, mountains"))))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Coruscant"))
                .andExpect(jsonPath("$.clima").value("temperate"))
                .andExpect(jsonPath("$.terreno").value("cityscape, mountains"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").value(4))
                .andReturn().getResponse();

        Assert.assertTrue(response.getHeader("Location").contains("nome=Coruscant"));
    }

    @Test
    public void deveDarErroAoTentarSalvarUmPlanetaExistente() throws Exception {
        this.service.save(new Planeta("Coruscant", "temperate", "cityscape, mountains"));

        this.mockMvc.perform(post("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Planeta("Coruscant", "temperate", "cityscape, mountains"))))
                .andExpect(status().isExpectationFailed())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].erro").value("Já existe um planeta cadastrado com este nome"))
                .andExpect(jsonPath("$[0].exception").value("UniqueException: Planeta já existente na base de dados!"))
                .andReturn().getResponse();

    }

    @Test
    public void deveExcluirUmPlaneta() throws Exception {
        Planeta planeta = this.service.save(new Planeta("Coruscant", "temperate", "cityscape, mountains"));

        this.mockMvc.perform(delete("/planetas/" + planeta.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveDarErroAoTentarExcluirUmPlanetaInexistente() throws Exception {
        this.mockMvc.perform(delete("/planetas/5c7da5e6b3e05137fa8e2155")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveListarOsPlanetasCadastrados() throws Exception{
        this.service.save(new Planeta("Aldebaran", "temperate", "desert"));
        this.service.save(new Planeta("Coruscant", "temperate", "cityscape, mountains"));

        this.mockMvc.perform(get("/planetas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("Aldebaran"))
                .andExpect(jsonPath("$[0].clima").value("temperate"))
                .andExpect(jsonPath("$[0].terreno").value("desert"))
                .andExpect(jsonPath("$[0].totalDeAparicoesEmFilmes").isEmpty())
                .andExpect(jsonPath("$[1].nome").value("Coruscant"))
                .andExpect(jsonPath("$[1].clima").value("temperate"))
                .andExpect(jsonPath("$[1].terreno").value("cityscape, mountains"))
                .andExpect(jsonPath("$[1].totalDeAparicoesEmFilmes").value(4))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void devePesquisarUmPlanetaComSeuRespectivoIdAndQueExistaNaApiDeDesafio() throws Exception{
        Planeta planeta = this.service.save(new Planeta("Coruscant", "temperate", "cityscape, mountains"));

        this.mockMvc.perform(get("/planetas/param?id=" + planeta.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Coruscant"))
                .andExpect(jsonPath("$.clima").value("temperate"))
                .andExpect(jsonPath("$.terreno").value("cityscape, mountains"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").value(4));
    }

    @Test
    public void devePesquisarUmPlanetaComSeuRespectivoIdAndQueNaoExistaNaApiDeDesafio() throws Exception{
        Planeta planeta = this.service.save(new Planeta("Aldebaran", "temperate", "desert"));

        this.mockMvc.perform(get("/planetas/param?id=" + planeta.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Aldebaran"))
                .andExpect(jsonPath("$.clima").value("temperate"))
                .andExpect(jsonPath("$.terreno").value("desert"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").isEmpty());
    }

    @Test
    public void deveDarErroAoTentarPesquisarUmPlanetaInexistentePorSeuRespectivoId() throws Exception{
        this.mockMvc.perform(get("/planetas/param?id=5c7daed6b3e051404133f2aa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].erro").value("Não foi(foram) encontrado(s) planeta(s) com os dados informados"))
                .andExpect(jsonPath("$[0].exception").value("RecurseNotFoundException: A API do desafio não retornou nenhuma informação!"));
    }

    @Test
    public void devePesquisarUmPlanetaComSeuRespectivoNomeAndQueExistaNaApiDeDesafio() throws Exception{
        Planeta planeta = this.service.save(new Planeta("Coruscant", "temperate", "cityscape, mountains"));

        this.mockMvc.perform(get("/planetas/param?nome=" + planeta.getNome())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Coruscant"))
                .andExpect(jsonPath("$.clima").value("temperate"))
                .andExpect(jsonPath("$.terreno").value("cityscape, mountains"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").value(4));
    }

    @Test
    public void devePesquisarUmPlanetaComSeuRespectivoNomeAndQueNaoExistaNaApiDeDesafio() throws Exception{
        Planeta planeta = this.service.save(new Planeta("Aldebaran", "temperate", "desert"));

        this.mockMvc.perform(get("/planetas/param?nome=" + planeta.getNome())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Aldebaran"))
                .andExpect(jsonPath("$.clima").value("temperate"))
                .andExpect(jsonPath("$.terreno").value("desert"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").isEmpty());
    }

    @Test
    public void deveDarErroAoTentarPesquisarUmPlanetaInexistentePorSeuRespectivoNome() throws Exception{
        this.mockMvc.perform(get("/planetas/param?nome=XPTO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].erro").value("Não foi(foram) encontrado(s) planeta(s) com os dados informados"))
                .andExpect(jsonPath("$[0].exception").value("RecurseNotFoundException: A API do desafio não retornou nenhuma informação!"));
    }
}

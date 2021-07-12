package br.com.rodrigo.cadastrocliente.controller;

import br.com.rodrigo.cadastrocliente.dto.ClienteRequest;
import br.com.rodrigo.cadastrocliente.feign.EnderecoResponse;
import br.com.rodrigo.cadastrocliente.feign.SistemaEndereco;
import br.com.rodrigo.cadastrocliente.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;


//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonMapper;

    //@MockBean
    @Autowired
    private ClienteRepository clienteRepository;

    @MockBean
    private SistemaEndereco sistemaEndereco;


    @Test
    @DisplayName("Esse método deve criar um cadastro de um novo Cliente e retornar o status 201")
    void deveCriarUmCliente() throws Exception {


        ClienteRequest request = new ClienteRequest("Rodrigo", "664.426.470-05", "rodrigo@gmail.com", "37552162", "100");
        request.setDataNascimento(LocalDate.of(1991, 7, 1));
        String json = jsonMapper.writeValueAsString(request);

        Mockito.when(sistemaEndereco.consulta("37552162")).thenReturn(new EnderecoResponse("Rua Mariana", "Santo Antonio", "Pouso", "MG"));

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/cliente/**"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(request.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("rodrigo@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento").value(request.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));


        //Aqui poderiamos fazer um find do objeto, e depois verificar seus atributos!
        Boolean resultado = clienteRepository.existsByEmail("rodrigo@gmail.com");
        Assertions.assertTrue(resultado);
    }

    @Test
    void deveRetonrarNotFoundAoBuscarClienteInexistente() throws Exception {

        clienteRepository.deleteAll();

        //Fazendo busca por cliente com ID = 500
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/{id}", 500)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void deveRetornarBadRequestAoCadastrarClienteSemNome() throws Exception {

        ClienteRequest request = new ClienteRequest("", "664.426.470-05", "rodrigo@gmail.com", "37552162", "100");
        request.setDataNascimento(LocalDate.of(1991, 7, 1));
        String json = jsonMapper.writeValueAsString(request);

        Mockito.when(sistemaEndereco.consulta("37552162")).thenReturn(new EnderecoResponse("Rua Mariana", "Santo Antonio", "Pouso", "MG"));

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$..campo").value("nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..erro").value("must not be blank"));

        /*
        Para testar Expressao Json Path: https://jsonpath.com/
         */

    }

    @Test
    void deveRetornarBadRequestAoCadastrarClienteSemNomeESemCPF() throws Exception {

        ClienteRequest request = new ClienteRequest("", "", "rodrigo@gmail.com", "37552162", "100");
        request.setDataNascimento(LocalDate.of(1991, 7, 1));
        String json = jsonMapper.writeValueAsString(request);

        Mockito.when(sistemaEndereco.consulta("37552162")).thenReturn(new EnderecoResponse("Rua Mariana", "Santo Antonio", "Pouso", "MG"));

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:2].campo").value(Matchers.containsInAnyOrder(Matchers.is("nome"), Matchers.is("cpf"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[:2].erro").value(Matchers.containsInAnyOrder(Matchers.is("must not be blank"), Matchers.is("invalid Brazilian individual taxpayer registry number (CPF)"))));

        /*
        Para testar Expressao Json Path: https://jsonpath.com/
         */

    }

}
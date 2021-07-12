package br.com.rodrigo.cadastrocliente.dto;

import br.com.rodrigo.cadastrocliente.entity.Cliente;
import br.com.rodrigo.cadastrocliente.entity.Endereco;
import br.com.rodrigo.cadastrocliente.feign.EnderecoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteResponse {

    private Long id;
    private String nome;
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    private String email;
    private Integer idade;
    private EnderecoDto endereco;
    private String numero;
    private String cep;

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dataNascimento = cliente.getDataNascimento();
        this.email = cliente.getEmail();
        this.idade = cliente.getIdade();
        EnderecoDto response = new EnderecoDto(cliente.getEndereco());
        this.endereco = response;
        this.numero = cliente.getNumero();
        this.cep = cliente.getCep();

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdade() {
        return idade;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public static List<ClienteResponse> convertListModelToResponse(List<Cliente> clientes) {
        return clientes.stream().map(ClienteResponse::new).collect(Collectors.toList());
    }
}

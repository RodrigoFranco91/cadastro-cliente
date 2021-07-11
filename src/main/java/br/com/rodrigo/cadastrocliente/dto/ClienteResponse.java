package br.com.rodrigo.cadastrocliente.dto;

import br.com.rodrigo.cadastrocliente.entity.Cliente;
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

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dataNascimento = cliente.getDataNascimento();
        this.email = cliente.getEmail();
        this.idade = cliente.getIdade();
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


    public static List<ClienteResponse> convertListModelToResponse(List<Cliente> clientes){
        return clientes.stream().map(ClienteResponse::new).collect(Collectors.toList());
    }
}

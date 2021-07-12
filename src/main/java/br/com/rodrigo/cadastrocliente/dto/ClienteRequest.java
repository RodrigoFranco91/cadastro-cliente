package br.com.rodrigo.cadastrocliente.dto;

import br.com.rodrigo.cadastrocliente.entity.Cliente;
import br.com.rodrigo.cadastrocliente.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class ClienteRequest {

    @NotBlank
    @JsonProperty
    private String nome;

    @CPF
    @JsonProperty
    private String cpf;

    //Ao usar o pattern com shape teremos que criar um SET para esse atributo. Recebolo via construtor nao funciona
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @JsonProperty
    private LocalDate dataNascimento;

    @Email
    @JsonProperty
    private String email;

    @JsonProperty
    private String cep;

    @JsonProperty
    private String numero;

    public ClienteRequest(String nome, String cpf, String email, String cep, String numero) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cep = cep;
        this.numero = numero;
    }

    public Cliente toModel(Endereco endereco) {
        return new Cliente(this.nome, this.cpf, this.dataNascimento, this.email, this.cep, this.numero, endereco);
    }

    //Criei este método por ter usado @JsonFormat com pattern e shape
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}


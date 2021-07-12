package br.com.rodrigo.cadastrocliente.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private Integer idade;
    private String cep;
    private String numero;

    @Embedded
    private Endereco endereco;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, String email, String cep, String numero, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.idade = calculaIdade(dataNascimento);
        this.cep = cep;
        this.numero = numero;
        this.endereco = endereco;
    }

    //Apenas para a JPA
    @Deprecated
    public Cliente() {

    }

    private Integer calculaIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return hoje.compareTo(dataNascimento);
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}

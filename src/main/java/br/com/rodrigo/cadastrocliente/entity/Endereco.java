package br.com.rodrigo.cadastrocliente.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String rua, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    // Apenas para uso da JPA/Hibernate
    @Deprecated
    public Endereco() {
    }


    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}

package br.com.rodrigo.cadastrocliente.dto;

import br.com.rodrigo.cadastrocliente.entity.Endereco;

public class EnderecoDto {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public EnderecoDto(Endereco endereco) {
        this.rua = endereco.getRua();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
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

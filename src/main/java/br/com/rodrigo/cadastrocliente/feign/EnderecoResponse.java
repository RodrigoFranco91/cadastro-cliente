package br.com.rodrigo.cadastrocliente.feign;

import br.com.rodrigo.cadastrocliente.entity.Endereco;

public class EnderecoResponse {

    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public EnderecoResponse(String logradouro, String bairro, String localidade, String uf) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public Endereco converToModel(){
        return new Endereco(this.logradouro, this.bairro, this.localidade, this.uf);
    }
}

package br.com.rodrigo.cadastrocliente.feign;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sistemaEnderecoViaCep", url = "${viaCep.host}")
public interface SistemaEndereco {

    @GetMapping("/ws/{cep}/json/")
    @Headers("Content-Type: application/json")
    public EnderecoResponse consulta(@PathVariable String cep);
}

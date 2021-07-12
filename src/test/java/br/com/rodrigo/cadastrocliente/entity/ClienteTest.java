package br.com.rodrigo.cadastrocliente.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class ClienteTest {

    @Test
    public void verificandoOCalculoDeIdade(){

        //Cenario
        Cliente cliente = new Cliente();
        LocalDate dataNascimento = LocalDate.of(1991, 7, 9);


        //Acao
        Integer idade = cliente.calculaIdade(dataNascimento);

        //Verificacao
        Assertions.assertEquals(30, idade);

    }
}
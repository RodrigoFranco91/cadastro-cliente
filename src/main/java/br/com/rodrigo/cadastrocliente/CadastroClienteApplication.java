package br.com.rodrigo.cadastrocliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CadastroClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroClienteApplication.class, args);
	}

}

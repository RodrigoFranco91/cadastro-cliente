package br.com.rodrigo.cadastrocliente.repository;

import br.com.rodrigo.cadastrocliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Boolean existsByEmail(String email);
}

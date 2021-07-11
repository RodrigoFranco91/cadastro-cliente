package br.com.rodrigo.cadastrocliente.controller;

import br.com.rodrigo.cadastrocliente.dto.ClienteRequest;
import br.com.rodrigo.cadastrocliente.dto.ClienteResponse;
import br.com.rodrigo.cadastrocliente.entity.Cliente;
import br.com.rodrigo.cadastrocliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping()
    public ResponseEntity<?> cadastra(@RequestBody @Valid ClienteRequest request, UriComponentsBuilder uriBuilder) {
        Cliente cliente = request.toModel();
        clienteRepository.save(cliente);
        URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public ResponseEntity<?> listaTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> clienteResponses = ClienteResponse.convertListModelToResponse(clientes);
        return ResponseEntity.ok(clienteResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaPorId(@PathVariable Long id) {
        Optional<Cliente> possivelCliente = clienteRepository.findById(id);
        if (possivelCliente.isPresent()) {
            return ResponseEntity.ok(new ClienteResponse(possivelCliente.get()));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        Optional<Cliente> possivelCLiente = clienteRepository.findById(id);
        if (possivelCLiente.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atuliza(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        Optional<Cliente> possivelCLiente = clienteRepository.findById(id);
        if (possivelCLiente.isPresent()) {
            Cliente cliente = request.toModel();
            cliente.setId(id);
            clienteRepository.save(cliente);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
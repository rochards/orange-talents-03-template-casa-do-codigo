package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.ClienteRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.ClienteRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final PaisRepository paisRepository;
    private final EstadoRepository estadoRepository;

    public ClienteController(ClienteRepository clienteRepository, PaisRepository paisRepository,
        EstadoRepository estadoRepository) {

        this.clienteRepository = clienteRepository;
        this.paisRepository = paisRepository;
        this.estadoRepository = estadoRepository;
    }

    @PostMapping
    public ResponseEntity<Integer> cadastra(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        var cliente = clienteRequestDTO.toModel(paisRepository, estadoRepository);

        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente.getId());
    }
}

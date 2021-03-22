package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.ClienteRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.EstadoPertenceAPaisValidator;
import br.com.zupacademy.rodrigo.casadocodigo.repository.ClienteRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final PaisRepository paisRepository;
    private final EstadoRepository estadoRepository;
    private final EstadoPertenceAPaisValidator estadoPaisValidator;

    public ClienteController(ClienteRepository clienteRepository, PaisRepository paisRepository,
        EstadoRepository estadoRepository, EstadoPertenceAPaisValidator estadoPaisValidator) {

        this.clienteRepository = clienteRepository;
        this.paisRepository = paisRepository;
        this.estadoRepository = estadoRepository;
        this.estadoPaisValidator = estadoPaisValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(estadoPaisValidator);
    }

    @PostMapping
    public ResponseEntity<Integer> cadastra(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        var cliente = clienteRequestDTO.toModel(paisRepository, estadoRepository);

        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente.getId());
    }
}

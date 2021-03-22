package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.EstadoRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.EstadoResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.UniqueEstadoPaisValidator;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final PaisRepository paisRepository;
    private final UniqueEstadoPaisValidator estadoPaisValidator;

    public EstadoController(EstadoRepository estadoRepository, PaisRepository paisRepository,
        UniqueEstadoPaisValidator estadoPaisValidator) {
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
        this.estadoPaisValidator = estadoPaisValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(estadoPaisValidator);
    }

    @PostMapping
    public ResponseEntity<EstadoResponseDTO> cadastra(@RequestBody @Valid EstadoRequestDTO estadoRequestDTO) {
        var estado = estadoRequestDTO.toModel(paisRepository, estadoRepository);

        estado = estadoRepository.save(estado);

        return ResponseEntity.ok(new EstadoResponseDTO(estado));
    }
}

package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.EstadoRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.EstadoResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final PaisRepository paisRepository;

    public EstadoController(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @PostMapping
    public ResponseEntity<EstadoResponseDTO> cadastra(@RequestBody @Valid EstadoRequestDTO estadoRequestDTO) {
        var estado = estadoRequestDTO.toModel(paisRepository, estadoRepository);

        estado = estadoRepository.save(estado);

        return ResponseEntity.ok(new EstadoResponseDTO(estado));
    }
}

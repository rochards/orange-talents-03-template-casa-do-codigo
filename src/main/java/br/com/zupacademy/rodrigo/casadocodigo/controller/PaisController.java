package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.PaisRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.PaisResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("paises")
public class PaisController {

    private final PaisRepository paisRepository;

    public PaisController(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @PostMapping
    public ResponseEntity<PaisResponseDTO> cadastra(@RequestBody @Valid PaisRequestDTO paisRequestDTO) {
        var pais = paisRequestDTO.toModel();

        pais = paisRepository.save(pais);

        return ResponseEntity.ok(new PaisResponseDTO(pais));
    }
}

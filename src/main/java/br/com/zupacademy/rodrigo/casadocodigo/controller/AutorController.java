package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.AutorRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.AutorResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.exception.type.EmailExistsException;
import br.com.zupacademy.rodrigo.casadocodigo.repository.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> cadastra(@RequestBody @Valid AutorRequestDTO autorRequestDTO) {
        var autor = autorRequestDTO.toModel();

        if (autorRepository.findByEmail(autor.getEmail()).isPresent()) {
            throw new EmailExistsException(autor.getEmail());
        }

        autor = autorRepository.save(autor);

        return ResponseEntity.ok(AutorResponseDTO.toDTO(autor));
    }
}

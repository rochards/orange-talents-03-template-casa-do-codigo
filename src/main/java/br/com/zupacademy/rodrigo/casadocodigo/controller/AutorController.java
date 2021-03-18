package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.AutorRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.AutorResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.DuplicateEmailValidator;
import br.com.zupacademy.rodrigo.casadocodigo.repository.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorRepository autorRepository;
    private final DuplicateEmailValidator emailValidator;

    public AutorController(AutorRepository autorRepository, DuplicateEmailValidator emailValidator) {
        this.autorRepository = autorRepository;
        this.emailValidator = emailValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> cadastra(@RequestBody @Valid AutorRequestDTO autorRequestDTO) {
        var autor = autorRequestDTO.toModel();

        autor = autorRepository.save(autor);

        return ResponseEntity.ok(AutorResponseDTO.toDTO(autor));
    }
}

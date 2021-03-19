package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.CategoriaRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.CategoriaResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.DuplicateNomeCategoriaValidator;
import br.com.zupacademy.rodrigo.casadocodigo.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final DuplicateNomeCategoriaValidator categoriaValidator;

    public CategoriaController(CategoriaRepository categoriaRepository, DuplicateNomeCategoriaValidator categoriaValidator) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaValidator = categoriaValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(categoriaValidator);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> cadastra(@RequestBody @Valid CategoriaRequestDTO categoriaRequestDTO) {
        var categoria = categoriaRequestDTO.toModel();

        categoria = categoriaRepository.save(categoria);

        return ResponseEntity.ok(CategoriaResponseDTO.toDTO(categoria));
    }
}

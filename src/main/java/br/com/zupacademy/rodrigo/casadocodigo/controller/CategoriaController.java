package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.CategoriaRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.CategoriaResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> cadastra(@RequestBody @Valid CategoriaRequestDTO categoriaRequestDTO) {
        var categoria = categoriaRequestDTO.toModel();

        categoria = categoriaRepository.save(categoria);

        return ResponseEntity.ok(CategoriaResponseDTO.toDTO(categoria));
    }
}

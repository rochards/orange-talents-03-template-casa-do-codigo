package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.LivroRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.LivroResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroController(LivroRepository livroRepository, AutorRepository autorRepository,
        CategoriaRepository categoriaRepository) {

        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastra(@RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        var livro = livroRequestDTO.toModel(autorRepository, categoriaRepository);

        livro = livroRepository.save(livro);

        return ResponseEntity.ok(LivroResponseDTO.toDTO(livro));
    }
}

package br.com.zupacademy.rodrigo.casadocodigo.controller;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.DetalheLivroResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.ListaLivroResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.LivroRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.LivroResponseDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(new LivroResponseDTO(livro));
    }

    @GetMapping
    public ResponseEntity<Page<ListaLivroResponseDTO>> listaTodos(Pageable pageable) {
        var livrosPage = livroRepository.findAll(pageable);

        var listaLivrosPage = livrosPage.map(ListaLivroResponseDTO::new);
        return ResponseEntity.ok(listaLivrosPage);
    }

    @GetMapping("{livroId}")
    public ResponseEntity<DetalheLivroResponseDTO> buscaDetalhes(@PathVariable Integer livroId) {
        var optLivro = livroRepository.findById(livroId);

        return optLivro.map(livro -> ResponseEntity.ok(new DetalheLivroResponseDTO(livro)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

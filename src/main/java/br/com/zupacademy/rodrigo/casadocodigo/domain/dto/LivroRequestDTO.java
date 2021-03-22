package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.*;
import br.com.zupacademy.rodrigo.casadocodigo.exception.type.RegisterNotFoundException;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.ExistsIdentifier;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.NotDuplicate;
import br.com.zupacademy.rodrigo.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public class LivroRequestDTO {

    @NotNull
    @ExistsIdentifier(message = "não há registro de autor com esse id", fieldName = "id", domainClass = Autor.class)
    private Integer autorId;

    @NotNull
    @ExistsIdentifier(message = "não há registro de categoria com esse id", fieldName = "id", domainClass = Estado.class)
    private Integer categoriaId;

    @NotBlank
    @NotDuplicate(message = "esse título já está cadastrado", fieldName = "titulo", domainClass = Livro.class)
    private String titulo;

    @NotBlank @Length(max = 500)
    private String resumo;
    private String sumario;

    @NotNull @Min(20)
    private BigDecimal preco;

    @NotNull @Min(100)
    private Integer numeroDePaginas;

    @NotBlank
    @NotDuplicate(message = "esse isbn já está cadastrado", fieldName = "isbn", domainClass = Livro.class)
    private String isbn;

    @NotNull @Future @JsonFormat(pattern = "dd-MM-yyyy", shape = STRING)
    private LocalDate dataLancamento;

    public LivroRequestDTO(@NotNull Integer autorId, @NotNull Integer categoriaId, @NotBlank String titulo,
        @NotBlank @Length(max = 500) String resumo, String sumario, @NotNull @Min(20) BigDecimal preco,
        @NotNull @Min(100) Integer numeroDePaginas, @NotBlank String isbn,
        @JsonProperty("dataLancamento") @NotNull LocalDate dataLancamento) {

        this.autorId = autorId;
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
    }

    public Livro toModel(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        var autor = buscaAutor(autorRepository);
        var categoria = buscaCategoria(categoriaRepository);

        return new Livro(titulo, resumo, sumario, preco, numeroDePaginas, isbn, dataLancamento, categoria, autor);
    }

    private Autor buscaAutor(AutorRepository autorRepository) {
        return autorRepository.findById(autorId).get();
    }

    private Categoria buscaCategoria(CategoriaRepository categoriaRepository) {
        return categoriaRepository.findById(categoriaId).get();
    }
}

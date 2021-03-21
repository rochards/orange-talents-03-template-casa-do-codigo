package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Livro;
import br.com.zupacademy.rodrigo.casadocodigo.exception.type.RegisterNotFoundException;
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
    private Integer autorId;

    @NotNull
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
        var autor = autorRepository
                .findById(autorId).orElseThrow(() -> new RegisterNotFoundException("autorId", String.format("não há " +
                        "registro de autor com id '%d'", autorId)));
        var categoria = categoriaRepository
                .findById(categoriaId).orElseThrow(() -> new RegisterNotFoundException("categoriaId", String.format(
                        "não há registro de categoria com id '%d'", categoriaId)));

        return new Livro(titulo, resumo, sumario, preco, numeroDePaginas, isbn, dataLancamento, categoria, autor);
    }
}

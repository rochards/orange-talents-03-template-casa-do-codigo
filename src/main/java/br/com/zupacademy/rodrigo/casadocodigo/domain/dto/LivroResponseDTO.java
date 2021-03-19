package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public class LivroResponseDTO {

    private String nomeAutor;
    private String nomeCategoria;
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroDePaginas;
    private String isbn;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataLancamento;

    private LivroResponseDTO(String nomeAutor, String nomeCategoria, String titulo, String resumo, String sumario,
        BigDecimal preco, Integer numeroDePaginas, String isbn, LocalDate dataLancamento) {

        this.nomeAutor = nomeAutor;
        this.nomeCategoria = nomeCategoria;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
    }

    public static LivroResponseDTO toDTO(Livro livro) {
        return new LivroResponseDTO(livro.getAutor().getNome(), livro.getCategoria().getNome(), livro.getTitulo(),
                livro.getResumo(), livro.getSumario(), livro.getPreco(), livro.getNumeroDePaginas(), livro.getIsbn(),
                livro.getDataLancamento());
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
}

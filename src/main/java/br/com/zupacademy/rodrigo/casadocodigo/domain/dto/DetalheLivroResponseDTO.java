package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Autor;
import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalheLivroResponseDTO {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroDePaginas;
    private String isbn;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataLancamento;
    private DetalheAutorLivroResponseDTO autor;

    public DetalheLivroResponseDTO(Livro livro) {

        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataLancamento = livro.getDataLancamento();
        this.autor = new DetalheAutorLivroResponseDTO(livro.getAutor());
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

    public DetalheAutorLivroResponseDTO getAutor() {
        return autor;
    }
}

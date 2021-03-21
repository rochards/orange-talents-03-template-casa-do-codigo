package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public LivroResponseDTO(Livro livro) {

        this.nomeAutor = livro.getAutor().getNome();
        this.nomeCategoria = livro.getCategoria().getNome();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataLancamento = livro.getDataLancamento();
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

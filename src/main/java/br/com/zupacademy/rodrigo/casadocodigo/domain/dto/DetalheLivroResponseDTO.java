package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

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
    private AutorDTO autor;

    public DetalheLivroResponseDTO(String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroDePaginas,
        String isbn, LocalDate dataLancamento, AutorDTO autor) {

        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.autor = autor;
    }

    public static DetalheLivroResponseDTO toDTO(Livro livro) {
        var autor = new AutorDTO(livro.getAutor().getNome(), livro.getAutor().getDescricao());
        return new DetalheLivroResponseDTO(livro.getTitulo(), livro.getResumo(), livro.getSumario(), livro.getPreco(),
                livro.getNumeroDePaginas(), livro.getIsbn(), livro.getDataLancamento(), autor);
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

    public AutorDTO getAutor() {
        return autor;
    }
}

class AutorDTO {

    private String nome;
    private String descricao;

    public AutorDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

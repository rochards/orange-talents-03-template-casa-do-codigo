package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Livro;

public class ListaLivroResponseDTO {

    private Integer id;
    private String nome;

    private ListaLivroResponseDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static ListaLivroResponseDTO toDTO(Livro livro) {
        return new ListaLivroResponseDTO(livro.getId(), livro.getTitulo());
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}

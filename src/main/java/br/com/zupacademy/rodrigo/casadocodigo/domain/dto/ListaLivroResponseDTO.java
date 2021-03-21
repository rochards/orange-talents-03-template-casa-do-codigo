package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Livro;

public class ListaLivroResponseDTO {

    private Integer id;
    private String nome;

    public ListaLivroResponseDTO(Livro livro) {
        this.id = livro.getId();
        this.nome = livro.getTitulo();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}

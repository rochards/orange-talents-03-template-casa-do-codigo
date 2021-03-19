package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Categoria;

public class CategoriaResponseDTO {

    private Integer id;
    private String nome;

    private CategoriaResponseDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static CategoriaResponseDTO toDTO(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}

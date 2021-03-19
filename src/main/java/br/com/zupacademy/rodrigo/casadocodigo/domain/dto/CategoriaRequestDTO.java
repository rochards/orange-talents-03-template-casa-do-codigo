package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequestDTO {

    @NotBlank
    private String nome;

    public Categoria toModel() {
        return new Categoria(nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

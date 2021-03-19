package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Categoria;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.NotDuplicate;

import javax.validation.constraints.NotBlank;

public class CategoriaRequestDTO {

    @NotBlank
    @NotDuplicate(message = "esse nome já está cadastrado", fieldName = "nome", domainClass = Categoria.class)
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

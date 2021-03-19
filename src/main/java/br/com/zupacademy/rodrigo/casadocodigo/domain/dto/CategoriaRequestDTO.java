package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Categoria;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.NotDuplicate;

import javax.validation.constraints.NotBlank;

import static br.com.zupacademy.rodrigo.casadocodigo.exception.validation.CheckField.NOME_CATEGORIA;

public class CategoriaRequestDTO {

    @NotBlank
    @NotDuplicate(message = "esse nome já está cadastrado", value = NOME_CATEGORIA)
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

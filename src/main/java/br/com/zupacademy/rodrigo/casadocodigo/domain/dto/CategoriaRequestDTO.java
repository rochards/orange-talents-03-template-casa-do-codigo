package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Categoria;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.NotDuplicate;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoriaRequestDTO {

    @NotBlank
    @NotDuplicate(message = "esse nome já está cadastrado", fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public CategoriaRequestDTO(@NotBlank @JsonProperty("nome") String nome) {
        // A anotacao @JsonProperty eh necessaria pq o Jackson nao consegue fazer mapeamento de um unico parametro
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(nome);
    }
}

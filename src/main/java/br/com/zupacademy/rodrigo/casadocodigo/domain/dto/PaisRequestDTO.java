package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Pais;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.NotDuplicate;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class PaisRequestDTO {

    @NotBlank
    @NotDuplicate(message = "esse país já está cadastrado", fieldName = "nome", domainClass = Pais.class)
    private String nome;

    public PaisRequestDTO(@NotBlank @JsonProperty("nome") String nome) {
        // A anotacao @JsonProperty eh necessaria pq o Jackson nao consegue fazer mapeamento de um unico parametro
        this.nome = nome;
    }

    public Pais toModel() {
        return new Pais(nome);
    }
}

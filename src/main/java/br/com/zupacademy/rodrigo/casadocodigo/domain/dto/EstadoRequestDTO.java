package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Estado;
import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Pais;
import br.com.zupacademy.rodrigo.casadocodigo.exception.type.DuplicateRegisterException;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.ExistsIdentifier;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequestDTO {

    @NotNull
    @ExistsIdentifier(message = "não há registro de país com esse id", fieldName = "id", domainClass = Pais.class)
    private Integer paisId;

    @NotBlank
    private String nome;

    public EstadoRequestDTO(@NotNull Integer paisId, @NotBlank String nome) {
        this.paisId = paisId;
        this.nome = nome;
    }

    public Estado toModel(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        var pais = buscaPais(paisRepository);

        return new Estado(nome, pais);
    }

    private Pais buscaPais(PaisRepository paisRepository) {
        return paisRepository.findById(paisId).get();
    }

    public Integer getPaisId() {
        return paisId;
    }

    public String getNome() {
        return nome;
    }
}

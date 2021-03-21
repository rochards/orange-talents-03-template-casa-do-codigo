package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Estado;
import br.com.zupacademy.rodrigo.casadocodigo.exception.type.DuplicateRegisterException;
import br.com.zupacademy.rodrigo.casadocodigo.exception.type.RegisterNotFoundException;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequestDTO {

    @NotNull
    private Integer paisId;

    @NotBlank
    private String nome;

    public EstadoRequestDTO(@NotNull Integer paisId, @NotBlank String nome) {
        this.paisId = paisId;
        this.nome = nome;
    }

    public Estado toModel(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        var pais = paisRepository.findById(paisId)
                .orElseThrow(() -> new RegisterNotFoundException("paisId", String.format("não há " +
                        "registro de país com id '%d'", paisId)));

        var optEstado = estadoRepository.findByNomeAndPaisId(nome, paisId);
        if (optEstado.isPresent()) {
            throw new DuplicateRegisterException("nome", String.format("já há um estado '%s' para o país com id " +
                    "'%d'", nome, paisId));
        }
        
        return new Estado(nome, pais);
    }
}

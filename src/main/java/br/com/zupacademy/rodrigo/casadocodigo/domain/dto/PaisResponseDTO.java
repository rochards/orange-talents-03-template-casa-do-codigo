package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Pais;

public class PaisResponseDTO {

    private Integer id;
    private String nome;

    public PaisResponseDTO(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}

package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Estado;

public class EstadoResponseDTO {

    private String nomePais;
    private Integer id;
    private String nome;

    public EstadoResponseDTO(Estado estado) {
        this.nomePais = estado.getPais().getNome();
        this.id = estado.getId();
        this.nome = estado.getNome();
    }

    public String getNomePais() {
        return nomePais;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}

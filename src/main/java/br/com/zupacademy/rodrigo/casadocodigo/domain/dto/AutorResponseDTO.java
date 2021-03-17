package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Autor;

import java.time.OffsetDateTime;

public class AutorResponseDTO {

    private Integer id;
    private String nome;
    private String email;
    private String descricao;
    private OffsetDateTime dataCadastro;

    private AutorResponseDTO(Integer id, String nome, String email, String descricao, OffsetDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    public static AutorResponseDTO toDTO(Autor autor) {
        return new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDescricao(),
                autor.getDataCadastro());
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }
}

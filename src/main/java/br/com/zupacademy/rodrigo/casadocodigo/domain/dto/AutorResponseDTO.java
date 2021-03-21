package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Autor;

import java.time.OffsetDateTime;

public class AutorResponseDTO {

    private Integer id;
    private String nome;
    private String email;
    private String descricao;
    private OffsetDateTime dataCadastro;

    public AutorResponseDTO(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.email = autor.getEmail();
        this.descricao = autor.getDescricao();
        this.dataCadastro = autor.getDataCadastro();
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

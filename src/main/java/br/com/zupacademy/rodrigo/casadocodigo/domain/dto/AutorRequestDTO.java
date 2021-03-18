package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(max = 400)
    private String descricao;

    public AutorRequestDTO(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(nome, email, descricao);
    }

    public String getEmail() {
        return email;
    }
}

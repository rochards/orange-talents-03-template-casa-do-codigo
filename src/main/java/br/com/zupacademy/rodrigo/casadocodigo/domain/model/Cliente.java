package br.com.zupacademy.rodrigo.casadocodigo.domain.model;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobreNome;

    @Column(unique = true, nullable = false)
    private String documento;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String cep;

    @ManyToOne(optional = false)
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @Deprecated
    public Cliente() {
    }

    public Cliente(String email, String nome, String sobreNome, String documento, String endereco, String complemento,
                   String cidade, String telefone, String cep, Pais pais, Estado estado) {

        this.email = email;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }
}

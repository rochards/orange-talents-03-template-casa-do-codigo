package br.com.zupacademy.rodrigo.casadocodigo.domain.dto;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Cliente;
import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Estado;
import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Pais;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.CpfOrCnpj;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.ExistsIdentifier;
import br.com.zupacademy.rodrigo.casadocodigo.exception.validation.NotDuplicate;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.PaisRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteRequestDTO {

    @NotBlank @Email
    @NotDuplicate(message = "esse email já está cadastrado", fieldName = "email", domainClass = Cliente.class)
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobreNome;

    @NotBlank @CpfOrCnpj
    @NotDuplicate(message = "esse documento já está cadastrado", fieldName = "documento", domainClass = Cliente.class)
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @ExistsIdentifier(message = "não há registro de país com esse id", fieldName = "id", domainClass = Pais.class)
    private Integer paisId;

    // lembrando que a anotacao abaixo valida como verdadeiro se o parametro passado for nulo
    @ExistsIdentifier(message = "não há registro de estado com esse id", fieldName = "id", domainClass = Estado.class)
    private Integer estadoId;

    public ClienteRequestDTO(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobreNome,
        @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade,
        @NotBlank String telefone, @NotBlank String cep, @NotNull Integer paisId, Integer estadoId) {

        this.email = email;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.paisId = paisId;
        this.estadoId = estadoId;
    }

    public Cliente toModel(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        var pais = buscaPais(paisRepository);
        var estado = buscaEstado(estadoRepository);

        return new Cliente(email, nome, sobreNome, documento, endereco, complemento, cidade, telefone, cep, pais, estado);
    }

    private Pais buscaPais(PaisRepository paisRepository) {
        return paisRepository.findById(paisId).get();
    }

    private Estado buscaEstado(EstadoRepository estadoRepository) {
        if (estadoId != null) {
            var optEstado = estadoRepository.findById(estadoId);
            return optEstado.orElse(null);
        }
        
        return null;
    }
}

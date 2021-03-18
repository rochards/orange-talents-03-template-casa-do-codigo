package br.com.zupacademy.rodrigo.casadocodigo.exception.type;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String email) {
        super(String.format("Email '%s' já cadastrado!", email));
    }
}

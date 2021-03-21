package br.com.zupacademy.rodrigo.casadocodigo.exception.type;

public class DuplicateRegisterException extends RuntimeException {

    private String fieldName;
    private String message;

    public DuplicateRegisterException(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

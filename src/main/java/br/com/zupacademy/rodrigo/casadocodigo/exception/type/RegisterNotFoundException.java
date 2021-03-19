package br.com.zupacademy.rodrigo.casadocodigo.exception.type;

public class RegisterNotFoundException extends RuntimeException {

    private String fieldName;
    private String message;

    public RegisterNotFoundException(String fieldName, String message) {
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

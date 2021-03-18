package br.com.zupacademy.rodrigo.casadocodigo.exception;

import java.util.ArrayList;
import java.util.List;

public class ExceptionMessage {

    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorMessage> errors = new ArrayList<>();

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorMessage> getErrors() {
        return errors;
    }

    public int getNumberOfErrors() {
        return errors.size();
    }
}


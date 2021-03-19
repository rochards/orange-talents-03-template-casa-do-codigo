package br.com.zupacademy.rodrigo.casadocodigo.exception;

import br.com.zupacademy.rodrigo.casadocodigo.exception.type.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(RegisterNotFoundException.class)
    public ResponseEntity<Object> handleRegisterNotFoundException(RegisterNotFoundException ex) {
        var exceptionMessage = new ExceptionMessage();
        exceptionMessage.getErrors().add(new FieldErrorMessage(ex.getFieldName(), ex.getMessage()));

        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        List<ObjectError> objectErrors = ex.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        var exceptionMessage = buildExceptionMessage(objectErrors,fieldErrors);

        return new ResponseEntity<>(exceptionMessage, headers, status);
    }

    private ExceptionMessage buildExceptionMessage(List<ObjectError> objectErrors, List<FieldError> fieldErrors) {

        var exceptionMessage = new ExceptionMessage();
        objectErrors.forEach(error -> {
            var message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            exceptionMessage.getGlobalErrorMessages().add(message);
        });
        fieldErrors.forEach(error -> {
            var message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            exceptionMessage.getErrors().add(new FieldErrorMessage(error.getField(), message));
        });

        return exceptionMessage;
    }
}

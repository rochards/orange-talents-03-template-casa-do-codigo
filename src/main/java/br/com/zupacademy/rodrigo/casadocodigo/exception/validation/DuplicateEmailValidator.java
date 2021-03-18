package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.AutorRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.AutorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DuplicateEmailValidator implements Validator {

    private final AutorRepository autorRepository;

    public DuplicateEmailValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        AutorRequestDTO autorRequest = (AutorRequestDTO) target;
        var optAutor = autorRepository.findByEmail(autorRequest.getEmail());
        if (optAutor.isPresent()) {
            errors.rejectValue("email", "email.already.registered",String.format("Email '%s' já cadastrado no sistema",
                    autorRequest.getEmail()));
        }
    }
}

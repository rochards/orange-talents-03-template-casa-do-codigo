package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import br.com.zupacademy.rodrigo.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.rodrigo.casadocodigo.repository.CategoriaRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotDuplicateValidator implements ConstraintValidator<NotDuplicate, String> {

    private CheckField checkField;

    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public NotDuplicateValidator(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void initialize(NotDuplicate constraintAnnotation) {
        this.checkField = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (CheckField.EMAIL_AUTOR == checkField) {
            var optAutor = autorRepository.findByEmail(value);
            return optAutor.isEmpty();
        } else {
            var optCategoria = categoriaRepository.findByNome(value);
            return optCategoria.isEmpty();
        }
    }
}

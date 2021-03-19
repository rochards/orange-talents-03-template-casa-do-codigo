package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.CategoriaRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DuplicateNomeCategoriaValidator implements Validator {

    private final CategoriaRepository categoriaRepository;

    public DuplicateNomeCategoriaValidator(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriaRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CategoriaRequestDTO categoriaRequest = (CategoriaRequestDTO) target;
        var optCategoria = categoriaRepository.findByNome(categoriaRequest.getNome());
        if (optCategoria.isPresent()) {
            errors.rejectValue("nome", "nome.already.registered", String.format("Categoria '%s' já cadastrada no " +
                            "sistema", categoriaRequest.getNome()));
        }
    }
}

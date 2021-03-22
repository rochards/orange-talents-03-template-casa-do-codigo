package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.EstadoRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueEstadoPaisValidator implements Validator {

    private final EstadoRepository estadoRepository;

    public UniqueEstadoPaisValidator(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        EstadoRequestDTO estadoRequest = (EstadoRequestDTO) target;
        var optEstado = estadoRepository.findByNomeAndPaisId(estadoRequest.getNome(), estadoRequest.getPaisId());
        if (optEstado.isPresent()) {
            errors.rejectValue("nome", "notunique.estado.pais", String.format("já há um estado '%s' cadastrado para o" +
                    " país de id '%d'", estadoRequest.getNome(), estadoRequest.getPaisId()));
        }
    }
}

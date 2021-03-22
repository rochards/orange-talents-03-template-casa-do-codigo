package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import br.com.zupacademy.rodrigo.casadocodigo.domain.dto.ClienteRequestDTO;
import br.com.zupacademy.rodrigo.casadocodigo.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

    private final EstadoRepository estadoRepository;

    public EstadoPertenceAPaisValidator(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        ClienteRequestDTO clienteRequest = (ClienteRequestDTO) target;

        var paisId = clienteRequest.getPaisId();
        var estadoId = clienteRequest.getEstadoId();

        var listaDeEstados = estadoRepository.findByPaisId(paisId);
        if (!listaDeEstados.isEmpty() && estadoId == null) {
            errors.rejectValue("estadoId", "estadoId.nao.deve.ser.nulo", String.format("há estados cadastrados para " +
                    "paisId '%d'. Você deve fornecer um estadoId não nulo", paisId));

        } else if (estadoId != null) {
            var estado = estadoRepository.findById(estadoId).orElseThrow();
            var optEstado = estadoRepository.findByNomeAndPaisId(estado.getNome(), paisId);

            if (optEstado.isEmpty()) {
                errors.rejectValue("estadoId", "estadoId.nao.pertence.paisId", String.format("o estadoId '%d' não " +
                        "fica no paisId '%d'", estadoId, paisId));
            }
        }
    }
}

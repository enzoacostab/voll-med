package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.model.Consulta;

import java.time.LocalDateTime;

public record DatosRespuestaConsulta(
        Long id,
        Long medicoId,
        Long pacienteId,
        LocalDateTime fecha
) {
        public DatosRespuestaConsulta(Consulta consulta) {
                this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(),
                        consulta.getFecha());
        }
}

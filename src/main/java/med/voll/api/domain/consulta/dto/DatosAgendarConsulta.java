package med.voll.api.domain.consulta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.dto.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long medicoId,
        @NotNull
        Long pacienteId,
        @NotNull
        @Future
        //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fecha,
        Especialidad especialidad
) {
}

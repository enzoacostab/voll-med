package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsulta(
        @NotNull
        Long consultaId,
        @NotNull
        MotivoCancelamiento motivo
) {
}

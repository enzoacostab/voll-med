package med.voll.api.domain.medico.dto;

import med.voll.api.domain.medico.model.Medico;

public record DatosListadoMedico(
        String nombre,
        String email,
        String documento,
        Especialidad Especialidad
) {
    public DatosListadoMedico(Medico medico) {
        this(medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}

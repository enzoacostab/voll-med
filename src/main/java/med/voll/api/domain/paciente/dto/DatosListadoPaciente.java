package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.paciente.model.Paciente;

public record DatosListadoPaciente(
        String nombre,
        String email,
        String documento
) {
    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}

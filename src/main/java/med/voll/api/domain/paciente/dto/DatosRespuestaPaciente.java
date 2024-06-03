package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.direccion.dto.DatosDireccion;
import med.voll.api.domain.paciente.model.Paciente;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        DatosDireccion direccion
) {
    public DatosRespuestaPaciente(Paciente paciente) {
        this(paciente.getId(),
            paciente.getNombre(),
            paciente.getEmail(),
            paciente.getDocumento(),
            paciente.getTelefono(),
            new DatosDireccion(paciente.getDireccion())
        );
    }
}

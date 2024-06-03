package med.voll.api.domain.medico.dto;

import med.voll.api.domain.medico.model.Medico;
import med.voll.api.domain.direccion.dto.DatosDireccion;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        DatosDireccion direccion
) {
    public DatosRespuestaMedico(Medico medico) {
        this(medico.getId(),
            medico.getNombre(),
            medico.getEmail(),
            medico.getDocumento(),
            medico.getTelefono(),
            new DatosDireccion(medico.getDireccion())
        );
    }
}

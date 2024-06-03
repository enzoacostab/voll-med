package med.voll.api.domain.medico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.dto.DatosActualizarMedico;
import med.voll.api.domain.medico.dto.DatosRegistroMedico;
import med.voll.api.domain.medico.dto.Especialidad;
import med.voll.api.domain.direccion.model.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private Boolean activo;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        nombre = datosRegistroMedico.nombre();
        email = datosRegistroMedico.email();
        documento = datosRegistroMedico.documento();
        telefono = datosRegistroMedico.telefono();
        especialidad = datosRegistroMedico.especialidad();
        direccion = new Direccion(datosRegistroMedico.direccion());
        activo = true;
    }

    public void actualizarMedico(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null) {
            nombre = datosActualizarMedico.nombre();
        };
        if (datosActualizarMedico.direccion() != null) {
            direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());
        };
        if (datosActualizarMedico.documento() != null) {
            nombre = datosActualizarMedico.nombre();
        };
    }

    public void desactivarMedico() {
        activo = false;
    }
}

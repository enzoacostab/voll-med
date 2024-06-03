package med.voll.api.domain.paciente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.paciente.dto.DatosActualizarPaciente;
import med.voll.api.domain.direccion.model.Direccion;
import med.voll.api.domain.paciente.dto.DatosRegistroPaciente;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private Boolean activo;
    private String documento;
    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        nombre = datosRegistroPaciente.nombre();
        email = datosRegistroPaciente.email();
        documento = datosRegistroPaciente.documento();
        telefono = datosRegistroPaciente.telefono();
        direccion = new Direccion(datosRegistroPaciente.direccion());
        activo = true;
    }

    public void actualizarPaciente(DatosActualizarPaciente datosActualizarPaciente) {
        if (datosActualizarPaciente.nombre() != null) {
            nombre = datosActualizarPaciente.nombre();
        };
        if (datosActualizarPaciente.direccion() != null) {
            direccion = direccion.actualizarDireccion(datosActualizarPaciente.direccion());
        };
        if (datosActualizarPaciente.telefono() != null) {
            nombre = datosActualizarPaciente.nombre();
        };
    }

    public void desactivarPaciente() {
        activo = false;
    }
}

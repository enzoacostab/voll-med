package med.voll.api.domain.direccion.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.dto.DatosDireccion;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    private String calle;
    private String distrito;
    private String ciudad;
    private String numero;
    private String complemento;

    public Direccion(DatosDireccion datosDireccion) {
        calle = datosDireccion.calle();
        distrito = datosDireccion.distrito();
        ciudad = datosDireccion.ciudad();
        numero = datosDireccion.numero();
        complemento = datosDireccion.complemento();
    }

    public Direccion actualizarDireccion(DatosDireccion datosDireccion) {
        calle = datosDireccion.calle();
        distrito = datosDireccion.distrito();
        ciudad = datosDireccion.ciudad();
        numero = datosDireccion.numero();
        complemento = datosDireccion.complemento();
        return this;
    }
}

package med.voll.api.domain.direccion.dto;

import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.direccion.model.Direccion;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento
) {
        public DatosDireccion(Direccion direccion) {
                this(direccion.getCalle(), direccion.getDistrito(),
                        direccion.getCiudad(), direccion.getNumero(),
                        direccion.getComplemento()
                );
        }
}

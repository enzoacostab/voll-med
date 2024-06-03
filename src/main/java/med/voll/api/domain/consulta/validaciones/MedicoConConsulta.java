package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.dto.DatosAgendarConsulta;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.medicoId() == null) {
            return;
        }
        var medicoConConsulta = consultaRepository
                .existsByMedicoIdAndFecha(datos.pacienteId(), datos.fecha());
        if (medicoConConsulta) {
            throw new ValidationException("No se permite programar más de una consulta en el mismo día para el mismo paciente");
        }
    }
}

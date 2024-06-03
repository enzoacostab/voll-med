package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.dto.DatosAgendarConsulta;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.pacienteId() == null) {
            return;
        }
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteConConsulta = consultaRepository
                .existsByPacienteIdAndFechaBetween(datos.pacienteId(), primerHorario, ultimoHorario);
        if (pacienteConConsulta) {
            throw new ValidationException("No se permite programar más de una consulta en el mismo día para el mismo paciente");
        }
    }
}

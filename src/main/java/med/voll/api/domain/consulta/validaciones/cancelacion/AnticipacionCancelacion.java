package med.voll.api.domain.consulta.validaciones.cancelacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.dto.DatosCancelamientoConsulta;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AnticipacionCancelacion implements ValidadorCancelacionConsultas {
    @Autowired
    ConsultaRepository repository;

    public void validar(DatosCancelamientoConsulta datos) {
        var consulta = repository.getReferenceById(datos.consultaId());
        var ahora = LocalDateTime.now();
        var horaDeConsulta = consulta.getFecha();
        var diferenciaEnHoras = Duration.between(ahora, horaDeConsulta).toHours();
        if (diferenciaEnHoras < 24) {
            throw new ValidationException("Una cita solo se puede cancelar con al menos 24 horas de anticipación");
        }
    }
}

package med.voll.api.domain.consulta.service;

import med.voll.api.domain.consulta.dto.DatosAgendarConsulta;
import med.voll.api.domain.consulta.dto.DatosCancelamientoConsulta;
import med.voll.api.domain.consulta.dto.DatosRespuestaConsulta;
import med.voll.api.domain.consulta.model.Consulta;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.consulta.validaciones.cancelacion.ValidadorCancelacionConsultas;
import med.voll.api.domain.medico.model.Medico;
import med.voll.api.domain.medico.repository.MedicoRepository;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    List<ValidadorDeConsultas> validadores;
    @Autowired
    List<ValidadorCancelacionConsultas> validadoresCancelacion;

    public DatosRespuestaConsulta agendar(DatosAgendarConsulta datos) {
        validadores.forEach(v -> v.validar(datos));
        var paciente = pacienteRepository.findByIdAndActivoTrue(datos.pacienteId());
        if (paciente.isEmpty()) {
            throw new ValidacionDeIntegridad("paciente inexistente o inactivo");
        }
        Medico medico = seleccionarMedico(datos);
        var consulta = new Consulta(paciente.get(), medico, datos.fecha());
        return new DatosRespuestaConsulta(consultaRepository.save(consulta));
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.medicoId() != null) {
            var medico = medicoRepository.findByIdAndActivoTrue(datos.medicoId());
            if (medico.isEmpty()) {
                throw new ValidacionDeIntegridad("medico inexistente o inactivo");
            }
            return medico.get();
        }
        if (datos.especialidad() == null) {
            throw new ValidacionDeIntegridad("debe ingresar la especialidad del medico");
        }
        var medico = medicoRepository.seleccionarMedicoPorEspecialidadYFecha(datos.especialidad(), datos.fecha());
        if (medico.isEmpty()) {
            throw new ValidacionDeIntegridad("no se encontro ningun medico libre para la fecha ingresada");
        }
        return medico.get();
    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.consultaId())) {
            throw new ValidacionDeIntegridad("no existe ninguna consulta con el id proporcionado");
        }
        validadoresCancelacion.forEach(v -> v.validar(datos));
        Consulta consulta = consultaRepository.getReferenceById(datos.consultaId());
        consulta.cancelar(datos.motivo());
    }
}

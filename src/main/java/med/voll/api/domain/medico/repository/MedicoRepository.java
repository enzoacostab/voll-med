package med.voll.api.domain.medico.repository;

import med.voll.api.domain.medico.dto.Especialidad;
import med.voll.api.domain.medico.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    Optional<Medico> findByIdAndActivoTrue(Long id);

    @Query("""
            select m from Medico m
            where m.especialidad = :especialidad
            and m.id not in (select c.medico.id from Consulta c where c.fecha = :fecha)
            and m.activo = true
            order by rand()
            limit 1
            """)
    Optional<Medico> seleccionarMedicoPorEspecialidadYFecha(Especialidad especialidad, LocalDateTime fecha);

}

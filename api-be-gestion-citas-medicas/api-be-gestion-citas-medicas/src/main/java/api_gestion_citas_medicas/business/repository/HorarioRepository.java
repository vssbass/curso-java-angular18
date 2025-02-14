package api_gestion_citas_medicas.business.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import api_gestion_citas_medicas.business.entity.HorarioEntity;

public interface HorarioRepository extends JpaRepository<HorarioEntity, Long> 
{
	@Query("select p from HorarioEntity p where p.fecha = :fecha")
	List<HorarioEntity> findHorariosFecha(@Param("fecha") LocalDate fecha);
	
	@Query("select p from HorarioEntity p where p.hora = :hora and fecha = :fecha")
	HorarioEntity findFechaHora(@Param("hora") LocalTime  hora, @Param("fecha") LocalDate  fecha);
	
	@Modifying
	@Query(value = "update horario h set h.estado = false, id_local = :idlocal, id_cita = :idcita, c_cita = :codcita "
			+ "where "
			+ "h.hora = :hora and fecha = :fecha and h.estado is true", nativeQuery = true)
	void updateHorarioCita(
			@Param("idlocal") Long idlocal, 
			@Param("idcita") Long idcita, 
			@Param("codcita") String codcita,
			@Param("hora") LocalTime hora, 
			@Param("fecha") LocalDate  fecha);
}

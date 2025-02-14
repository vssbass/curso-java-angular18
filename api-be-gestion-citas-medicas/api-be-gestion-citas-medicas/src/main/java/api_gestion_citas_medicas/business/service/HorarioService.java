package api_gestion_citas_medicas.business.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import api_gestion_citas_medicas.business.dto.HorarioDTO;

public interface HorarioService extends GenericService<HorarioDTO> {

	HorarioDTO findFechaHora(LocalTime t, LocalDate d) throws ServiceException;
	
	List<HorarioDTO> findHorariosFecha(HorarioDTO t) throws ServiceException;
}

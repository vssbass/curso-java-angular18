package api_gestion_citas_medicas.business.mapper;

import java.util.List;
import api_gestion_citas_medicas.business.dto.HorarioDTO;
import api_gestion_citas_medicas.business.entity.HorarioEntity;


public interface HorarioMapper {
	HorarioDTO toDTO(HorarioEntity e);
	
	HorarioEntity toEntity(HorarioDTO d);

	List<HorarioDTO> toDTO(List<HorarioEntity> lstE);

	List<HorarioEntity> toEntity(List<HorarioDTO> lstD);
}

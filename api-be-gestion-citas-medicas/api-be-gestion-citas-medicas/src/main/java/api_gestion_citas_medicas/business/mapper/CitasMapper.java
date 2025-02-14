package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import api_gestion_citas_medicas.business.dto.CitasDTO;
import api_gestion_citas_medicas.business.entity.CitasEntity;


public interface CitasMapper {
	CitasDTO toDTO(CitasEntity e);
	
	CitasEntity toEntity(CitasDTO d);

	List<CitasDTO> toDTO(List<CitasEntity> lstE);

	List<CitasEntity> toEntity(List<CitasDTO> lstD);
}

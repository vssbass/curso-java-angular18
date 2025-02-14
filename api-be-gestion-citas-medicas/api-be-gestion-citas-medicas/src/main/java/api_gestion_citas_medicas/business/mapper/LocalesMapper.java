package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import api_gestion_citas_medicas.business.dto.LocalesDTO;
import api_gestion_citas_medicas.business.entity.LocalesEntity;


public interface LocalesMapper {
	
	LocalesDTO toDTO(LocalesEntity e);
	
	LocalesEntity toEntity(LocalesDTO d);

	List<LocalesDTO> toDTO(List<LocalesEntity> lstE);

	List<LocalesEntity> toEntity(List<LocalesDTO> lstD);
}

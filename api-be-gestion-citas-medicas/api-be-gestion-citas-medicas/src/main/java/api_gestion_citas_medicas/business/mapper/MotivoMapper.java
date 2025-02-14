package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import api_gestion_citas_medicas.business.dto.MotivoDTO;
import api_gestion_citas_medicas.business.entity.MotivoEntity;

public interface MotivoMapper {

	MotivoDTO toDTO(MotivoEntity e);
	
	MotivoEntity toEntity(MotivoDTO d);

	List<MotivoDTO> toDTO(List<MotivoEntity> lstE);

	List<MotivoEntity> toEntity(List<MotivoDTO> lstD);
}

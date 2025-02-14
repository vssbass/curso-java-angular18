package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import api_gestion_citas_medicas.business.dto.SubMenuUserVODTO;
import api_gestion_citas_medicas.business.entity.SubMenuUserVOEntity;

public interface SubMenuUserVOMapper {

	SubMenuUserVODTO toDTO(SubMenuUserVOEntity e);
	
	SubMenuUserVOEntity toEntity(SubMenuUserVODTO d);

	List<SubMenuUserVODTO> toDTO(List<SubMenuUserVOEntity> lstE);

	List<SubMenuUserVOEntity> toEntity(List<SubMenuUserVODTO> lstD);
}

package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import api_gestion_citas_medicas.business.dto.MenuUserVODTO;
import api_gestion_citas_medicas.business.entity.MenuUserVOEntity;

public interface MenuUserVOMapper {
	
	MenuUserVODTO toDTO(MenuUserVOEntity e);
	
	MenuUserVOEntity toEntity(MenuUserVODTO d);

	List<MenuUserVODTO> toDTO(List<MenuUserVOEntity> lstE);

	List<MenuUserVOEntity> toEntity(List<MenuUserVODTO> lstD);
}

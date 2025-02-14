package api_gestion_citas_medicas.business.mapper;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api_gestion_citas_medicas.business.dto.MenuUserVODTO;
import api_gestion_citas_medicas.business.entity.MenuUserVOEntity;

@Component
public class MenuUserVOMapperImpl implements MenuUserVOMapper {

	private final ModelMapper modelMapper;
	public MenuUserVOMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@Override
	public MenuUserVODTO toDTO(MenuUserVOEntity e) {
		return  modelMapper.map(e, MenuUserVODTO.class);
	}

	@Override
	public MenuUserVOEntity toEntity(MenuUserVODTO d) {
		return modelMapper.map(d, MenuUserVOEntity.class);
	}

	@Override
	public List<MenuUserVODTO> toDTO(List<MenuUserVOEntity> lstE) {
		return lstE.stream().map(e-> toDTO(e)).toList();
	}

	@Override
	public List<MenuUserVOEntity> toEntity(List<MenuUserVODTO> lstD) {
		return lstD.stream().map(e-> toEntity(e)).toList();	
	}

}

package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api_gestion_citas_medicas.business.dto.SubMenuUserVODTO;
import api_gestion_citas_medicas.business.entity.SubMenuUserVOEntity;

@Component
public class SubMenuUserVOMapperImpl implements SubMenuUserVOMapper {

	private final ModelMapper modelMapper;
	
	public SubMenuUserVOMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@Override
	public SubMenuUserVODTO toDTO(SubMenuUserVOEntity e) {
		return  modelMapper.map(e, SubMenuUserVODTO.class);
	}

	@Override
	public SubMenuUserVOEntity toEntity(SubMenuUserVODTO d) {
		return modelMapper.map(d, SubMenuUserVOEntity.class);
	}

	@Override
	public List<SubMenuUserVODTO> toDTO(List<SubMenuUserVOEntity> lstE) {
		return lstE.stream().map(e-> toDTO(e)).toList();
	}

	@Override
	public List<SubMenuUserVOEntity> toEntity(List<SubMenuUserVODTO> lstD) {
		return lstD.stream().map(e-> toEntity(e)).toList();	
	}

}

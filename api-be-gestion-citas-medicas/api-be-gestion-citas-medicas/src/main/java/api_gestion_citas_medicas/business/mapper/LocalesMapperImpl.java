package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api_gestion_citas_medicas.business.dto.LocalesDTO;
import api_gestion_citas_medicas.business.entity.LocalesEntity;


@Component
public class LocalesMapperImpl implements LocalesMapper {
	
	private final ModelMapper modelMapper;
	
	public LocalesMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public LocalesDTO toDTO(LocalesEntity e) {
	    return  modelMapper.map(e, LocalesDTO.class);
	}
	
	@Override
	public List<LocalesDTO> toDTO(List<LocalesEntity> lstE) {
		return lstE.stream().map(e-> toDTO(e)).toList();
	}
	
	@Override
	public LocalesEntity toEntity(LocalesDTO d) {
		return modelMapper.map(d, LocalesEntity.class);
	}

	@Override
	public List<LocalesEntity> toEntity(List<LocalesDTO> lstD) {
		return lstD.stream().map(e-> toEntity(e)).toList();	
	}

}

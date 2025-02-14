package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api_gestion_citas_medicas.business.dto.MotivoDTO;
import api_gestion_citas_medicas.business.entity.MotivoEntity;

@Component
public class MotivoMapperImpl implements MotivoMapper {

	private final ModelMapper modelMapper;
	
	public MotivoMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	
	}

	@Override
	public MotivoDTO toDTO(MotivoEntity e) {
		MotivoDTO dto = modelMapper.map(e, MotivoDTO.class);
	    return dto;
	}

	@Override
	public MotivoEntity toEntity(MotivoDTO d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MotivoDTO> toDTO(List<MotivoEntity> lstE) {
		return lstE.stream().map(e-> toDTO(e)).toList();
	}

	@Override
	public List<MotivoEntity> toEntity(List<MotivoDTO> lstD) {
		// TODO Auto-generated method stub
		return null;
	}

}

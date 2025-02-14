package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import api_gestion_citas_medicas.business.dto.HorarioDTO;
import api_gestion_citas_medicas.business.entity.HorarioEntity;

@Component
public class HorarioMapperImpl implements HorarioMapper {

	private final ModelMapper modelMapper;
	
	public HorarioMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public HorarioDTO toDTO(HorarioEntity e) {
		HorarioDTO dto = modelMapper.map(e, HorarioDTO.class);
		return dto;
	}

	@Override
	public HorarioEntity toEntity(HorarioDTO d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HorarioDTO> toDTO(List<HorarioEntity> lstE) {
		return lstE.stream().map(e-> toDTO(e)).toList();
	}

	@Override
	public List<HorarioEntity> toEntity(List<HorarioDTO> lstD) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

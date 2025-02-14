package api_gestion_citas_medicas.business.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api_gestion_citas_medicas.business.dto.CitasDTO;
import api_gestion_citas_medicas.business.entity.CitasEntity;

@Component
public class CitasMapperImpl implements CitasMapper {

	private final ModelMapper modelMapper;
	
	public CitasMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public CitasDTO toDTO(CitasEntity e) {
		CitasDTO dto = modelMapper.map(e, CitasDTO.class);
		return dto;
	}

	@Override
	public CitasEntity toEntity(CitasDTO d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CitasDTO> toDTO(List<CitasEntity> lstE) {
		return lstE.stream().map(e-> toDTO(e)).toList();
	}

	@Override
	public List<CitasEntity> toEntity(List<CitasDTO> lstD) {
		// TODO Auto-generated method stub
		return null;
	}

}

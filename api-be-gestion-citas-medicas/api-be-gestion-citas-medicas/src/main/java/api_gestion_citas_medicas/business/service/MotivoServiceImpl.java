package api_gestion_citas_medicas.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import api_gestion_citas_medicas.business.dto.MotivoDTO;
import api_gestion_citas_medicas.business.entity.MotivoEntity;
import api_gestion_citas_medicas.business.mapper.MotivoMapper;
import api_gestion_citas_medicas.business.repository.MotivoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MotivoServiceImpl implements MotivoService {

	public final MotivoRepository motivoRepository;
	public final MotivoMapper motivoMapper;
	
	public MotivoServiceImpl(MotivoRepository motivoRepository, MotivoMapper motivoMapper) {
		this.motivoMapper = motivoMapper;
		this.motivoRepository = motivoRepository;
	}
	
	@Override
	public List<MotivoDTO> findAll() throws ServiceException {
		try {
			return motivoMapper.toDTO(motivoRepository.findAll());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<MotivoDTO> findById(Long id) throws ServiceException {
		try {
			Optional<MotivoEntity> optMotivoEntity = motivoRepository.findById(id);
			if (optMotivoEntity.isEmpty()) {
				return Optional.empty();
			}
			return Optional.ofNullable(motivoMapper.toDTO(optMotivoEntity.get()));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<MotivoDTO> findLikeObject(MotivoDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MotivoDTO save(MotivoDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(MotivoDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MotivoDTO t) throws ServiceException {
		// TODO Auto-generated method stub

	}

}

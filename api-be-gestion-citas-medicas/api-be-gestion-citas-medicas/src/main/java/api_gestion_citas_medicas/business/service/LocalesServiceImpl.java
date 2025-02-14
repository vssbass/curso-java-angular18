package api_gestion_citas_medicas.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import api_gestion_citas_medicas.business.dto.LocalesDTO;
import api_gestion_citas_medicas.business.entity.LocalesEntity;
import api_gestion_citas_medicas.business.mapper.LocalesMapper;
import api_gestion_citas_medicas.business.repository.LocalesRepository;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class LocalesServiceImpl implements LocalesService {

	public final LocalesRepository localesRepository;
	public final LocalesMapper localesMapper;
	
	public LocalesServiceImpl(LocalesRepository localesRepository, LocalesMapper localesMapper) {
		this.localesMapper = localesMapper;
		this.localesRepository = localesRepository;
	}

	@Override
	public List<LocalesDTO> findAll() throws ServiceException {
		try {
			return localesMapper.toDTO(localesRepository.findAll());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Optional<LocalesDTO> findById(Long id) throws ServiceException {
		try {
			/*
			 * ProductoEntity productoEntity = productoRespository.findById(id)
			 * .orElseThrow(() -> new
			 * ServiceException(String.format("No existe producto con el id= %s", id)));
			 */
			Optional<LocalesEntity> optLocalesEntity = localesRepository.findById(id);
			if (optLocalesEntity.isEmpty()) {
				return Optional.empty();
			}
			return Optional.ofNullable(localesMapper.toDTO(optLocalesEntity.get()));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<LocalesDTO> findLikeObject(LocalesDTO localesDto) throws ServiceException {
		try {
			if (isNull(localesDto)) {
				throw new ServiceException("El local es null");
			}
			return localesMapper.toDTO(localesRepository.findLikeNombre("%" + localesDto.getDescripcion() + "%"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean update(LocalesDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(LocalesDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocalesDTO save(LocalesDTO localesDto) throws ServiceException {
		try {
			return localesMapper.toDTO(localesRepository.save(localesMapper.toEntity(localesDto)));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}

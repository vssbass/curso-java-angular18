package api_gestion_citas_medicas.business.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import api_gestion_citas_medicas.business.dto.HorarioDTO;
import api_gestion_citas_medicas.business.mapper.HorarioMapper;
import api_gestion_citas_medicas.business.repository.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService {

	public final HorarioMapper horarioMapper;
	public final HorarioRepository horarioRepository;
	
	public HorarioServiceImpl(HorarioMapper horarioMapper, HorarioRepository horarioRepository) {
		this.horarioMapper = horarioMapper;
		this.horarioRepository = horarioRepository;
	}
	
	@Override
	public List<HorarioDTO> findAll() throws ServiceException {
		try {
			return horarioMapper.toDTO(horarioRepository.findAll());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<HorarioDTO> findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<HorarioDTO> findLikeObject(HorarioDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HorarioDTO save(HorarioDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(HorarioDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(HorarioDTO t) throws ServiceException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public HorarioDTO findFechaHora(LocalTime t, LocalDate d) throws ServiceException 
	{
		try {
			
			return horarioMapper.toDTO(horarioRepository.findFechaHora(t, d));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<HorarioDTO> findHorariosFecha(HorarioDTO t) throws ServiceException {
		try {
			
			return horarioMapper.toDTO(horarioRepository.findHorariosFecha(t.getFecha()));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

}

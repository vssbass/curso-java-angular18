package api_gestion_citas_medicas.business.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api_gestion_citas_medicas.business.dto.CitasDTO;
import api_gestion_citas_medicas.business.dto.HorarioDTO;
import api_gestion_citas_medicas.business.entity.CitasEntity;
import api_gestion_citas_medicas.business.entity.LocalesEntity;
import api_gestion_citas_medicas.business.entity.MotivoEntity;
import api_gestion_citas_medicas.business.mapper.CitasMapper;
import api_gestion_citas_medicas.business.mapper.HorarioMapper;
import api_gestion_citas_medicas.business.mapper.LocalesMapper;
import api_gestion_citas_medicas.business.repository.CitasRepository;
import api_gestion_citas_medicas.business.repository.HorarioRepository;
import api_gestion_citas_medicas.business.repository.LocalesRepository;
import api_gestion_citas_medicas.business.repository.MotivoRepository;
import static java.util.Objects.isNull;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class CitasServiceImpl implements CitasService {

	public final CitasRepository citasRepository;
	public final CitasMapper citasMapper;
	public final LocalesMapper localesMapper;
	public final HorarioMapper horarioMapper;
	
	private final LocalesRepository localesRepository;
	private final HorarioRepository horarioRepository;
	private final MotivoRepository motivoRepository;
	
	public CitasServiceImpl(
			CitasRepository citasRepository, 
			CitasMapper citasMapper,
			LocalesRepository localesRepository,
			MotivoRepository motivoRepository,
			LocalesMapper localesMapper, 
			HorarioMapper horarioMapper,
			HorarioRepository horarioRepository
			) 
	{
		this.citasRepository = citasRepository;
		this.localesRepository = localesRepository;
		this.motivoRepository = motivoRepository;
		this.horarioRepository = horarioRepository;
		this.citasMapper = citasMapper;
		this.horarioMapper = horarioMapper;
		this.localesMapper = localesMapper;
	}

	@Override
	public List<CitasDTO> findAll() throws ServiceException {
		try {
			return citasMapper.toDTO(citasRepository.findLikeEmail("%"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<CitasDTO> findById(Long id) throws ServiceException {
		try {
			Optional<CitasEntity> optCitasEntity = citasRepository.findById(id);
			if (optCitasEntity.isEmpty()) {
				return Optional.empty();
			}
			return Optional.ofNullable(citasMapper.toDTO(optCitasEntity.get()));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<CitasDTO> findLikeObject(CitasDTO citasDto) throws ServiceException {
		try {
			if (isNull(citasDto)) {
				throw new ServiceException("El local es null");
			}
			return citasMapper.toDTO(citasRepository.findLikeEmail("%" + citasDto.getEmail() + "%"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean update(CitasDTO t) throws ServiceException {
		try 
		{
			Optional<CitasEntity> optionalCita = citasRepository.findById(t.getId());
			
			Optional<LocalesEntity> local = localesRepository.findById(t.getLocales().getId()); 
			Optional<MotivoEntity> motivo = motivoRepository.findById(t.getMotivo().getId());
			  
			LocalDate fecha = t.getFechacita();
			LocalTime hora = t.getHoracita();
				
			  CitasEntity updCita = optionalCita.get();
			  //updCita.setCodigo(t.getCodigo()); 
			  updCita.setDocumento(t.getDocumento());
			  updCita.setNombres(t.getNombres()); 
			  updCita.setEmail(t.getEmail());
			  updCita.setCelular(t.getCelular()); 
			  updCita.setEstado(t.isEstado());
			  updCita.setObservaciones(t.getObservaciones());
			  updCita.setHoracita(t.getHoracita());
			  updCita.setFechacita(t.getFechacita()); 
			  updCita.setLocales(local.get());
			  updCita.setMotivo(motivo.get()); 
			  updCita.setUser_upd(t.getCreador());
			  updCita.setFechaUpd(LocalDateTime.now());
		  
		  //guardo en mi tabla citas
		  CitasDTO resultCita = citasMapper.toDTO(citasRepository.save(updCita));
		  //luego extraigo el codigo de mi cita creada para actualizar en la tabla horarios
		  Long idlocal = resultCita.getLocales().getId();
		  Long idcita = resultCita.getId();
		  String codcita = resultCita.getCodigo();

		  horarioRepository.updateHorarioCita(idlocal,idcita,codcita,hora, fecha);
		  
		  return true;
			 
		} 
		catch (Exception e) 
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(CitasDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CitasDTO save(CitasDTO t) throws ServiceException 
	{
		try 
		{
			Optional<LocalesEntity> local = localesRepository.findById(t.getLocales().getId()); 
			Optional<MotivoEntity> motivo = motivoRepository.findById(t.getMotivo().getId());
			  
			LocalDate fecha = t.getFechacita();
			LocalTime hora = t.getHoracita();
			
			HorarioDTO horario = horarioMapper.toDTO(horarioRepository.findFechaHora(hora, fecha));
			if(!horario.isEstado()) {
				throw new ServiceException("La Fecha y horario ya se encuentran registrados");
			}
			
			String codigoCita = generaCodigoCita();
		  
		  CitasEntity nuevaCita = new CitasEntity();
		  nuevaCita.setCodigo(codigoCita); 
		  nuevaCita.setDocumento(t.getDocumento());
		  nuevaCita.setNombres(t.getNombres()); 
		  nuevaCita.setEmail(t.getEmail());
		  nuevaCita.setCelular(t.getCelular()); 
		  nuevaCita.setEstado(t.isEstado());
		  nuevaCita.setObservaciones(t.getObservaciones());
		  nuevaCita.setHoracita(t.getHoracita());
		  nuevaCita.setFechacita(t.getFechacita()); 
		  nuevaCita.setLocales(local.get());
		  nuevaCita.setMotivo(motivo.get()); 
		  nuevaCita.setCreador(t.getCreador());

		  
		  
		  //guardo en mi tabla citas
		  CitasDTO resultCita = citasMapper.toDTO(citasRepository.save(nuevaCita));
		  //luego extraigo el codigo de mi cita creada para actualizar en la tabla horarios
		  Long idlocal = resultCita.getLocales().getId();
		  Long idcita = resultCita.getId();
		  String codcita = resultCita.getCodigo();

		  horarioRepository.updateHorarioCita(idlocal,idcita,codcita,hora, fecha);
		  
		  return resultCita;
			 
		} 
		catch (Exception e) 
		{
			throw new ServiceException(e);
		}
	}
	
	private String generaCodigoCita() {
		String codigoCita = citasRepository.getCodigoCita();
		  if (codigoCita == null) {
			  return "C00001"; // Para la primera cita 
		   }

	    int numero = Integer.parseInt(codigoCita.substring(1));
	    DecimalFormat formato = new DecimalFormat("00000");
	    return "C" + formato.format(numero + 1);
	}

}

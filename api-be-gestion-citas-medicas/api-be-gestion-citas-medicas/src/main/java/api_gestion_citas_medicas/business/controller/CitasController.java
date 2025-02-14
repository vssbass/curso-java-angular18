package api_gestion_citas_medicas.business.controller;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api_gestion_citas_medicas.business.dto.CitasDTO;
import api_gestion_citas_medicas.business.service.CitasService;
import api_gestion_citas_medicas.business.service.HorarioService;
import api_gestion_citas_medicas.business.service.LocalesService;
import api_gestion_citas_medicas.business.service.MotivoService;
import api_gestion_citas_medicas.business.service.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/citas")
public class CitasController {

	private final CitasService citasService;

	
	public CitasController(CitasService citasService,LocalesService localesService, 
			MotivoService motivoService, HorarioService horarioService) {
		this.citasService = citasService;

	}
	
	@GetMapping("getCitas")
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<CitasDTO> lstCitasDto = citasService.findAll();
			if (lstCitasDto.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstCitasDto);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/getCita/{id}")
	ResponseEntity<?> findById(@PathVariable(value = "id",required = true) Long id) {

		Map<String, Object> res = new HashMap<>();

		try {
			
			if (isNull(id) || id<=0) {
				res.put("error", String.format("El id=%s ingresado no es válido",id));
				return ResponseEntity.badRequest().body(res);
			}
			
			Optional<CitasDTO> optCitasDTO = citasService.findById(id);
			if (optCitasDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optCitasDTO);

		} catch (ServiceException e) {
			e.printStackTrace();
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/getCita/find-by-email")
	ResponseEntity<?> findLikeObject(@RequestParam(value = "email", required = false, defaultValue = "") String email) {

		Map<String, Object> res = new HashMap<>();

		try {
			CitasDTO citasDTO= new CitasDTO();
			citasDTO.setEmail(email);
			List<CitasDTO> lstCitasDTO = citasService.findLikeObject(citasDTO);
			if (lstCitasDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstCitasDTO);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PostMapping("/register-cita")
	ResponseEntity<?> save(@RequestBody  CitasDTO citaDTO) throws ServiceException 
	{
		Map<String, Object> res = new HashMap<>();
		
		try {
			CitasDTO resCitaDto = citasService.save(citaDTO);
			if (isNull(resCitaDto)) {
				res.put("error", "Error al registrar el cliente");
				return ResponseEntity.badRequest().build();
			}
			return new ResponseEntity<>(resCitaDto, HttpStatus.CREATED);

		} catch (ServiceException e) {
			res.put("error", "Error interno "+ e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PutMapping("/update-cita")
	ResponseEntity<?> update(@RequestBody CitasDTO citasDTO) {

		log.info("CitasDTO => {}",citasDTO);
		
		Map<String, Object> res = new HashMap<>();

		try {
			boolean resp = citasService.update(citasDTO);
			if (!resp) {
				res.put("error", "Error al actualizar cita");
				return ResponseEntity.badRequest().build();
			}
			res.put("message", "Exito al actualiar cita");
			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}


}

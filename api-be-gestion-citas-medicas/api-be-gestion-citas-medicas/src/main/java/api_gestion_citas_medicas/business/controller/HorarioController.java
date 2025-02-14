package api_gestion_citas_medicas.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api_gestion_citas_medicas.business.dto.HorarioDTO;
import api_gestion_citas_medicas.business.service.HorarioService;
import api_gestion_citas_medicas.business.service.ServiceException;


@RestController
@RequestMapping("/api/horarios")
public class HorarioController {
	private final HorarioService horarioService;
	
	public HorarioController(HorarioService horarioService) {
		this.horarioService = horarioService;
	}
	
	@GetMapping("getHorarios")
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<HorarioDTO> lstHorarioDto = horarioService.findAll();
			if (lstHorarioDto.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstHorarioDto);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PostMapping("horario-custom")
	ResponseEntity<?> HorarioCustom(@RequestBody  HorarioDTO horarioDTO) throws ServiceException 
	{ 
		
		Map<String, Object> res = new HashMap<>();

		try {
			HorarioDTO ListHorario = horarioService.findFechaHora(horarioDTO.getHora(), horarioDTO.getFecha());

			return ResponseEntity.ok(ListHorario);

		} catch (ServiceException e) {
			res.put("error", "Error interno "+ e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PostMapping("horario-fecha")
	ResponseEntity<?> HorarioFecha(@RequestBody  HorarioDTO horarioDTO) throws ServiceException 
	{ 
		
		Map<String, Object> res = new HashMap<>();

		try {
			List<HorarioDTO> ListHorario = horarioService.findHorariosFecha(horarioDTO);

			return ResponseEntity.ok(ListHorario);

		} catch (ServiceException e) {
			res.put("error", "Error interno "+ e.getMessage());
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PutMapping("actualizar-horario")
	ResponseEntity<?> update(@RequestBody  HorarioDTO horarioDTO) throws ServiceException{
		
		Map<String, Object> res = new HashMap<>();

		try {
			
			if (horarioService.update(horarioDTO)) {
				res.put("error", "Error al actualizar horario");
				return ResponseEntity.badRequest().build();
			}
			res.put("message", "Exito al actualiar el horario");
			return new ResponseEntity<>(res, HttpStatus.OK);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}

}

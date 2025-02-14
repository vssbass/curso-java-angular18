package api_gestion_citas_medicas.business.controller;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api_gestion_citas_medicas.business.dto.MotivoDTO;
import api_gestion_citas_medicas.business.service.MotivoService;
import api_gestion_citas_medicas.business.service.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/motivos")
public class MotivosController {
private final MotivoService motivosService;
	
	public MotivosController(MotivoService motivosService) {
		this.motivosService = motivosService;
	}
	
	@GetMapping("/getMotivos")
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<MotivoDTO> lstMotivosDto = motivosService.findAll();
			if (lstMotivosDto.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstMotivosDto);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/getMotivo/{id}")
	ResponseEntity<?> findById(@PathVariable(value = "id",required = true) Long id) {

		Map<String, Object> res = new HashMap<>();

		try {
			
			if (isNull(id) || id<=0) {
				res.put("error", String.format("El id=%s ingresado no es válido",id));
				return ResponseEntity.badRequest().body(res);
			}
			
			Optional<MotivoDTO> optMotivosDTO = motivosService.findById(id);
			if (optMotivosDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optMotivosDTO);

		} catch (ServiceException e) {
			e.printStackTrace();
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
}

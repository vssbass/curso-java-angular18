package api_gestion_citas_medicas.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api_gestion_citas_medicas.business.dto.LocalesDTO;
import api_gestion_citas_medicas.business.service.LocalesService;
import api_gestion_citas_medicas.business.service.ServiceException;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api/locales")
public class LocalesController {

	private final LocalesService localesService;
	
	public LocalesController(LocalesService localesService) {
		this.localesService = localesService;
	}
	
	@GetMapping("/getLocales")
	ResponseEntity<?> findAll() {

		Map<String, Object> res = new HashMap<>();

		try {
			List<LocalesDTO> lstLocalesDto = localesService.findAll();
			//log.info("Lista de productos: {}", lstProductoDto);
			if (lstLocalesDto.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstLocalesDto);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/getLocal/{id}")
	ResponseEntity<?> findById(@PathVariable(value =  "id",required = true) Long id) {

		Map<String, Object> res = new HashMap<>();

		try {
			
			if (isNull(id) || id<=0) {
				res.put("error", String.format("El id=%s ingresado no es válido",id));
				return ResponseEntity.badRequest().body(res);
			}
			
			Optional<LocalesDTO> optLocalesDTO = localesService.findById(id);
			if (optLocalesDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optLocalesDTO);

		} catch (ServiceException e) {
			e.printStackTrace();
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@GetMapping("/getLocal/find-by-nombre")
	ResponseEntity<?> findLikeObject(@RequestParam(value =  "descripcion", required = false, defaultValue = "") String descripcion) {

		Map<String, Object> res = new HashMap<>();

		try {
			LocalesDTO localesDTO= new LocalesDTO();
			localesDTO.setDescripcion(descripcion);
			List<LocalesDTO> lstLocalesDTO = localesService.findLikeObject(localesDTO/*PersonalDTO.builder().nombre(nombre).build()*/);
			if (lstLocalesDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstLocalesDTO);

		} catch (ServiceException e) {
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
}

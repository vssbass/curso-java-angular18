package api_gestion_citas_medicas.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api_gestion_citas_medicas.business.dto.MenuUserVODTO;
import api_gestion_citas_medicas.business.service.MenuUserService;
import api_gestion_citas_medicas.business.service.ServiceException;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuUserController {
	
	private final MenuUserService MenuUser;
	
	public MenuUserController(MenuUserService MenuUser) {
		this.MenuUser = MenuUser;
	}
	
	@PostMapping("list-user-menus-vo")
	ResponseEntity<?> MenuSubMenuUser(@RequestBody MenuUserVODTO menuDTO) { 

		Map<String, Object> res = new HashMap<>();

		try {
	
			List<Map<String, Object>> menuResponse = MenuUser.getMenuSubMenuUSerVO(menuDTO);
			
			if (isNull(menuResponse)) {
				res.put("error", "Error al listar menu de usuario");
				return ResponseEntity.badRequest().build();
			}

			return new ResponseEntity<>(menuResponse, HttpStatus.OK);

		} catch (ServiceException e) {
			e.printStackTrace();
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
	
	@PostMapping("list-user-menu-vo")
	ResponseEntity<?> MenuUser(@RequestBody MenuUserVODTO menuDTO) { 

		Map<String, Object> res = new HashMap<>();

		try {
			List<MenuUserVODTO> lstMenu = MenuUser.MenuUserVO(menuDTO);
			
			
			if (isNull(lstMenu)) {
				res.put("error", "Error al listar menu de usuario");
				return ResponseEntity.badRequest().build();
			}

			return new ResponseEntity<>(lstMenu, HttpStatus.OK);

		} catch (ServiceException e) {
			e.printStackTrace();
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
}

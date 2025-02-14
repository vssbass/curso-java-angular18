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
import api_gestion_citas_medicas.business.dto.SubMenuUserVODTO;
import api_gestion_citas_medicas.business.service.ServiceException;
import api_gestion_citas_medicas.business.service.SubMenuUserService;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api/submenu")
public class SubMenuUserController {
	
	private final SubMenuUserService SubMenuUser;
	
	public SubMenuUserController(SubMenuUserService SubMenuUser) {
		this.SubMenuUser = SubMenuUser;
	}
	
	@PostMapping("list-user-submenu-vo")
	ResponseEntity<?> SubMenuUser(@RequestBody SubMenuUserVODTO menuDTO) { 

		Map<String, Object> res = new HashMap<>();

		try {
			List<SubMenuUserVODTO> lstSubMenu = SubMenuUser.SubMenuUserVO(menuDTO);
			if (isNull(lstSubMenu)) {
				res.put("error", "Error al listar submenu de usuario");
				return ResponseEntity.badRequest().build();
			}

			return new ResponseEntity<>(lstSubMenu, HttpStatus.OK);

		} catch (ServiceException e) {
			e.printStackTrace();
			res.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(res);
		}
	}
}

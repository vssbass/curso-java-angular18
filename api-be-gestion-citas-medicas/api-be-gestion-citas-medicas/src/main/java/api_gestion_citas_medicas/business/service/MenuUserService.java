package api_gestion_citas_medicas.business.service;


import java.util.List;
import java.util.Map;

import api_gestion_citas_medicas.business.dto.MenuUserVODTO;

public interface MenuUserService extends GenericService<MenuUserVODTO>{

	List<MenuUserVODTO> MenuUserVO( MenuUserVODTO menu) throws ServiceException;
	
	List<Map<String, Object>> getMenuSubMenuUSerVO(MenuUserVODTO menu) throws ServiceException;

}

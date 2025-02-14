package api_gestion_citas_medicas.business.service;


import java.util.List;
import api_gestion_citas_medicas.business.dto.SubMenuUserVODTO;

public interface SubMenuUserService extends GenericService<SubMenuUserVODTO>{

	List<SubMenuUserVODTO> SubMenuUserVO( SubMenuUserVODTO submenu) throws ServiceException;

}

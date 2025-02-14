package api_gestion_citas_medicas.business.service;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import api_gestion_citas_medicas.business.dto.MenuUserVODTO;
import api_gestion_citas_medicas.business.dto.SubMenuUserVODTO;
import api_gestion_citas_medicas.business.mapper.MenuUserVOMapper;
import api_gestion_citas_medicas.business.mapper.SubMenuUserVOMapper;
import api_gestion_citas_medicas.business.repository.VO.MenuUserVORepository;
import api_gestion_citas_medicas.business.repository.VO.SubMenuUserVORepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuUserServiceImpl implements MenuUserService {

	public final MenuUserVORepository menuUserVoRepo;
	public final MenuUserVOMapper menuUserMapper ;
	
	public final SubMenuUserVORepository submenuUserVoRepo;
	public final SubMenuUserVOMapper submenuUserMapper;
	
	public MenuUserServiceImpl(MenuUserVORepository menuUserVoRepo, MenuUserVOMapper menuUserMapper, SubMenuUserVORepository submenuUserVoRepo,
			SubMenuUserVOMapper submenuUserMapper) {
		this.menuUserMapper = menuUserMapper;
		this.menuUserVoRepo = menuUserVoRepo;
		this.submenuUserVoRepo = submenuUserVoRepo;
		this.submenuUserMapper = submenuUserMapper;
	}

	public List<Map<String, Object>> getMenuSubMenuUSerVO(MenuUserVODTO menuParam)  throws ServiceException
	{
		try 
		{
			if (isNull(menuParam)) {
				throw new ServiceException("Parametros de usuario nulos");
			}
			Long USER_ID = menuParam.getUser_id();
			Long AUTHORITY_ID = menuParam.getAuthority_id();
			
			List<MenuUserVODTO> lstMenu = menuUserMapper.toDTO(menuUserVoRepo.lstMenuUser(USER_ID, AUTHORITY_ID).stream()
			        .distinct()
			        .collect(Collectors.toList()));
	  
			List<SubMenuUserVODTO> listSubMenu = submenuUserMapper.toDTO(submenuUserVoRepo.ListVOSubMenuUser(USER_ID, AUTHORITY_ID));
	
	        List<Map<String, Object>> response = new ArrayList<>();
	
	        for (MenuUserVODTO menu : lstMenu) {
	 
	            Map<String, Object> menuMap = new HashMap<>();
	            menuMap.put("id_menu", menu.getId_menu());
	            menuMap.put("menu_name", menu.getMenu_name());
	
	 
	            List<Map<String, Object>> subMenuList = listSubMenu.stream()
	                .filter(sub -> sub.getId_menu().equals(menu.getId_menu()))
	                .map(sub -> {
	                    Map<String, Object> subMenuMap = new HashMap<>();
	                    subMenuMap.put("id_sub_menu", sub.getId_sub_menu());
	                    subMenuMap.put("sub_menu_name", sub.getSub_menu_name());
	                    subMenuMap.put("url_menu", sub.getUrl_menu());
	                    return subMenuMap;
	                })
	                .collect(Collectors.toList());
	
	            menuMap.put("submenus", subMenuList);
	            response.add(menuMap);
	        }
	
	        return response;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		
    }
	
	@Override
	public List<MenuUserVODTO> MenuUserVO(MenuUserVODTO menuParam) throws ServiceException {
		try {
			if (isNull(menuParam)) {
				throw new ServiceException("Parametros de usuario nulos");
			}
			Long USER_ID = menuParam.getUser_id();
			Long AUTHORITY_ID = menuParam.getAuthority_id();
			
			List<MenuUserVODTO> lstMenu = menuUserMapper.toDTO(menuUserVoRepo.lstMenuUser(USER_ID, AUTHORITY_ID).stream()
			        .distinct()  // Elimina duplicados antes de mapear
			        .collect(Collectors.toList()));
			System.out.println("Datos antes del mapeo: " + lstMenu);

			Map<String, Object> res = new HashMap<>();
			res.put("lstMenu", lstMenu);
		    return lstMenu; 

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	


	@Override
	public List<MenuUserVODTO> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<MenuUserVODTO> findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


	@Override
	public List<MenuUserVODTO> findLikeObject(MenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public MenuUserVODTO save(MenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean update(MenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(MenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	
}

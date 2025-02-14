package api_gestion_citas_medicas.business.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import api_gestion_citas_medicas.business.dto.SubMenuUserVODTO;
import api_gestion_citas_medicas.business.mapper.SubMenuUserVOMapper;
import api_gestion_citas_medicas.business.repository.VO.SubMenuUserVORepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubMenuUserServiceImpl implements SubMenuUserService {

	public final SubMenuUserVORepository submenuUserVoRepo;
	public final SubMenuUserVOMapper submenuUserMapper ;
	
	public SubMenuUserServiceImpl(SubMenuUserVORepository submenuUserVoRepo, SubMenuUserVOMapper submenuUserMapper) 
	{
		this.submenuUserMapper = submenuUserMapper;
		this.submenuUserVoRepo = submenuUserVoRepo;
	}
	
	@Override
	public List<SubMenuUserVODTO> SubMenuUserVO(SubMenuUserVODTO menuParam) throws ServiceException {
		try {
			if (isNull(menuParam)) {
				throw new ServiceException("Parametros de usuario nulos");
			}
			Long USER_ID = menuParam.getUser_id();
			Long AUTHORITY_ID = menuParam.getAuthority_id();
			
			
			List<SubMenuUserVODTO> listSubMenu = submenuUserMapper.toDTO(submenuUserVoRepo.ListVOSubMenuUser(USER_ID, AUTHORITY_ID));

		    return listSubMenu;

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}



	@Override
	public List<SubMenuUserVODTO> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Optional<SubMenuUserVODTO> findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



	@Override
	public List<SubMenuUserVODTO> findLikeObject(SubMenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public SubMenuUserVODTO save(SubMenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean update(SubMenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete(SubMenuUserVODTO t) throws ServiceException {
		// TODO Auto-generated method stub
		
	}




	
}

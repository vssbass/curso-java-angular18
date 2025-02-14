package api_gestion_citas_medicas.business.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
	
	List<T> findAll() throws ServiceException;
	
	Optional<T> findById(Long id) throws ServiceException;
	
	List<T> findLikeObject(T t) throws ServiceException;
	
	T save(T t)throws ServiceException;
	
	Boolean update(T t)throws ServiceException;
	
	void delete(T t)throws ServiceException;
	
	
}

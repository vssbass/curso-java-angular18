package api_gestion_citas_medicas.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api_gestion_citas_medicas.business.entity.LocalesEntity;

@Repository
public interface LocalesRepository extends JpaRepository<LocalesEntity, Long> {

	@Query("select p from LocalesEntity p where upper(p.descripcion) like upper(:descripcion) and p.estado=true")
	List<LocalesEntity> findLikeNombre(@Param("descripcion") String descripcion);
	
//	@Modifying
//	@Query(value = "update locales set estado='0' where producto_id=:id", nativeQuery = true)
//	void delete(@Param("id") Long id);
}

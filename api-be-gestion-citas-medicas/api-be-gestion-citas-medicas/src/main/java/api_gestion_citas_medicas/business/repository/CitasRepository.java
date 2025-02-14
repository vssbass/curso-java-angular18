package api_gestion_citas_medicas.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api_gestion_citas_medicas.business.entity.CitasEntity;

public interface CitasRepository extends JpaRepository<CitasEntity, Long> {

	//JPQL
	@Query("select p from CitasEntity p where upper(p.email) like upper(:email) and p.estado is true")
	List<CitasEntity> findLikeEmail(@Param("email") String email);
	
	@Query("SELECT c.codigo FROM CitasEntity c ORDER BY c.id DESC LIMIT 1")
    String getCodigoCita();
}

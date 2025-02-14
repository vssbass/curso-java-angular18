package api_gestion_citas_medicas.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import api_gestion_citas_medicas.business.entity.MotivoEntity;

@Repository
public interface MotivoRepository extends JpaRepository<MotivoEntity, Long>{
	@Query("select p from MotivoEntity p where upper(p.descripcion) like upper(:descripcion) and p.estado=true")
	List<MotivoEntity> findLikeNombre(@Param("descripcion") String descripcion);
}

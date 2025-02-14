package api_gestion_citas_medicas.business.repository.VO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api_gestion_citas_medicas.business.entity.SubMenuUserVOEntity;

public interface SubMenuUserVORepository extends JpaRepository<SubMenuUserVOEntity, Long> {

	@Query(value = "SELECT * FROM VW_MENU_USER WHERE "
			+ "USER_ID = :USER_ID AND AUTHORITY_ID = :AUTHORITY_ID", nativeQuery = true)
	List<SubMenuUserVOEntity> ListVOSubMenuUser(@Param("USER_ID") Long USER_ID, @Param("AUTHORITY_ID") Long AUTHORITY_ID);
}

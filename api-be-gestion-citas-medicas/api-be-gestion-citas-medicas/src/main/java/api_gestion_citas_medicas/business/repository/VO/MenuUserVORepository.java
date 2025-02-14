package api_gestion_citas_medicas.business.repository.VO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import api_gestion_citas_medicas.business.entity.MenuUserVOEntity;

public interface MenuUserVORepository extends JpaRepository <MenuUserVOEntity, Long> {
	
	@Query(value ="    SELECT DISTINCT\r\n"
			+ "		T2.USER_ID AS USER_ID,\r\n"
			+ "        T2.AUTHORITY_ID AS AUTHORITY_ID,\r\n"
			+ "        T1.ID AS ID_MENU,\r\n"
			+ "        T1.MENU_DESCRIPCION AS MENU_NAME\r\n"
			+ "    FROM \r\n"
			+ "        menu_modulos T1\r\n"
			+ "    INNER JOIN \r\n"
			+ "        seg_user_authority_menu T2 ON T1.ID = T2.ID_MENU\r\n"
			+ "    WHERE  \r\n"
			+ "        T2.USER_ID = :p_user_id \r\n"
			+ "        AND T2.AUTHORITY_ID = :p_authority_id", nativeQuery = true)
	List<MenuUserVOEntity> lstMenuUser(@Param("p_user_id") Long USER_ID, @Param("p_authority_id") Long AUTHORITY_ID);	
	
}

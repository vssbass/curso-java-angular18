package api_gestion_citas_medicas.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "SubMenuUserVOEntity")
@Table(name = "VW_MENU_USER")
public class SubMenuUserVOEntity {
	
	@Column(name ="USER_ID", nullable = false)
	private Long user_id;
	
	@Column(name ="USER_NAME", nullable = false)
	private String user_name;
	
	@Column(name ="AUTHORITY_ID", nullable = true)
	private Long authority_id;
	
	@Column(name ="AUTHORITY_NAME", nullable = false)
	private String authority_name;
	
	@Column(name ="ID_MENU", nullable = false)
	private Long id_menu;

	@Column(name ="MENU_NAME", nullable = false)
	private String menu_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID_SUB_MENU", nullable = true)
	private Long id_sub_menu;
	
	@Column(name ="SUB_MENU_NAME", nullable = false)
	private String sub_menu_name;
	
	@Column(name ="URL_MENU", nullable = false)
	private String url_menu;
	
}

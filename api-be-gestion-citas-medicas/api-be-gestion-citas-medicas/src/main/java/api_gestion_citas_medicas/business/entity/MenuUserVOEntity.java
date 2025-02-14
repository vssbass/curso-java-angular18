package api_gestion_citas_medicas.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "MenuUserVOEntity")
@Table(name = "seg_user_authority_menu")
public class MenuUserVOEntity {
	
	
	@Column(name ="USER_ID", nullable = false)
	private Long user_id;
	
	@Column(name ="AUTHORITY_ID", nullable = false)
	private Long authority_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID_MENU", nullable = false)
	private Long id_menu;

	@Column(name ="MENU_NAME", nullable = false)
	private String menu_name;
	
}

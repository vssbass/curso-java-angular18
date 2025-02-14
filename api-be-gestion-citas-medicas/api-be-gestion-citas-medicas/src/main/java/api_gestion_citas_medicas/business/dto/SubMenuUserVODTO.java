package api_gestion_citas_medicas.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubMenuUserVODTO {

	private Long user_id;
	
	private String user_name;
	
	private Long authority_id;
	
	private String authority_name;
	
	private Long id_menu;

	private String menu_name;
	
	private Long id_sub_menu;
	
	private String sub_menu_name;
	
	private String url_menu;
	
}

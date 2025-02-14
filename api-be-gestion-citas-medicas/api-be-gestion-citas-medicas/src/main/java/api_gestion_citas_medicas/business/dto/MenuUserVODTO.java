package api_gestion_citas_medicas.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuUserVODTO {
	
	private Long user_id;
	
	private Long authority_id;
	
	private Long id_menu;

	private String menu_name;
	
}

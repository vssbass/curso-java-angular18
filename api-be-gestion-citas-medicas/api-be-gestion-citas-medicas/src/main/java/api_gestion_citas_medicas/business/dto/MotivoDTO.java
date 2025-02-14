package api_gestion_citas_medicas.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MotivoDTO {
	
	private Long id;
	
	private String descripcion;
	private boolean estado;

}

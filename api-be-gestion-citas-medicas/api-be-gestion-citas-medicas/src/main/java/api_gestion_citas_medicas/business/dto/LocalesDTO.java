package api_gestion_citas_medicas.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocalesDTO {

	private Long id;
	
	private String descripcion;

	private String correo;
	
	private String direccion;
	
	private String celular;
	
	private String creador;
	
	private boolean estado;

}

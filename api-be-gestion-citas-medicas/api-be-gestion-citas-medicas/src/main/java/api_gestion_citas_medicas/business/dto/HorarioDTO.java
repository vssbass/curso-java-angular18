package api_gestion_citas_medicas.business.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HorarioDTO {
	
	private Long id;

	private LocalTime  hora;
	
	private LocalDate  fecha;
	
	private boolean estado;
	
	private String creador;
}

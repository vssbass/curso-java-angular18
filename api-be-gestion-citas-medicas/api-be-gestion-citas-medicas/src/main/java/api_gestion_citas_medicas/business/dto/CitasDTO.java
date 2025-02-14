package api_gestion_citas_medicas.business.dto;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//import com.fasterxml.jackson.annotation.JsonIgnore;

//import api_gestion_citas_medicas.business.entity.MotivoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CitasDTO {
	
	private Long id;
	
	private String codigo;
	
	private String documento;
	
	private String nombres;
	
	private String email;
	
	private String celular;
	
	private boolean estado;
	
	private String observaciones;
	
	private LocalTime horacita;
	
	private LocalDate fechacita;

	//@JsonIgnore
	private LocalesDTO locales;
	
	//@JsonIgnore
	private  MotivoDTO motivo;
	
	private String creador;
	
	private String user_upd;
	
	private LocalDateTime fechaUpd;
}

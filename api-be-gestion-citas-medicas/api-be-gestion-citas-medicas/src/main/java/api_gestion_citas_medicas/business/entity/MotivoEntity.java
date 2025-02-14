package api_gestion_citas_medicas.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "MotivoEntity")
@Table(name = "MOTIVO")
public class MotivoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID", nullable = false)
	private Long id;

	@NotNull(message = "La descripcion del motivo es requerido")
	@Column(name ="DESCRIPCION", nullable = false)
	private String descripcion;
	
	@Column(name ="ESTADO", nullable = true)
	private boolean estado;
	
	@NotNull(message = "El usuario debe estar logueado")
	@Column(name ="USER_CREA", nullable = false)
	private String creador;
		
}

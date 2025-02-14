package api_gestion_citas_medicas.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "LocalesEntity")
@Table(name = "LOCALES")
public class LocalesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID", nullable = false)
	private Long id;
	
	@NotNull(message = "Debe ingresar el nombre del local")
	@Column(name ="NOM_LOCAL", nullable = false)
	private String descripcion;
	
	@NotNull(message = "Debe ingresar el correo del local")
	@Column(name ="CORREO", nullable = false)
	private String correo;
	
	@Column(name ="DIRECCION", nullable = true)
	private String direccion;
	
	@Column(name ="CELULAR", nullable = true)
	private String celular;
	
	@NotNull(message = "El usuario debe estar logueado")
	@Column(name ="USER_CREA", nullable = false)
	private String creador;
	
	@Column(name ="ESTADO", nullable = true)
	private boolean estado;
	
//	@PrePersist
//	void setEstado() {
//		this.estado="1";
//	}

}

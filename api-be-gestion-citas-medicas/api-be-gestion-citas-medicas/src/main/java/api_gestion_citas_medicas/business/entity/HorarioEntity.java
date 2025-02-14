package api_gestion_citas_medicas.business.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "HorarioEntity")
@Table(name = "HORARIO")
public class HorarioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID", nullable = false)
	private Long id;

	@NotNull(message = "La Hora es requerido")
	@Column(name ="HORA", nullable = false)
	private LocalTime  hora;
	
	@NotNull(message = "La fecha es requerido")
	@Column(name ="FECHA", nullable = false)
	private LocalDate  fecha;
	
	@Column(name ="ESTADO", nullable = true)
	private boolean estado;
	
	@NotNull(message = "El usuario debe estar logueado")
	@Column(name ="USER_CREA", nullable = false)
	private String creador;
}

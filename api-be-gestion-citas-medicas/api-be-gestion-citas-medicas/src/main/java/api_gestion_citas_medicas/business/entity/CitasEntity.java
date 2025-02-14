package api_gestion_citas_medicas.business.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "CitasEntity")
@Table(name = "CITAS")
public class CitasEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID", nullable = false)
	private Long id;
	
	//@NotNull(message = "El precio debe ser positivo")
	@Column(name ="C_CITA", nullable = false)
	private String codigo;
	
	@NotNull(message = "Debe ingresar su numero de documento Ruc o Dni")
	@Column(name ="DNIRUC_CLIENTE", nullable = false)
	private String documento;
	
	@Column(name ="NOMBRES_CLIENTE", nullable = true)
	private String nombres;
	
	@Column(name ="EMAIL_CLIENTE", nullable = true)
	private String email;
	
	@Column(name ="CELULAR_CLIENTE", nullable = true)
	private String celular;
	
	@Column(name ="ESTADO", nullable = true)
	private boolean estado;
	
	@Column(name ="OBSERVACIONES", nullable = true)
	private String observaciones;
	
	@Column(name ="HORA_CITA", nullable = true)
	private LocalTime horacita;
	
	@Column(name ="FECHA_CITA", nullable = true)
	private LocalDate fechacita;
	
	//@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_LOCAL", nullable = false)
	private  LocalesEntity locales;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_MOTIVO", nullable = false)
	private  MotivoEntity motivo;
	
	@NotNull(message = "El usuario debe estar logueado")
	@Column(name ="USER_CREA", nullable = false)
	private String creador;
	
	//@NotNull(message = "El usuario actualizador debe estar logueado")
	@Column(name ="USER_UPD", nullable = true)
	private String user_upd;
	
	@Column(name ="FEC_UPD", nullable = true)
	private LocalDateTime fechaUpd;
	
}

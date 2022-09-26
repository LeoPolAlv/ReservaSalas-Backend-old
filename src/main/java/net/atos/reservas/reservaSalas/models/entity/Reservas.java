package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reservas") // , uniqueConstraints=@UniqueConstraint(columnNames={"id_user",
							// "roomreference"}))
@Getter @Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Reservas implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idreserve;
	
	/*
	 * Esto sirve para el control de operacion concurrentes en BD.
	 * El bloqueo optimista utiliza la comparación de la suma de comprobación de la versión 
	 * de los datos para garantizar que esta actualización sea la más reciente, 
	 * de lo contrario fallará. Con un error OptimisticLockException
	 */
	@Version
	private long version;  

	//Indica si una reserva esta activa
	private boolean activa;
	
	private Date fechaReserva;
	
	private Date fechaHasta;
	
	private String titulo;
	
	private String descripcion;
	
	@OneToMany(mappedBy = "reserved", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "reserve-alerts")
	private Set<Alert> alerts;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_user")
	@JsonBackReference(value = "owner-reserves")
	private User owner;

	@ManyToOne(optional = false)
	@JoinColumn(name = "roomReference")
	@JsonBackReference(value = "room-reserves")
	private Room room;

	/*@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "owner-reserves")
	private Set<TramosReservas> tramosReserva;*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

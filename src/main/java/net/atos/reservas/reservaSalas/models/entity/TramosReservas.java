package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tramos_reserva")
@Getter @Setter
public class TramosReservas implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTramoReserva;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaReserva;

	@ManyToOne(optional = false)
	@JoinColumn(name = "FK_Reserva")
	@JsonBackReference(value = "owner-reserves")
	private Reservas reserva;

	@ManyToOne(optional = false)
	@JoinColumn(name = "FK_Tramo")
	@JsonBackReference(value = "owner-tramo")
	private TramosHoras tramosHoras;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

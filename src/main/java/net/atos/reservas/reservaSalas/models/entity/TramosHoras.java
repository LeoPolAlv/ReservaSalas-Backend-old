/*
 * ANULAMOS ESTA TABLA EL TRAMO DE HORAS VA EN LA TABLA DE RESRVAS
 */

/*package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tramo_horas")
@Getter @Setter
public class TramosHoras implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtramo;

	@Column(name = "tramo_horario")
	private String tramos;

	@OneToMany(mappedBy = "tramosHoras", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "owner-tramo")
	private Set<TramosReservas> tramosReserva;

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

//}

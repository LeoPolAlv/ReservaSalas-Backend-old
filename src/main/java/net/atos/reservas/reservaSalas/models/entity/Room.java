package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "room")
@Getter @Setter
public class Room implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idroom;

	@Column(name = "roomname", nullable = false)
	private String roomName;

	private Integer capacity;

	// ***************************************
	// Join otras tablas
	// ********************************

	@ManyToOne(optional = false)
	@JoinColumn(name = "FK_office")
	@JsonBackReference(value = "office-room")
	private Oficinas office;

	@OneToMany(mappedBy = "room")
	@JsonManagedReference(value = "room-roomEquipment")
	//@JsonProperty(access = Access.WRITE_ONLY)
	private List<RoomEquipment> equipment;

	@OneToMany(mappedBy = "room")
	@JsonManagedReference(value = "room-reserves")
	//@JsonProperty(access = Access.WRITE_ONLY)
	private List<Reservas> reserves;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

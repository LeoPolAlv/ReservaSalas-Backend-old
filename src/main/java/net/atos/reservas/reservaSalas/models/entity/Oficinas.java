package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "oficinas") //, indexes = { @Index(name = "officename_inx", columnList = "officename", unique = true) })
@NoArgsConstructor
@Getter @Setter
public class Oficinas implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idoffice;

	private String officename;
	
	//Direccion donde esta ubicada la oficina
	private String direccion;
	
	//Localidad ubicacion oficina
	private String localidad;
	
	//CP postal Oficina
	private String codPostal;
	
	//Provincia de la localidad de la oficcina
	private String provincia;
	
	//Coordenas de la ubicacion de la oficina
	// ---- Longitud
	private String longitud;
	//---- Longitud
	private String latitud;	

	@OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "office-room")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Room> rooms;

	@OneToMany(mappedBy = "office")
	@JsonManagedReference(value = "office-owner")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<User> users;

	@ManyToOne
	@JoinColumn(name = "FK_country")
	@JsonBackReference(value = "country-office")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Pais country;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

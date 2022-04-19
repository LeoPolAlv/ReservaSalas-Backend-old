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

	@OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "office-room")
	private Set<Room> rooms;

	@OneToMany(mappedBy = "office")
	@JsonManagedReference(value = "office-owner")
	private Set<User> users;

	@ManyToOne
	@JoinColumn(name = "FK_country")
	@JsonBackReference(value = "country-office")
	private Pais country;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

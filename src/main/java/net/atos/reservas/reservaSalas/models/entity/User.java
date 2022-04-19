package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.Set;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@Column(name = "das_user", unique = true)
	private String dasUser;

	@Column(unique = true)
	private String email;

	private String password;

	//@Enumerated(EnumType.STRING)
	private String rol;

	private boolean msgreserve;

	private boolean msgalert;

	private boolean estadoUser;

	@OneToMany(mappedBy = "owner")
	@JsonManagedReference(value = "owner-reserves")
	private Set<Reservas> reserves;

	@OneToMany(mappedBy = "owner")
	@JsonManagedReference(value = "owner-alerts")
	private Set<Alert> alerts;

	@ManyToOne(optional = true)
	@JoinColumn(name = "officereference")
	@JsonBackReference(value = "office-owner")
	private Oficinas office;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

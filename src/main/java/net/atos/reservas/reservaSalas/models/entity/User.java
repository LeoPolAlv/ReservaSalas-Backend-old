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
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Version
	private Long version;

	@Column(name = "das_user", unique = true)
	private String dasUser;

	@Column(unique = true)
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)// Para no enviar este campo en la Response
	private String password;

	//@Enumerated(EnumType.STRING)
	private String rol;

	private boolean msgreserve;

	private boolean msgalert;

	private boolean estadoUser;

	@OneToMany(mappedBy = "owner")
	@JsonManagedReference(value = "owner-reserves")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Reservas> reserves;

	@OneToMany(mappedBy = "owner")
	@JsonManagedReference(value = "owner-alerts")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Alert> alerts;

	@ManyToOne(optional = true)
	@JoinColumn(name = "officereference")
	@JsonBackReference(value = "office-owner")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Oficinas office;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

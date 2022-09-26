package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pais")
@Getter @Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Pais implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPais;
	
	@Version
	@Column(columnDefinition = "long DEFAULT 0", nullable = false)
	private Long version = 0L;

	private String countryName;

	@OneToMany(mappedBy = "country")
	@JsonManagedReference(value = "country-office")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Oficinas> offices;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
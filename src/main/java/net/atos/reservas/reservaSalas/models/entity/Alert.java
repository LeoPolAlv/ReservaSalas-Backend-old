package net.atos.reservas.reservaSalas.models.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alert")
@Getter @Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Alert implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idalert;
	
	@Version
	private Long version;

	@ManyToOne(optional = false)
	@JoinColumn(name = "das")
	@JsonBackReference(value = "owner-alerts")
	private User owner;

	@ManyToOne(optional = false)
	@JoinColumn(name = "reserveReference")
	@JsonBackReference(value = "reserve-alerts")
	private Reservas reserved;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

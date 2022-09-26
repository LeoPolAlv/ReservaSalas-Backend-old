package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "equipamientos", indexes = @Index(columnList = "codigo", name = "equipment_inx_codigo", unique = true), uniqueConstraints = @UniqueConstraint(columnNames = {
		"codigo", "nombre" }))
@Getter @Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Equipamientos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idequipment;

	@Version
	private Long version;
	
	private String codigo;

	private String nombre;

	private String description;

	@OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "equipment-roomEquipment")
	private List<RoomEquipment> roomsEquipment;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

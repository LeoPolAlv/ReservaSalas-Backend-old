package net.atos.reservas.reservaSalas.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "room_equipment", uniqueConstraints = @UniqueConstraint(columnNames = { "FKequipment", "FKroom" }))
@Getter @Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class RoomEquipment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idroomeq;
	
	@Version
	private Long version;

	@ManyToOne(optional = false)
	@JoinColumn(name = "FKequipment")
	@JsonBackReference(value = "equipment-roomEquipment")
	private Equipamientos equipment;

	@ManyToOne(optional = false)
	@JoinColumn(name = "FKroom")
	@JsonBackReference(value = "room-roomEquipment")
	private Room room;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

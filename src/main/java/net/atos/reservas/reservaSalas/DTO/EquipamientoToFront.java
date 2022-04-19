package net.atos.reservas.reservaSalas.DTO;

import java.util.List;

import lombok.Data;
import net.atos.reservas.reservaSalas.models.entity.RoomEquipment;

@Data
public class EquipamientoToFront {

	private String codigo;
	private String nombre;
	private String description;
	private List<RoomEquipment> roomsEquipment;
}

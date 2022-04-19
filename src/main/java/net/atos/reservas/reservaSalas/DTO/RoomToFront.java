package net.atos.reservas.reservaSalas.DTO;

import java.util.List;

import lombok.Data;
import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.RoomEquipment;

@Data
public class RoomToFront {

	private String roomName;
	private Integer capacity;
	private String oficina;
	private List<RoomEquipment> equipamientos;
	private List<Reservas> reservas;

}

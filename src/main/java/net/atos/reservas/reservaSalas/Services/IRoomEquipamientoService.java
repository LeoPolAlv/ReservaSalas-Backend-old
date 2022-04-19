package net.atos.reservas.reservaSalas.Services;

import java.util.List;

import net.atos.reservas.reservaSalas.models.entity.Equipamientos;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.RoomEquipment;

public interface IRoomEquipamientoService {

	public RoomEquipment newEquipoSala(Room room, Equipamientos equipo);

	public void borroEquipoSala(RoomEquipment roomEquipo);

	public RoomEquipment findById(Integer idRoomEquip);

	public List<RoomEquipment> findByRoom(Room room);
}

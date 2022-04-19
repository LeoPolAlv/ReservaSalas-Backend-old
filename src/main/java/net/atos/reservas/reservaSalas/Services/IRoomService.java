package net.atos.reservas.reservaSalas.Services;

import java.util.List;

import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Room;

public interface IRoomService {

	public List<Room> findAllRooms();

	public Room findRoom(String nombreSala);

	public List<Room> findRoomsOffice(Oficinas oficina);

	public Room loadRoom(Room sala);

	public void deleteRoom(Room sala);
}

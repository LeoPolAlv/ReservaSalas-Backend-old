package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Room;

@Repository
public interface IRoomDAO extends JpaRepository<Room, Integer> {

	public Room findByRoomName(String roomName);

	public List<Room> findByOffice(Oficinas oficina);

}

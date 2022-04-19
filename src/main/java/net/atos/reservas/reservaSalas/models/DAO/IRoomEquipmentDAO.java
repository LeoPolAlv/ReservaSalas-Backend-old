package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Equipamientos;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.RoomEquipment;

@Repository
public interface IRoomEquipmentDAO extends JpaRepository<RoomEquipment, Integer> {

	public RoomEquipment findByIdroomeq(Integer idroomeq);

	public List<RoomEquipment> findByRoom(Room room);

	public List<RoomEquipment> findByEquipment(Equipamientos equipment);
}

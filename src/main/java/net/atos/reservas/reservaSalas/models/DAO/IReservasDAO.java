package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;

public interface IReservasDAO extends JpaRepository<Reservas, Long> {

	public Reservas findByIdreserve(Long idReserve);
	
	public List<Reservas> findByRoom(Room room);
	
	public List<Reservas> findByActivaTrue();

}

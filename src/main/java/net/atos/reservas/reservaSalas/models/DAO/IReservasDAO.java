package net.atos.reservas.reservaSalas.models.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;

public interface IReservasDAO extends JpaRepository<Reservas, Long> {

	public Reservas findByIdreserve(Long idReserve);
	
	public List<Reservas> findByRoom(Room room);
	
	public List<Reservas> findByActivaTrue();
	
	@Modifying
	@Query("UPDATE Reservas SET fechaReserva= ?1, fechaHasta= ?2 WHERE idreserve = ?3")
	public void actualizarFechaReserva( Date fechaReserva, Date fechaHasta, Long id);

}

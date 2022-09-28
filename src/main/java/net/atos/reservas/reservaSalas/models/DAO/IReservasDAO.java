package net.atos.reservas.reservaSalas.models.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;

@Repository
public interface IReservasDAO extends JpaRepository<Reservas, Long> {

	public Reservas findByIdreserve(Long idReserve);
	
	public List<Reservas> findByRoom(Room room);
	
	public List<Reservas> findByActivaTrueOrderByIdreserveAsc();
	
	@Modifying
	@Query("UPDATE Reservas SET fechaReserva= ?1, fechaHasta= ?2 WHERE idreserve = ?3")
	@Transactional
	public void actualizarFechaReserva( Date fechaReserva, Date fechaHasta, Long id);
	
	@Modifying
	@Query("UPDATE Reservas r SET r.activa=0 WHERE r.idreserve = ?1")
	@Transactional
	public void actualizoReservaActiva(Long id);

}

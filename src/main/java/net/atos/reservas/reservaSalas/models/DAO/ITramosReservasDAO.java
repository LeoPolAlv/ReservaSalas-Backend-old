package net.atos.reservas.reservaSalas.models.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.TramosHoras;
import net.atos.reservas.reservaSalas.models.entity.TramosReservas;


@Repository
public interface ITramosReservasDAO extends JpaRepository<TramosReservas, Long> {

	public List<TramosReservas> findByReserva(Reservas reserva);

	@Query("SELECT tr.tramosHoras  FROM TramosReservas tr WHERE tr.reserva = ?1 "
			+ "AND tr.fechaReserva = ?2")
	public List<TramosHoras> findTramosOcupados(Reservas reserva, Date fechaReser);
}

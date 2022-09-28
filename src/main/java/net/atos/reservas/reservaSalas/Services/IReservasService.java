package net.atos.reservas.reservaSalas.Services;

import java.util.Date;
import java.util.List;

import net.atos.reservas.reservaSalas.DTO.ReservaRequest;
import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.User;

public interface IReservasService {
	
	public Reservas buscoReserva(Long idreserva);
	
	public List<Reservas> buscoReservasSala(Room sala);

	public Reservas nuevaReserva(Room room, User usuario, ReservaRequest reservaRequest);

	public void borroReserva(Long idReserva);
	
	public Reservas modificoFechasReserva(Date fechaReserva, Date fechaHasta, Long id);
}

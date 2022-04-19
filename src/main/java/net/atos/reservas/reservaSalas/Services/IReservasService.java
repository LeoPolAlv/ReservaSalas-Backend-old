package net.atos.reservas.reservaSalas.Services;


import java.util.List;

import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.User;

public interface IReservasService {

	public Reservas nuevaReserva(Room room, User usuario);

	public void borroReserva(Reservas reserva);

	public Reservas buscoReserva(Long idreserva);
	
	public List<Reservas> buscoReservasSala(Room sala);
}

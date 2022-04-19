package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.IReservasService;
import net.atos.reservas.reservaSalas.models.DAO.IReservasDAO;
import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.User;

@Service
public class ReservasServiceImpl implements IReservasService {

	@Autowired
	IReservasDAO reservaDao;
	
	@Override
	@Transactional
	public Reservas nuevaReserva(Room room, User usuario) {
		// TODO Auto-generated method stub
		Reservas nuevareserva = new Reservas();

		nuevareserva.setRoom(room);
		nuevareserva.setActiva(true);
		nuevareserva.setOwner(usuario);
		nuevareserva.setAlerts(null);

		return reservaDao.save(nuevareserva);
	}

	@Override
	@Transactional
	public void borroReserva(Reservas reserva) {
		reservaDao.delete(reserva);
	}

	@Override
	@Transactional(readOnly = true)
	public Reservas buscoReserva(Long idreserva) {
		return reservaDao.findByIdreserve(idreserva);
	}

	@Override
	public List<Reservas> buscoReservasSala(Room sala) {
		return reservaDao.findByRoom(sala);
	}

}

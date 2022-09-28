package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.DTO.ReservaRequest;
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
	//@Transactional(readOnly = true)
	public Reservas buscoReserva(Long idreserva) {
		return reservaDao.findByIdreserve(idreserva);
	}

	@Override
	public List<Reservas> buscoReservasSala(Room sala) {
		return reservaDao.findByRoom(sala);
	}
	
	@Override
	@Transactional
	public Reservas nuevaReserva(Room room, User usuario, ReservaRequest reservaRequest) {
		// TODO Auto-generated method stub
		Reservas nuevareserva = new Reservas();

		nuevareserva.setRoom(room);
		nuevareserva.setActiva(true);
		nuevareserva.setOwner(usuario);
		nuevareserva.setFechaReserva(reservaRequest.getFechaReserva());
		nuevareserva.setFechaHasta(reservaRequest.getFechaHasta());
		nuevareserva.setTitulo(reservaRequest.getTitulo());
		nuevareserva.setDescripcion(reservaRequest.getDescripcion());
		nuevareserva.setAlerts(null);

		return reservaDao.save(nuevareserva);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public void borroReserva(Long idReserva) {
		Reservas reserva = reservaDao.findByIdreserve(idReserva);
		reservaDao.delete(reserva);
	}

	@Override
	@Transactional
	public Reservas modificoFechasReserva(Date fechaReserva, Date fechaHasta, Long idReserva) {
			Reservas reserva = reservaDao.findByIdreserve(idReserva);
			reservaDao.actualizarFechaReserva(fechaReserva, fechaHasta, reserva.getIdreserve());
			// Retorna la reserva modificada
			return reserva = reservaDao.findByIdreserve(idReserva);
		
	}

}

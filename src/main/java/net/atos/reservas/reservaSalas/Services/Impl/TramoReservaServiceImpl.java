/*
 * ANULADO SERVICIO
 */
/*package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.ITramoReservaService;
import net.atos.reservas.reservaSalas.models.DAO.ITramosHorasDAO;
import net.atos.reservas.reservaSalas.models.DAO.ITramosReservasDAO;
import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.TramosHoras;
import net.atos.reservas.reservaSalas.models.entity.TramosReservas;

@Service
public class TramoReservaServiceImpl implements ITramoReservaService {

	@Autowired
	ITramosReservasDAO tramosReserva;
	
	@Autowired
	ITramosHorasDAO tramosHoras;
	
	@Override
	@Transactional
	public void nuevoTramo(Date fechaReserva, TramosHoras tramo, Reservas reserva) {
		TramosReservas tramoReserva = new TramosReservas();

		tramoReserva.setFechaReserva(fechaReserva);
		tramoReserva.setReserva(reserva);
		tramoReserva.setTramosHoras(tramo);

		tramosReserva.save(tramoReserva);

	}

	@Override
	@Transactional(readOnly = true)
	public List<TramosReservas> findTramos(Reservas reserva) {
		return tramosReserva.findByReserva(reserva);
	}

	/*@Override
	@Transactional(readOnly = true)
	public List<TramosHoras> buscoTramosFecha(Date fechaABuscar) {
		return tramosReserva.findTramosOcupados(fechaABuscar);
	}*/

	
	/*@Override
	@Transactional(readOnly = true)
	public List<TramosHoras> buscoTramosLibres(List<Integer> tramos) {
		return tramosHoras.findByIdtramoNotIn(tramos);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TramosHoras> allTramosHoras() {
		return tramosHoras.findAll();
	}

	@Override
	public List<TramosHoras> buscoTramosPorFecha(Reservas reserva, Date fecha) {
		return tramosReserva.findTramosOcupados(reserva, fecha);
	}

}*/

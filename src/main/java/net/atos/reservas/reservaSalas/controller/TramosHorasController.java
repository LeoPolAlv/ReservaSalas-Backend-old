package net.atos.reservas.reservaSalas.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.atos.reservas.reservaSalas.Services.IReservasService;
import net.atos.reservas.reservaSalas.Services.IRoomService;
import net.atos.reservas.reservaSalas.Services.ITramoReservaService;
import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.TramosHoras;

@RestController
@RequestMapping(path = "/tramoshoras")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
public class TramosHorasController {

	private final static Logger logger = LoggerFactory.getLogger(TramosHorasController.class);
	
	@Autowired
	IReservasService reservasService;
	
	@Autowired
	IRoomService roomService;
	
	@Autowired
	ITramoReservaService tramosReserva;

	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/free/{sala}/{fechaReser}")
	public ResponseEntity<?> buscoTramosFecha(@PathVariable String sala, @PathVariable Date fechaReser)
			throws ParseException {
		logger.info("Sala que nos envian a consultar: " + sala);
		logger.info("Fecha que nos envian a consultar: " + fechaReser);
		List<TramosHoras> tramosReservados = new ArrayList<TramosHoras>();
		List<Integer> tramReserAux = new ArrayList<Integer>();
		
		// primero buscamos la sala para ver que reservas tiene
		Room salAux = roomService.findRoom(sala);
		
		logger.info("Sala econtrada: " + salAux);
		
		// Busco las reservas de la sala
		List<Reservas> reservaAux = reservasService.buscoReservasSala(salAux);
		//logger.info("Reservas que tenemos por sala: " + reservaAux);
		if(!reservaAux.isEmpty()) {
			
			reservaAux.forEach( reserva -> {
				logger.info("reserva tratada: " + reserva.getIdreserve());
				//Con esto saco los tramos ocupados de la sala para esa reserva.
				tramosReservados.addAll(tramosReserva.buscoTramosPorFecha(reserva, fechaReser));
			});
			logger.info("tramos reservados : " + tramosReservados);
		}	
		tramosReservados.forEach(tramo -> {
			logger.info("Tramos reservado: " + tramo.getIdtramo());
			tramReserAux.add(tramo.getIdtramo());
		});
		if (!tramosReservados.isEmpty()) {
			//tramosReservados.forEach(tramoR -> {
			//	tramReserAux.add(tramoR.getIdtramo());
			//});
			return new ResponseEntity<List<TramosHoras>>(tramosReserva.buscoTramosLibres(tramReserAux), HttpStatus.OK);
		} else {
			logger.info("Enviamos todos los tramos a la salida.");
			return new ResponseEntity<List<TramosHoras>>(tramosReserva.allTramosHoras(), HttpStatus.OK);
		}
		
	}

}

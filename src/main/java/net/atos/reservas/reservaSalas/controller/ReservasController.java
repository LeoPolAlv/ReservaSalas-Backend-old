package net.atos.reservas.reservaSalas.controller;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.atos.reservas.reservaSalas.DTO.PutFechasReservas;
import net.atos.reservas.reservaSalas.DTO.ReservaRequest;
import net.atos.reservas.reservaSalas.DTO.ReservaToFront;
import net.atos.reservas.reservaSalas.Services.IReservasService;
import net.atos.reservas.reservaSalas.Services.IRoomService;
//import net.atos.reservas.reservaSalas.Services.ITramoReservaService;
import net.atos.reservas.reservaSalas.Services.IUserService;
//import net.atos.reservas.reservaSalas.helpers.HelperClass;
//import net.atos.reservas.reservaSalas.models.DAO.ITramosHorasDAO;
import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.Room;
//import net.atos.reservas.reservaSalas.models.entity.TramosReservas;
import net.atos.reservas.reservaSalas.models.entity.User;

@RestController
@RequestMapping(path = "/reserva")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE, RequestMethod.PUT})
//@PreAuthorize("hasAuthority('USER') OR hasAuthority('ADMIN')")
public class ReservasController {

	private final static Logger logger = LoggerFactory.getLogger(ReservasController.class);

	@Autowired
	IReservasService reservaService;

	@Autowired
	IRoomService roomService;

	@Autowired
	IUserService userService;

	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@PostMapping(path = "/new")
	public ResponseEntity<?> NuevaReserva(@RequestBody ReservaRequest reservaRequest) {
		logger.info("ReservaRequest: " + reservaRequest);
		try {
			// buscamos los datos exactos de cada una de las dos entidades
			Optional<Room> room = roomService.findRoomById(reservaRequest.getRoomId());
			User usuario = userService.findUserByDAS(reservaRequest.getDasUser()).get();

			// cargamos la reserva con los datos de sala y usuario
			Reservas nuevaReserva = reservaService.nuevaReserva(room.get(), usuario, reservaRequest);

			return new ResponseEntity<Long>(nuevaReserva.getIdreserve(), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("ReservaRequest: " + reservaRequest);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@PutMapping(path="/nuevafecha")
	public ResponseEntity<?> ModificarFechasReserva(@RequestBody PutFechasReservas nuevasFechas){
		try {
			reservaService.modificoFechasReserva(nuevasFechas.getFechaReserva(), nuevasFechas.getFechaHasta(), nuevasFechas.getReservaId());
			return new ResponseEntity<>(HttpStatus.OK);
					
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@DeleteMapping(path = "/delete/{idReserva}")
	public ResponseEntity<?> DeleteSalaEquipo(@PathVariable long idReserva) {
		try {
			Optional<Reservas> reservaAux = Optional.ofNullable(reservaService.buscoReserva(idReserva));
			reservaService.borroReserva(reservaAux.get());
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public ReservaToFront findReserva(Long idReserva) {
		List<String> listaEquipos = new ArrayList<String>();
		Optional<Reservas> reservaAux = Optional.ofNullable(reservaService.buscoReserva(idReserva));

		Room roomAux = reservaAux.get().getRoom();
		//logger.info("(Findreserva)Sala: " + roomAux);

		// Obtenemos el nombre de la oficina de la sala
		String nombreOficina = roomAux.getOffice().getOfficename();
		
		//Obtenemos el pais de la oficina
		String paisOficina = roomAux.getOffice().getCountry().getCountryName();
		//logger.info("(Findreserva)Nombre Oficina: " + nombreOficina);

		// Obtenemos los equipos que tiene la sala reservados
		roomAux.getEquipment().forEach(equipo -> {
			listaEquipos.add(equipo.getEquipment().getNombre());
		});

		boolean activa = reservaAux.get().isActiva();
		
		String titulo = reservaAux.get().getTitulo();
		String descripcion = reservaAux.get().getDescripcion();
		
		Date fechaReserva = reservaAux.get().getFechaReserva();
		Date fechaHasta = reservaAux.get().getFechaHasta();
		
		String userReserva = reservaAux.get().getOwner().getDasUser(); 

		return new ReservaToFront(idReserva, activa, roomAux.getRoomName(), roomAux.getCapacity(), nombreOficina, paisOficina, titulo, descripcion, listaEquipos,
				fechaReserva, fechaHasta, userReserva);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/find/{dasUser}")
	public ResponseEntity<?> findReserva(@PathVariable String dasUser) {
		logger.info("Entro a buscar reservas del usuario: " + dasUser);
		//Set<ReservaToFront> listaReservasAux = new HashSet<>();
		List<ReservaToFront> listaReservas = new ArrayList<ReservaToFront>();
		
		Optional<User> reservasUser = userService.findUserByDAS(dasUser);

		reservasUser.get().getReserves().forEach(reserva -> {
			listaReservas.add(this.findReserva(reserva.getIdreserve()));
		});
		//Ordenamos la lista de reservas que se envia al front
		listaReservas.sort(Comparator.comparing(ReservaToFront::getFechaReserva));
		
		return new ResponseEntity<List<ReservaToFront>>(listaReservas, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/findbysala/{idSala}")
	public ResponseEntity<?> findReservasSala(@PathVariable Integer idSala){
		List<ReservaToFront> listaReservas = new ArrayList<ReservaToFront>();
		List<Reservas> reservasAux = new ArrayList<Reservas>();
		
		Optional<Room> salaAux = roomService.findRoomById(idSala);
		
		reservasAux = reservaService.buscoReservasSala(salaAux.get());
		
		reservasAux.forEach(reserva -> {
			listaReservas.add(this.findReserva(reserva.getIdreserve()));
		});
		
		return new ResponseEntity<List<ReservaToFront>>(listaReservas, HttpStatus.OK);
	}
}

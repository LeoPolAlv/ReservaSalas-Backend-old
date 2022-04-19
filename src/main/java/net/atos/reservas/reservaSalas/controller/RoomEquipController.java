package net.atos.reservas.reservaSalas.controller;

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

import net.atos.reservas.reservaSalas.DTO.EquipamientoToFront;
import net.atos.reservas.reservaSalas.Services.IEquipamientoService;
import net.atos.reservas.reservaSalas.Services.IRoomEquipamientoService;
import net.atos.reservas.reservaSalas.Services.IRoomService;
import net.atos.reservas.reservaSalas.models.entity.Equipamientos;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.RoomEquipment;

@RestController
@RequestMapping(path = "/roomequip")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE}) 
public class RoomEquipController {

	private final static Logger logger = LoggerFactory.getLogger(RoomEquipController.class);
	private ConversorTipos conversorTipos = new ConversorTipos();
	
	@Autowired 
	IRoomEquipamientoService roomEquipmentService;
	
	@Autowired 
	IRoomService roomService;
	
	@Autowired 
	IEquipamientoService equipService;
	 
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/find/{roomName}") 
	public ResponseEntity<?> buscoEquiposPorSala(@PathVariable String roomName){
		logger.info("Parametro pasado por URL: " + roomName);
		
		Room room = roomService.findRoom(roomName);
		
		logger.info("Salaencontrada por nombre: " + room);
		
	    // Buscamos todos los equipos asignados a una sala 
		List<RoomEquipment> listaEquipos = roomEquipmentService.findByRoom(room);
	    
	    logger.info("Lista equipos por sala: " + listaEquipos);
	    
	    List<EquipamientoToFront> listaEquiposFront = new ArrayList<>();
	  
	    //Buscamos cada dato del equipo 
	    listaEquipos.forEach(equipoAux -> { 
	    	Equipamientos equipo = new Equipamientos(); 
	    	equipo = equipService.buscoCodigoEquipo(equipoAux.getEquipment().getCodigo());
	    	EquipamientoToFront equipoT = conversorTipos.equipamientoToFront(equipService.buscoCodigoEquipo(equipo.getCodigo()));
	    	listaEquiposFront.add(equipoT); 
	    	logger.info("Lista de equipos a enviar: " + listaEquiposFront); 
	    });
	   return new ResponseEntity<List<EquipamientoToFront>>(listaEquiposFront, HttpStatus.OK);
	  }
}

package net.atos.reservas.reservaSalas.controller;

import java.util.List;

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

import net.atos.reservas.reservaSalas.Services.IOficinasService;
import net.atos.reservas.reservaSalas.Services.IRoomService;
import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Room;

@RestController
@RequestMapping(path = "/room")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE})
public class RoomController {

	@Autowired
	private IRoomService roomService;
	
	@Autowired
	private IOficinasService oficinasService;
	
	//private ConversorTipos conversorTipos;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path="/findall") 
    public ResponseEntity<?> findAllsRooms(){
		try {
			return new ResponseEntity <List<Room>>(roomService.findAllRooms(), HttpStatus.OK);   
					
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path="/findrooms/{idOficina}") 
	public ResponseEntity<?> findRoomsPorOficina( @PathVariable Integer idOficina){
		Oficinas oficina = oficinasService.findOffice(idOficina);
		
		return new ResponseEntity<List<Room>>(roomService.findRoomsOffice(oficina),HttpStatus.OK)  ;
	}
}

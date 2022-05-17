package net.atos.reservas.reservaSalas.controller;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
import net.atos.reservas.reservaSalas.Services.IPaisService;
import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Pais;

@RestController
@RequestMapping(path = "/oficinas")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE})
public class OficinasController {

	//private final static Logger logger = LoggerFactory.getLogger(OficinasController.class);
	
	@Autowired
	private IOficinasService oficinasService;
	
	@Autowired
	private IPaisService paisService;
	
	List<String> listaOficinas;
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/all")
	public ResponseEntity<?> buscarOficinas() {
		
		return new ResponseEntity<List<Oficinas>>(oficinasService.findAllOffices(),HttpStatus.OK);		
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping("/country/{paisName}")
	public ResponseEntity<?> oficinasPorPais(@PathVariable String paisName){
	
		listaOficinas = new ArrayList<String>();
		
		Pais paisAux = paisService.buscoNombrePais(paisName);
		
		//logger.info("Pais encontrado: ", paisAux);
		
		List<Oficinas> oficinas = oficinasService.findByCountry(paisAux);
		
		oficinas.forEach(oficina -> {
			listaOficinas.add(oficina.getOfficename());
		});
		
		return new ResponseEntity<List<String>>(listaOficinas, HttpStatus.OK);
	}
	
}
package net.atos.reservas.reservaSalas.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.atos.reservas.reservaSalas.DTO.PaisRequest;
import net.atos.reservas.reservaSalas.Services.IOficinasService;
import net.atos.reservas.reservaSalas.Services.IPaisService;
import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Pais;

@RestController
@RequestMapping(path = "/pais")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class PaisController {

	private final static Logger logger = LoggerFactory.getLogger(OficinasController.class);
	
	@Autowired
	IPaisService paisService;
	
	@Autowired
	IOficinasService oficinasService;
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping(path="/new")
	public ResponseEntity<?> nuevoPais(@RequestBody PaisRequest nombrePais){
		logger.info("**[RESERVAS]--- Estamos creando un nuevo Pais");
		logger.info("**[RESERVAS]--- Nombre Pais: " + nombrePais.getCountryName());
		try {
			Pais nuevoPais = new Pais();
			
			nuevoPais.setCountryName(nombrePais.getCountryName());
			nuevoPais.setOffices(null);
			
			paisService.newCountry(nuevoPais);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.info("**[RESERVAS]--- Error crear nuevo Pais: " + e);
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/all")
	public ResponseEntity<?> buscoPaises() {
		try {
			List<Pais> listaPaises = paisService.allPaises();
			return new ResponseEntity<List<Pais>>(listaPaises, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/{idOficina}")
	public ResponseEntity<?> buscoPaisPorOficina(@PathVariable Integer idOficina) {
		try {
			Oficinas oficinaAux = oficinasService.findOffice(idOficina);
			logger.info("Oficina encontrada: ", oficinaAux);
			Pais pais = paisService.buscoPaisOficina(oficinaAux);
			logger.info("Pais encontrado: ", pais);
			return new ResponseEntity<Pais>(pais, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

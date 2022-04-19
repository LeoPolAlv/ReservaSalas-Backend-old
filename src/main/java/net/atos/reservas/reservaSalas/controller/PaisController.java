package net.atos.reservas.reservaSalas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.atos.reservas.reservaSalas.Services.IPaisService;
import net.atos.reservas.reservaSalas.models.entity.Pais;

@RestController
@RequestMapping(path = "/pais")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class PaisController {

	@Autowired
	IPaisService paisService;

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
}

package net.atos.reservas.reservaSalas.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.atos.reservas.reservaSalas.Services.IOficinasService;
import net.atos.reservas.reservaSalas.Services.IUserService;
import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.User;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE })
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@Autowired
	IOficinasService officeService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@GetMapping(path = "/find/{user}")
	public ResponseEntity<?> buscoUser(@PathVariable String user) {
		logger.info("Entro en find User del controller");
		try {
			//Optional<User> userFind = userService.findUserByDAS(user);
	
			/*if (userFind.isPresent()) {
				User userFindFront = new User();
	
				userFindFront.setDasUser(userFind.get().getDasUser());
				userFindFront.setEmail(userFind.get().getEmail());
				// userFindFront.setPassword("#######");
				userFindFront.setRol(userFind.get().getRol());
				userFindFront.setEstadoUser(userFind.get().isEstadoUser());
	
				userFindFront.setOffice(userFind.get().getOffice());
				userFindFront.setAlerts(userFind.get().getAlerts());
				userFindFront.setReserves(userFind.get().getReserves());
	
				// return new ResponseEntity<UserToFront>(userFindFront,HttpStatus.OK);
				 */
				return new ResponseEntity<User>(userService.findUserByDAS(user).get(), HttpStatus.OK);
			//} 
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	
	/*@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping(path = "/new")
	public ResponseEntity<?> nuevoUser(@RequestBody User usuario) {

		String mensaje = "";
		
		if (userService.existeUsuario(usuario.getDasUser())) {
			mensaje = "Este usuario " + usuario.getDasUser() + " esta dado de alta en el sistema";
			return new ResponseEntity<String>(mensaje, HttpStatus.FOUND);
		}

		if (userService.existeEmail(usuario.getEmail())) {
			mensaje = "Este usuario " + usuario.getEmail() + " esta dado de alta en el sistema";
			return new ResponseEntity<String>(mensaje, HttpStatus.FOUND);
		}

		User userNew = new User();
		Oficinas oficina = new Oficinas();

		usuario.setEstadoUser(true);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		// buscamos el objeto oficina a la que pertenece el user
		oficina = officeService.findOffice(usuario.getOffice().getOfficename());
		// return new ResponseEntity<Office>(oficina,HttpStatus.OK);
		usuario.setOffice(oficina);

		userNew = userService.newUser(usuario);

		return new ResponseEntity<User>(userNew, HttpStatus.OK);
	}
*/
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping(path = "/delete")
	public ResponseEntity<?> deleteUser(@RequestBody User userFront) {
		Optional<User> userDelete = userService.findUserByDAS(userFront.getDasUser());

		if (userDelete.isPresent()) {
			userService.deleteUser(userDelete.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			String mensaje = "Este usuario " + userFront.getDasUser() + " NO esta dada de alta en el sistema";
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
	}
}

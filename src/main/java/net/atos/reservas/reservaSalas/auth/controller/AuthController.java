package net.atos.reservas.reservaSalas.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.atos.reservas.reservaSalas.auth.DTO.AuthenticationRequest;
import net.atos.reservas.reservaSalas.auth.DTO.AuthenticationResponse;
import net.atos.reservas.reservaSalas.auth.jwt.JwtProvider;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE }, exposedHeaders = "**")
public class AuthController {

	private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;

	@Autowired  
	PasswordEncoder passwordEncoder;

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@Validated @RequestBody AuthenticationRequest authenticationRequest) {
		logger.info("Iniciamos Login");
		logger.info("Antes de autenticar manager");
		logger.info("Authenticationrequest: " + authenticationRequest);
		//this.PasswordEncoderTest(authenticationRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		logger.info("Autentication manager");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.info("Autentication get context");

		String token = jwtProvider.generateToken(authentication);
		logger.info("Token generado");
		AuthenticationResponse respuesta = new AuthenticationResponse(true, token);

		return new ResponseEntity<AuthenticationResponse>(respuesta, HttpStatus.OK);
		//return new ResponseEntity<String>("Usuario existente en BD. Se enviara token", HttpStatus.OK);
	} 
}

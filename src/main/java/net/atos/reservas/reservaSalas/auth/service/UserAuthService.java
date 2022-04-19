package net.atos.reservas.reservaSalas.auth.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.atos.reservas.reservaSalas.Services.IUserService;
import net.atos.reservas.reservaSalas.auth.entity.UserAuth;
import net.atos.reservas.reservaSalas.models.entity.User;

@Service
public class UserAuthService implements UserDetailsService {

	private final static Logger logger = LoggerFactory.getLogger(UserAuthService.class);
	
	@Autowired
	IUserService userService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = userService.findUserByDAS(username).get();
		logger.info("USERAUTHSERVICE -- Usuario recogido de BBDD: " + usuario);
		if(usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return UserAuth.build(usuario);
	}

}

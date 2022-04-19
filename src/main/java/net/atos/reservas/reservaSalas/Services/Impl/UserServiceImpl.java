package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.IUserService;
import net.atos.reservas.reservaSalas.models.DAO.IUserDAO;
import net.atos.reservas.reservaSalas.models.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	//private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDAO userDao;

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findUserByDAS(String username) {
		//logger.info("Username en servicio UserServiceImpl: " + username);
		//Optional<User> usernameAux = userDao.findByDasUser(username);
		//logger.info("La busqueda devuelve: " + usernameAux);
		return userDao.findByDasUser(username);
	
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	@Transactional
	public User newUser(User user) {
		return userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existeUsuario(String username) {
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existeEmail(String email) {
		return false;
	}

}

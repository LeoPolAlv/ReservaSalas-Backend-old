package net.atos.reservas.reservaSalas.Services;

import java.util.Optional;

import net.atos.reservas.reservaSalas.models.entity.User;

public interface IUserService {

	public Optional<User> findUserByDAS(String username);

	public void deleteUser(User user);

	public User newUser(User user);

	public boolean existeUsuario(String username);

	public boolean existeEmail(String email);
	
}

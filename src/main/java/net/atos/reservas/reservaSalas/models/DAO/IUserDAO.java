package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Reservas;
import net.atos.reservas.reservaSalas.models.entity.User;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {

	Optional<User> findByDasUser(String username);

	boolean existsByDasUser(String Username);

	boolean existsByEmail(String email);

	Optional<List<Reservas>> findReservesByDasUser(String dasUser);

}

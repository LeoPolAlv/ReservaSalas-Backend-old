package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Pais;

@Repository
public interface IPaisDAO extends JpaRepository<Pais, Integer> {
	
	List<Pais> findAllByOrderByIdPaisAsc();

	Pais findByCountryName(String nombrePais);

}

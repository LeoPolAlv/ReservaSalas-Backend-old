package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Pais;

@Repository
public interface IOficinasDAO extends JpaRepository<Oficinas, Integer> {

	public Oficinas findByIdoffice(Integer idoffice);

	public List<Oficinas> findByCountry(Pais country);
	
}

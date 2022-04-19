package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.TramosHoras;

@Repository
public interface ITramosHorasDAO extends JpaRepository<TramosHoras, Integer> {

	public TramosHoras findByTramos(String tramoH);

	public List<TramosHoras> findByIdtramoNotIn(List<Integer> tramos);
	
}

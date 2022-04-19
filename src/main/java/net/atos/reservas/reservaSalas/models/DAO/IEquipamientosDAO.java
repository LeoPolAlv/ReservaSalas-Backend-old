package net.atos.reservas.reservaSalas.models.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Equipamientos;

@Repository
public interface IEquipamientosDAO extends JpaRepository<Equipamientos, Integer> {

	public List<Equipamientos> findByCodigoContainingIgnoreCase(String codigo);

	public List<Equipamientos> findByNombreContainingIgnoreCase(String nombreEquipo);

	public Equipamientos findByCodigo(String codigo);

	
}

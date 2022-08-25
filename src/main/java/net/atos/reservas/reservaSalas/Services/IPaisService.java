package net.atos.reservas.reservaSalas.Services;

import java.util.List;
import java.util.Optional;

import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Pais;

public interface IPaisService {

	public List<Pais> allPaises();

	public Pais newCountry(Pais datosPais);

	public Optional<Pais> buscoPais(Integer idpais);

	public Pais buscoNombrePais(String nombrePais);
	
	public Pais buscoPaisOficina(Oficinas oficina);

	public void borraCountry(Integer id);

}

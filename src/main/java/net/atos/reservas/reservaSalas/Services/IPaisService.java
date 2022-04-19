package net.atos.reservas.reservaSalas.Services;

import java.util.List;

import net.atos.reservas.reservaSalas.models.entity.Pais;

public interface IPaisService {

	public List<Pais> allPaises();

	public Pais newCountry(Pais datosPais);

	public Pais buscoPais(Pais country);

	public Pais buscoNombrePais(String nombrePais);

	public void borraCountry(Integer id);

}

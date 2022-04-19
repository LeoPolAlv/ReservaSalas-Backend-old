package net.atos.reservas.reservaSalas.Services;

import java.util.List;

import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Pais;

public interface IOficinasService {

	public List<Oficinas> findByCountry(Pais country);

	public Oficinas findOffice(String OficinaName);

	public List<Oficinas> findAllOffices();

	public Oficinas newOffice(Oficinas oficina);

	public void deleteOffice(Oficinas office);

}

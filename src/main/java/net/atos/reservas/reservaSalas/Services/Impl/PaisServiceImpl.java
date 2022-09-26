package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.IPaisService;
import net.atos.reservas.reservaSalas.models.DAO.IOficinasDAO;
import net.atos.reservas.reservaSalas.models.DAO.IPaisDAO;
import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Pais;

@Service
public class PaisServiceImpl implements IPaisService {

	@Autowired
	public IPaisDAO iPaisDao;
	
	@Autowired
	public IOficinasDAO iOficinaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pais> allPaises() {
		return this.iPaisDao.findAllByOrderByIdPaisAsc();
	}

	@Override
	@Transactional
	public Pais newCountry(Pais datosPais) {
		return this.iPaisDao.save(datosPais);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Pais> buscoPais(Integer idPais) {
		return iPaisDao.findById(idPais);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Pais buscoNombrePais(String nombrePais) {
		return iPaisDao.findByCountryName(nombrePais);
	}

	@Override
	@Transactional
	public void borraCountry(Integer id) {
		iPaisDao.deleteById(id);

	}

	@Override
	public Pais buscoPaisOficina(Oficinas oficina) {
		// TODO Auto-generated method stub
		return iPaisDao.findByOffices(oficina);
	}

}

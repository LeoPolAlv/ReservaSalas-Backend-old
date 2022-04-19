package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.IOficinasService;
import net.atos.reservas.reservaSalas.models.DAO.IOficinasDAO;
import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Pais;

@Service
public class OficinasServiceImpl implements IOficinasService {

	@Autowired
	private IOficinasDAO officeDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Oficinas> findByCountry(Pais country) {
		return officeDao.findByCountry(country);
	}

	@Override
	@Transactional(readOnly = true)
	public Oficinas findOffice(String OficinaName) {
		return officeDao.findByOfficename(OficinaName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Oficinas> findAllOffices() {
		return officeDao.findAll();
	}

	@Override
	@Transactional
	public Oficinas newOffice(Oficinas oficina) {
		return officeDao.save(oficina);
	}

	@Override
	@Transactional
	public void deleteOffice(Oficinas office) {
		officeDao.delete(office);
	}

}

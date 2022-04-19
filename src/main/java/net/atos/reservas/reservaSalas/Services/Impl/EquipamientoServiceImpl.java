package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.IEquipamientoService;
import net.atos.reservas.reservaSalas.models.DAO.IEquipamientosDAO;
import net.atos.reservas.reservaSalas.models.entity.Equipamientos;

@Service
public class EquipamientoServiceImpl implements IEquipamientoService {

	@Autowired
	IEquipamientosDAO equipmentDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Equipamientos> allEquipments() {
		return equipmentDao.findAll();
	}

	@Override
	@Transactional
	public Equipamientos newEquipment(Equipamientos equipment) {
		return equipmentDao.save(equipment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipamientos> buscoCodigoEquipment(String equipment) {
		return equipmentDao.findByCodigoContainingIgnoreCase(equipment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipamientos> buscoNombreEquipment(String equipment) {
		return equipmentDao.findByNombreContainingIgnoreCase(equipment);
	}

	@Override
	@Transactional(readOnly = true)
	public Equipamientos buscoCodigoEquipo(String codigo) {
		return equipmentDao.findByCodigo(codigo);
	}

	@Override
	@Transactional()
	public void borroEquipment(Equipamientos equipment) {
		equipmentDao.delete(equipment);
	}

}

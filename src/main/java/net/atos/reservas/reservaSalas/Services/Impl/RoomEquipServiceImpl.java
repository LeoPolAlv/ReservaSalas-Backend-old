package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.IRoomEquipamientoService;
import net.atos.reservas.reservaSalas.models.DAO.IRoomEquipmentDAO;
import net.atos.reservas.reservaSalas.models.entity.Equipamientos;
import net.atos.reservas.reservaSalas.models.entity.Room;
import net.atos.reservas.reservaSalas.models.entity.RoomEquipment;

@Service
public class RoomEquipServiceImpl implements IRoomEquipamientoService {

	@Autowired
	IRoomEquipmentDAO roomEquipmentDao;
	
	
	@Override
	@Transactional
	public RoomEquipment newEquipoSala(Room room, Equipamientos equipo) {
		RoomEquipment roomEquipmentAux = new RoomEquipment();

		roomEquipmentAux.setEquipment(equipo);
		roomEquipmentAux.setRoom(room);

		return roomEquipmentDao.save(roomEquipmentAux);
	}

	@Override
	@Transactional
	public void borroEquipoSala(RoomEquipment roomEquipo) {
		roomEquipmentDao.delete(roomEquipo);
	}

	@Override
	@Transactional(readOnly = true)
	public RoomEquipment findById(Integer idRoomEquip) {
		return roomEquipmentDao.findByIdroomeq(idRoomEquip);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoomEquipment> findByRoom(Room room) {
		return roomEquipmentDao.findByRoom(room);
	}

}

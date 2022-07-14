package net.atos.reservas.reservaSalas.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.reservas.reservaSalas.Services.IRoomService;
import net.atos.reservas.reservaSalas.models.DAO.IRoomDAO;
import net.atos.reservas.reservaSalas.models.entity.Oficinas;
import net.atos.reservas.reservaSalas.models.entity.Room;

@Service
public class RoomServiceImpl implements IRoomService {

	@Autowired
	private IRoomDAO roomDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Room> findAllRooms() {
		// TODO Auto-generated method stub
		return roomDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Room findRoom(String nombreSala) {
		return roomDao.findByRoomName(nombreSala);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Room> findRoomsOffice(Oficinas oficina) {
		return roomDao.findByOffice(oficina);
	}

	@Override
	@Transactional
	public Room loadRoom(Room sala) {
		return roomDao.save(sala);
	}

	@Override
	@Transactional
	public void deleteRoom(Room sala) {
		roomDao.delete(sala);
	}

	@Override
	public Optional<Room> findRoomById(Integer id) {
		// TODO Auto-generated method stub
		return roomDao.findById(id);
	}

}

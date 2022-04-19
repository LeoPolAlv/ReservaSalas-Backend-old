package net.atos.reservas.reservaSalas.controller;

import org.springframework.stereotype.Component;

import net.atos.reservas.reservaSalas.DTO.EquipamientoToFront;
import net.atos.reservas.reservaSalas.DTO.RoomToFront;
import net.atos.reservas.reservaSalas.models.entity.Equipamientos;
import net.atos.reservas.reservaSalas.models.entity.Room;

@Component
public class ConversorTipos {

	/*
	 * Conversores para la entidad COUNTRY
	 */
/*	public Country convetirACountry(CountryToFront country) {
		Country pais = new Country();

		pais.setCountryName(country.getCountryName());

		return pais;
	}

	public CountryToFront countryToFront(Country countryAux) {
		CountryToFront pais = new CountryToFront();

		pais.setCountryName(countryAux.getCountryName());

		return pais;
	}
*/
	/*
	 * Conversores para la entidad OFFICE
	 */

/*	public Office convertirToOffice(OfficeToFront office) {
		Office oficina = new Office();

		oficina.setOfficename(office.getOfficename());
		oficina.getCountry().setCountryName(office.getCountry());
		oficina.setRooms(office.getRooms());
		oficina.setUsers(office.getUsers());

		return oficina;
	}

	public OfficeToFront officeToFront(Office office) {
		OfficeToFront oficina = new OfficeToFront();

		oficina.setOfficename(office.getOfficename());
		oficina.setCountry(office.getCountry().getCountryName());
		oficina.setRooms(office.getRooms());
		oficina.setUsers(office.getUsers());

		return oficina;
	}
*/
	/*
	 * Conversores para la entidad ROOM
	 */

	public Room convertirToRoom(RoomToFront sala) {
		Room salaAux = new Room();

		salaAux.setRoomName(sala.getRoomName());
		salaAux.setCapacity(sala.getCapacity());
		salaAux.getOffice().setOfficename(sala.getOficina());
		salaAux.setEquipment(sala.getEquipamientos());
		salaAux.setReserves(sala.getReservas());

		return salaAux;
	}

	public RoomToFront roomToFront(Room sala) {
		RoomToFront salaAux = new RoomToFront();

		salaAux.setRoomName(sala.getRoomName());
		salaAux.setCapacity(sala.getCapacity());
		salaAux.setOficina(sala.getOffice().getOfficename());
		salaAux.setEquipamientos(sala.getEquipment());
		salaAux.setReservas(sala.getReserves());

		return salaAux;
	}

	/*
	 * Conversores para la entidad EQUIPMENT
	 */

	/*
	 * public Equipment convertirToEquipment(EquipmentToFront equipment) { Equipment
	 * equipmentAux = new Equipment();
	 * 
	 * equipmentAux.setCodigo(equipment.getCodigo());
	 * equipmentAux.setNombre(equipment.getNombre());
	 * equipmentAux.setDescription(equipment.getDescription());
	 * equipmentAux.setRoomsEquipment(equipment.getRoomsEquipment());
	 * 
	 * return equipmentAux; }
	 * 
	 * public EquipmentToFront equipmentToFront(Equipment equipment) {
	 * EquipmentToFront equipmentAux = new EquipmentToFront();
	 * 
	 * equipmentAux.setCodigo(equipment.getCodigo());
	 * equipmentAux.setNombre(equipment.getNombre());
	 * equipmentAux.setDescription(equipment.getDescription());
	 * equipmentAux.setRoomsEquipment(equipment.getRoomsEquipment());
	 * 
	 * return equipmentAux; }
	 */

	/*
	 * Conversores para la entidad ROOM-EQUIPMENT
	 */

	
	 /* public RoomEquipment convertirToRoomEquip(RoomToFront roomEquip, EquipmentToFront equipRoom ) { 
		  RoomEquipment roomEquipmentAux = new RoomEquipment();
	  
		  //convertimos la entidad equipment por separado
		  roomEquipmentAux.getEquipment().setCodigo(equipRoom.getCodigo());
		  roomEquipmentAux.getEquipment().setNombre(equipRoom.getNombre());
		  roomEquipmentAux.getEquipment().setDescription(equipRoom.getDescription());
		  roomEquipmentAux.getEquipment().setRoomsEquipment(equipRoom.getRoomsEquipment());
		  
		  //convertimos la entidad room por separado
		  roomEquipmentAux.getRoom().setRoomName(roomEquip.getRoomName());
		  roomEquipmentAux.getRoom().setCapacity(roomEquip.getCapacity());
		  roomEquipmentAux.getRoom().getOffice().setOfficename(roomEquip.getOficina());
		  roomEquipmentAux.getRoom().setEquipment(roomEquip.getEquipamientos());
		  roomEquipmentAux.getRoom().setReserves(roomEquip.getReservas());
		  
		  return roomEquipmentAux; 
	  }*/
	  
	  public EquipamientoToFront equipamientoToFront(Equipamientos equipRoom) { 
		  EquipamientoToFront roomEquipmentAux = new EquipamientoToFront();
	  
		  //convertimos la entidad equipment por separado
		  roomEquipmentAux.setCodigo(equipRoom.getCodigo());
		  roomEquipmentAux.setNombre(equipRoom.getNombre());
		  roomEquipmentAux.setDescription(equipRoom.getDescription());
		  roomEquipmentAux.setRoomsEquipment(equipRoom.getRoomsEquipment());
		  
		  //convertimos la entidad room por separado
		  /*roomEquipmentAux.getRoom().setRoomName(roomEquip.getRoomName());
		  roomEquipmentAux.getRoom().setCapacity(roomEquip.getCapacity());
		  roomEquipmentAux.getRoom().setOficina(roomEquip.getRoomName());
		  roomEquipmentAux.getRoom().setEquipamientos(roomEquip.getEquipamientos());
		  roomEquipmentAux.getRoom().setReservas(roomEquip.getReservas());
		  */
		  return roomEquipmentAux; 
	  }
	 
	
	/*
	 * Conversores para la entidad USER
	 */
/*	public UserToFront userToFront(User userNew, Office oficina) {
		UserToFront userToFront = new UserToFront();

		userToFront.setDasUser(userNew.getDasUser());
		userToFront.setEmail(userNew.getEmail());
		userToFront.setRol(userNew.getRol());
		userToFront.setEstadoUser(userNew.isEstadoUser());
		userToFront.setOffice(userNew.getOffice().getOfficename());
		userToFront.setAlerts(userNew.getAlerts());
		userToFront.setReserves(userNew.getReserves());

		return userToFront;
	}*/
}


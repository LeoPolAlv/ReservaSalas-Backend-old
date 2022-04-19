package net.atos.reservas.reservaSalas.Services;

import java.util.List;

import net.atos.reservas.reservaSalas.models.entity.Equipamientos;

public interface IEquipamientoService {

	public List<Equipamientos> allEquipments();

	public Equipamientos newEquipment(Equipamientos equipment);

	public List<Equipamientos> buscoCodigoEquipment(String equipment);

	public List<Equipamientos> buscoNombreEquipment(String equipment);

	public Equipamientos buscoCodigoEquipo(String codigo);

	public void borroEquipment(Equipamientos equipment);
}

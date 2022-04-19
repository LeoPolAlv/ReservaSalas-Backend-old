package net.atos.reservas.reservaSalas.DTO;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class ReservaToFront {

	@NonNull
	private Long idReserva;

	@NonNull
	private String roomName;

	@NonNull
	private Integer capacity;

	@NonNull
	private String officeRoom;
	
	@NonNull
	private String countryName;

	@NonNull
	private List<String> nombreEquipo;

	@NonNull
	private String fechaReserva;

	@NonNull
	private List<String> horasReservas;
}

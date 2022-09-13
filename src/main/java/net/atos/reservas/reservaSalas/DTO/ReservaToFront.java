package net.atos.reservas.reservaSalas.DTO;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ReservaToFront {

	@NonNull
	private Long idReserva;
	
	private boolean activa;

	@NonNull
	private String roomName;

	@NonNull
	private Integer capacity;

	@NonNull
	private String officeRoom;
	
	@NonNull
	private String countryName;
	
	@NonNull
	private String titulo;
	
	@NonNull
	private String descripcion;

	@NonNull
	private List<String> nombreEquipo;

	@NonNull
	private Date fechaReserva;

	@NonNull
	private Date fechaHasta;
	
	@NonNull
	private String userReserva;
}

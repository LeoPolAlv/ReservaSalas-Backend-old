package net.atos.reservas.reservaSalas.DTO;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;

@Data
public class ReservaRequest {

	@NonNull
	private Integer roomId;

	@NonNull
	private String dasUser;

	@NonNull
	private Date fechaReserva;
	
	@NonNull
	private Date fechaHasta;
	
	@NonNull
	private String titulo;
	
	@NonNull
	private String descripcion;

}
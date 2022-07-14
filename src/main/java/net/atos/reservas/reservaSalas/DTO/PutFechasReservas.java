package net.atos.reservas.reservaSalas.DTO;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;

@Data
public class PutFechasReservas {

	@NonNull
	private Date fechaReserva;
	
	@NonNull
	private Date fechaHasta;
	
	@NonNull
	private Long reservaId;
}

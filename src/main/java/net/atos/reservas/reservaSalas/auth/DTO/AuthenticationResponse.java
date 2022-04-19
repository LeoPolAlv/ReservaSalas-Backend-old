package net.atos.reservas.reservaSalas.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

	private boolean ok;
	private String token;
}

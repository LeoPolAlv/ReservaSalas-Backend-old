package net.atos.reservas.reservaSalas.auth.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticationRequest {
	@NonNull
	private String username;
	@NonNull
	private String password;
}

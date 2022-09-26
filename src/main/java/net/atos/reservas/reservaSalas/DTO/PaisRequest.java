package net.atos.reservas.reservaSalas.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisRequest {

	@NonNull
	private String countryName;
}

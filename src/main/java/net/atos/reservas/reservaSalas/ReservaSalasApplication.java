package net.atos.reservas.reservaSalas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReservaSalasApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ReservaSalasApplication.class, args);
	}

}

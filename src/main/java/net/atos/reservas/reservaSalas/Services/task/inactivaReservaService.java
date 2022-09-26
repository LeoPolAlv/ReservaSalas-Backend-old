package net.atos.reservas.reservaSalas.Services.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.atos.reservas.reservaSalas.models.DAO.IReservasDAO;
import net.atos.reservas.reservaSalas.models.entity.Reservas;

@Component
public class inactivaReservaService {

	private final static Logger logger = LoggerFactory.getLogger(inactivaReservaService.class);
	
	@Autowired
	private IReservasDAO reservasDAO;
	
	@Scheduled(cron = "0 0,15,30,45 * * * *")
	private void execDesactivoReserva() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date now = new Date();
	    logger.info("**[RESERVAS]---Fecha del dia: " + now);
	    String strDate = sdf.format(now);
	    
		logger.info("**[RESERVAS]--- Comienza Tarea de desactivado de Reservas: " + strDate);
		
		List<Reservas> reservasActivasList = new ArrayList<>();
		
		reservasActivasList = reservasDAO.findByActivaTrueOrderByIdreserveAsc();
		
		reservasActivasList.forEach(reserva -> {
			logger.info("**[RESERVAS]--- Reservas activas: " + reserva.getIdreserve() + ": " +reserva.getFechaHasta());
			if(reserva.getFechaHasta().compareTo(now) <= 0) {
				logger.info("**[RESERVAS]--- Reservas a desactivar: " + reserva.getIdreserve() + ": " +reserva.getFechaHasta());
				reservasDAO.actualizoReservaActiva(reserva.getIdreserve());
			}
		});
		
		logger.info("**[RESERVAS]--- Fin tarea de desactivacion de Reservas ");
	}
}

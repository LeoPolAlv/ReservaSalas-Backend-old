package net.atos.reservas.reservaSalas.models.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.reservas.reservaSalas.models.entity.Alert;

@Repository
public interface IAlertDAO extends JpaRepository<Alert, Integer>{

}

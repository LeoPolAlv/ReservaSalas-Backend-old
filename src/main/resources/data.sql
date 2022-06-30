--** Country
INSERT IGNORE INTO `reservedb`.`pais` (`id_pais`, `country_name`) VALUES (1, 'España'),
                                                                 (2, 'Francia'),
																 (3, 'Gran Bretaña'),
																 (4, 'Portugal'),
																 (5, 'Italia'),
																 (6, 'Estados Unidos');


--******** Equipment
INSERT IGNORE INTO `reservedb`.`equipamientos` (`idequipment`, `codigo`, `description`, `nombre`) VALUES  (1, 'codigo1', 'Equipo prueba 1', 'equipo 1'),
                                                                                               (2, 'codigo2', 'Equipo prueba 2', 'equipo 2'),
																							   (3, 'codigo3', 'Equipo prueba 3', 'equipo 3'),
																							   (4, 'codigo4', 'Equipo prueba 4', 'equipo 4'),
																							   (5, 'codigo5', 'Equipo prueba 5', 'equipo 5'),
																							   (6, 'codigo6', 'Equipo prueba 6', 'equipo 6'),
																							   (7, 'codigo7', 'Equipo prueba 7', 'equipo 7'),
																							   (8, 'codigo8', 'Equipo prueba 8', 'equipo 8');
																							   
--- ***** Office
INSERT IGNORE INTO `reservedb`.`oficinas` (`idoffice`, `officename`, `fk_country`, `cod_postal`, `direccion`, `latitud`, `localidad`, `longitud`, `provincia`) 
       VALUES (1, 'Atos', 1,'47151','C. Andres Laguna 9-11','41.527657750232734','Boecillo','-4.72288531735084','Valladolid'),
	          (2, 'Bull & Atos technologies', 1,'47003','C. del León 1','41.66322390318605','Valladolid','-4.724945253706968','Valladolid'),
			  (3, 'Atos', 1,'28037','C. de Albarracín 25','40.452204936023236','Madrid','-3.6348598062030795','Madrid'),
			  (4, 'Atos', 1,'28042','Paseo Doce Estrellas 2','40.452204936023236','Madrid','-3.6348598062030795','Madrid'),
			  (5, 'RIVER OUEST', 2,'95870','80 Quai Voltaire','49.29597937700274','Bezons','2.2503863980237995','Bezons'),
			  (6, 'Atos', 2,'31300','Les Espaces St Martin 6 Imp Alice Guy','43.88151339368778','Toulouse','1.4373981828048448','Toulouse'),
			  (7, 'Atos It Solutions And Services, Unipessoal, Lda', 4,'1070-062','Avda. José Malhoa 16','38.75247971102245','Lisboa','-9.168899335423577','Lisboa'),
			  (8, 'Atos', 6,'75063','4851 Regent Blvd','35.19176692266165','Irving','-96.39397097585169','Irving'),
			  (9, 'Atos Consulting Canarias', 1,'38110','Subida al Mayorazgo 24B','28.445411592359694','Santa Cruz de Tenerife','-16.286383351596328','Tenerife');
																				
																							
--**** Room
INSERT IGNORE INTO `reservedb`.`room` (`idroom`, `capacity`, `roomname`, `fk_office`) VALUES (1, '52', 'Sala 1', 1),
																					  (2, '52', 'Sala 2', 1),
																					  (3, '52', 'Sala 3', 3),
																					  (4, '52', 'Sala 4', 3),
																					  (5, '52', 'Sala 5', 3),
																					  (6, '52', 'Sala 6', 5),
																					  (7, '52', 'Sala 7', 5);

--*** RoomEquipment 
INSERT IGNORE INTO `reservedb`.`room_equipment` (`idroomeq`, `fkequipment`, `fkroom`) VALUES (1, '1', '1'),
																					  (2, '2', '1'),
																					  (3, '3', '1'),
																					  (4, '4', '2'),
																					  (5, '5', '3'),
																					  (6, '6', '3'),
																					  (7, '7', '4'),
																					  (8, '8', '4'),
																					  (9, '1', '4'),
																					  (10, '3', '7'),
																					  (11, '5', '7');

--*** USER
INSERT IGNORE INTO reservedb.user (`id_user`,`das_user`,`email`,`estado_user`,`msgalert`,`msgreserve`,`password`,`rol`,`officereference`) 
    VALUES (1,'A00000','admin@gmail.com','1','0','0','$2a$12$wZil0e4/D.0reP1cdAh/XuxVPKJ.dUN2yp5QKplr9tVhI5b4yLXU6','ROLE_ADMIN',4),
           (3,'A00001','A00001@gmail.com','1','0','0','$2a$10$emecYT7Y35DnbM0PVqwlt.j7fgu9Dv697Ee895iaUgn4I.vAE//L6','ROLE_USER',1),
           (4,'A00002','A00002@gmail.com','1','0','0','$2a$10$rHtwjAOvR1eYcpGU6IMvd.zt3ECxsezU/3kdPetyTexmRzhBpnMw.','ROLE_USER',3);

--*** RESERVA
INSERT IGNORE INTO reservedb.reservas (`idreserve`, `activa`, `descripcion`, `fecha_reserva`, `fecha_hasta`, `titulo`, `id_user`, `room_reference`) 
    VALUES (1, 1, 'Reserva prueba 1', '2022-06-02 17:00:00','2022-06-02 17:30:00', 'Reunion 1', 3, 1),
		   (2, 1, 'Reserva prueba 2', '2022-06-02 09:00:00','2022-06-02 10:00:00', 'Reunion 2', 3, 1),
		   (3, 1, 'Reserva prueba 3', '2022-06-03 11:00:00','2022-06-03 13:00:00', 'Reunion 3', 4, 2),
		   (4, 1, 'Reserva prueba 4', '2022-06-03 10:30:00','2022-06-03 12:00:00', 'Reunion 4', 4, 2),
		   (5, 1, 'Reserva prueba 5', '2022-06-02 08:00:00','2022-06-02 08:30:00', 'Reunion 5', 3, 3);
		   
--** CURRENT_TIMESTAMP()
--*** Tramos Horarios de reservas																																	  
/*INSERT IGNORE  INTO reservedb.tramo_horas (`idtramo`,`tramo_horario`) VALUES (1,'8:00'),
												                     (2,'8:30'),
												                     (3,'9:00'),
												                     (4,'9:30'),
												                     (5,'10:00'),
												                     (6,'10:30'),
												                     (7,'11:00'),
												                     (8,'11:30'),
												                     (9,'12:00'),
												                     (10,'13:00'),
												                     (11,'13:30'),
												                     (12,'14:00'),
												                     (13,'14:30');
*/
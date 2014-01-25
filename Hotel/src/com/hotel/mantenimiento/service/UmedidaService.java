package com.hotel.mantenimiento.service;

import com.hotel.dao.DAOFactory;
import com.hotel.mantenimiento.dao.UmedidaDAO;
import com.hotel.util.Constantes;

public class UmedidaService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	UmedidaDAO objUmedidaDAO = fabrica.getUmedidaDAO();
}

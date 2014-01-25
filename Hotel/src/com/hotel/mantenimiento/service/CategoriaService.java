package com.hotel.mantenimiento.service;

import com.hotel.dao.DAOFactory;
import com.hotel.mantenimiento.dao.CategoriaDAO;
import com.hotel.util.Constantes;

public class CategoriaService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	CategoriaDAO objCategoriaDAO = fabrica.getCategoriaDAO();
}

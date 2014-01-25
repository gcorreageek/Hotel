package com.hotel.seguridad.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.seguridad.bean.MenuDTO;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.seguridad.dao.TrabajadorDAO;
import com.hotel.util.Constantes;

public class LogueoService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	TrabajadorDAO objUsuDAO = fabrica.getUsuarioDAO();
 
	public TrabajadorDTO obtieneXusuario(String id_usuario) {
		return objUsuDAO.buscaUsuario(id_usuario);
	}
	public List<MenuDTO> obtenerMenuXcodPerfil(Integer codPerfil) {
		return objUsuDAO.obtenerMenu(codPerfil);
	}
	
	
}

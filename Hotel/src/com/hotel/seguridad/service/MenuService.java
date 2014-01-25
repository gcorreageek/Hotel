package com.hotel.seguridad.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.seguridad.bean.MenuDTO;
import com.hotel.seguridad.dao.MenuDAO;
import com.hotel.util.Constantes;

public class MenuService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	MenuDAO objMenuDAO = fabrica.getMenuDAO(); 
	
	public List<MenuDTO>  listaMenu1() throws Exception {
		return objMenuDAO.listarMenu();
	}  
	public List<MenuDTO>  listaMenu2(Integer idSubMenu) throws Exception {
		return objMenuDAO.listarMenuXidSubMenu(idSubMenu);
	} 
	public MenuDTO  getMenu(Integer idMenu) throws Exception {
		return objMenuDAO.getMenu(idMenu);
	} 
	 
}

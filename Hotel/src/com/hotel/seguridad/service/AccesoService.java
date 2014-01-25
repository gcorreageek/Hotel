package com.hotel.seguridad.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.seguridad.bean.AccesoMenuDTO;
import com.hotel.seguridad.dao.AccesoDAO;
import com.hotel.util.Constantes;

public class AccesoService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	AccesoDAO objAccesoDAO = fabrica.getAccesoDAO(); 
	
	public List<AccesoMenuDTO>  listaAcceso(Integer idCargo) throws Exception {
		return objAccesoDAO.listarMenuAccesoXidCargo(idCargo);
	}  
	
	public Boolean registrarAcceso(AccesoMenuDTO objAcceso) throws Exception {
		return (Boolean) objAccesoDAO.registrar(objAcceso);
	} 
	public Boolean actualizarAcceso(AccesoMenuDTO objAcceso) throws Exception {
		return (Boolean) objAccesoDAO.actualizarEstado(objAcceso);
	} 
	public AccesoMenuDTO  getAcceso(Integer idAcceso) throws Exception {
		return objAccesoDAO.getAcceso(idAcceso);
	}
	public AccesoMenuDTO  getAccesoXIdMenu(Integer idMenu) throws Exception {
		return objAccesoDAO.getAccesoXIdMenu(idMenu);
	}
	public AccesoMenuDTO  getAccesoXIdMenuIdCargo(Integer idMenu,Integer idCargo) throws Exception {
		return objAccesoDAO.getAccesoXIdMenuIdCargo(idMenu, idCargo);
	}
}

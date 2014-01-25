package com.hotel.seguridad.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.seguridad.bean.AreaDTO;
import com.hotel.seguridad.dao.AreaDAO;
import com.hotel.util.Constantes;

public class AreaService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	AreaDAO objAreaDAO = fabrica.getAreaDAO(); 
	
	public List<AreaDTO>  listaArea() {
		return objAreaDAO.listarArea(null);
	}
	public AreaDTO  getArea(Integer cod) {
		return objAreaDAO.getArea(cod);
	}
	
	
	
	public List<AreaDTO> listaAreaPaginado(Integer inicio,Integer tamano)throws Exception  {
		return objAreaDAO.buscarAreaPaginados(null, inicio,tamano);
	} 
	public List<AreaDTO> buscarAreaXDescPaginado(AreaDTO area,Integer inicio,Integer tamano)throws Exception  {
		return objAreaDAO.buscarAreaPaginados(area, inicio, tamano);
	}
	public Integer listaAreaTotal()throws Exception  {
		return objAreaDAO.buscarArea(null).size();
	}
	public Integer buscarAreaXDescTotal(AreaDTO area ) throws Exception {
		return objAreaDAO.buscarArea(area).size();
	}
	
	
	
	
	
	
	
	public Boolean registrarArea(AreaDTO objArea) throws Exception {
		return (Boolean) objAreaDAO.registrarArea(objArea);
	} 
	public Boolean actualizarArea(AreaDTO objArea) throws Exception {
		return (Boolean) objAreaDAO.actualizarArea(objArea);
	}
	public Boolean eliminarArea(AreaDTO objArea) throws Exception {
		return (Boolean) objAreaDAO.eliminarArea(objArea);
	}
}

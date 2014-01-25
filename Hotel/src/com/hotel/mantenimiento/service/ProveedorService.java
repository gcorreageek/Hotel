package com.hotel.mantenimiento.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.mantenimiento.bean.ProveedorDTO;
import com.hotel.mantenimiento.dao.ProveedorDAO;
import com.hotel.util.Constantes;

public class ProveedorService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	ProveedorDAO objProveeDAO = fabrica.getProveedorDAO();
	public List<ProveedorDTO> listaProveedor() throws Exception {
		return objProveeDAO.buscarProveedor(null);
	}
	public List<ProveedorDTO> buscarProveedorXDesc(ProveedorDTO proveedor)throws Exception  {
		return objProveeDAO.buscarProveedor(null);
	}
	
	
	public List<ProveedorDTO> listaProveedorPaginado(Integer inicio,Integer tamano)throws Exception  {
		return objProveeDAO.buscarProveedorPaginados(null, inicio,tamano);
	} 
	public List<ProveedorDTO> buscarProveedorXDescPaginado(ProveedorDTO proveedor,Integer inicio,Integer tamano)throws Exception  {
		return objProveeDAO.buscarProveedorPaginados(proveedor, inicio, tamano);
	}
	public Integer listaProveedorTotal()throws Exception  {
		return objProveeDAO.buscarProveedor(null).size();
	}
	public Integer buscarProveedorXDescTotal(ProveedorDTO proveedor ) throws Exception {
		return objProveeDAO.buscarProveedor(proveedor).size();
	}
	
	public Boolean registrarProveedor(ProveedorDTO objProveedor)throws Exception  {
		return objProveeDAO.registrarProveedor(objProveedor);
	}
	public Boolean actualizarProveedor(ProveedorDTO objProveedor) throws Exception {
		return objProveeDAO.actualizarProveedor(objProveedor);
	}
	public Boolean eliminarProveedor(ProveedorDTO objProveedor)throws Exception  {
		return objProveeDAO.eliminarProveedor(objProveedor);
	}
	public ProveedorDTO getProveedor(ProveedorDTO proveedor)throws Exception  {
		return objProveeDAO.getProveedor(proveedor);
	} 
	public List<ProveedorDTO> listaProveedorPaginadoHabilitado(
			Integer comienzo, Integer filasXPagina) throws Exception{
		return objProveeDAO.buscarProveedorPaginadosHabilitado(null, comienzo,filasXPagina);
	} 
	public List<ProveedorDTO> buscarProveedorXDescPaginadoHabilitado(
			ProveedorDTO objProveedor, Integer comienzo, Integer filasXPagina) throws Exception{
		return objProveeDAO.buscarProveedorPaginadosHabilitado(objProveedor, comienzo, filasXPagina);
	} 
	public Integer listaProveedorTotalHabilitado()throws Exception {
		return objProveeDAO.buscarProveedorHabilitado(null).size();
	} 
	public Integer buscarProveedorXDescTotalHabilitado(ProveedorDTO objProveedor)throws Exception {
		return objProveeDAO.buscarProveedorHabilitado(objProveedor).size();
	}
	
}

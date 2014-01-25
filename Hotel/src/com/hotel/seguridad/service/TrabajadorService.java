package com.hotel.seguridad.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.seguridad.dao.TrabajadorDAO;
import com.hotel.util.Constantes;

public class TrabajadorService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	TrabajadorDAO objUsuarioDAO = fabrica.getUsuarioDAO();
 
	public TrabajadorDTO  getUsuario(Integer cod) throws Exception { 
		return objUsuarioDAO.getUsuario(cod);
	}
	
	
	
	public List<TrabajadorDTO> listaUsuarioPaginado(Integer inicio,Integer tamano)throws Exception  {
		return objUsuarioDAO.buscarUsuarioPaginados(null, inicio,tamano);
	} 
	public List<TrabajadorDTO> buscarUsuarioXDescPaginado(TrabajadorDTO usuario,Integer inicio,Integer tamano)throws Exception  {
		return objUsuarioDAO.buscarUsuarioPaginados(usuario, inicio, tamano);
	}
	public Integer listaUsuarioTotal()throws Exception  {
		return objUsuarioDAO.buscarUsuario(null).size();
	}
	public Integer buscarUsuarioXDescTotal(TrabajadorDTO usuario ) throws Exception {
		return objUsuarioDAO.buscarUsuario(usuario).size();
	}
	
	
	
	
	
	
	
	public Boolean registrarUsuario(TrabajadorDTO objUsuario) throws Exception {
		return (Boolean) objUsuarioDAO.registrarUsuario(objUsuario);
	} 
	public Boolean actualizarUsuario(TrabajadorDTO objUsuario) throws Exception {
		return (Boolean) objUsuarioDAO.actualizarUsuario(objUsuario);
	}
	public Boolean eliminarTrabajador(TrabajadorDTO objUsuario) throws Exception {
		return (Boolean) objUsuarioDAO.eliminarTrabajador(objUsuario);
	}

	
	
	 
	
}

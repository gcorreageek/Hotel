package com.hotel.seguridad.dao;

import java.util.List;

import com.hotel.seguridad.bean.MenuDTO;
import com.hotel.seguridad.bean.TrabajadorDTO;

public interface TrabajadorDAO { 

	List<MenuDTO> obtenerMenu(Integer codCargo); 
	TrabajadorDTO buscaUsuario(String usuario);
	
	TrabajadorDTO getUsuario(Integer codusuario) throws Exception; 
	
	List<TrabajadorDTO> buscarUsuarioPaginados(TrabajadorDTO usuario,Integer inicio,Integer tamano) throws Exception;
	List<TrabajadorDTO> buscarUsuario(TrabajadorDTO usuario) throws Exception; 
	
	Object registrarUsuario(TrabajadorDTO objUsuario) throws Exception;
	Object actualizarUsuario(TrabajadorDTO objUsuario) throws Exception;
	Object eliminarTrabajador(TrabajadorDTO objUsuario) throws Exception;
	
	
	
	
	

}

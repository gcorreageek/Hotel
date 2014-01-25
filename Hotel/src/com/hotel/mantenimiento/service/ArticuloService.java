package com.hotel.mantenimiento.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.dao.ArticuloDAO;
import com.hotel.util.Constantes;

public class ArticuloService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	ArticuloDAO objProDAO = fabrica.getProductoDAO();
	
	
	public List<ArticuloDTO> buscarProductosXDesc(ArticuloDTO producto)throws Exception {
		return objProDAO.buscarProductos(producto);
	} 
	public List<ArticuloDTO> listaProductos()throws Exception {
		return objProDAO.buscarProductos(null);
	}
	//Modal
	public List<ArticuloDTO> buscarProductosXDescPaginado(ArticuloDTO producto,Integer inicio,Integer tamano)throws Exception {
		return objProDAO.buscarProductosPaginados(producto, inicio, tamano);//Se cambio
	}
	public List<ArticuloDTO> listaProductosPaginado(Integer inicio,Integer tamano) throws Exception{
		return objProDAO.buscarProductosPaginados(null, inicio,tamano);//se cambio
	}
	public Integer buscarProductosXDescTotal(ArticuloDTO producto ) throws Exception{
		return objProDAO.buscarProductos(producto).size();
	} 
	public Integer listaProductosTotal() throws Exception{
		return objProDAO.buscarProductos(null).size();
	}
	//Modal idProvee
	public List<ArticuloDTO> buscarProductosIdProveeXDescPaginado(ArticuloDTO producto,Integer idProvee,Integer inicio,Integer tamano)throws Exception {
		return objProDAO.buscarProductosIdProveePaginados(producto,idProvee, inicio, tamano);
	}
	public List<ArticuloDTO> listaProductosIdProveePaginado(Integer idProvee,Integer inicio,Integer tamano)throws Exception {
		return objProDAO.buscarProductosIdProveePaginados(null,idProvee, inicio,tamano);
	}
	public Integer buscarProductosIdProveeXDescTotal(ArticuloDTO producto,Integer idProvee)throws Exception {
		return objProDAO.buscarProductosIdProvee(producto,idProvee).size();
	}
	public Integer listaProductosIdProveeTotal(Integer idProvee)throws Exception {
		return objProDAO.buscarProductosIdProvee(null,idProvee).size();
	}
	//------------------------------------
	
	public Boolean registrarProducto(ArticuloDTO objProducto)throws Exception {
		return objProDAO.registrarProducto(objProducto);
	}
	public ArticuloDTO getProducto(ArticuloDTO producto) throws Exception{
		return objProDAO.getProducto(producto);
	}
	public Boolean actualizarProducto(ArticuloDTO objProducto)throws Exception {
		return objProDAO.actualizarProducto(objProducto);
	}
	public Boolean eliminarArticulo(ArticuloDTO objProducto) throws Exception{
		return objProDAO.eliminarArticulo(objProducto);
	} 
	
	public List<ArticuloDTO> listaProductosPaginadoHabilitados(
			Integer comienzo, Integer filasXPagina) throws Exception{
		return objProDAO.buscarHabilitadosProductosPaginados(null, comienzo,filasXPagina);//se cambio
	} 
	public List<ArticuloDTO> buscarProductosXDescPaginadoHabilitados(
			ArticuloDTO objProducto, Integer comienzo, Integer filasXPagina) throws Exception{
		return objProDAO.buscarHabilitadosProductosPaginados(objProducto, comienzo, filasXPagina);//Se cambio
	} 
	public Integer listaProductosTotalHabilitados()throws Exception {
		return objProDAO.buscarHabilitadosProductos(null).size();
	} 
	public Integer buscarProductosXDescTotalHabilitados(ArticuloDTO objProducto) throws Exception{
		return objProDAO.buscarHabilitadosProductos(objProducto).size();
	} 
	public List<ArticuloDTO> listaProductosIdProveePaginadoHabilitados(
			Integer idProve, Integer comienzo, Integer filasXPagina)throws Exception {
		return objProDAO.buscarHabilitadosProductosIdProveePaginados(null,idProve, comienzo,filasXPagina);
	} 
	public List<ArticuloDTO> buscarProductosIdProveeXDescPaginadoHabilitados(
			ArticuloDTO objProducto, Integer idProve, Integer comienzo,
			Integer filasXPagina)throws Exception {
		return objProDAO.buscarHabilitadosProductosIdProveePaginados(objProducto,idProve, comienzo, filasXPagina);
	} 
	public Integer listaProductosIdProveeTotalHabilitados(Integer idProve) throws Exception{
		return objProDAO.buscarHabilitadosProductosIdProvee(null,idProve).size();
	} 
	public Integer buscarProductosIdProveeXDescTotalHabilitados(
			ArticuloDTO objProducto, Integer idProve) throws Exception{
		return objProDAO.buscarHabilitadosProductosIdProvee(objProducto,idProve).size();
	}
	
}

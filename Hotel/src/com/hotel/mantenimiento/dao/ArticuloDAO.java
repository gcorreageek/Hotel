package com.hotel.mantenimiento.dao;

import java.util.List;

import com.hotel.mantenimiento.bean.ArticuloDTO;

public interface ArticuloDAO {
	ArticuloDTO getProducto(ArticuloDTO producto)throws Exception ;
	List<ArticuloDTO> buscarProductosPaginados(ArticuloDTO producto,Integer inicio,Integer tamano) throws Exception ;
	List<ArticuloDTO> buscarProductos(ArticuloDTO producto) throws Exception ; 
	List<ArticuloDTO> buscarProductosIdProveePaginados(ArticuloDTO producto,Integer idProvee,Integer inicio,Integer tamano) throws Exception ;
	List<ArticuloDTO> buscarProductosIdProvee(ArticuloDTO producto, Integer idProvee) throws Exception ;
	Boolean registrarProducto(ArticuloDTO objProducto) throws Exception;
	Boolean actualizarProducto(ArticuloDTO objProducto) throws Exception;
	Boolean eliminarArticulo(ArticuloDTO objProducto) throws Exception;
	
	List<ArticuloDTO> buscarHabilitadosProductosPaginados(ArticuloDTO producto,Integer inicio,Integer tamano) throws Exception ;
	List<ArticuloDTO> buscarHabilitadosProductos(ArticuloDTO producto) throws Exception ;
 
	List<ArticuloDTO> buscarHabilitadosProductosIdProveePaginados( Object object, Integer idProve, Integer comienzo, Integer filasXPagina)throws Exception ;
	List<ArticuloDTO> buscarHabilitadosProductosIdProvee(Object object, Integer idProve)throws Exception ;

}

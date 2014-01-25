/**
 * ProductoProveedorDAO 24/07/2013
 */
package com.hotel.mantenimiento.dao;

import java.util.List;

import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;

/**
 * @author Gustavo A. Correa C.
 *
 */
public interface ArticuloProveedorDAO {
	ArticuloProveedorDTO getProductoProveedor(ArticuloProveedorDTO productoProveedor) throws Exception;
	ArticuloProveedorDTO getProductoProveedorXIdProdAndIdProvee(ArticuloProveedorDTO productoProveedor) throws Exception;
	List<ArticuloProveedorDTO> buscarProductoProveedorPaginados(ArticuloProveedorDTO productoProveedor,Integer inicio,Integer tamano) throws Exception;
	List<ArticuloProveedorDTO> buscarProductoProveedor(ArticuloProveedorDTO productoProveedor) throws Exception;
	
	Boolean registrarProductoProveedor(ArticuloProveedorDTO objProductoProvedor) throws Exception;
	Boolean actualizarProductoProveedor(ArticuloProveedorDTO objProductoProvedor) throws Exception;
	Boolean eliminarArticuloProveedor(ArticuloProveedorDTO objProductoProvedor) throws Exception;

}

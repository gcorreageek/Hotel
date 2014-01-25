package com.hotel.mantenimiento.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;
import com.hotel.mantenimiento.dao.ArticuloProveedorDAO;
import com.hotel.util.Constantes;

public class ArticuloProveedorService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	ArticuloProveedorDAO objProDAO = fabrica.getProductoProveedorDAO();
	//lista todo
	public List<ArticuloProveedorDTO> listaProductoProveedor() throws Exception{
		return objProDAO.buscarProductoProveedor(null);
	} 
	public List<ArticuloProveedorDTO> buscarProductosProveedorXRazonSocialAndDescProd(ArticuloProveedorDTO productoProveedor)throws Exception {
		return objProDAO.buscarProductoProveedor(productoProveedor);
	}
	//Total
	public Integer listaProductosProveedorTotal() throws Exception {
		return objProDAO.buscarProductoProveedor(null).size();
	}
	public Integer buscarProductosProveedorXRazonSocialAndDescProdTotal(ArticuloProveedorDTO productoProveedor ) throws Exception{
		return objProDAO.buscarProductoProveedor(productoProveedor).size();
	}
	//Paginacion
	public List<ArticuloProveedorDTO> listaProductosProveedorPaginado(Integer inicio,Integer tamano) throws Exception {
		return objProDAO.buscarProductoProveedorPaginados(null, inicio,tamano);
	} 
	public List<ArticuloProveedorDTO> buscarProductosProveedorXRazonSocialAndDescProdPaginado(ArticuloProveedorDTO productoProveedor,Integer inicio,Integer tamano) throws Exception{
		return objProDAO.buscarProductoProveedorPaginados(productoProveedor, inicio, tamano);
	}
	
	
	
	public Boolean registrarProductoProveedor(ArticuloProveedorDTO objProductoProveedor)throws Exception {
		return objProDAO.registrarProductoProveedor(objProductoProveedor);
	}
	public Boolean actualizarProductoProveedor(ArticuloProveedorDTO objProductoProveedor)throws Exception {
		return objProDAO.actualizarProductoProveedor(objProductoProveedor);
	}
	public Boolean eliminarArticuloProveedor(ArticuloProveedorDTO objProductoProveedor) throws Exception{
		return objProDAO.eliminarArticuloProveedor(objProductoProveedor);
	}
	public ArticuloProveedorDTO getProductoProveedor(ArticuloProveedorDTO productoProveedor)throws Exception {
		return objProDAO.getProductoProveedor(productoProveedor);
	}
	public ArticuloProveedorDTO getProductoProveedorIdProdAndIdProvee(ArticuloProveedorDTO productoProveedor)throws Exception {
		return objProDAO.getProductoProveedorXIdProdAndIdProvee(productoProveedor);
	}
	
}

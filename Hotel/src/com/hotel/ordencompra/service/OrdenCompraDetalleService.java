package com.hotel.ordencompra.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.ordencompra.bean.OrdenCompraDetalleDTO;
import com.hotel.ordencompra.dao.OrdenCompraDetalleDAO;
import com.hotel.util.Constantes;

public class OrdenCompraDetalleService {

	
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
//	pedido objSolPDAO = fabrica.getSolicitudPedidoDAO();
	OrdenCompraDetalleDAO objOrdenCompraDetDAO = fabrica.getOrdenCompraDetalleDAO();
	 
	public List<OrdenCompraDetalleDTO> listaOrdenCompraXidOrdenCompra(OrdenCompraDetalleDTO det) throws Exception {
		return objOrdenCompraDetDAO.getOCDetalle(det);
	}  
	
	
	public  OrdenCompraDetalleDTO  getOrdenCompraXidOrdenCompra(OrdenCompraDetalleDTO det) throws Exception {
		return objOrdenCompraDetDAO.getOCDetalle1(det);
	}  
	

}

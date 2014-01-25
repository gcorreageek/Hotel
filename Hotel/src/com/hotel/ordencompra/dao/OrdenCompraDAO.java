/**
 * OrdenCompraDAO 28/07/2013
 */
package com.hotel.ordencompra.dao;

import java.util.List;

import com.hotel.ordencompra.bean.OrdenCompraDTO;
import com.hotel.ordencompra.bean.OrdenCompraDetalleDTO;

/**
 * @author Gustavo A. Correa C.
 *
 */
public interface OrdenCompraDAO {
	Object insertarOC(OrdenCompraDTO oc, List<OrdenCompraDetalleDTO> ocDet) throws Exception ;
	
	List<OrdenCompraDTO> buscarOCPag(OrdenCompraDTO ocViene,Integer inicio,Integer tamano) throws Exception;
	List<OrdenCompraDTO> buscarOC(OrdenCompraDTO ocViene) throws Exception;
	
	List<OrdenCompraDTO> buscarOCPagEntregada(OrdenCompraDTO ocViene,Integer inicio,Integer tamano) throws Exception;
	List<OrdenCompraDTO> buscarOCEntregada(OrdenCompraDTO ocViene) throws Exception;
}

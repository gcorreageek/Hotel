package com.hotel.solicitudreq.dao;

import java.util.List;

import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;

public interface SolicitudPedidoDAO {

	Object registrarPedido(SolicitudReqDTO objSolP,List<DetalleSolicitudReqDTO> lstDetPed) throws Exception;
	List<SolicitudReqDTO> buscarPedidoPag(SolicitudReqDTO pedidoViene,Integer inicio,Integer tamano) throws Exception;
	List<SolicitudReqDTO> buscarPedido(SolicitudReqDTO pedidoViene) throws Exception;
	
	Object actualizarPedido(SolicitudReqDTO objSolP) ;
	/**
	 * @param objPedido
	 * @param inicio
	 * @param tamano
	 * @return
	 */
	List<SolicitudReqDTO> buscarPedidoPagSinAtender(
			SolicitudReqDTO objPedido, Integer inicio, Integer tamano);
	/**
	 * @param pedido
	 * @return
	 */
	List<SolicitudReqDTO> buscarPedidoSinAtender(SolicitudReqDTO pedido);
	/**
	 * @param objPedido
	 * @param inicio
	 * @param tamano
	 * @return
	 */
	List<SolicitudReqDTO> buscarPedidoPagAprobados(
			SolicitudReqDTO objPedido, Integer inicio, Integer tamano);
	/**
	 * @param pedido
	 * @return
	 */
	List<SolicitudReqDTO> buscarPedidoAprobados(SolicitudReqDTO pedido);
	/**
	 * @param objPedido
	 * @param comienzo
	 * @param filasXPagina
	 * @return
	 */
	List<SolicitudReqDTO> buscarPedidoPagFaltanDevolver(
			SolicitudReqDTO objPedido, Integer comienzo, Integer filasXPagina);
	/**
	 * @param objPedido
	 * @return
	 */
	List<SolicitudReqDTO> buscarPedidoFaltanDevolver(
			SolicitudReqDTO objPedido);
	 
	
}

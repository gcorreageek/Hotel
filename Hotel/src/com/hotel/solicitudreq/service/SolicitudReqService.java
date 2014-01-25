package com.hotel.solicitudreq.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;
import com.hotel.solicitudreq.dao.SolicitudPedidoDAO;
import com.hotel.util.Constantes;

public class SolicitudReqService {

	
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	SolicitudPedidoDAO objSolPDAO = fabrica.getSolicitudPedidoDAO();
	
	
//	//Lista todo
	public List<SolicitudReqDTO> listaPedido() throws Exception {
		return objSolPDAO.buscarPedido(null);
	} 
	public List<SolicitudReqDTO> buscarPedido(SolicitudReqDTO pedido)throws Exception {
		return objSolPDAO.buscarPedido(pedido);
	}
//	//Total
	public Integer listaPedidoTotal() throws Exception {
		return objSolPDAO.buscarPedido(null).size();
	}
	public Integer buscarSolicitudReqTotal(SolicitudReqDTO pedido ) throws Exception{
		return objSolPDAO.buscarPedido(pedido).size();
	}
//	//Paginacion
	public List<SolicitudReqDTO> listaPedidoPaginado(Integer inicio,Integer tamano) throws Exception {
		return objSolPDAO.buscarPedidoPag(null, inicio,tamano);
	} 
	public List<SolicitudReqDTO> buscarPedidoPaginado(SolicitudReqDTO productoProveedor,Integer inicio,Integer tamano) throws Exception{
		return objSolPDAO.buscarPedidoPag(productoProveedor, inicio, tamano);
	}
	
	public void registrarPedido(SolicitudReqDTO objSolP, List<DetalleSolicitudReqDTO> lstDetPed) throws Exception{
		objSolPDAO.registrarPedido(objSolP,lstDetPed);
	}
	public void guardarEvaluacionSolicitudReq(SolicitudReqDTO objSolP ) {
		objSolPDAO.actualizarPedido(objSolP);
	} 
	
	
	
	//////////////////////////////////////////////////////////////
	public List<SolicitudReqDTO> listaPedidoPaginadoSinAtender(
			Integer inicio, Integer tamano) throws Exception {
		return objSolPDAO.buscarPedidoPagSinAtender(null, inicio,tamano);
	} 
	public List<SolicitudReqDTO> buscarPedidoPaginadoSinAtender(
			SolicitudReqDTO objPedido, Integer inicio, Integer tamano) throws Exception {
		return objSolPDAO.buscarPedidoPagSinAtender(objPedido, inicio, tamano);
	} 
	public Integer listaPedidoTotalSinAtender() throws Exception {
		return objSolPDAO.buscarPedidoSinAtender(null).size();
	} 
	public Integer buscarPedidoTotalSinAtender(SolicitudReqDTO pedido) throws Exception {
		return objSolPDAO.buscarPedidoSinAtender(pedido).size();
	} 
	
	
	/////////////////////////////////////////////////////////
	public List<SolicitudReqDTO> listaPedidoPaginadoAprobados(
			Integer inicio, Integer tamano) {
		return objSolPDAO.buscarPedidoPagAprobados(null, inicio,tamano);
	} 
	public List<SolicitudReqDTO> buscarPedidoPaginadoAprobados(
			SolicitudReqDTO objPedido, Integer inicio, Integer tamano) {
		return objSolPDAO.buscarPedidoPagAprobados(objPedido, inicio, tamano);
	} 
	public Integer listaPedidoTotalAprobados() {
		return objSolPDAO.buscarPedidoAprobados(null).size();
	} 
	public Integer buscarSolicitudReqTotalAprobados(SolicitudReqDTO pedido) {
		return objSolPDAO.buscarPedidoAprobados(pedido).size();
	} 
	public List<SolicitudReqDTO> listaPedidoPaginadoFaltanDevolver(
			Integer comienzo, Integer filasXPagina) {
		return objSolPDAO.buscarPedidoPagFaltanDevolver(null, comienzo,filasXPagina);
	} 
	public List<SolicitudReqDTO> buscarPedidoPaginadoFaltanDevolver(
			SolicitudReqDTO objPedido, Integer comienzo, Integer filasXPagina) {
		return objSolPDAO.buscarPedidoPagFaltanDevolver(objPedido, comienzo, filasXPagina);
	} 
	public Integer listaPedidoTotalFaltanDevolver() {
		return objSolPDAO.buscarPedidoFaltanDevolver(null).size();
	} 
	public Integer buscarSolicitudReqTotalFaltanDevolver(SolicitudReqDTO objPedido) {
		return objSolPDAO.buscarPedidoFaltanDevolver(objPedido).size();
	}
	
	

}

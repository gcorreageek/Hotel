package com.hotel.solicitudreq.service;

import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;
import com.hotel.solicitudreq.dao.SolicitudReqDetalleDAO;
import com.hotel.util.Constantes;

public class SolicitudReqDetalleService {

	
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
//	pedido objSolPDAO = fabrica.getSolicitudPedidoDAO();
	SolicitudReqDetalleDAO objPedDetDAO = fabrica.getPedidoDetalleDAO();
	 
	public List<DetalleSolicitudReqDTO> listaPedidoXidPedido(DetalleSolicitudReqDTO det) throws Exception {
		return objPedDetDAO.getPedidoDetalle(det);
	}  
	
	public  DetalleSolicitudReqDTO  getPedidoIdPedido(DetalleSolicitudReqDTO det) throws Exception {
		return objPedDetDAO.getPedidoDetalle1(det);
	} 
	

}

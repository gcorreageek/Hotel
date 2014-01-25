package com.hotel.cotizacion.service;

import java.util.List;

import com.hotel.cotizacion.bean.CotizacionDetalleDTO;
import com.hotel.cotizacion.dao.CotizacionDetalleDAO;
import com.hotel.dao.DAOFactory;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;
import com.hotel.solicitudreq.dao.SolicitudReqDetalleDAO;
import com.hotel.util.Constantes;

public class CotizacionDetalleService {

	
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
//	pedido objSolPDAO = fabrica.getSolicitudPedidoDAO();
	CotizacionDetalleDAO objCotDetDAO = fabrica.getCotizacionDetalleDAO();
	 
	public List<CotizacionDetalleDTO> listaCotizacionXidCotizacion(CotizacionDetalleDTO det) throws Exception {
		return objCotDetDAO.getCotizacionDetalle(det);
	}  
	
	
	

}

package com.hotel.cotizacion.service;

import java.util.List;

import com.hotel.cotizacion.bean.CotizacionDTO;
import com.hotel.cotizacion.bean.CotizacionDetalleDTO;
import com.hotel.cotizacion.dao.CotizacionDAO;
import com.hotel.dao.DAOFactory;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;
import com.hotel.solicitudreq.dao.SolicitudPedidoDAO;
import com.hotel.util.Constantes;

public class CotizacionService {

	
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	CotizacionDAO objSolPDAO = fabrica.getCotizacionDAO();
	
	
//	//Lista todo
	public List<CotizacionDTO> listaCotizacion() throws Exception {
		return objSolPDAO.buscarCotizacion(null);
	} 
	public List<CotizacionDTO> buscarCotizacion(CotizacionDTO cot)throws Exception {
		return objSolPDAO.buscarCotizacion(cot);
	}
//	//Total
	public Integer listaCotizacionTotal() throws Exception {
		return objSolPDAO.buscarCotizacion(null).size();
	}
	public Integer buscarCotizacionTotal(CotizacionDTO cot ) throws Exception{
		return objSolPDAO.buscarCotizacion(cot).size();
	}
//	//Paginacion
	public List<CotizacionDTO> listaCotizacionPaginado(Integer inicio,Integer tamano) throws Exception {
		return objSolPDAO.buscarCotizacionPag(null, inicio,tamano);
	} 
	public List<CotizacionDTO> buscarCotizacionPaginado(CotizacionDTO cot,Integer inicio,Integer tamano) throws Exception{
		return objSolPDAO.buscarCotizacionPag(cot, inicio, tamano);
	}
	
	public void registrarCotizacion(CotizacionDTO objSolP, List<CotizacionDetalleDTO> lstDetPed) throws Exception  {
		objSolPDAO.insertarCotizacion(objSolP,lstDetPed);
	} 
	
	

}

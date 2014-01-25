/**
 * CotizacionDAO 28/07/2013
 */
package com.hotel.cotizacion.dao;

import java.util.List;

import com.hotel.cotizacion.bean.CotizacionDTO;
import com.hotel.cotizacion.bean.CotizacionDetalleDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;

/**
 * @author Gustavo A. Correa C.
 *
 */
public interface CotizacionDAO {

	Object insertarCotizacion(CotizacionDTO coti, List<CotizacionDetalleDTO> cotiDet) throws Exception;
	
	List<CotizacionDTO> buscarCotizacionPag(CotizacionDTO cotizacionViene,Integer inicio,Integer tamano) throws Exception;
	List<CotizacionDTO> buscarCotizacion(CotizacionDTO cotizacionViene) throws Exception;
}

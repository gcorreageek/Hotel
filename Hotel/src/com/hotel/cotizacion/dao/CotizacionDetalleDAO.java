/**
 * CotizacionDetalleDAO 28/07/2013
 */
package com.hotel.cotizacion.dao;

import java.util.List;

import com.hotel.cotizacion.bean.CotizacionDetalleDTO;

/**
 * @author Gustavo A. Correa C.
 *
 */
public interface CotizacionDetalleDAO {
 
	List<CotizacionDetalleDTO> getCotizacionDetalle(CotizacionDetalleDTO detalle);
}

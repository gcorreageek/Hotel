/**
 * PedidoDetalleDAO 27/07/2013
 */
package com.hotel.solicitudreq.dao;

import java.util.List;

import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;

/**
 * @author Gustavo A. Correa C.
 *
 */
public interface SolicitudReqDetalleDAO {

	List<DetalleSolicitudReqDTO> getPedidoDetalle(DetalleSolicitudReqDTO detalle);
	DetalleSolicitudReqDTO getPedidoDetalle1(DetalleSolicitudReqDTO detalle);
}

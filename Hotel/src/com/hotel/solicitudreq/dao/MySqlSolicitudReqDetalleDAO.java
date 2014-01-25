/**
 * MySqlPedidoDetalleDAO 27/07/2013
 */
package com.hotel.solicitudreq.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.util.MySqlConexion;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class MySqlSolicitudReqDetalleDAO implements SolicitudReqDetalleDAO {
	SqlSessionFactory sqlMapper = MySqlConexion.getMapper();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleSolicitudReqDTO> getPedidoDetalle(DetalleSolicitudReqDTO detalle) {
		SqlSession sesion = sqlMapper.openSession();
		List<DetalleSolicitudReqDTO> det = new ArrayList<DetalleSolicitudReqDTO>();
		try {
			if (detalle != null) {
				if (detalle.getCod_solicitudRequerimiento() != null) {
					det = (List<DetalleSolicitudReqDTO>) sesion.selectList(
							"pedidodetalle.SQL_getDetallePedido",
							detalle.getCod_solicitudRequerimiento());
				}
			}
		}  finally {
			sesion.close();
		}
		return det;
	} 
	@SuppressWarnings("unchecked")
	@Override
	public DetalleSolicitudReqDTO getPedidoDetalle1(DetalleSolicitudReqDTO detalle) {
		SqlSession sesion = sqlMapper.openSession();
		 DetalleSolicitudReqDTO  det = new DetalleSolicitudReqDTO();
		try {
			if (detalle != null) {
				if (detalle.getCod_detalleRequerimiento() != null) {
					det = (DetalleSolicitudReqDTO) sesion.selectOne(
							"pedidodetalle.SQL_getDetallePedido1",
							detalle.getCod_detalleRequerimiento());
				}
			}
		}  finally {
			sesion.close();
		}
		return det;
	}

}

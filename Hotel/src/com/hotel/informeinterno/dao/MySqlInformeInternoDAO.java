/**
 * MySqlInformeInternoDAO 29/07/2013
 */
package com.hotel.informeinterno.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hotel.informeinterno.bean.InformeInternoDTO;
import com.hotel.informeinterno.bean.InformeInternoDetalleDTO;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;
import com.hotel.util.MySqlConexion;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class MySqlInformeInternoDAO implements InformeInternoDAO {
	private final Log log = LogFactory.getLog(MySqlInformeInternoDAO.class);
	SqlSessionFactory sqlMapper = MySqlConexion.getMapper();  
	@Override
	public Object registrarII(InformeInternoDTO ii,
			List<InformeInternoDetalleDTO> iiDet) throws Exception{
		Integer rsult=0;
		SqlSession sesion =sqlMapper.openSession();
		try {  
			
			sesion.insert("ii.SQL_registraII",ii);
			SolicitudReqDTO ped = (SolicitudReqDTO) sesion.selectOne("pedido.SQL_getPedido",ii.getCod_solicitud_requerimiento()); 
			ped.setCod_solicitudRequerimiento(ii.getCod_solicitud_requerimiento());
			if("Salida".equals(ii.getTipo_informe_interno())){
				if("Abastecimiento".equals(ped.getTipo_solicitud_requerimiento())){ 
					ped.setEstado_solicitud_requerimiento("Atentido");
				}else{//PRestamo 
					ped.setEstado_solicitud_requerimiento("Falta Devolver");
				}
			}else{ 
				ped.setEstado_solicitud_requerimiento("Devuelto");
			}
			 
			sesion.update("pedido.SQL_actualizarPedidoEstado",ped);
			for (InformeInternoDetalleDTO  detalleInformeInternoDTO : iiDet) {  
				detalleInformeInternoDTO.setCod_informe_interno(ii.getCod_informe_interno()); 
				DetalleSolicitudReqDTO det = (DetalleSolicitudReqDTO) sesion.selectOne("pedidodetalle.SQL_getDetallePedido1",
						detalleInformeInternoDTO.getCod_detalle_solicitud_requerimiento()); 
				ArticuloDTO  objProducto = (ArticuloDTO) sesion.selectOne("articulo.SQL_getProductoXCodProd",
						det.getCod_articulo()); 
				Integer stock=0; 
				if("Salida".equals(ii.getTipo_informe_interno())){
					stock = objProducto.getStock_articulo() -  det.getCantidad();
				}else{
					stock = det.getCantidad() + objProducto.getStock_articulo();
				}
				
				objProducto.setStock_articulo(stock);
				sesion.update("articulo.SQL_updateProducto", objProducto);
				sesion.insert("iidetalle.SQL_registraDetalleII",detalleInformeInternoDTO);
			} 
			sesion.commit();
			rsult=1;
		} catch (Exception e) { 
			sesion.rollback(); 
			rsult=-1;
			throw e;
		}finally{
			sesion.close();
		} 
		return rsult;
	} 
	@SuppressWarnings("unchecked")
	@Override
	public List<InformeInternoDTO> buscaInformeInterno(InformeInternoDTO ii)
			throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Tipo:"+ii.getTipo_informe_interno());
			log.debug("codigoII:"+ii.getCod_informe_interno());
		} 
		SqlSession sesion = sqlMapper.openSession();
		List<InformeInternoDTO> det = new ArrayList<InformeInternoDTO>();
		try {
//			SQL_listaInformeInterno SQL_getInformeInterno
			if (ii.getCod_informe_interno() != null) {
				det = (List<InformeInternoDTO>) sesion.selectList(
						"ii.SQL_getInformeInterno",
						ii.getCod_informe_interno());
			}else{
				ii.setTipo_informe_interno("%"+ ii.getTipo_informe_interno() +"%");
				det = (List<InformeInternoDTO>) sesion.selectList(
						"ii.SQL_listaInformeInterno",
						ii.getTipo_informe_interno());
			}
			 
		}  finally {
			sesion.close();
		}
		return det; 
	}

}

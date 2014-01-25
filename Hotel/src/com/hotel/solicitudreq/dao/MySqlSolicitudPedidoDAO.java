package com.hotel.solicitudreq.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;
import com.hotel.util.MySqlConexion;


public class MySqlSolicitudPedidoDAO implements SolicitudPedidoDAO {
	SqlSessionFactory sqlMapper = MySqlConexion.getMapper();
	
	@Override
	public Object registrarPedido(SolicitudReqDTO objSolP,
			List<DetalleSolicitudReqDTO> lstDetPed) throws Exception{
		
		SqlSession sesion =sqlMapper.openSession();
		try {  
			Integer res = sesion.insert("pedido.SQL_registraPedido",objSolP); 
			for (DetalleSolicitudReqDTO detallePedidoDTO : lstDetPed) { 
				detallePedidoDTO.setCod_solicitudRequerimiento(objSolP.getCod_solicitudRequerimiento());
				sesion.insert("pedidodetalle.SQL_registraDetallePedido",detallePedidoDTO);
			} 
			sesion.commit();	
		} catch (Exception e) { 
//			e.printStackTrace();
			sesion.rollback(); 
//			sesion.close();
			throw e;
		}finally{
			sesion.close();
		} 
		return null;
	}

 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedidoPag(
			SolicitudReqDTO pedidoViene, Integer inicio,
			Integer tamano) throws Exception { 
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				SolicitudReqDTO pedidoHere = new SolicitudReqDTO();
				pedidoHere.setInicio(inicio);
				pedidoHere.setTamano(tamano);
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList(
						"pedido.SQL_listaPedidoPag", pedidoHere);
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento()); 
				 
				
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%");
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombrePag",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXAreaPag",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null   &&  pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechasPag",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipoPag",pedidoViene);
				} 
			}
		} finally {
			sesion.close();
		}
		return lstPedido; 
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedido(
			SolicitudReqDTO pedidoViene) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedido");
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento()); 
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%"); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombre",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXArea",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null  && pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechas",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipo",pedidoViene);
				} 
			}
		}  finally {
			sesion.close();
		}
		return lstPedido;
	}
//	SQL_actualizarPedido
	@Override
	public Object actualizarPedido(SolicitudReqDTO objSolP) {
		SqlSession sesion =sqlMapper.openSession(); 
		try {
			sesion.update("pedido.SQL_actualizarPedido", objSolP);
			sesion.commit();
		} finally {
			sesion.close();
		} 
		return objSolP;
	}


 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedidoPagSinAtender(
			SolicitudReqDTO pedidoViene, Integer inicio, Integer tamano) {
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				SolicitudReqDTO pedidoHere = new SolicitudReqDTO();
				pedidoHere.setInicio(inicio);
				pedidoHere.setTamano(tamano);
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList(
						"pedido.SQL_listaPedidoPagSinAtender", pedidoHere);
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento()); 
				 
				
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%");
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombrePagSinAtender",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXAreaPagSinAtender",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null   &&  pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechasPagSinAtender",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipoPagSinAtender",pedidoViene);
				} 
			}
		} finally {
			sesion.close();
		}
		return lstPedido; 
	}


 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedidoSinAtender(
			SolicitudReqDTO pedidoViene) {
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoSinAtender");
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento()); 
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%"); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombreSinAtender",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXAreaSinAtender",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null  && pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechasSinAtender",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipoSinAtender",pedidoViene);
				} 
			}
		}  finally {
			sesion.close();
		}
		return lstPedido;
	}


 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedidoPagAprobados(
			SolicitudReqDTO pedidoViene, Integer inicio, Integer tamano) {
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				SolicitudReqDTO pedidoHere = new SolicitudReqDTO();
				pedidoHere.setInicio(inicio);
				pedidoHere.setTamano(tamano);
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList(
						"pedido.SQL_listaPedidoPagAprobado", pedidoHere);
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento()); 
				 
				
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%");
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombrePagAprobado",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXAreaPagAprobado",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null   &&  pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechasPagAprobado",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipoPagAprobado",pedidoViene);
				} 
			}
		} finally {
			sesion.close();
		}
		return lstPedido; 
	}

 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedidoAprobados(
			SolicitudReqDTO pedidoViene) {
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoAprobado");
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento()); 
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%"); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombreAprobado",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXAreaAprobado",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null  && pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechasAprobado",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipoAprobado",pedidoViene);
				} 
			}
		}  finally {
			sesion.close();
		}
		return lstPedido;
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedidoPagFaltanDevolver(
			SolicitudReqDTO pedidoViene, Integer inicio, Integer tamano) {
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				SolicitudReqDTO pedidoHere = new SolicitudReqDTO();
				pedidoHere.setInicio(inicio);
				pedidoHere.setTamano(tamano);
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList(
						"pedido.SQL_listaPedidoPagFaltaDevolver", pedidoHere);
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento());  
				
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%");
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombrePagFaltaDevolver",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXAreaPagFaltaDevolver",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null   &&  pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechasPagFaltaDevolver",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento());
					pedidoViene.setInicio(inicio);
					pedidoViene.setTamano(tamano);
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipoPagFaltaDevolver",pedidoViene);
				} 
			}
		} finally {
			sesion.close();
		}
		return lstPedido; 
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudReqDTO> buscarPedidoFaltanDevolver(
			SolicitudReqDTO pedidoViene) {
		SqlSession sesion = sqlMapper.openSession();
		List<SolicitudReqDTO> lstPedido = new ArrayList<SolicitudReqDTO>();
		try {
			if (pedidoViene == null) {
				lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoFaltaDevolver");
			} else {
				System.out.println("nomusu:"+pedidoViene.getNom_trabajador());
				System.out.println("area:"+pedidoViene.getCod_area());
				System.out.println("fecha:"+pedidoViene.getFechaInicio()+"|"+pedidoViene.getFechaFin());
				System.out.println("tipo:"+pedidoViene.getTipo_solicitud_requerimiento()); 
				if(!"".equals(pedidoViene.getNom_trabajador())){
					System.out.println("nomsuario");
					pedidoViene.setNom_trabajador("%"+pedidoViene.getNom_trabajador()+"%"); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXNombreFaltaDevolver",pedidoViene);
				}else if(pedidoViene.getCod_area()!=0){
					System.out.println("codarea");
					pedidoViene.setCod_area(pedidoViene.getCod_area()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXAreaFaltaDevolver",pedidoViene);
				}else if(pedidoViene.getFechaInicio()!=null  && pedidoViene.getFechaFin()!=null ){
					System.out.println("fechas");
					pedidoViene.setFechaInicio(pedidoViene.getFechaInicio());
					pedidoViene.setFechaFin(pedidoViene.getFechaFin()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXFechasFaltaDevolver",pedidoViene);
				}else if(!"0".equals(pedidoViene.getTipo_solicitud_requerimiento())){
					System.out.println("tipo");
					pedidoViene.setTipo_solicitud_requerimiento(pedidoViene.getTipo_solicitud_requerimiento()); 
					lstPedido = (List<SolicitudReqDTO>) sesion.selectList("pedido.SQL_listaPedidoXTipoFaltaDevolver",pedidoViene);
				} 
			}
		}  finally {
			sesion.close();
		}
		return lstPedido;
	}
 
 
 
 

 
    
	
	
	
	
}

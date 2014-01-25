package com.hotel.mantenimiento.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mysql.jdbc.log.LogFactory;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.util.MySqlConexion;

public class MySqlArticuloDAO implements ArticuloDAO {
	private final Log log = org.apache.commons.logging.LogFactory.getLog(getClass());

	SqlSessionFactory sqlMapper = MySqlConexion.getMapper();

	@Override
	public Boolean registrarProducto(ArticuloDTO objProducto) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.insert("articulo.SQL_registraProducto", objProducto);
			session.commit();
			result = true;
		} catch (Exception e) { 
			result = false;
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarProductos(ArticuloDTO producto) throws Exception  {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				lstProductos = (List<ArticuloDTO>) sesion.selectList("articulo.SQL_listaProductos");
			} else { 
				producto.setDesc_articulo("%"+producto.getDesc_articulo()+"%");
				producto.setUnidadMedida("%"+producto.getUnidadMedida()+"%");
				if(log.isDebugEnabled()){
				log.debug("articulo desproducto:"+producto.getDesc_articulo());
				log.debug("articulo umedida:"+producto.getUnidadMedida());
				}

					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProducto", producto  );
			}
		}  finally {
			sesion.close();
		}
		return lstProductos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarProductosPaginados(ArticuloDTO producto, Integer inicio, Integer tamano)throws Exception  {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				ArticuloDTO prod = new ArticuloDTO();
				prod.setInicio(inicio);
				prod.setTamano(tamano);
				lstProductos = (List<ArticuloDTO>) sesion.selectList(
						"articulo.SQL_listaProductosPaginados", prod);
			} else {   
				    producto.setUnidadMedida("%" + producto.getUnidadMedida()
						+ "%");
					producto.setDesc_articulo("%" + producto.getDesc_articulo()
							+ "%");
					producto.setInicio(inicio);
					producto.setTamano(tamano);
					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProductoPaginados",
							producto); 
			}
		}   finally {
			sesion.close();
		}
		return lstProductos;
	}
	
	//Para el PROVEEDOR!!
	

	@Override
	public ArticuloDTO getProducto(ArticuloDTO productoViene) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		ArticuloDTO producto = new ArticuloDTO();
		try {
			if (productoViene != null) {
				if (productoViene.getCod_articulo() != null) {
					producto = (ArticuloDTO) sesion.selectOne(
							"articulo.SQL_getProductoXCodProd",
							productoViene.getCod_articulo());
				}
			}
		}  finally {
			sesion.close();
		}
		return producto;
	}

	@Override
	public Boolean actualizarProducto(ArticuloDTO objProducto)throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.update("articulo.SQL_updateProducto", objProducto);
			session.commit();
			result = true;
		} catch (Exception e) { 
			session.rollback();
			throw e;
		} finally { 
			session.close();
		}
		return result;
	}

	@Override
	public Boolean eliminarArticulo(ArticuloDTO objProducto) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.delete("articulo.SQL_deleteProducto",
					objProducto.getCod_articulo());
			session.commit();
			result = true;
		} catch (Exception e) { 
			result = false;
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarProductosIdProveePaginados(
			ArticuloDTO producto, Integer idProvee, Integer inicio,
			Integer tamano) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				ArticuloDTO prod = new ArticuloDTO();
				prod.setCod_proveedor(idProvee);
				prod.setInicio(inicio);
				prod.setTamano(tamano);
				lstProductos = (List<ArticuloDTO>) sesion.selectList(
						"articulo.SQL_listaProductosPaginadosIdProvee", prod);
			} else {
					producto.setCod_proveedor(idProvee);
					producto.setDesc_articulo("%" + producto.getDesc_articulo()
							+ "%");
					producto.setInicio(inicio);
					producto.setTamano(tamano);
					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProductoPaginadosIdProvee",
							producto);
			}
		}  finally {
			sesion.close();
		}
		return lstProductos;
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarProductosIdProvee(ArticuloDTO producto,
			Integer idProvee)throws Exception  {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				ArticuloDTO prod = new ArticuloDTO();
				prod.setCod_proveedor(idProvee);
				lstProductos = (List<ArticuloDTO>) sesion.selectList("articulo.SQL_listaProductosIdProvee",prod);
			} else {
					producto.setCod_proveedor(idProvee);
					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProductoIdProvee", "%"
									+ producto.getDesc_articulo() + "%");
			}
		} finally {
			sesion.close();
		}
		return lstProductos;
	} 
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarHabilitadosProductosPaginados(
			ArticuloDTO producto, Integer inicio, Integer tamano)throws Exception  {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				ArticuloDTO prod = new ArticuloDTO();
				prod.setInicio(inicio);
				prod.setTamano(tamano);
				lstProductos = (List<ArticuloDTO>) sesion.selectList(
						"articulo.SQL_listaProductosPaginadosHabilitados", prod);
			} else { 
					producto.setDesc_articulo("%" + producto.getDesc_articulo()
							+ "%");
					producto.setInicio(inicio);
					producto.setTamano(tamano);
					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProductoPaginadosHabilitados",
							producto);
			}
		} finally {
			sesion.close();
		}
		return lstProductos;
	} 
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarHabilitadosProductos(ArticuloDTO producto)throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				lstProductos = (List<ArticuloDTO>) sesion.selectList("articulo.SQL_listaProductosHabilitados");
			} else { 
					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProductoHabilitados", "%"
									+ producto.getDesc_articulo() + "%");
			}
		} finally {
			sesion.close();
		}
		return lstProductos;
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarHabilitadosProductosIdProveePaginados  (
			Object  producto2, Integer idProvee, Integer inicio,
			Integer tamano) throws Exception{
		ArticuloDTO producto = (ArticuloDTO) producto2;
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				ArticuloDTO prod = new ArticuloDTO();
				prod.setCod_proveedor(idProvee);
				prod.setInicio(inicio);
				prod.setTamano(tamano);
				lstProductos = (List<ArticuloDTO>) sesion.selectList(
						"articulo.SQL_listaProductosPaginadosIdProveeHabilitados", prod);
			} else { 
					producto.setCod_proveedor(idProvee);
					producto.setDesc_articulo("%" + producto.getDesc_articulo()
							+ "%");
					producto.setInicio(inicio);
					producto.setTamano(tamano);
					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProductoPaginadosIdProveeHabilitados",
							producto); 
			}
		}  finally {
			sesion.close();
		}
		return lstProductos;
	}

 
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloDTO> buscarHabilitadosProductosIdProvee(Object object,
			Integer idProvee) throws Exception {
		ArticuloDTO producto = (ArticuloDTO) object;
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloDTO> lstProductos = new ArrayList<ArticuloDTO>();
		try {
			if (producto == null) {
				ArticuloDTO prod = new ArticuloDTO();
				prod.setCod_proveedor(idProvee);
				lstProductos = (List<ArticuloDTO>) sesion.selectList("articulo.SQL_listaProductosIdProveeHabilitados",prod);
			} else { 
					producto.setCod_proveedor(idProvee);
					producto.setDesc_articulo( "%"+ producto.getDesc_articulo() + "%");
					lstProductos = (List<ArticuloDTO>) sesion.selectList(
							"articulo.SQL_listaProductosDescProductoIdProveeHabilitados",producto);
			}
		}  finally {
			sesion.close();
		}
		return lstProductos;
	}
 
 

}

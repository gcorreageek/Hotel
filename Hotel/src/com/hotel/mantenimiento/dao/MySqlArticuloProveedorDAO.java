/**
 * MySqlProductoProveedorDAO 24/07/2013
 */
package com.hotel.mantenimiento.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;
import com.hotel.util.MySqlConexion;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class MySqlArticuloProveedorDAO implements ArticuloProveedorDAO {
 
	SqlSessionFactory sqlMapper = MySqlConexion.getMapper();
 
	@Override
	public ArticuloProveedorDTO getProductoProveedor(
			ArticuloProveedorDTO productoProveedorViene) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		ArticuloProveedorDTO productoProveedor = new ArticuloProveedorDTO();
		try {
			if (productoProveedorViene != null) {
				if (productoProveedorViene.getCod_articulo_proveedor() != null) {
					productoProveedor = (ArticuloProveedorDTO) sesion.selectOne(
							"productoproveedor.SQL_getProductoProveedorXCodProductoProveedor",
							productoProveedorViene.getCod_articulo_proveedor());
				}
			}
		} finally {
			sesion.close();
		}
		return productoProveedor;
	}
 
	@Override
	public List<ArticuloProveedorDTO> buscarProductoProveedorPaginados(
			ArticuloProveedorDTO productoProveedor, Integer inicio,
			Integer tamano) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloProveedorDTO> lstProductosProveedor = new ArrayList<ArticuloProveedorDTO>();
		try {
			if (productoProveedor == null) {
				ArticuloProveedorDTO prodProvee = new ArticuloProveedorDTO();
				prodProvee.setInicio(inicio);
				prodProvee.setTamano(tamano);
				lstProductosProveedor = (List<ArticuloProveedorDTO>) sesion.selectList(
						"productoproveedor.SQL_listaProductoProveedorPaginados", prodProvee);
			} else {
				System.out.println("razon:"+productoProveedor.getRaz_social());
				System.out.println("desc:"+productoProveedor.getDesc_articulo());
				if (productoProveedor.getRaz_social()!=null && productoProveedor.getDesc_articulo()!=null) {
					System.out.println("eee");
					productoProveedor.setDesc_articulo("%" + productoProveedor.getDesc_articulo()+ "%");
					productoProveedor.setRaz_social("%" + productoProveedor.getRaz_social()+ "%");
					productoProveedor.setInicio(inicio);
					productoProveedor.setTamano(tamano);
					lstProductosProveedor = (List<ArticuloProveedorDTO>) sesion.selectList(
							"productoproveedor.SQL_listaProductoProveedorRazonSocialDescProductoPaginados",
							productoProveedor);
				}
			}
		} finally {
			sesion.close();
		}
		return lstProductosProveedor;
	}
 
	@Override
	public List<ArticuloProveedorDTO> buscarProductoProveedor(
			ArticuloProveedorDTO productoProveedor) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		List<ArticuloProveedorDTO> lstProductosProveedor = new ArrayList<ArticuloProveedorDTO>();
		try {
			if (productoProveedor == null) {
				lstProductosProveedor = (List<ArticuloProveedorDTO>) sesion
						.selectList("productoproveedor.SQL_listaProductoProveedor");
			} else {
				if (!productoProveedor.getRaz_social().isEmpty() || !productoProveedor.getDesc_articulo().isEmpty()) {
					productoProveedor.setDesc_articulo("%" + productoProveedor.getDesc_articulo()+ "%");
					productoProveedor.setRaz_social("%" + productoProveedor.getRaz_social()+ "%");
					lstProductosProveedor = (List<ArticuloProveedorDTO>) sesion.selectList(
							"productoproveedor.SQL_listaProductoProveedorRazonSocialAndDescProducto", productoProveedor);
				}
			}
		}  finally {
			sesion.close();
		}
		return lstProductosProveedor;
	}
 
	@Override
	public Boolean registrarProductoProveedor(
			ArticuloProveedorDTO objProductoProvedor) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.insert("productoproveedor.SQL_registraProductoProveedor", objProductoProvedor);
			session.commit();
			result = true;
		} finally {
			session.close();
		}
		return result;
	}
 
	@Override
	public Boolean actualizarProductoProveedor(
			ArticuloProveedorDTO objProductoProvedor) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.update("productoproveedor.SQL_updateProductoProveedor", objProductoProvedor);
			session.commit();
			result = true;
		} finally {
			session.close();
		}
		return result;
	}
 
	@Override
	public Boolean eliminarArticuloProveedor(
			ArticuloProveedorDTO objProductoProvedor) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.delete("productoproveedor.SQL_deleteProductoProveedor",
					objProductoProvedor.getCod_articulo_proveedor());
			session.commit();
			result = true;
		}  finally {
			session.close();
		}
		return result;
	}
 
	@Override
	public ArticuloProveedorDTO getProductoProveedorXIdProdAndIdProvee(
			ArticuloProveedorDTO productoProveedorViene) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		ArticuloProveedorDTO productoProveedor = new ArticuloProveedorDTO();
		try {  
			productoProveedor = (ArticuloProveedorDTO) sesion.selectOne(
							"productoproveedor.SQL_getProductoProveedorXcodProdAndCodProvee",
							productoProveedorViene);
			 
		} finally {
			sesion.close();
		}
		return productoProveedor;
	}

	 
}

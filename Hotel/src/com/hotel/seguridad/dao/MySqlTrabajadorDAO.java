package com.hotel.seguridad.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.seguridad.bean.MenuDTO;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.util.MySqlConexion;

public class MySqlTrabajadorDAO implements TrabajadorDAO {
	SqlSessionFactory sqlMapper = MySqlConexion.getMapper();
	
	@Override
	public TrabajadorDTO buscaUsuario(String id_usuario) {
		SqlSession sesion =sqlMapper.openSession();
		TrabajadorDTO objUsu = new TrabajadorDTO(); 
		try {
			objUsu= (TrabajadorDTO)sesion.selectOne("trabajador.SQL_getUsuarioLogueo",id_usuario);
		} catch (Exception e) {
		System.out.println(e  +" "+this.getClass().toString()); 
		} finally{
			sesion.close();
		}
		return objUsu;
	} 
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDTO> obtenerMenu(Integer codCargo) {
		SqlSession sesion = sqlMapper.openSession();
		List<MenuDTO> listaMenu = new ArrayList<MenuDTO>();
		try {
			listaMenu = (List<MenuDTO>) sesion.selectList("menu.SQL_listaMenuXCargo",codCargo);
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			sesion.close();
		} 
		return listaMenu;
	} 
	
	
	@Override
	public TrabajadorDTO getUsuario(Integer codusuario) throws Exception {
		SqlSession sesion =sqlMapper.openSession();
		TrabajadorDTO objUsuario = new TrabajadorDTO(); 
		try {
			objUsuario= (TrabajadorDTO)sesion.selectOne("trabajador.SQL_getUsuario",codusuario);
		} finally{
			sesion.close();
		}
		return objUsuario;
	} 
	@SuppressWarnings("unchecked")
	@Override
	public List<TrabajadorDTO> buscarUsuarioPaginados(TrabajadorDTO usuario,
			Integer inicio, Integer tamano) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		List<TrabajadorDTO> lstUsuario = new ArrayList<TrabajadorDTO>();
		try {
			if (usuario == null) {
				TrabajadorDTO provee = new TrabajadorDTO();
				provee.setInicio(inicio);
				provee.setTamano(tamano);
				lstUsuario = (List<TrabajadorDTO>) sesion.selectList(
						"trabajador.SQL_listaUsuarioPaginados", provee);
			} else {
				if (!usuario.getNom_trabajador().isEmpty()) {
					usuario.setNom_trabajador("%" + usuario.getNom_trabajador()+ "%");
					usuario.setInicio(inicio);
					usuario.setTamano(tamano);
					lstUsuario = (List<TrabajadorDTO>) sesion.selectList(
							"trabajador.SQL_listaUsuarioNom_usuarioPaginados",
							usuario);
				}
			}
		}  finally {
			sesion.close();
		}
		return lstUsuario;
	} 
	@SuppressWarnings("unchecked")
	@Override
	public List<TrabajadorDTO> buscarUsuario(TrabajadorDTO usuario) throws Exception {
		SqlSession sesion = sqlMapper.openSession();
		List<TrabajadorDTO> lstUsuario= new ArrayList<TrabajadorDTO>();
		try {
			if (usuario == null) {
				lstUsuario = (List<TrabajadorDTO>) sesion.selectList("trabajador.SQL_listaUsuario");
			} else {
				if (!usuario.getNom_trabajador().isEmpty()) {
					lstUsuario = (List<TrabajadorDTO>) sesion.selectList( 
							"trabajador.SQL_listaNom_usuario", "%"
									+ usuario.getNom_trabajador() + "%");
				}
			}
		} finally {
			sesion.close();
		}
		return lstUsuario;
	} 
	@Override
	public Object registrarUsuario(TrabajadorDTO objUsuario) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.insert("trabajador.SQL_registraUsuario", objUsuario);
			session.commit();
			result = true;
		}  finally {
			session.close();
		}
		return result;
	} 
	@Override
	public Object actualizarUsuario(TrabajadorDTO objUsuario) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			System.out.println("1:"+objUsuario.getCod_trabajador());
			System.out.println("2:"+objUsuario.getHabilitado());
			System.out.println("3:"+objUsuario.getUsu_trabajador());
			session.update("trabajador.SQL_updateUsuario", objUsuario);
			session.commit();
			result = true;
		} finally {
			session.close();
		}
		return result;
	} 
	@Override
	public Object eliminarTrabajador(TrabajadorDTO objUsuario) throws Exception {
		Boolean result = false;
		SqlSession session = sqlMapper.openSession();
		try {
			session.delete("trabajador.SQL_deleteUsuario", objUsuario.getCod_trabajador());
			session.commit();
			result = true;
		} finally {
			session.close();
		}
		return result;
	} 
 

}

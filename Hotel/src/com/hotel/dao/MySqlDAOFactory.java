package com.hotel.dao;

import com.hotel.cotizacion.dao.CotizacionDAO;
import com.hotel.cotizacion.dao.CotizacionDetalleDAO;
import com.hotel.cotizacion.dao.MySqlCotizacionDAO;
import com.hotel.cotizacion.dao.MySqlCotizacionDetalleDAO;
import com.hotel.informeexterno.dao.InformeExternoDAO;
import com.hotel.informeexterno.dao.InformeExternoDetalleDAO;
import com.hotel.informeexterno.dao.MySqlInformeExternoDAO;
import com.hotel.informeexterno.dao.MySqlInformeExternoDetalleDAO;
import com.hotel.informeinterno.dao.InformeInternoDAO;
import com.hotel.informeinterno.dao.InformeInternoDetalleDAO;
import com.hotel.informeinterno.dao.MySqlInformeInternoDAO;
import com.hotel.informeinterno.dao.MySqlInformeInternoDetalleDAO;
import com.hotel.mantenimiento.dao.CategoriaDAO;
import com.hotel.mantenimiento.dao.MySqlCategoriaDAO;
import com.hotel.mantenimiento.dao.MySqlArticuloDAO;
import com.hotel.mantenimiento.dao.MySqlArticuloProveedorDAO;
import com.hotel.mantenimiento.dao.MySqlProveedorDAO;
import com.hotel.mantenimiento.dao.MySqlUmedidaDAO;
import com.hotel.mantenimiento.dao.ArticuloDAO;
import com.hotel.mantenimiento.dao.ArticuloProveedorDAO;
import com.hotel.mantenimiento.dao.ProveedorDAO;
import com.hotel.mantenimiento.dao.UmedidaDAO;
import com.hotel.ordencompra.dao.MySqlOrdenCompraDAO;
import com.hotel.ordencompra.dao.MySqlOrdenCompraDetalleDAO;
import com.hotel.ordencompra.dao.OrdenCompraDAO;
import com.hotel.ordencompra.dao.OrdenCompraDetalleDAO;
import com.hotel.solicitudreq.dao.MySqlSolicitudReqDetalleDAO;
import com.hotel.solicitudreq.dao.MySqlSolicitudPedidoDAO;
import com.hotel.solicitudreq.dao.SolicitudReqDetalleDAO;
import com.hotel.solicitudreq.dao.SolicitudPedidoDAO;
import com.hotel.seguridad.dao.AccesoDAO;
import com.hotel.seguridad.dao.AreaDAO;
import com.hotel.seguridad.dao.CargoDAO;
import com.hotel.seguridad.dao.MenuDAO;
import com.hotel.seguridad.dao.MySqlAccesoDAO;
import com.hotel.seguridad.dao.MySqlAreaDAO;
import com.hotel.seguridad.dao.MySqlCargoDAO;
import com.hotel.seguridad.dao.MySqlMenuDAO;
import com.hotel.seguridad.dao.MySqlTrabajadorDAO;
import com.hotel.seguridad.dao.TrabajadorDAO;


public class MySqlDAOFactory extends DAOFactory {

	@Override
	public TrabajadorDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new MySqlTrabajadorDAO();
	}

	@Override
	public SolicitudPedidoDAO getSolicitudPedidoDAO() {
		// TODO Auto-generated method stub
		return new MySqlSolicitudPedidoDAO();
	}

	@Override
	public ArticuloDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return new MySqlArticuloDAO();
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		// TODO Auto-generated method stub
		return new MySqlCategoriaDAO();
	}

	@Override
	public UmedidaDAO getUmedidaDAO() {
		// TODO Auto-generated method stub
		return new MySqlUmedidaDAO();
	}

	@Override
	public CargoDAO getCargoDAO() {
		return new MySqlCargoDAO();
	}
 
	@Override
	public AreaDAO getAreaDAO() {
		return new MySqlAreaDAO();
	} 
	@Override
	public ProveedorDAO getProveedorDAO() {
		return new MySqlProveedorDAO();
	}
 
	@Override
	public ArticuloProveedorDAO getProductoProveedorDAO() {
		return new MySqlArticuloProveedorDAO();
	} 
	@Override
	public SolicitudReqDetalleDAO getPedidoDetalleDAO() {
		return new MySqlSolicitudReqDetalleDAO();
	}
 
	@Override
	public CotizacionDAO getCotizacionDAO() {
		return new MySqlCotizacionDAO();
	}
 
	@Override
	public CotizacionDetalleDAO getCotizacionDetalleDAO() {
		return new MySqlCotizacionDetalleDAO();
	} 
	@Override
	public OrdenCompraDAO getOrdenCompraDAO() {
		return new MySqlOrdenCompraDAO();
	} 
	@Override
	public OrdenCompraDetalleDAO getOrdenCompraDetalleDAO() {
		return new MySqlOrdenCompraDetalleDAO();
	} 
	@Override
	public InformeInternoDAO getInformeInternoDAO() {
		return new MySqlInformeInternoDAO();
	} 
	@Override
	public InformeInternoDetalleDAO getInformeInternoDetalleDAO() {
		return new MySqlInformeInternoDetalleDAO();
	} 
	@Override
	public InformeExternoDAO getInformeExternoDAO() {
		return new MySqlInformeExternoDAO();
	} 
	@Override
	public InformeExternoDetalleDAO getInformeExternoDetalleDAO() {
		return new MySqlInformeExternoDetalleDAO();
	} 
	@Override
	public AccesoDAO getAccesoDAO() {
		return new MySqlAccesoDAO();
	} 
	@Override
	public MenuDAO getMenuDAO() {
		return new MySqlMenuDAO();
	}

 
 
}

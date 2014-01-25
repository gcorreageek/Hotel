package com.hotel.dao;

import com.hotel.cotizacion.dao.CotizacionDAO;
import com.hotel.cotizacion.dao.CotizacionDetalleDAO;
import com.hotel.informeexterno.dao.InformeExternoDAO;
import com.hotel.informeexterno.dao.InformeExternoDetalleDAO;
import com.hotel.informeinterno.dao.InformeInternoDAO;
import com.hotel.informeinterno.dao.InformeInternoDetalleDAO;
import com.hotel.mantenimiento.dao.CategoriaDAO;
import com.hotel.mantenimiento.dao.ArticuloDAO;
import com.hotel.mantenimiento.dao.ArticuloProveedorDAO;
import com.hotel.mantenimiento.dao.ProveedorDAO;
import com.hotel.mantenimiento.dao.UmedidaDAO;
import com.hotel.ordencompra.dao.OrdenCompraDAO;
import com.hotel.ordencompra.dao.OrdenCompraDetalleDAO;
import com.hotel.solicitudreq.dao.SolicitudReqDetalleDAO;
import com.hotel.solicitudreq.dao.SolicitudPedidoDAO;
import com.hotel.seguridad.dao.AccesoDAO;
import com.hotel.seguridad.dao.AreaDAO;
import com.hotel.seguridad.dao.CargoDAO;
import com.hotel.seguridad.dao.MenuDAO;
import com.hotel.seguridad.dao.TrabajadorDAO;



public abstract class DAOFactory {
	public static final int MYSQL=1;
	public static final int ORACLE=2;
	
	public abstract TrabajadorDAO getUsuarioDAO();
	public abstract CargoDAO getCargoDAO();
	public abstract AreaDAO getAreaDAO();
	public abstract AccesoDAO getAccesoDAO();
	public abstract MenuDAO getMenuDAO();
	
	//producto
	public abstract ArticuloDAO getProductoDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	public abstract UmedidaDAO getUmedidaDAO();
	public abstract ProveedorDAO getProveedorDAO();
	public abstract ArticuloProveedorDAO getProductoProveedorDAO();
	//pedido
	public abstract SolicitudPedidoDAO getSolicitudPedidoDAO();
	public abstract SolicitudReqDetalleDAO getPedidoDetalleDAO();
	//cotizacion
	public abstract CotizacionDAO getCotizacionDAO();
	public abstract CotizacionDetalleDAO getCotizacionDetalleDAO();
	//oc
	public abstract OrdenCompraDAO getOrdenCompraDAO();
	public abstract OrdenCompraDetalleDAO getOrdenCompraDetalleDAO();
	//informes int
	public abstract InformeInternoDAO getInformeInternoDAO();
	public abstract InformeInternoDetalleDAO getInformeInternoDetalleDAO();
	//informes ext
	public abstract InformeExternoDAO getInformeExternoDAO();
	public abstract InformeExternoDetalleDAO getInformeExternoDetalleDAO();
	
	
	public static DAOFactory getDAOFactory(int wichFactory){
		
		switch (wichFactory) {
		
		case MYSQL:
			return new MySqlDAOFactory();
		case ORACLE:
			//return new MySqlDAOFactory();
		default:
			return null;
		}

	}
    
    
}

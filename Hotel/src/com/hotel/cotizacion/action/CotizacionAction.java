/**
 * Cotizacion 21/07/2013
 */
package com.hotel.cotizacion.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.hotel.cotizacion.bean.CotizacionDTO;
import com.hotel.cotizacion.bean.CotizacionDetalleDTO;
import com.hotel.cotizacion.service.CotizacionDetalleService;
import com.hotel.cotizacion.service.CotizacionService;
import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;
import com.hotel.mantenimiento.service.ArticuloProveedorService;
import com.hotel.mantenimiento.service.ArticuloService;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.util.Constantes;
import com.hotel.util.UtilSigal;

/**
 * @author Gustavo A. Correa C.
 *
 */
@ParentPackage("Hotel")
public class CotizacionAction extends ActionSupport {

	private Map<String, Object> lasesion = ActionContext.getContext().getSession(); 
	private List<CotizacionDetalleDTO> lstDetCoti ;
	CotizacionService objCotizacionServ = new CotizacionService();
	CotizacionDetalleService objCotizacionDetalleServ = new CotizacionDetalleService();
	ArticuloService objProductoServ = new ArticuloService(); 
	ArticuloProveedorService objProdProveeServ = new ArticuloProveedorService();
//	idProd cantidad
	private Integer idProd;
	private Integer cantidad;
	private CotizacionDTO objCotizacion;
	private Integer rsult;
	private String mensaje;
	private Integer inicio;
	private List<CotizacionDTO> lstCotizacion ;
	private Integer numeroPaginasModalCotizacion;
	private String fechaComienzaInicio;
	private String fechaTerminaFin;
	
	private String tipoPedido;
	private String fechaEntrega;
	private String fechaDevolucion;
	
	
	@Action(value="/mainCotizacion",results={@Result(name="success",type="tiles",location="d_maincotizacion")})
	public String mainCotizacion(){ 
		lasesion.remove("lstDetCoti");
		return SUCCESS;
	}
	@Action(value="/agregarDetalleCotizacion",results={
			@Result(name="error",location="/paginas/cotizacion/detalle_cotizacion.jsp"),
			@Result(name="success",location="/paginas/cotizacion/detalle_cotizacion.jsp")})
	public String agregarDetalleSolicitudReq(){
		lstDetCoti = (List<CotizacionDetalleDTO>) lasesion.get("lstDetCoti");
		
		CotizacionDetalleDTO objDetalle = new CotizacionDetalleDTO(); 
		ArticuloProveedorDTO prodProvee = new ArticuloProveedorDTO();
		prodProvee.setCod_articulo(getIdProd());
		prodProvee.setCod_proveedor(objCotizacion.getCod_proveedor());
		
		try {
			prodProvee = objProdProveeServ.getProductoProveedorIdProdAndIdProvee(prodProvee);
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		objDetalle.setCod_articulo_proveedor(prodProvee.getCod_articulo_proveedor());
		objDetalle.setCod_articulo(getIdProd());
		objDetalle.setDesc_articulo(prodProvee.getDesc_articulo());
		objDetalle.setUnidadMedida(prodProvee.getUnidadMedida());
		objDetalle.setCant_detallecotizacion(getCantidad());
		if(lstDetCoti==null){
			lstDetCoti = new ArrayList<CotizacionDetalleDTO>();
		}
		for (CotizacionDetalleDTO prod2 : lstDetCoti) {
			if(prod2.getCod_articulo().equals(getIdProd())){
				this.rsult=0;
				this.mensaje="Producto ya existe!";
				return ERROR;
			}
		}
		lstDetCoti.add(objDetalle); 
		lasesion.put("lstDetCoti", lstDetCoti);
		return SUCCESS;
	}
//	eliminarDetalleCotizacion
	@Action(value="/eliminarDetalleCotizacion",results={@Result(name="success",location="/paginas/cotizacion/detalle_cotizacion.jsp")})
	public String eliminarDetalleCotizacion(){
		lstDetCoti = (List<CotizacionDetalleDTO>) lasesion.get("lstDetCoti");
		for (int i = 0; i < lstDetCoti.size(); i++) { 
			CotizacionDetalleDTO det = lstDetCoti.get(i); 
			if(det.getCod_articulo().equals(getIdProd())){
				lstDetCoti.remove(i);
			}
		}  
		lasesion.put("lstDetCoti", lstDetCoti);
		return SUCCESS;
	}
	@Action(value="/guardarCotizacion",results={@Result(name="success",location="/paginas/cotizacion/cotizacion_mensaje.jsp")})
	public String guardarCotizacion(){
		try {
			System.out.println("Guadrarr!"); 
			//validar las fechas
			java.sql.Date fechaEntregaS = UtilSigal.fechaDateSql(getFechaEntrega());
			java.sql.Date fechaDevolucionS = UtilSigal.fechaDateSql(getFechaDevolucion());
			//fecha1 es anterior a la fecha2
			
			if("Prestamo".equals(getTipoPedido())){
				if(fechaEntregaS==null){
					this.rsult=0;
					this.mensaje="Fecha de Entrega vacia";
					return SUCCESS;
				}
				if(fechaDevolucionS==null){
					this.rsult=0;
					this.mensaje="Fecha de Devolucion vacio";
					return SUCCESS;
				}
				if(fechaDevolucionS.before(fechaEntregaS)){
					this.rsult=0;
					this.mensaje="Fecha de Entrega es mayor a la Fecha de Devolucion";
					return SUCCESS;
				} 	
			}
			
			TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
			objCotizacion.setCod_trabajador(usuario.getCod_trabajador()); 
			objCotizacion.setFecha_devolucion_cotizacion(UtilSigal.fechaDateSql(getFechaDevolucion()) );
			objCotizacion.setFecha_entrega_cotizacion((UtilSigal.fechaDateSql(getFechaEntrega()))); 
			objCotizacion.setTipo_cotizacion(getTipoPedido());
			if(objCotizacion.getCod_proveedor()==null){
				this.rsult=0;
				this.mensaje="Agregar Proveedor";
				return SUCCESS;
			}
			lstDetCoti = (ArrayList<CotizacionDetalleDTO>) lasesion.get("lstDetCoti"); 
			if(lstDetCoti==null){
				this.rsult=0;
				this.mensaje="Agregar detalle";
				return SUCCESS;
			}
			objCotizacionServ.registrarCotizacion(objCotizacion, lstDetCoti);
			lasesion.remove("lstDetCoti");
			this.rsult=1;
			this.mensaje="Se guardo correctamente!";
		} catch (Exception e) {
			System.out.println("Try:"+e);
			e.printStackTrace();
			this.rsult=0;
			this.mensaje="Ocurrio un error al Grabar";
		} 
		return SUCCESS;
	}
	@Action(value = "/getDetalleCotizacion", results = { @Result(name = "success", location = "/paginas/cotizacion/detalle_cotizacion.jsp") })
	public String getDetalleCotizacion() { 
		try {
			lstDetCoti = (List<CotizacionDetalleDTO>) lasesion.get("lstDetCoti");
			if(lstDetCoti==null){
				lstDetCoti = new ArrayList<CotizacionDetalleDTO>();
			}
			CotizacionDetalleDTO det = new CotizacionDetalleDTO();
			det.setCod_cotizacion(getObjCotizacion().getCod_cotizacion()); 
			this.lstDetCoti = objCotizacionDetalleServ.listaCotizacionXidCotizacion(det);
			lasesion.put("lstDetCoti", lstDetCoti); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//Modal
	@Action(value = "/listarCotizacionPagModal", results = { @Result(name = "success", location = "/paginas/cotizacion/buscar_cotizacion.jsp") })
	public String listarCotizacionPagModal() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		} 
		try {  
			lstCotizacion =   objCotizacionServ.listaCotizacionPaginado(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	} 
	@Action(value = "/buscarCotizacionPagModal", results = { @Result(name = "success", location = "/paginas/cotizacion/buscar_cotizacion.jsp") })
	public String buscarCotizacionPagModal() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			int r=0;
			if(!"".equals(objCotizacion.getNom_trabajador())){
				r=1; 
			}else if(!"".equals(getFechaComienzaInicio()) ||  !"".equals(getFechaTerminaFin())){
				r=1;
				objCotizacion.setFechaInicio(UtilSigal.fechaDateSql(getFechaComienzaInicio()));
				objCotizacion.setFechaFin(UtilSigal.fechaDateSql(getFechaTerminaFin()));
			}
			if(r==0){
				lstCotizacion =   objCotizacionServ.listaCotizacionPaginado(0, Constantes.FILAS_X_PAGINA);
			}else{
				lstCotizacion = objCotizacionServ.buscarCotizacionPaginado(objCotizacion, comienzo, Constantes.FILAS_X_PAGINA);
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "/listarCotizacionTotal", results = { @Result(name = "success", location = "/paginas/cotizacion/cotizacion_listado_total.jsp") })
	public String listarCotizacionTotal() { 
		try {
			System.out.println("totla:"+objCotizacionServ.listaCotizacionTotal());
			this.numeroPaginasModalCotizacion= UtilSigal.totalDePaginas(objCotizacionServ.listaCotizacionTotal());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("nunmeroPaginas:"+numeroPaginasModalCotizacion); 
		return SUCCESS;
	}
	@Action(value = "/buscarCotizacionTotal", results = { @Result(name = "success", location = "/paginas/cotizacion/cotizacion_buscar_total.jsp") })
	public String buscarCotizacionTotal() { 
		try {
//			int r=0;
//			if(!"".equals(objCotizacion.getNom_usuario())){
//				r=1; 
//			}else if(!"".equals(getFechaComienzaInicio()) ||  !"".equals(getFechaTerminaFin())){
//				r=1;
				objCotizacion.setFechaInicio(UtilSigal.fechaDateSql(getFechaComienzaInicio()));
				objCotizacion.setFechaFin(UtilSigal.fechaDateSql(getFechaTerminaFin()));
//			}
//			if(r==0){
//				this.numeroPaginasModalCotizacion= UtilSigal.totalDePaginas(objCotizacionServ.listaCotizacionTotal());
//			}else{
				this.numeroPaginasModalCotizacion= UtilSigal.totalDePaginas(objCotizacionServ.buscarCotizacionTotal(objCotizacion));	
//			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	} 
	
	
	
	public Integer getIdProd() {
		return idProd;
	}
	public void setIdProd(Integer idProd) {
		this.idProd = idProd;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public CotizacionDTO getObjCotizacion() {
		return objCotizacion;
	}
	public void setObjCotizacion(CotizacionDTO objCotizacion) {
		this.objCotizacion = objCotizacion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<CotizacionDetalleDTO> getLstDetCoti() {
		return lstDetCoti;
	}
	public void setLstDetCoti(List<CotizacionDetalleDTO> lstDetCoti) {
		this.lstDetCoti = lstDetCoti;
	}
	public Integer getInicio() {
		return inicio;
	}
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}
	public List<CotizacionDTO> getLstCotizacion() {
		return lstCotizacion;
	}
	public void setLstCotizacion(List<CotizacionDTO> lstCotizacion) {
		this.lstCotizacion = lstCotizacion;
	}
	public Integer getNumeroPaginasModalCotizacion() {
		return numeroPaginasModalCotizacion;
	}
	public void setNumeroPaginasModalCotizacion(Integer numeroPaginasModalCotizacion) {
		this.numeroPaginasModalCotizacion = numeroPaginasModalCotizacion;
	}
	public Integer getRsult() {
		return rsult;
	}
	public void setRsult(Integer rsult) {
		this.rsult = rsult;
	}
	public String getFechaComienzaInicio() {
		return fechaComienzaInicio;
	}
	public void setFechaComienzaInicio(String fechaComienzaInicio) {
		this.fechaComienzaInicio = fechaComienzaInicio;
	}
	public String getFechaTerminaFin() {
		return fechaTerminaFin;
	}
	public void setFechaTerminaFin(String fechaTerminaFin) {
		this.fechaTerminaFin = fechaTerminaFin;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	} 

	
	
}

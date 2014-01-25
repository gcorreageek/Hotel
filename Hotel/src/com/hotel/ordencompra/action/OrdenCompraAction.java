/**
 * OrdenCompraAction 21/07/2013
 */
package com.hotel.ordencompra.action;

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
import com.hotel.ordencompra.bean.OrdenCompraDTO;
import com.hotel.ordencompra.bean.OrdenCompraDetalleDTO;
import com.hotel.ordencompra.service.OrdenCompraDetalleService;
import com.hotel.ordencompra.service.OrdenCompraService;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.util.Constantes;
import com.hotel.util.UtilSigal;

/**
 * @author Gustavo A. Correa C.
 *
 */
@ParentPackage("Hotel")
public class OrdenCompraAction extends ActionSupport {

	Map<String, Object> lasesion = ActionContext.getContext().getSession(); 
	OrdenCompraService objOrdenCompraServ = new OrdenCompraService(); 
	OrdenCompraDetalleService objOrdenCompraDetalleServ = new OrdenCompraDetalleService(); 
	
	
	private OrdenCompraDTO objOrdenCompra;
	private Integer rsult;
	private String mensaje;
	private Integer inicio;
	private List<OrdenCompraDTO> lstOrdenCompra ;
	private List<OrdenCompraDetalleDTO> lstDetOC ;
	private Integer numeroPaginasModalOrdenCompra;
	
	private String tipoPedido;
	private String fechaEntrega;
	private String fechaDevolucion;
	
	@Action(value="/mainOrdenCompra",results={@Result(name="success",type="tiles",location="d_mainordencompra")})
	public String mainCotizacion(){
		lasesion.remove("lstDetCoti");
		return SUCCESS;
	} 
	@Action(value="/guardarOrdenCompra",results={@Result(name="success",location="/paginas/orden_compra/ordencompra_mensaje.jsp")})
	public String guardarOrdenCompra(){
		try {
			System.out.println("Guadrarr OC!"); 
			TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
			System.out.println("d1:"+usuario);
			System.out.println("d2:"+usuario.getCod_trabajador());
			objOrdenCompra.setCod_trabajador(usuario.getCod_trabajador()); 
			objOrdenCompra.setEstado_ordencompra("Pendiente");
			if( objOrdenCompra.getCod_cotizacion()==null){
				this.rsult=0;
				this.mensaje="Agregar Cotizacion";
				return SUCCESS;
			}
			
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
			
			objOrdenCompra.setFecha_devolucion_ordencompra(UtilSigal.fechaDateSql(getFechaDevolucion()) );
			objOrdenCompra.setFecha_entrega_ordencompra(UtilSigal.fechaDateSql(getFechaEntrega()) );
			objOrdenCompra.setTipo_ordencompra(getTipoPedido());
			objOrdenCompra.setEstado_ordencompra("Pendiente");
			
			lstDetOC = new ArrayList<OrdenCompraDetalleDTO>();
			List<CotizacionDetalleDTO> lstDetCotizacion = (List<CotizacionDetalleDTO>) lasesion.get("lstDetCoti");
			if(lstDetCotizacion==null || lstDetCotizacion.isEmpty() ){
				this.rsult=0;
				this.mensaje="Agregar Detalle";
				return SUCCESS;
			} 
			for (CotizacionDetalleDTO cotizacionDetalleDTO : lstDetCotizacion) {
				OrdenCompraDetalleDTO det = new OrdenCompraDetalleDTO();
				det.setCantidad(cotizacionDetalleDTO.getCant_detallecotizacion());
				det.setCod_articulo_proveedor(cotizacionDetalleDTO.getCod_articulo_proveedor());
				lstDetOC.add(det);
			}
			objOrdenCompraServ.registrarOrdenCompra(objOrdenCompra, lstDetOC);
			lasesion.remove("lstDetCoti");
			this.rsult=1;
			this.mensaje="Se guardo correctamente!";
		} catch (Exception e) { 
			e.printStackTrace();
			this.rsult=0;
			this.mensaje="Ocurrio un error al Grabar";
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/getDetalleOrdenCompra", results = { @Result(name = "success", location = "/paginas/orden_compra/detalle_orden_compra.jsp") })
	public String getDetalleOrdenCompra() { 
		try { 
			lstDetOC = new ArrayList<OrdenCompraDetalleDTO>();
			OrdenCompraDetalleDTO det = new OrdenCompraDetalleDTO();
			det.setCod_ordenCompra(objOrdenCompra.getCod_OrdenCompra()); 
			this.lstDetOC = objOrdenCompraDetalleServ.listaOrdenCompraXidOrdenCompra(det); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//Modal
	@Action(value = "/listarOrdenCompraPagModal", results = { @Result(name = "success", location = "/paginas/orden_compra/buscar_orden_compra.jsp") })
	public String listarOrdenCompraPagModal() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		} 
		try {  
			lstOrdenCompra =   objOrdenCompraServ.listaOrdenCompraPaginado(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (OrdenCompraDTO dd : lstOrdenCompra) {
			System.out.println("ee:"+dd.getCod_proveedor()+"|"+dd.getRaz_social());
		}
		return SUCCESS;
	} 
	@Action(value = "/buscarOrdenCompraPagModal", results = { @Result(name = "success", location = "/paginas/orden_compra/buscar_orden_compra.jsp") })
	public String buscarOrdenCompraPagModal() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstOrdenCompra = objOrdenCompraServ.buscarOrdenCompraPaginado(objOrdenCompra, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "/listarOrdenCompraTotal", results = { @Result(name = "success", location = "/paginas/orden_compra/orden_compra_listado_total.jsp") })
	public String listarOrdenCompraTotal() { 
		try {
			System.out.println("totla:"+objOrdenCompraServ.listaOrdenCompraTotal());
			this.numeroPaginasModalOrdenCompra= UtilSigal.totalDePaginas(objOrdenCompraServ.listaOrdenCompraTotal());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("nunmeroPaginas:"+numeroPaginasModalOrdenCompra); 
		return SUCCESS;
	}
	@Action(value = "/buscarOrdenCompraTotal", results = { @Result(name = "success", location = "/paginas/orden_compra/orden_compra_buscar_total.jsp") })
	public String buscarOrdenCompraTotal() { 
		try {
			System.out.println("total reg:"+objOrdenCompraServ.buscarOrdenCompraTotal(objOrdenCompra));
			this.numeroPaginasModalOrdenCompra= UtilSigal.totalDePaginas(objOrdenCompraServ.buscarOrdenCompraTotal(objOrdenCompra));
			System.out.println("total paginas:"+numeroPaginasModalOrdenCompra);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	
	
	
	//Modal
	@Action(value = "/listarOrdenCompraPagModalEntregada", results = { @Result(name = "success", location = "/paginas/orden_compra/buscar_orden_compra.jsp") })
	public String listarOrdenCompraPagModalEntregada() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		} 
		try {  
			lstOrdenCompra =   objOrdenCompraServ.listaOrdenCompraPaginadoEntregada(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (OrdenCompraDTO dd : lstOrdenCompra) {
			System.out.println("ee:"+dd.getCod_proveedor()+"|"+dd.getRaz_social());
		}
		return SUCCESS;
	} 
	@Action(value = "/buscarOrdenCompraPagModalEntregada", results = { @Result(name = "success", location = "/paginas/orden_compra/buscar_orden_compra.jsp") })
	public String buscarOrdenCompraPagModalEntregada() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstOrdenCompra = objOrdenCompraServ.buscarOrdenCompraPaginadoEntregada(objOrdenCompra, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "/listarOrdenCompraTotalEntregada", results = { @Result(name = "success", location = "/paginas/orden_compra/orden_compra_listado_total_entregado.jsp") })
	public String listarOrdenCompraTotalEntregada() { 
		try {
			System.out.println("totla:"+objOrdenCompraServ.listaOrdenCompraTotalEntregada());
			this.numeroPaginasModalOrdenCompra= UtilSigal.totalDePaginas(objOrdenCompraServ.listaOrdenCompraTotalEntregada());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("nunmeroPaginas:"+numeroPaginasModalOrdenCompra); 
		return SUCCESS;
	}
	@Action(value = "/buscarOrdenCompraTotalEntregada", results = { @Result(name = "success", location = "/paginas/orden_compra/orden_compra_buscar_total_entregado.jsp") })
	public String buscarOrdenCompraTotalEntregada() { 
		try {
			System.out.println("total reg:"+objOrdenCompraServ.buscarOrdenCompraTotalEntregada(objOrdenCompra));
			this.numeroPaginasModalOrdenCompra= UtilSigal.totalDePaginas(objOrdenCompraServ.buscarOrdenCompraTotalEntregada(objOrdenCompra));
			System.out.println("total paginas:"+numeroPaginasModalOrdenCompra);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	public OrdenCompraDTO getObjOrdenCompra() {
		return objOrdenCompra;
	}
	public void setObjOrdenCompra(OrdenCompraDTO objOrdenCompra) {
		this.objOrdenCompra = objOrdenCompra;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Integer getInicio() {
		return inicio;
	}
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}
	public List<OrdenCompraDTO> getLstOrdenCompra() {
		return lstOrdenCompra;
	}
	public void setLstOrdenCompra(List<OrdenCompraDTO> lstOrdenCompra) {
		this.lstOrdenCompra = lstOrdenCompra;
	}
	public List<OrdenCompraDetalleDTO> getLstDetOC() {
		return lstDetOC;
	}
	public void setLstDetOC(List<OrdenCompraDetalleDTO> lstDetOC) {
		this.lstDetOC = lstDetOC;
	}
	public Integer getNumeroPaginasModalOrdenCompra() {
		return numeroPaginasModalOrdenCompra;
	}
	public void setNumeroPaginasModalOrdenCompra(
			Integer numeroPaginasModalOrdenCompra) {
		this.numeroPaginasModalOrdenCompra = numeroPaginasModalOrdenCompra;
	}
	public Integer getRsult() {
		return rsult;
	}
	public void setRsult(Integer rsult) {
		this.rsult = rsult;
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

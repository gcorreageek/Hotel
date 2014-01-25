package com.hotel.solicitudreq.action;

//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.service.ArticuloService;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.bean.SolicitudReqDTO;
import com.hotel.solicitudreq.service.SolicitudReqDetalleService;
import com.hotel.solicitudreq.service.SolicitudReqService;
import com.hotel.seguridad.bean.AreaDTO;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.seguridad.dao.AreaDAO;
import com.hotel.seguridad.service.AreaService;
import com.hotel.util.Constantes;
import com.hotel.util.UtilSigal;
/**
 * 
 * @author David
 * @descripcion Clase encargada de la gestion de las solicitudes de pedido
 */
@ParentPackage("Hotel")
public class SolicitudReqAction extends ActionSupport {
 
//	LogueoService objLogServ = new LogueoService();
	SolicitudReqService objPedidoServ = new SolicitudReqService(); 
	SolicitudReqDetalleService objPedidoDetalleServ = new SolicitudReqDetalleService();
	ArticuloService objProductoServ = new ArticuloService(); 
	
	AreaService objAreaServ = new AreaService();
	 
	private Map<String, Object> lasesion = ActionContext.getContext().getSession(); 
	private List<DetalleSolicitudReqDTO> lstDetPed ;
	private String mensaje;
	private Integer rsult;
	 
	private SolicitudReqDTO objPedido;
	private DetalleSolicitudReqDTO objDetallePedido;
	private Integer idProd;
	private Integer cantidad;
	
	private String tipoPedido;
	private String fechaEntrega;
	private String fechaDevolucion;
	private String obsDevolucion;
	
	private List<SolicitudReqDTO> lstPedido ;
	private Integer inicio;
	private Integer numeroPaginasModalPedido;
	private List<AreaDTO> lstArea = new ArrayList<AreaDTO>();
	private AreaDTO area;
	 
	
	
//	Date fecha = new Date();
	SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
	
	//variable para mostrar resultado de registro
	
	private String mensajeR;
	
	Integer cod_area;
	String desc_area; 
	private Integer codProd;
	 
	
	
	@Action(value="/nuevoSolicitudReq",results={@Result(name="success",type="tiles",location="d_mainsolicitudreq")})
	public String mainPedido(){ 
		lasesion.remove("lstDetPed");
		return SUCCESS;
	}
	@Action(value="/evaluarSolicitudReq",results={@Result(name="success",type="tiles",location="d_evaluarSolicitudReq")})
	public String evaluarSolicitudReq(){  
		lstArea = objAreaServ.listaArea(); 
		return SUCCESS;
	}							 
	@Action(value="/agregarDetalleSolicitudReq",results={
			@Result(name="error",location="/paginas/solicitudreq/detalle_solicitudreq.jsp"),
			@Result(name="success",location="/paginas/solicitudreq/detalle_solicitudreq.jsp")})
	public String agregarDetalleSolicitudReq(){
		lstDetPed = (ArrayList<DetalleSolicitudReqDTO>) lasesion.get("lstDetPed");
		
		DetalleSolicitudReqDTO objDetalle = new DetalleSolicitudReqDTO();
		ArticuloDTO prod = new ArticuloDTO();
		prod.setCod_articulo(getIdProd());
		try {
			prod = objProductoServ.getProducto(prod);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objDetalle.setCod_articulo(prod.getCod_articulo());
		objDetalle.setDesc_articulo(prod.getDesc_articulo());
		objDetalle.setUnidadMedida(prod.getUnidadMedida());
		objDetalle.setCantidad(getCantidad());
		if(lstDetPed==null){
			lstDetPed = new ArrayList<DetalleSolicitudReqDTO>();
		}
		for (DetalleSolicitudReqDTO prod2 : lstDetPed) {
			if(prod2.getCod_articulo().equals(getIdProd())){
				this.rsult=0;
				this.mensaje="Producto ya existe!";
				return ERROR;
			}
		}
		lstDetPed.add(objDetalle); 
		lasesion.put("lstDetPed", lstDetPed);
		return SUCCESS;
	} 
	@Action(value="/eliminarDetalleSolicitudReq",results={@Result(name="success",location="/paginas/solicitudreq/detalle_solicitudreq.jsp")})
	public String eliminarDetalleSolicitudReq(){
		lstDetPed = (ArrayList<DetalleSolicitudReqDTO>) lasesion.get("lstDetPed");
		for (int i = 0; i < lstDetPed.size(); i++) { 
			DetalleSolicitudReqDTO det = lstDetPed.get(i); 
			if(det.getCod_articulo().equals(getIdProd())){
				lstDetPed.remove(i);
			}
		}  
		lasesion.put("lstDetPed", lstDetPed);
		return SUCCESS;
	}
	@Action(value="/guardarSolicitudReq",results={@Result(name="success",location="/paginas/solicitudreq/detalle_solicitudreq_mensaje.jsp")})
	public String guardarSolicitudReq(){
		try {
			System.out.println("Guadrarr!");
			SolicitudReqDTO pedido = new SolicitudReqDTO();
			TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
			pedido.setCod_usuario(usuario.getCod_trabajador());
			pedido.setComentario_solicitud_requerimiento(getObsDevolucion());
			pedido.setEstado_solicitud_requerimiento("Sin Atender");
			//validar las fechas
			java.sql.Date fechaEntregaS = UtilSigal.fechaDateSql(getFechaEntrega());
			java.sql.Date fechaDevolucionS = UtilSigal.fechaDateSql(getFechaDevolucion());
			//fecha1 es anterior a la fecha2
			if(fechaEntregaS==null){
				this.rsult=0;
				this.mensaje="Fecha de Entrega vacia";
				return SUCCESS;
			}
			if("Prestamo".equals(getTipoPedido())){
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
			  
			pedido.setFechaDevolucion_solicitud_requerimiento(UtilSigal.fechaDateSql(getFechaDevolucion()) );
			pedido.setFechaEntrega_solicitud_requerimiento(UtilSigal.fechaDateSql(getFechaEntrega())); 
			pedido.setTipo_solicitud_requerimiento(getTipoPedido());
			lstDetPed = (ArrayList<DetalleSolicitudReqDTO>) lasesion.get("lstDetPed");
			if(lstDetPed==null){
				this.rsult=0;
				this.mensaje="Agregar detalle";
				return SUCCESS;
			}
			objPedidoServ.registrarPedido(pedido, lstDetPed);
			lasesion.remove("lstDetPed");
			this.rsult=1;
			this.mensaje="Se registro su Pedido!";
		} catch (Exception e) { 
			e.printStackTrace();
			this.rsult=0;
			this.mensaje="Ocurrio un error al Grabar";
		}
		
		return SUCCESS;
	}
//	guardarEvaluacionSolicitudReq
	@Action(value="/guardarEvaluacionSolicitudReq",results={@Result(name="success",location="/paginas/solicitudreq/solicitudreq_evaluacion_mensaje.jsp")})
	public String guardarEvaluacionSolicitudReq(){
		try {
			//aprobar o desaprobar
			System.out.println("commenatrio Evaluacion!"+objPedido.getComentarioevaluacion_solicitud_requerimiento()); 
			objPedidoServ.guardarEvaluacionSolicitudReq(objPedido);
			this.mensaje = "El Pedido se guardo con estado "+objPedido.getEstado_solicitud_requerimiento()+"";
			this.rsult= 1;
		} catch (Exception e) {
			System.out.println("Try:"+e);
			e.printStackTrace();
			this.mensaje = "Ocurrio un error en guardar el Pedido";
			this.rsult= 0;
		}
		
		return SUCCESS;
	}

//	getDetalleSolicitudReq 
	@Action(value = "/getDetalleSolicitudReq", results = { @Result(name = "success", location = "/paginas/solicitudreq/detalle_solicitudreq_modal.jsp") })
	public String getDetalleSolicitudReq() { 
		try {
			DetalleSolicitudReqDTO det = new DetalleSolicitudReqDTO();
			det.setCod_solicitudRequerimiento(getObjPedido().getCod_solicitudRequerimiento());
			this.lstDetPed = objPedidoDetalleServ.listaPedidoXidPedido(det) ;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return SUCCESS;
	} 
	//Modal
		@Action(value = "/listarSolicitudReqPagModal", results = { @Result(name = "success", location = "/paginas/solicitudreq/buscar_solicitudreq.jsp") })
		public String listarSolicitudReqPagModal() { 
			System.out.println("pedido1");
			Integer comienzo = null;
			if (inicio == null || inicio == 0) {
				comienzo = 0;
			} else {
				comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
			} 
			try {
				lstPedido =   objPedidoServ.listaPedidoPaginadoSinAtender(comienzo, Constantes.FILAS_X_PAGINA);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
		} 
		@Action(value = "/buscarSolicitudReqPagModal", results = { @Result(name = "success", location = "/paginas/solicitudreq/buscar_solicitudreq.jsp") })
		public String buscarSolicitudReqPagModal() { 
			System.out.println("pedido2");
			Integer comienzo = null;
			if (inicio == null || inicio == 0) {
				comienzo = 0;
			} else {
				comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
			}
			try {
				lstPedido = objPedidoServ.buscarPedidoPaginadoSinAtender(objPedido, comienzo, Constantes.FILAS_X_PAGINA);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
		}
		@Action(value = "/listarSolicitudReqTotal", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_listado_total.jsp") })
		public String listarSolicitudReqTotal() { 
			System.out.println("pedido3");
			try {
				System.out.println("totla:"+objPedidoServ.listaPedidoTotal());
				this.numeroPaginasModalPedido= UtilSigal.totalDePaginas(objPedidoServ.listaPedidoTotalSinAtender());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("nunmeroPaginas:"+numeroPaginasModalPedido); 
			return SUCCESS;
		}
		@Action(value = "/buscarSolicitudReqTotal", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_buscar_total.jsp") })
		public String buscarSolicitudReqTotal() {  
			System.out.println("pedido4");
			try {
				System.out.println("total reg:"+objPedidoServ.buscarSolicitudReqTotal(objPedido));
				this.numeroPaginasModalPedido = UtilSigal.totalDePaginas(objPedidoServ.buscarPedidoTotalSinAtender(objPedido));
				System.out.println("total paginas:"+numeroPaginasModalPedido);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return SUCCESS;
		} 
	
	
	//Modal Pedidos Aprobados
	@Action(value = "/listarSolicitudReqPagModalAprobados", results = { @Result(name = "success", location = "/paginas/solicitudreq/buscar_solicitudreq.jsp") })
	public String listarSolicitudReqPagModalAprobados() { 
		System.out.println("pedido Aprobados 1");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		} 
		try {
			lstPedido =   objPedidoServ.listaPedidoPaginadoAprobados(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	} 
	@Action(value = "/buscarSolicitudReqPagModalAprobados", results = { @Result(name = "success", location = "/paginas/solicitudreq/buscar_solicitudreq.jsp") })
	public String buscarSolicitudReqPagModalAprobados() { 
		System.out.println("pedido Aprobados 2");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstPedido = objPedidoServ.buscarPedidoPaginadoAprobados(objPedido, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "/listarSolicitudReqTotalAprobados", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_listado_total_aprobados.jsp") })
	public String listarSolicitudReqTotalAprobados() { 
		System.out.println("pedido Aprobados 3");
		try { 
			this.numeroPaginasModalPedido= UtilSigal.totalDePaginas(objPedidoServ.listaPedidoTotalAprobados());
		} catch (Exception e) { 
			e.printStackTrace();
		}
		System.out.println("nunmeroPaginas:"+numeroPaginasModalPedido); 
		return SUCCESS;
	}
	@Action(value = "/buscarSolicitudReqTotalAprobados", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_buscar_total_aprobados.jsp") })
	public String buscarSolicitudReqTotalAprobados() {   
		System.out.println("pedido Aprobados 4");
		try { 
			this.numeroPaginasModalPedido = UtilSigal.totalDePaginas(objPedidoServ.buscarSolicitudReqTotalAprobados(objPedido));
			System.out.println("total paginas:"+numeroPaginasModalPedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	} 
	
	//Modal Pedidos FaltanDevolver  =Falta Devolver
	@Action(value = "/listarSolicitudReqPagModalFaltanDevolver", results = { @Result(name = "success", location = "/paginas/solicitudreq/buscar_solicitudreq.jsp") })
	public String listarSolicitudReqPagModalFaltanDevolver() { 
		System.out.println("pedido Aprobados 1");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		} 
		try {
			lstPedido =   objPedidoServ.listaPedidoPaginadoFaltanDevolver(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	} 
	@Action(value = "/buscarSolicitudReqPagModalFaltanDevolver", results = { @Result(name = "success", location = "/paginas/solicitudreq/buscar_solicitudreq.jsp") })
	public String buscarSolicitudReqPagModalFaltanDevolver() { 
		System.out.println("pedido Aprobados 2");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstPedido = objPedidoServ.buscarPedidoPaginadoFaltanDevolver(objPedido, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "/listarSolicitudReqTotalFaltanDevolver", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_listado_total_faltadevolver.jsp") })
	public String listarSolicitudReqTotalFaltanDevolver() { 
		System.out.println("pedido Aprobados 3");
		try { 
			this.numeroPaginasModalPedido= UtilSigal.totalDePaginas(objPedidoServ.listaPedidoTotalFaltanDevolver());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("nunmeroPaginas:"+numeroPaginasModalPedido); 
		return SUCCESS;
	}
	@Action(value = "/buscarSolicitudReqTotalFaltanDevolver", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_buscar_total_faltadevolver.jsp") })
	public String buscarSolicitudReqTotalFaltanDevolver() {   
		System.out.println("pedido Aprobados 4");
		try { 
			this.numeroPaginasModalPedido = UtilSigal.totalDePaginas(objPedidoServ.buscarSolicitudReqTotalFaltanDevolver(objPedido));
			System.out.println("total paginas:"+numeroPaginasModalPedido);
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
	public String getObsDevolucion() {
		return obsDevolucion;
	}
	public void setObsDevolucion(String obsDevolucion) {
		this.obsDevolucion = obsDevolucion;
	}
	public List<SolicitudReqDTO> getLstPedido() {
		return lstPedido;
	}
	public void setLstPedido(List<SolicitudReqDTO> lstPedido) {
		this.lstPedido = lstPedido;
	}
	public Integer getInicio() {
		return inicio;
	}
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}
	public SolicitudReqDTO getObjPedido() {
		return objPedido;
	}
	public void setObjPedido(SolicitudReqDTO objPedido) {
		this.objPedido = objPedido;
	}
	public Integer getNumeroPaginasModalPedido() {
		return numeroPaginasModalPedido;
	}
	public void setNumeroPaginasModalPedido(Integer numeroPaginasModalPedido) {
		this.numeroPaginasModalPedido = numeroPaginasModalPedido;
	}
	public List<AreaDTO> getLstArea() {
		return lstArea;
	}
	public void setLstArea(List<AreaDTO> lstArea) {
		this.lstArea = lstArea;
	}
	public AreaDTO getArea() {
		return area;
	}
	public void setArea(AreaDTO area) {
		this.area = area;
	}
	public Integer getCod_area() {
		return cod_area;
	}
	public void setCod_area(Integer cod_area) {
		this.cod_area = cod_area;
	}
	public String getDesc_area() {
		return desc_area;
	}
	public void setDesc_area(String desc_area) {
		this.desc_area = desc_area;
	}
	public List<DetalleSolicitudReqDTO> getLstDetPed() {
		return lstDetPed;
	}
	public void setLstDetPed(ArrayList<DetalleSolicitudReqDTO> lstDetPed) {
		this.lstDetPed = lstDetPed;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Integer getRsult() {
		return rsult;
	}
	public void setRsult(Integer rsult) {
		this.rsult = rsult;
	}
	/**
	 * @return the codProd
	 */
	public Integer getCodProd() {
		return codProd;
	}
	/**
	 * @param codProd the codProd to set
	 */
	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}
	
 
	
 
	
	
}

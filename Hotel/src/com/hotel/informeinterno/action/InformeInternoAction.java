/**
 * InformeInternoAction 22/07/2013p
 */
package com.hotel.informeinterno.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.hotel.informeinterno.bean.InformeInternoDTO;
import com.hotel.informeinterno.bean.InformeInternoDetalleDTO;
import com.hotel.informeinterno.service.InformeInternoService;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.service.ArticuloService;
import com.hotel.solicitudreq.bean.DetalleSolicitudReqDTO;
import com.hotel.solicitudreq.service.SolicitudReqDetalleService;
import com.hotel.seguridad.bean.AreaDTO;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.seguridad.service.AreaService;

/**
 * @author Gustavo A. Correa C.
 *
 */
@ParentPackage("Hotel")
public class InformeInternoAction extends ActionSupport {
	InformeInternoService objInfInterServ = new InformeInternoService();
	ArticuloService objProdServ = new ArticuloService();
	SolicitudReqDetalleService objPedDetServ = new SolicitudReqDetalleService();
	
	ArticuloDTO objProducto;
	AreaService objAreaServ = new AreaService();
	
	private List<AreaDTO> lstArea;
	Map<String, Object> lasesion = ActionContext.getContext().getSession(); 
	
	private Integer codProd;
	private String mensaje;
	private Integer rsult;
	private InformeInternoDTO objInformeInterno;
	List<InformeInternoDetalleDTO> lstIIDet;
	
//	rsult mensaje
	private final int totalProductoEnElDetalle=2;
	private ArrayList<DetalleSolicitudReqDTO> lstDetPed ;
	
	
	@Action(value="/mainInformeInternoSalidaMovil",results={@Result(name="success",location="/paginas/informe_interno/salida_movil.jsp")})
	public String mainInformeInternoSalidaMovil(){ 
		lstDetPed = (ArrayList<DetalleSolicitudReqDTO>) lasesion.get("lstProdII");
		System.out.println("CodProd:"+getCodProd());
		ArticuloDTO prod = new ArticuloDTO();
		prod.setCod_articulo(getCodProd());
		try {
			prod = objProdServ.getProducto(prod);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(prod==null){
			this.setMensaje("No es un producto Valido!");
			this.setRsult(-1);
		}else{  
			setObjProducto(prod); 
		} 
		return SUCCESS;
	}
	
	

	@Action(value="/mainInformeInternoSalida",results={@Result(name="success",type="tiles",location="d_maininformeinternosalida")})
	public String mainInformeInternoSalida(){
		System.out.println("entra1");
//		Object[] obj =  (Object[]) lasesion.get("DatosQR"); 
//		if(obj!=null){
//			if("Salida".equals(obj[1])){
//				codProd = (Integer) obj[0];
//			} 
//		}
		lstArea = objAreaServ.listaArea();
		return SUCCESS;
	}
	
	@Action(value="/guardarIIS",results={@Result(name="success",location="/paginas/solicitudreq/solicitudreq_evaluacion_mensaje.jsp")})
	public String guardarIIS(){
//		objInformeInterno.cod_pedido
		System.out.println("guarda iis"+objInformeInterno.getCod_solicitud_requerimiento());
		try {
			TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
			objInformeInterno.setCod_trabajador(usuario.getCod_trabajador()); 
			objInformeInterno.setTipo_informe_interno("Salida"); 
			lstIIDet = new ArrayList<InformeInternoDetalleDTO>();
			DetalleSolicitudReqDTO pedDet = new DetalleSolicitudReqDTO(); 
			if(objInformeInterno.getCod_solicitud_requerimiento()==null){
				this.mensaje = "Agrege Pedido";
				this.rsult= 0;
				return SUCCESS;
			}
			pedDet.setCod_solicitudRequerimiento(objInformeInterno.getCod_solicitud_requerimiento()); 
			List<DetalleSolicitudReqDTO> lstDetPedido= objPedDetServ.listaPedidoXidPedido(pedDet);
			for (DetalleSolicitudReqDTO detallePedidoDTO : lstDetPedido) {
				InformeInternoDetalleDTO iiDet = new InformeInternoDetalleDTO();
				iiDet.setCod_detalle_solicitud_requerimiento(detallePedidoDTO.getCod_detalleRequerimiento());
				ArticuloDTO p = new ArticuloDTO();
				p.setCod_articulo(detallePedidoDTO.getCod_articulo());
				p = objProdServ.getProducto(p);
				if(p.getStock_articulo()<detallePedidoDTO.getCantidad()){
					this.mensaje = "No hay stock suficiente para el producto \""+p.getDesc_articulo()+"\"";
					this.rsult= 0;
					return SUCCESS;
				} 
				lstIIDet.add(iiDet);
			} 
			Integer r = (Integer) objInfInterServ.registrar(objInformeInterno, lstIIDet); 
			if(r>0){
				this.mensaje = "Se ingreso correctamente el Informe Interno Salida";
				this.rsult= 1;	
			} 
		} catch (Exception e) { 
			e.printStackTrace();
			this.mensaje = "Ocurrio un error en guardar el IIS";
			this.rsult= 0;
		} 
		return SUCCESS;
	}
	@Action(value="/guardarIIE",results={@Result(name="success",location="/paginas/solicitudreq/solicitudreq_evaluacion_mensaje.jsp")})
	public String guardarIIE(){
		try {
			TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
			objInformeInterno.setCod_trabajador(usuario.getCod_trabajador()); 
			objInformeInterno.setTipo_informe_interno("Entrada"); 
			lstIIDet = new ArrayList<InformeInternoDetalleDTO>();
			DetalleSolicitudReqDTO pedDet = new DetalleSolicitudReqDTO();
			if(objInformeInterno.getCod_solicitud_requerimiento()==null){
				this.mensaje = "Agrege Pedido";
				this.rsult= 0;
				return SUCCESS;
			}
			pedDet.setCod_solicitudRequerimiento(objInformeInterno.getCod_solicitud_requerimiento()); 
			List<DetalleSolicitudReqDTO> lstDetPedido= objPedDetServ.listaPedidoXidPedido(pedDet);
			for (DetalleSolicitudReqDTO detallePedidoDTO : lstDetPedido) {
				InformeInternoDetalleDTO iiDet = new InformeInternoDetalleDTO();
				iiDet.setCod_detalle_solicitud_requerimiento(detallePedidoDTO.getCod_detalleRequerimiento());  
				lstIIDet.add(iiDet);
			} 
			Integer r = (Integer) objInfInterServ.registrar(objInformeInterno, lstIIDet);  
			if(r>0){
				this.mensaje = "Se ingreso correctamente el Informe Interno Entrada";
				this.rsult= 1;	
			} 
		} catch (Exception e) { 
			e.printStackTrace();
			this.mensaje = "Ocurrio un error en guardar el IIE";
			this.rsult= 0;
		} 
		return SUCCESS;
	} 
	@Action(value="/mainInformeInternoEntrada",results={@Result(name="success",type="tiles",location="d_maininformeinternoentrada")})
	public String mainInformeInternoEntrada(){ 
		lstArea = objAreaServ.listaArea();
//		Object[] obj =  (Object[]) lasesion.get("DatosQR");
//		if(obj!=null){
//			if("Entrada".equals(obj[1])){
//				codProd = (Integer) obj[0];
//			} 
//		} 
		return SUCCESS;
	}

	
	public Object getPedidos(Integer idProd){
		if(idProd!=null ){
//			objPedDetServ.listaPedidoXidPedido(det)
		}
		
		
		return null;
	}


	public Integer getCodProd() {
		return codProd;
	} 
	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}

	public ArticuloDTO getObjProducto() {
		return objProducto;
	}

	public void setObjProducto(ArticuloDTO objProducto) {
		this.objProducto = objProducto;
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

	public List<AreaDTO> getLstArea() {
		return lstArea;
	}

	public void setLstArea(List<AreaDTO> lstArea) {
		this.lstArea = lstArea;
	}

	public InformeInternoDTO getObjInformeInterno() {
		return objInformeInterno;
	}

	public void setObjInformeInterno(InformeInternoDTO objInformeInterno) {
		this.objInformeInterno = objInformeInterno;
	}
	
	
	
	
	
}

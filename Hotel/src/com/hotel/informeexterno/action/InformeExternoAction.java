/**
 * InformeExternoAction 22/07/2013
 */
package com.hotel.informeexterno.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.hotel.informeexterno.bean.InformeExternoDTO;
import com.hotel.informeexterno.bean.InformeExternoDetalleDTO;
import com.hotel.informeexterno.service.InformeExternoService;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;
import com.hotel.mantenimiento.service.ArticuloProveedorService;
import com.hotel.mantenimiento.service.ArticuloService;
import com.hotel.ordencompra.bean.OrdenCompraDTO;
import com.hotel.ordencompra.bean.OrdenCompraDetalleDTO;
import com.hotel.ordencompra.service.OrdenCompraDetalleService;
import com.hotel.ordencompra.service.OrdenCompraService;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Gustavo A. Correa C.
 * 
 */
@ParentPackage("Hotel")
public class InformeExternoAction extends ActionSupport {
	InformeExternoService objInfExternoServ = new InformeExternoService();
	// ProductoService objProdServ = new ProductoService();
	OrdenCompraService objOCServ = new OrdenCompraService();
	OrdenCompraDetalleService objOCDetServ = new OrdenCompraDetalleService();
	ArticuloProveedorService objProdProveeServ = new ArticuloProveedorService();
	ArticuloService objProductoServ = new ArticuloService();
	// ProductoDTO objProducto;
	// AreaService objAreaServ = new AreaService();

	Map<String, Object> lasesion = ActionContext.getContext().getSession();
	List<InformeExternoDetalleDTO> lstIEDet;

	// private Integer codProd;
	private String mensaje;
	private Integer rsult;
	// private Integer rsult;
	private InformeExternoDTO objInformeExterno;

	@Action(value = "/mainInformeExternoEntrada", results = { @Result(name = "success", type = "tiles", location = "d_maininformeexternoentrada") })
	public String mainInformeExternoEntrada() {
		return SUCCESS;
	}

	@Action(value = "/mainInformeExternoSalida", results = { @Result(name = "success", type = "tiles", location = "d_maininformeexternosalida") })
	public String mainInformeExternoSalida() {
		return SUCCESS;
	}

	// guardarIEE
	@Action(value = "/guardarIEE", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_evaluacion_mensaje.jsp") })
	public String guardarIEE() {
		try {
			TrabajadorDTO usuario = (TrabajadorDTO) lasesion.get("objUsuario");
			objInformeExterno.setCod_trabajador(usuario.getCod_trabajador());
			objInformeExterno.setTipo_informe_externo("Entrada");
			lstIEDet = new ArrayList<InformeExternoDetalleDTO>();
			OrdenCompraDetalleDTO ocDet = new OrdenCompraDetalleDTO();
			if (objInformeExterno.getCod_ordencompra() == null) {
				this.mensaje = "Agrege Orden de Compra";
				this.rsult = 0;
				return SUCCESS;
			}
			ocDet.setCod_ordenCompra(objInformeExterno.getCod_ordencompra());
			List<OrdenCompraDetalleDTO> lstDetOC = objOCDetServ
					.listaOrdenCompraXidOrdenCompra(ocDet);
			for (OrdenCompraDetalleDTO detalleOCDTO : lstDetOC) {
				InformeExternoDetalleDTO ieDet = new InformeExternoDetalleDTO();
				ieDet.setCod_detalle_ordencompra(detalleOCDTO
						.getCod_DetalleOrdenCompra());
				// coddetalleordencimpra
				lstIEDet.add(ieDet);
			}
			Integer r = (Integer) objInfExternoServ.registrarOrdenCompra(objInformeExterno, lstIEDet);
			System.out.println("Respuesta:" + r);
			if (r > 0) {
				System.out.println("No deberia entrar aki1:" + r);
				this.mensaje = "Se ingreso correctamente el Informe Externo Entrada";
				this.rsult = 1;
			} else {
				System.out.println("No deberia entrar aki2:" + r);
				this.mensaje = "Ocurrio un error en guardar el IEE";
				this.rsult = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.mensaje = "Ocurrio un error en guardar el IEE";
			this.rsult = 0;
		}
		return SUCCESS;
	}

	@Action(value = "/guardarIES", results = { @Result(name = "success", location = "/paginas/solicitudreq/solicitudreq_evaluacion_mensaje.jsp") })
	public String guardarIES() {
		try {
			
			TrabajadorDTO usuario = (TrabajadorDTO) lasesion.get("objUsuario");
			objInformeExterno.setCod_trabajador(usuario.getCod_trabajador());
			objInformeExterno.setTipo_informe_externo("Salida");
			lstIEDet = new ArrayList<InformeExternoDetalleDTO>();
			OrdenCompraDetalleDTO ocDet = new OrdenCompraDetalleDTO();
			if (objInformeExterno.getCod_ordencompra() == null) {
				this.mensaje = "Agrege Orden de Compra";
				this.rsult = 0;
				return SUCCESS;
			}
			OrdenCompraDTO oc = new OrdenCompraDTO(); 
			List<OrdenCompraDTO> lstOc = objOCServ.buscarOrdenCompra(null);
			for (OrdenCompraDTO oc1 : lstOc) {
				if(objInformeExterno.getCod_ordencompra().equals(oc1.getCod_OrdenCompra()) ){
					oc = oc1;
				} 
			}
			
			ocDet.setCod_ordenCompra(objInformeExterno.getCod_ordencompra());
			List<OrdenCompraDetalleDTO> lstDetOC = objOCDetServ.listaOrdenCompraXidOrdenCompra(ocDet);
			for (OrdenCompraDetalleDTO detalleOCDTO : lstDetOC) {
				InformeExternoDetalleDTO ieDet = new InformeExternoDetalleDTO();
				ieDet.setCod_detalle_ordencompra(detalleOCDTO.getCod_DetalleOrdenCompra());
				System.out.println("mediavuelta:" + detalleOCDTO.getCod_articulo_proveedor());
				ArticuloProveedorDTO prodProvee = new ArticuloProveedorDTO();
				prodProvee.setCod_articulo_proveedor(detalleOCDTO.getCod_articulo_proveedor());
				System.out.println("gracias:" + prodProvee.getCod_articulo_proveedor());
				prodProvee = objProdProveeServ.getProductoProveedor(prodProvee);
				ArticuloDTO prod = new ArticuloDTO();
				prod.setCod_articulo(prodProvee.getCod_articulo());
				prod = objProductoServ.getProducto(prod);
//				System.out.println("gracias1:" + prod.getCod_articulo());
//				System.out.println("gracias2:" + prod.getStock_articulo());
				if("Abastecimiento".equals(oc.getTipo_ordencompra())){
					if (prod.getStock_articulo() < detalleOCDTO.getCantidad()) {
						this.mensaje = "No hay stock suficiente para el producto \"" + prod.getDesc_articulo() + "\"";
						this.rsult = 0; 
						return SUCCESS; 
					}	
				} 
				lstIEDet.add(ieDet);
			}
			Integer r = (Integer) objInfExternoServ.registrarOrdenCompra(objInformeExterno, lstIEDet);
			if (r > 0) {
				this.mensaje = "Se ingreso correctamente el Informe Externo Salida";
				this.rsult = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.mensaje = "Ocurrio un error en guardar el IES";
			this.rsult = 0;
		}
		return SUCCESS;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public InformeExternoDTO getObjInformeExterno() {
		return objInformeExterno;
	}

	public void setObjInformeExterno(InformeExternoDTO objInformeExterno) {
		this.objInformeExterno = objInformeExterno;
	}

	public Integer getRsult() {
		return rsult;
	}

	public void setRsult(Integer rsult) {
		this.rsult = rsult;
	}

}

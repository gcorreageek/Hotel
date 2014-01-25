package com.hotel.mantenimiento.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.service.ArticuloService;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.util.Constantes;
import com.hotel.util.UtilSigal;

@ParentPackage("Hotel")
public class ArticuloAction extends ActionSupport {
	private final Log log = LogFactory.getLog(getClass());
	Map<String, Object> lasesion = ActionContext.getContext().getSession(); 
	ArticuloService objProServ = new ArticuloService();
	private ArticuloDTO objProducto;
	private List<ArticuloDTO> lstProducto;
	private String mensaje;
	private Integer rsult;
	private Integer codProd;
	private Integer id;
	private Integer inicio;
	private Integer numeroPaginas;
	private Integer tagTipoListado; 
	private Integer numeroPaginasModalProducto;
	private Integer idProve;
	private String url;
	private String tipo;

	@Action(value = "/listarArticuloPag", results = { @Result(name = "success", location = "/paginas/mantenimientos/paginacion_articulo.jsp") })
	public String listarArticuloPag() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstProducto = objProServ.listaProductosPaginado(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/mainArticulo", results = { @Result(name = "success", type = "tiles", location = "d_mainarticulo") })
	public String mainArticulo() {
		try {
			lstProducto = objProServ.listaProductosPaginado(0,
					Constantes.FILAS_X_PAGINA);
			this.numeroPaginas = UtilSigal.totalDePaginas(objProServ
					.listaProductosTotal());
		} catch (Exception e) {
			log.error("",e);
		}
		this.tagTipoListado = 1;
		return SUCCESS;
	}
	
	@Action(value = "/buscarArticulosXDescProdPag", results = { @Result(name = "success", location = "/paginas/mantenimientos/paginacion_articulo.jsp") })
	public String buscarArticulosXDescProdPag() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			objProducto.setDesc_articulo(objProducto.getDesc_articulo().trim());
			if("Seleccionar".equals(objProducto.getUnidadMedida()) || objProducto.getUnidadMedida()==null){
				objProducto.setUnidadMedida("");
			}
			lstProducto = objProServ.buscarProductosXDescPaginado(objProducto, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/buscarArticulosXDescProd", results = { @Result(name = "success", type = "tiles", location = "d_mainarticulo") })
	public String buscarArticulosXDescProd() {
		try {
			String prod = objProducto.getDesc_articulo();
			log.debug("prod:"+objProducto.getDesc_articulo());
			objProducto.setDesc_articulo(objProducto.getDesc_articulo().trim());
			if("Seleccionar".equals(objProducto.getUnidadMedida()) || objProducto.getUnidadMedida()==null){
				objProducto.setUnidadMedida("");
			}
			lstProducto = objProServ.buscarProductosXDescPaginado(objProducto, 0,Constantes.FILAS_X_PAGINA);
			log.debug("tamm:"+lstProducto.size());
			this.numeroPaginas = UtilSigal.totalDePaginas(objProServ.buscarProductosXDescTotal(objProducto));
			objProducto.setDesc_articulo(prod);
		} catch (Exception e) {
			log.error("",e);
		}
		this.tagTipoListado = 2;
		return SUCCESS;
	} 
	@Action(value = "/accionArticulo", results = { @Result(name = "success", type = "tiles", location = "d_actuararticulo") })
	public String accionArticulo() {
		if (this.codProd != null) {
			ArticuloDTO prod = new ArticuloDTO();
			prod.setCod_articulo(this.codProd);
			try {
				this.objProducto = objProServ.getProducto(prod);
			} catch (Exception e) {
				log.error("",e);
			}
		}
		return SUCCESS;
	}

	@Action(value = "/eliminarArticulo", results = { @Result(name = "success", type = "tiles", location = "d_mainarticulo") })
	public String eliminarArticulo() {
//		lasesion
		TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
		if(usuario!=null){
			ArticuloDTO prod = new ArticuloDTO();
			prod.setCod_articulo(this.codProd);
			Boolean rsultado= false;
			try {
				rsultado = objProServ.eliminarArticulo(prod);
			} catch (Exception e) { 
				String errorMessage = e.getMessage(); 
				if(errorMessage.indexOf("fk_codproducto_producto")!=-1){
					this.rsult = 0;
					this.mensaje = "No se puede eliminar, se encuentra en una transacción; elimine as transacciones";
					mainArticulo();
					return SUCCESS;
				} 
				log.error("",e);
			}
			if (rsultado) {
				this.rsult = 1;
				this.mensaje = "Se Elimino Correctamente";
			} else {
				this.rsult = 0;
				this.mensaje = "Ocurrio un Problema";
			}
			mainArticulo();	
		} 
		return SUCCESS;
	}

	@Action(value = "/actuarArticulo", results = { @Result(name = "success", type = "tiles", location = "d_actuararticulo") })
	public String actuarArticulo() {
		Boolean rsultado = true;
		objProducto.setDesc_articulo(objProducto.getDesc_articulo().trim());
		if("".equals(objProducto.getDesc_articulo())){ 
			this.rsult = 0;
			this.mensaje = "Ingrese Producto Valido"; 
			return SUCCESS;
		} 
		try {
			if (rsultado) {
				if (objProducto.getCod_articulo() == null) {
					objProducto.setStock_articulo(0);
					rsultado = objProServ.registrarProducto(objProducto);
				} else {
					Integer stock = objProServ.getProducto(objProducto).getStock_articulo();
					objProducto.setStock_articulo(stock);
					rsultado = objProServ.actualizarProducto(objProducto);
				}
			} 
		} catch (Exception e) { 
			rsultado=false; 
			String errorMessage = e.getMessage(); 
			if(errorMessage.indexOf("desc_articulo_umedidad_UNIQUE")!=-1){
				this.rsult = 0;
				this.mensaje = "El producto y la unidad ya existen";
				return SUCCESS;
			} 
			log.error("",e);
		}
		
		if (rsultado) {
			this.rsult = 1;
			this.mensaje = "Todo Correctamente";
		} else {
			this.rsult = 0;
			this.mensaje = "Ocurrio un Problema";
		}
		return SUCCESS;
	} 
	//Modal
	@Action(value = "/listarArticuloPagModalHabilitados", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_articulo.jsp") })
	public String listarArticuloPagModalHabilitados() {
		System.out.println("buscar1");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstProducto = objProServ.listaProductosPaginadoHabilitados(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	} 
	@Action(value = "/buscarArticulosXDescProdPagModalHabilitados", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_articulo.jsp") })
	public String buscarArticulosXDescProdPagModalHabilitados() {
		System.out.println("buscar2");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			objProducto.setDesc_articulo(objProducto.getDesc_articulo().trim());
			if("Seleccionar".equals(objProducto.getUnidadMedida()) || objProducto.getUnidadMedida()==null){
				objProducto.setUnidadMedida("");
			}
			lstProducto = objProServ.buscarProductosXDescPaginadoHabilitados(objProducto, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/listarArticuloTotalHabilitados", results = { @Result(name = "success", location = "/paginas/mantenimientos/articulo_listado_total.jsp") })
	public String listarArticuloTotalHabilitados() {  
		try {
			this.numeroPaginasModalProducto = UtilSigal.totalDePaginas(objProServ.listaProductosTotalHabilitados());
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/buscarArticuloTotalHabilitados" , results = { @Result(name = "success", location = "/paginas/mantenimientos/articulo_buscar_total.jsp") })
	public String buscarArticuloTotalHabilitados() { 
		try {
			this.numeroPaginasModalProducto = UtilSigal.totalDePaginas(objProServ.buscarProductosXDescTotalHabilitados(objProducto));
		} catch (Exception e) {
			log.error("",e);
		} 
		return SUCCESS;
	}  
	//Modal idProve
	@Action(value = "/listarArticuloPagModalidProve", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_articulo.jsp") })
	public String listarArticuloPagModalidProve() {
		System.out.println("buscar3");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstProducto = objProServ.listaProductosIdProveePaginadoHabilitados(this.idProve,comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	} 
	@Action(value = "/buscarArticulosXDescProdPagModalidProve", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_articulo.jsp") })
	public String buscarArticulosXDescProdPagModalidProve() {
		System.out.println("buscar4");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstProducto = objProServ.buscarProductosIdProveeXDescPaginadoHabilitados(objProducto,this.idProve, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/listarArticuloTotalidProve", results = { @Result(name = "success", location = "/paginas/mantenimientos/articulo_listado_total_idproveedor.jsp") })
	public String listarArticuloTotalidProve() {  
		try {
			this.numeroPaginasModalProducto = UtilSigal.totalDePaginas(objProServ.listaProductosIdProveeTotalHabilitados(this.idProve));
		} catch (Exception e) {
			log.error("",e);
		} 
		return SUCCESS;
	}
	@Action(value = "/buscarArticuloTotalidProve", results = { @Result(name = "success", location = "/paginas/mantenimientos/articulo_buscar_total_idproveedor.jsp") })
	public String buscarArticuloTotalidProve() { 
		try {
			this.numeroPaginasModalProducto = UtilSigal.totalDePaginas(objProServ.buscarProductosIdProveeXDescTotalHabilitados(objProducto,this.idProve));
			log.debug("total:"+numeroPaginasModalProducto);
		} catch (Exception e) {
			log.error("",e);
		} 
		return SUCCESS;
	} 
	@Action(value = "/generarQR", results = { @Result(name = "success", location = "/paginas/mantenimientos/ver_qr.jsp") })
	public String generarQR() {   
		return SUCCESS;
	} 
	@Action(value = "/leerQR", results = { @Result(name = "success", location = "/paginas/mantenimientos/leer_qr.jsp") })
	public String leerQR() {
		Integer cod = getCodProd();
		ArticuloDTO prod =  new ArticuloDTO();
		prod.setCod_articulo(cod);
		try {
			objProducto = objProServ.getProducto(prod);
		} catch (Exception e) {
			log.error("",e);
		} 
		return SUCCESS;
	}
//	guardaSessionQR
	@Action(value = "/guardaSessionQR", results = { @Result(name = "success", location = "/paginas/seguridad/login.jsp") })
	public String guardaSessionQR() {
		Integer cod = getCodProd();
		String tipo = getTipo(); 
		Object[] obj = null;
		obj = (Object[]) lasesion.get("DatosQR");
		if(obj==null){
			obj = new Object[2];
		} 
		obj[0]=cod;
		obj[1]=tipo;
		lasesion.put("DatosQR", obj);  
		return SUCCESS;
	}
	//Reportessss
	@Action(value = "/listarArticuloTotal", results = { @Result(name = "success", location = "/paginas/mantenimientos/articulo_listado_total_reportes.jsp") })
	public String listarArticuloTotal() {  
		try {
			this.numeroPaginasModalProducto = UtilSigal.totalDePaginas(objProServ.listaProductosTotal());
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/listarArticuloPagModal", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_articulo_reportes.jsp") })
	public String listarArticuloPagModal() {
		System.out.println("buscar1 Reportes");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstProducto = objProServ.listaProductosPaginado(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/buscarArticulosXDescProdPagModal", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_articulo_reportes.jsp") })
	public String buscarArticulosXDescProdPagModal() {
		System.out.println("buscar2 reportes");
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		objProducto.setDesc_articulo(objProducto.getDesc_articulo().trim());
		if("Seleccionar".equals(objProducto.getUnidadMedida())){
			objProducto.setUnidadMedida("");
		}
		try {
			lstProducto = objProServ.buscarProductosXDescPaginado(objProducto, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}
	@Action(value = "/buscarArticuloTotal" , results = { @Result(name = "success", location = "/paginas/mantenimientos/articulo_buscar_total_reportes.jsp") })
	public String buscarArticuloTotal() { 
		objProducto.setDesc_articulo(objProducto.getDesc_articulo().trim());
		if("Seleccionar".equals(objProducto.getUnidadMedida())){
			objProducto.setUnidadMedida("");
		}
		try {
			this.numeroPaginasModalProducto = UtilSigal.totalDePaginas(objProServ.buscarProductosXDescTotal(objProducto));
		} catch (Exception e) {
			log.error("",e);
		} 
		return SUCCESS;
	}
	
	public ArticuloDTO getObjProducto() {
		return objProducto;
	}

	public void setObjProducto(ArticuloDTO objProducto) {
		this.objProducto = objProducto;
	}

	public List<ArticuloDTO> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<ArticuloDTO> lstProducto) {
		this.lstProducto = lstProducto;
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

	public Integer getCodProd() {
		return codProd;
	}

	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}

	public Integer getInicio() {
		return inicio;
	}

	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTagTipoListado() {
		return tagTipoListado;
	}

	public void setTagTipoListado(Integer tagTipoListado) {
		this.tagTipoListado = tagTipoListado;
	}
	public Integer getNumeroPaginasModalProducto() {
		return numeroPaginasModalProducto;
	}
	public void setNumeroPaginasModalProducto(Integer numeroPaginasModalProducto) {
		this.numeroPaginasModalProducto = numeroPaginasModalProducto;
	}
	public Integer getIdProve() {
		return idProve;
	}
	public void setIdProve(Integer idProve) {
		this.idProve = idProve;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
 
	
	
	
	
}

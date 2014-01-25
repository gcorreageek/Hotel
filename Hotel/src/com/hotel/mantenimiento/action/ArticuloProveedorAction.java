/**
 * ProveedorAction 21/07/2013
 */
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
import com.hotel.mantenimiento.bean.ArticuloProveedorDTO;
import com.hotel.mantenimiento.service.ArticuloProveedorService;
import com.hotel.mantenimiento.service.ArticuloService;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.util.Constantes;
import com.hotel.util.UtilSigal;

/**
 * @author Gustavo A. Correa C.
 *
 */
@ParentPackage("Hotel")
public class ArticuloProveedorAction extends ActionSupport {
	private final Map<String, Object> lasesion = ActionContext.getContext().getSession();
	private final Log log = LogFactory.getLog(getClass());
	ArticuloProveedorService objProServ = new ArticuloProveedorService();
	ArticuloService objProductoServ = new ArticuloService();
	private ArticuloProveedorDTO objProductoProveedor;
	private List<ArticuloProveedorDTO> lstProductoProveedor;
	private List<ArticuloDTO> lstProducto;
	private String mensaje;
	private Integer rsult;
	private Integer codProdProvee;
	private Integer id;
	private Integer inicio;
	private Integer numeroPaginas;
	private Integer tagTipoListado;

	@Action(value = "/listarArticuloProveedorPag", results = { @Result(name = "success", location = "/paginas/mantenimientos/paginacion_articulo_proveedor.jsp") })
	public String listarArticuloProveedorPag() {
		log.debug("paso:"+inicio);
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA)
					- Constantes.FILAS_X_PAGINA;
		} 
		try {
			lstProductoProveedor = objProServ.listaProductosProveedorPaginado(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) { 
			log.error("",e);
		}
		return SUCCESS;
	}

	@Action(value = "/mainArticuloProveedor", results = { @Result(name = "success", type = "tiles", location = "d_mainarticuloproveedor") })
	public String mainArticuloProveedor() {
		try {
			lstProductoProveedor = objProServ.listaProductosProveedorPaginado(0, Constantes.FILAS_X_PAGINA);
			this.numeroPaginas = UtilSigal.totalDePaginas(objProServ.listaProductosProveedorTotal());
			this.tagTipoListado = 1;
		} catch (Exception e) { 
			log.error("",e);
		}
		return SUCCESS;
	}
	
	@Action(value = "/buscarArticuloProveedorXRazonSocialAndDescProdPag", results = { @Result(name = "success", location = "/paginas/mantenimientos/paginacion_articulo_proveedor.jsp") })
	public String buscarArticuloProveedorXRazonSocialAndDescProdPag() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstProductoProveedor = objProServ.buscarProductosProveedorXRazonSocialAndDescProdPaginado(objProductoProveedor, comienzo, Constantes.FILAS_X_PAGINA);
			
		} catch (Exception e) {
			log.error("",e);
		}
		return SUCCESS;
	}

	@Action(value = "/buscarArticuloProveedorXRazonSocialAndDescProd", results = { @Result(name = "success", type = "tiles", location = "d_mainarticuloproveedor") })
	public String buscarArticuloProveedorXRazonSocialAndDescProd() {
		try {

			System.out.println("lsita"+objProductoProveedor.getDesc_articulo());
			System.out.println("lsita"+objProductoProveedor.getRaz_social());
			lstProductoProveedor = objProServ.buscarProductosProveedorXRazonSocialAndDescProdPaginado(objProductoProveedor, 0, Constantes.FILAS_X_PAGINA);
			this.numeroPaginas = UtilSigal.totalDePaginas(objProServ.buscarProductosProveedorXRazonSocialAndDescProdTotal(objProductoProveedor));
			System.out.println("lsita"+lstProductoProveedor.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.tagTipoListado = 2;
		return SUCCESS;
	}

	@Action(value = "/accionArticuloProveedor", results = { @Result(name = "success", type = "tiles", location = "d_actuararticuloproveedor") })
	public String accionArticuloProveedor() {
		if (this.codProdProvee != null) {
			ArticuloProveedorDTO prodProvee = new ArticuloProveedorDTO();
			prodProvee.setCod_articulo_proveedor(this.codProdProvee);
			try {
				this.objProductoProveedor = objProServ.getProductoProveedor(prodProvee);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		lstProducto = objProductoServ.listaProductosPaginado(0, Constantes.FILAS_X_PAGINA);
		return SUCCESS;
	}

	@Action(value = "/eliminarArticuloProveedor", results = { @Result(name = "success", type = "tiles", location = "d_mainarticuloproveedor") })
	public String eliminarArticuloProveedor() {
		TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
		if(usuario!=null){
			ArticuloProveedorDTO productProve = new ArticuloProveedorDTO();
			productProve.setCod_articulo_proveedor(this.codProdProvee);
			Boolean rsultado=false;
			try {
				rsultado = objProServ.eliminarArticuloProveedor(productProve);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (rsultado) {
				this.rsult = 0;
				this.mensaje = "Se Elimino Correctamente";
			} else {
				this.rsult = 1;
				this.mensaje = "Ocurrio un Problema";
			}
			mainArticuloProveedor();	
		} 
		return SUCCESS;
	}

	@Action(value = "/actuarArticuloProveedor", results = { @Result(name = "success", type = "tiles", location = "d_actuararticuloproveedor") })
	public String actuarArticuloProveedor() {
		log.debug("mra:"+objProductoProveedor.getDesc_articulo());
		Boolean rsultado = false;
		try {
			if (objProductoProveedor.getCod_articulo_proveedor() == null) {
				rsultado = objProServ.registrarProductoProveedor(objProductoProveedor);
			} else {
				rsultado = objProServ.actualizarProductoProveedor(objProductoProveedor);
			}
		} catch (Exception e) {
			rsultado=false;
			e.printStackTrace();
		}
		log.debug("mra:"+objProductoProveedor.getDesc_articulo());
		if (rsultado) {
			this.rsult = 0;
			this.mensaje = "Todo Correctamente";
		} else {
			this.rsult = 1;
			this.mensaje = "Ocurrio un Problema";
		}
		return SUCCESS;
	}

	public ArticuloProveedorDTO getObjProductoProveedor() {
		return objProductoProveedor;
	}

	public void setObjProductoProveedor(ArticuloProveedorDTO objProductoProveedor) {
		this.objProductoProveedor = objProductoProveedor;
	}

	public List<ArticuloProveedorDTO> getLstProductoProveedor() {
		return lstProductoProveedor;
	}

	public void setLstProductoProveedor(
			List<ArticuloProveedorDTO> lstProductoProveedor) {
		this.lstProductoProveedor = lstProductoProveedor;
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

	public Integer getCodProdProvee() {
		return codProdProvee;
	}

	public void setCodProdProvee(Integer codProdProvee) {
		this.codProdProvee = codProdProvee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getTagTipoListado() {
		return tagTipoListado;
	}

	public void setTagTipoListado(Integer tagTipoListado) {
		this.tagTipoListado = tagTipoListado;
	}

	public List<ArticuloDTO> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<ArticuloDTO> lstProducto) {
		this.lstProducto = lstProducto;
	}


	 
	
	
	
	
	
	
}

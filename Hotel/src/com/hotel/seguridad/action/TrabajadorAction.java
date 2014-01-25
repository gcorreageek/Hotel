/**
 * UsuarioAction 30/07/2013
 */
package com.hotel.seguridad.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.hotel.seguridad.bean.AreaDTO;
import com.hotel.seguridad.bean.CargoDTO;
import com.hotel.seguridad.bean.TrabajadorDTO;
import com.hotel.seguridad.service.AreaService;
import com.hotel.seguridad.service.CargoService;
import com.hotel.seguridad.service.TrabajadorService;
import com.hotel.util.Constantes;
import com.hotel.util.UtilSigal;

/**
 * @author Gustavo A. Correa C.
 *
 */
@ParentPackage("Hotel")
public class TrabajadorAction extends ActionSupport {
	private final Map<String, Object> lasesion = ActionContext.getContext().getSession();
	private final Log log = LogFactory.getLog(getClass());
	TrabajadorService objUsuarioServ = new TrabajadorService();
	AreaService objAreaServ = new AreaService();
	CargoService objCargoServ = new CargoService();
	private TrabajadorDTO objUsuario;
	private List<TrabajadorDTO> lstUsuario;
	private String mensaje;
	private Integer rsult;
	private Integer codUsuario;
	private Integer id;
	private Integer inicio;
	private Integer numeroPaginas;
	private Integer tagTipoListado;
	private Integer numeroPaginasModalUsuario;
	private List<AreaDTO> lstArea = new ArrayList<AreaDTO>();
	private List<CargoDTO> lstCargo = new ArrayList<CargoDTO>(); 
	

	@Action(value="/mainTrabajador",results={@Result(name="success",type="tiles",location="d_maintrabajador")})
	public String mainTrabajador(){ 
		try {
			lstUsuario = objUsuarioServ.listaUsuarioPaginado(0, Constantes.FILAS_X_PAGINA);
			this.numeroPaginas = UtilSigal.totalDePaginas(objUsuarioServ.listaUsuarioTotal());
			this.tagTipoListado = 1;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return SUCCESS; 
	} 
	@Action(value = "/listarTrabajadorPag", results = { @Result(name = "success", location = "/paginas/mantenimientos/paginacion_trabajador.jsp") })
	public String listarTrabajadorPag() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA)
					- Constantes.FILAS_X_PAGINA;
		}
		try {  
			lstUsuario = objUsuarioServ.listaUsuarioPaginado(comienzo, Constantes.FILAS_X_PAGINA);
			System.out.println("dddPaginacion:"+lstUsuario.size());
		} catch (Exception e) { 
			System.out.println(""+e.getMessage());
		}
		return SUCCESS;
	}
 

	@Action(value = "/buscarTrabajadorXDesc_usuarioPag", results = { @Result(name = "success", location = "/paginas/mantenimientos/paginacion_trabajador.jsp") })
	public String buscarTrabajadorXDesc_usuarioPag() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA)- Constantes.FILAS_X_PAGINA;
		}
		try {
			lstUsuario = objUsuarioServ.buscarUsuarioXDescPaginado(objUsuario,comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "/buscarTrabajadorXDesc_usuario", results = { @Result(name = "success", type = "tiles", location = "d_maintrabajador") })
	public String buscarTrabajadorXDesc_usuario() {
		try {
			lstUsuario = objUsuarioServ.buscarUsuarioXDescPaginado(objUsuario, 0,
					Constantes.FILAS_X_PAGINA);
			this.numeroPaginas = UtilSigal.totalDePaginas(objUsuarioServ
					.buscarUsuarioXDescTotal(objUsuario));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.tagTipoListado = 2;
		return SUCCESS;
	}

	@Action(value = "/accionTrabajador", results = { @Result(name = "success", type = "tiles", location = "d_actuartrabajador") })
	public String accionTrabajador() {
		System.out.println("cod:" + codUsuario);
		try {
			lstArea = objAreaServ.listaArea(); 
			if (this.codUsuario != null) {
				System.out.println("codUsuario:" + codUsuario);
				this.objUsuario = objUsuarioServ.getUsuario(this.codUsuario);
				objUsuario.setPass_trabajador("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}

	@Action(value = "/eliminarTrabajador", results = { @Result(name = "success", type = "tiles", location = "d_maintrabajador") })
	public String eliminarTrabajador() {
		TrabajadorDTO usuario =  (TrabajadorDTO) lasesion.get("objUsuario");
		if(usuario!=null){
			TrabajadorDTO provee = new TrabajadorDTO();
			provee.setCod_trabajador(this.codUsuario);
			Boolean rsultado=false;
			try {
				rsultado = objUsuarioServ.eliminarTrabajador(provee);
			}catch (Exception  e ) { 
				e.printStackTrace();
			}
			if (rsultado) {
				this.rsult = 0;
				this.mensaje = "Se Elimino Correctamente";
			} else {
				this.rsult = 1;
				this.mensaje = "Ocurrio un Problema";
			}
			mainTrabajador();
		} 
		return SUCCESS;
	}

	@Action(value = "/actuarTrabajador", results = { @Result(name = "success", location = "/paginas/mantenimientos/mensaje_trabajador.jsp") })
	public String actuarTrabajador() { 
		System.out.println("enyta al actuar");
		Boolean rsultado = false;
		objUsuario.setNom_trabajador(objUsuario.getNom_trabajador().trim());
		objUsuario.setCorreo_trabajador(objUsuario.getCorreo_trabajador().trim());
		objUsuario.setUsu_trabajador(objUsuario.getUsu_trabajador().trim()); 
		objUsuario.setPass_trabajador(objUsuario.getPass_trabajador().trim());
		if(!"".equals(objUsuario.getNom_trabajador()) && !"".equals(objUsuario.getCorreo_trabajador())
				&& !"".equals(objUsuario.getUsu_trabajador()) ){ 
			rsultado = true;
		}
		if(rsultado){
			try {
				if (objUsuario.getCod_trabajador() == null) { 
					if(!"".equals(objUsuario.getPass_trabajador())){
						String pass= objUsuario.getPass_trabajador(); 
						pass = UtilSigal.getHash(pass); 
						objUsuario.setPass_trabajador(pass);
						rsultado = objUsuarioServ.registrarUsuario(objUsuario);
					} else{
						rsultado=false;
					}
				} else {
					String pass=objUsuarioServ.getUsuario(objUsuario.getCod_trabajador()).getPass_trabajador();
					objUsuario.setPass_trabajador(pass);
					rsultado = objUsuarioServ.actualizarUsuario(objUsuario);
				}
			} catch (Exception e) {
				rsultado=false;
				e.printStackTrace();
			}	
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
	 
	@Action(value = "/actualizaPassTrabajador", results = { @Result(name = "success", location = "/paginas/mantenimientos/mensaje_trabajador.jsp") })
	public String actualizaPassTrabajador() { 
		Boolean rsultado = false;
		String pass= objUsuario.getPass_trabajador();
		if (!"".equals(pass)) {
			rsultado = true;
		} 
		if (rsultado) {
			try {
				// String pass= objUsuario.getPass_usuario();
				pass = UtilSigal.getHash(pass);
				objUsuario = objUsuarioServ.getUsuario(objUsuario.getCod_trabajador());
				objUsuario.setPass_trabajador(pass); 
				rsultado = objUsuarioServ.actualizarUsuario(objUsuario);
			} catch (Exception e) {
				rsultado = false;
				e.printStackTrace();
			}
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
	@Action(value = "/listarTrabajadorPagModal", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_trabajador.jsp") })
	public String listarTrabajadorPagModal() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstUsuario = objUsuarioServ.listaUsuarioPaginado(comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	} 
	@Action(value = "/buscarTrabajadorXDesc_usuarioPagModal", results = { @Result(name = "success", location = "/paginas/mantenimientos/buscar_trabajador.jsp") })
	public String buscarUsuarioXRazonSocialPagModal() {
		Integer comienzo = null;
		if (inicio == null || inicio == 0) {
			comienzo = 0;
		} else {
			comienzo = (inicio * Constantes.FILAS_X_PAGINA) - Constantes.FILAS_X_PAGINA;
		}
		try {
			lstUsuario = objUsuarioServ.buscarUsuarioXDescPaginado(objUsuario, comienzo, Constantes.FILAS_X_PAGINA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "/listarTrabajadorTotal", results = { @Result(name = "success", location = "/paginas/mantenimientos/trabajador_listado_total.jsp") })
	public String listarTrabajadorTotal() { 
		try {
			this.numeroPaginasModalUsuario = UtilSigal.totalDePaginas(objUsuarioServ.listaUsuarioTotal());
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	@Action(value = "/buscarTrabajadorTotal", results = { @Result(name = "success", location = "/paginas/mantenimientos/trabajador_buscar_total.jsp") })
	public String buscarTrabajadorTotal() { 
		try {
			this.numeroPaginasModalUsuario = UtilSigal.totalDePaginas(objUsuarioServ.buscarUsuarioXDescTotal(objUsuario));
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "/cboCargo", results = { @Result(name = "success", location = "/paginas/mantenimientos/cboCargo.jsp") })
	public String cboCargo() { 
		System.out.println("area:"+objUsuario.getCod_area());
		System.out.println("cargo:"+objUsuario.getCod_cargo());
		try {
			this.lstCargo = objCargoServ.listaCargoXIdArea(objUsuario.getCod_area()); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
		System.out.println("cargo:"+objUsuario.getCod_cargo());
		return SUCCESS;
	}





	public TrabajadorDTO getObjUsuario() {
		return objUsuario;
	}

	public void setObjUsuario(TrabajadorDTO objUsuario) {
		this.objUsuario = objUsuario;
	}

	public List<TrabajadorDTO> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<TrabajadorDTO> lstUsuario) {
		this.lstUsuario = lstUsuario;
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

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
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

	public Integer getNumeroPaginasModalUsuario() {
		return numeroPaginasModalUsuario;
	}

	public void setNumeroPaginasModalUsuario(Integer numeroPaginasModalUsuario) {
		this.numeroPaginasModalUsuario = numeroPaginasModalUsuario;
	}
	public List<AreaDTO> getLstArea() {
		return lstArea;
	}
	public void setLstArea(List<AreaDTO> lstArea) {
		this.lstArea = lstArea;
	}
	public List<CargoDTO> getLstCargo() {
		return lstCargo;
	}
	public void setLstCargo(List<CargoDTO> lstCargo) {
		this.lstCargo = lstCargo;
	}
	
	
	
	
	
	
}

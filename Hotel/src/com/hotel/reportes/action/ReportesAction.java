/**
 * Reportes 09/08/2013
 */
package com.hotel.reportes.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.hotel.informeexterno.bean.InformeExternoDTO;
import com.hotel.informeexterno.bean.InformeExternoDetalleDTO;
import com.hotel.informeexterno.service.InformeExternoService;
import com.hotel.informeinterno.bean.InformeInternoDTO;
import com.hotel.informeinterno.bean.InformeInternoDetalleDTO;
import com.hotel.informeinterno.service.InformeInternoService;
import com.hotel.mantenimiento.bean.ArticuloDTO;
import com.hotel.mantenimiento.service.ArticuloService;

/**
 * @author Gustavo A. Correa C.
 *
 */
@SuppressWarnings("serial")
@ParentPackage("Hotel")
public class ReportesAction extends ActionSupport {
	private final Log log = org.apache.commons.logging.LogFactory.getLog(getClass());
	private final ArticuloService objProServ = new ArticuloService();
	private final InformeInternoService  objInfInternoServ = new InformeInternoService();
	private final InformeExternoService  objInfExternoServ = new InformeExternoService();
	
	private String informe;
	private String tipo;
	
	private List<ArticuloDTO>  lstProductos = new ArrayList<ArticuloDTO>();
	private ArticuloDTO objProducto;
	
	
	private List<InformeInternoDTO>  lstInformeInterno = new ArrayList<InformeInternoDTO>();
	private List<InformeExternoDTO>  lstInformeExterno = new ArrayList<InformeExternoDTO>();
	
	
	
	@Action(value="/articulos",results={@Result(name="success",type="tiles",location="d_mainreportesarticulos")})
	public String articulos(){  
		return SUCCESS; 
	}  
	@Action(value="/reporteArticulos",results={@Result(name="success",type="jasper",
			params= {"format","PDF","contentDisposition","attachment;filename=\"report_articuloss.pdf\"","dataSource","lstProductos"}, 
			location = "reportes/rpt_productos.jasper")})
	public String reporteArticulos(){  
		log.debug("pasa por aki!");
		objProducto.setDesc_articulo(objProducto.getDesc_articulo().trim());
		if("Seleccionar".equals(objProducto.getUnidadMedida())){
			objProducto.setUnidadMedida("");
		}
		try {
			this.lstProductos = objProServ.buscarProductosXDesc(objProducto);
//			for (ArticuloDTO a : lstProductos) {
//				log.debug("art:"+a.getDesc_articulo()+"|"+a.getCod_articulo());
//			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		return SUCCESS;
	} 
	@Action(value="/informe",results={@Result(name="success",type="tiles",location="d_mainreportesinforme")})
	public String informe(){  
		return SUCCESS; 
	} 
	@Action(value="/reporteInformeInterno",results={@Result(name="success",type="jasper",
			params= {"format","PDF","contentDisposition","attachment;filename=\"reportes_informes_interno.pdf\"","dataSource","lstInformeInterno"}, 
			location = "reportes/report2.jasper")})
	public String reporteInforme(){  //rpt_informe_interno.jrxml
//		report2.jasper
//		report2_subreport1.jasper
		log.error("Tipo:"+this.tipo);
		InformeInternoDTO ii = new InformeInternoDTO();
		if("Seleccionar".equals(this.tipo)){
			ii.setTipo_informe_interno("");
		}else{
			ii.setTipo_informe_interno(this.tipo);
		} 
		
		try {
			lstInformeInterno= objInfInternoServ.lista(ii); 
			for (InformeInternoDTO iiie : lstInformeInterno) {
				log.debug("iie1:"+iiie.getCod_informe_interno());
				log.debug("iie2:"+iiie.getCod_trabajador());
				log.debug("iie2:"+iiie.getNom_trabajador());
				log.debug("iie3:"+iiie.getObs_informeinterno());
				log.debug("iie4:"+iiie.getTipo_informe_interno());
				log.debug("iie5:"+iiie.getFecha_informe_interno());
				List<InformeInternoDetalleDTO> ll = iiie.getDetalle();
				for (InformeInternoDetalleDTO iid : ll) {
					log.debug("ii:"+iid.getDesc_articulo());
					log.debug("ii:"+iid.getUnidadMedida());
					log.debug("ii:"+iid.getCantidad()); 
				} 
			}
			if(log.isDebugEnabled()){
				log.debug("valorr::"+lstInformeInterno);
			}
		} catch (Exception e) {
			log.error(e);
		} 
		return SUCCESS;
	} 
	@Action(value="/reporteInformeExterno",results={@Result(name="success",type="jasper",
			params= {"format","PDF","contentDisposition","attachment;filename=\"reportes_informes_externo.pdf\"","dataSource","lstInformeExterno"}, 
			location = "reportes/report3.jasper")})
	public String reporteInformeExterno(){
//		report3.jasper
//		report3_subreport1.jasper
		log.error("Tipo:"+this.tipo);
		InformeExternoDTO ie = new InformeExternoDTO();
		if("Seleccionar".equals(this.tipo)){
			ie.setTipo_informe_externo("");
		}else{
			ie.setTipo_informe_externo(this.tipo);
		} 
		
		try {
			lstInformeExterno= objInfExternoServ.lista(ie);
			for (InformeExternoDTO ieww : lstInformeExterno) { 
				log.debug("ie2:"+ieww.getFecha_informe_externo());
				log.debug("ie3:"+ieww.getNom_trabajador());
				log.debug("ie4:"+ieww.getEstado_ordencompra());
				log.debug("ie5:"+ieww.getCod_trabajador());
				log.debug("ie5:"+ieww.getTamano());
				log.debug("ie5:"+ieww.getCod_ordencompra());
				log.debug("ie5:"+ieww.getDesc_area());
				log.debug("ie5:"+ieww.getCod_informe_externo());
				log.debug("ie5:"+ieww.getObs_informeexterno());
				log.debug("ie5:"+ieww.getDesc_cargo());
				log.debug("ie5:"+ieww.getFecharegistro_ordencompra());
				log.debug("ie5:"+ieww.getTipo_informe_externo());
				log.debug("ie5:"+ieww.getTipo_ordencompra());
//				tipo_ordencompra
				List<InformeExternoDetalleDTO> iedd = ieww.getDetalle();
				for (InformeExternoDetalleDTO iedddd : iedd) {
					log.debug("det:"+iedddd.getCod_informe_externo());
					log.debug("det:"+iedddd.getRaz_social());
					log.debug("det:"+iedddd.getCod_detalle_informe_externo());
					log.debug("det:"+iedddd.getCod_detalle_ordencompra());
					log.debug("det:"+iedddd.getCantidad());
					log.debug("det:"+iedddd.getUnidadMedida());
					log.debug("det:"+iedddd.getDesc_articulo());
				}
			}
			if(log.isDebugEnabled()){
				log.debug("valorr::"+lstInformeExterno);
			}
		} catch (Exception e) {
			log.error(e);
		} 
		return SUCCESS;
	} 
	
	
	
	/**
	 * @return the lstProductos
	 */
	public List<ArticuloDTO> getLstProductos() {
		return lstProductos;
	} 
	/**
	 * @param lstProductos the lstProductos to set
	 */
	public void setLstProductos(List<ArticuloDTO> lstProductos) {
		this.lstProductos = lstProductos;
	}
	/**
	 * @return the objProducto
	 */
	public ArticuloDTO getObjProducto() {
		return objProducto;
	}
	/**
	 * @param objProducto the objProducto to set
	 */
	public void setObjProducto(ArticuloDTO objProducto) {
		this.objProducto = objProducto;
	}
	/**
	 * @return the informe
	 */
	public String getInforme() {
		return informe;
	}
	/**
	 * @param informe the informe to set
	 */
	public void setInforme(String informe) {
		this.informe = informe;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the lstInformeInterno
	 */
	public List<InformeInternoDTO> getLstInformeInterno() {
		return lstInformeInterno;
	}
	/**
	 * @param lstInformeInterno the lstInformeInterno to set
	 */
	public void setLstInformeInterno(List<InformeInternoDTO> lstInformeInterno) {
		this.lstInformeInterno = lstInformeInterno;
	}
	/**
	 * @return the lstInformeExterno
	 */
	public List<InformeExternoDTO> getLstInformeExterno() {
		return lstInformeExterno;
	}
	/**
	 * @param lstInformeExterno the lstInformeExterno to set
	 */
	public void setLstInformeExterno(List<InformeExternoDTO> lstInformeExterno) {
		this.lstInformeExterno = lstInformeExterno;
	}

 
	
	
	
	
	
	
	

}

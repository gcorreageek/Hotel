/**
 * InformeInternoDetalleDTO 29/07/2013
 */
package com.hotel.informeinterno.bean;

import java.io.Serializable;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class InformeInternoDetalleDTO implements Serializable{
//	cod_detalle_informe_interno,cod_informe_interno,cod_detalle_pedido
	 
	private static final long serialVersionUID = -4296690978663298816L;
	private Integer cod_detalle_informe_interno;
	private Integer cod_informe_interno;
	private Integer cod_detalle_solicitud_requerimiento;
//	cod_detalle_solicitud_requerimiento
	
	private	Integer	cantidad	;
	private	String	desc_articulo	;
	private	String	unidadMedida	; 
	
	public Integer getCod_detalle_informe_interno() {
		return cod_detalle_informe_interno;
	}
	public void setCod_detalle_informe_interno(Integer cod_detalle_informe_interno) {
		this.cod_detalle_informe_interno = cod_detalle_informe_interno;
	}
	public Integer getCod_informe_interno() {
		return cod_informe_interno;
	}
	public void setCod_informe_interno(Integer cod_informe_interno) {
		this.cod_informe_interno = cod_informe_interno;
	}
	public Integer getCod_detalle_solicitud_requerimiento() {
		return cod_detalle_solicitud_requerimiento;
	}
	public void setCod_detalle_solicitud_requerimiento(Integer cod_detalle_pedido) {
		this.cod_detalle_solicitud_requerimiento = cod_detalle_pedido;
	}
	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the desc_articulo
	 */
	public String getDesc_articulo() {
		return desc_articulo;
	}
	/**
	 * @param desc_articulo the desc_articulo to set
	 */
	public void setDesc_articulo(String desc_articulo) {
		this.desc_articulo = desc_articulo;
	}
	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}
	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	} 
	
	
	
	
	

}

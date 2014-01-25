/**
 * CotizacionDetalle 28/07/2013
 */
package com.hotel.cotizacion.bean;

import java.io.Serializable;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class CotizacionDetalleDTO implements Serializable{ 
	 
	private static final long serialVersionUID = 4789276187071888734L;
	private Integer cod_detallecotizacion;
	private Integer cant_detallecotizacion;
	private Integer cod_cotizacion;
	private Integer cod_articulo_proveedor;
	
	private Integer cod_articulo;
	private	String	desc_articulo	;
	private	String	unidadMedida	;
	public Integer getCod_detallecotizacion() {
		return cod_detallecotizacion;
	}
	public void setCod_detallecotizacion(Integer cod_detallecotizacion) {
		this.cod_detallecotizacion = cod_detallecotizacion;
	}
	public Integer getCant_detallecotizacion() {
		return cant_detallecotizacion;
	}
	public void setCant_detallecotizacion(Integer cant_detallecotizacion) {
		this.cant_detallecotizacion = cant_detallecotizacion;
	}
	public Integer getCod_cotizacion() {
		return cod_cotizacion;
	}
	public void setCod_cotizacion(Integer cod_cotizacion) {
		this.cod_cotizacion = cod_cotizacion;
	}
	public Integer getCod_articulo_proveedor() {
		return cod_articulo_proveedor;
	}
	public void setCod_articulo_proveedor(Integer cod_articulo_proveedor) {
		this.cod_articulo_proveedor = cod_articulo_proveedor;
	}
	public Integer getCod_articulo() {
		return cod_articulo;
	}
	public void setCod_articulo(Integer cod_articulo) {
		this.cod_articulo = cod_articulo;
	}
	public String getDesc_articulo() {
		return desc_articulo;
	}
	public void setDesc_articulo(String desc_articulo) {
		this.desc_articulo = desc_articulo;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	} 
	
	
	
	
	
	
	
	
	
}

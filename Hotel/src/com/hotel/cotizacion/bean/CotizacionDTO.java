/**
 * CotizacionDTO 28/07/2013
 */
package com.hotel.cotizacion.bean;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class CotizacionDTO { 
	
	private Integer cod_cotizacion;
//	private String obs_cotizacion;
	private Integer cod_trabajador;
	private java.sql.Date fecharegistro_cotizacion;
	private Integer cod_proveedor;
	private	String	tipo_cotizacion	; 
	private	java.sql.Date	fecha_devolucion_cotizacion	;
	private	java.sql.Date	fecha_entrega_cotizacion 	;
	
	private String raz_social;
	private String nom_trabajador;
	
	private Integer inicio;
	private Integer tamano; 
	private java.sql.Date fechaInicio;
	private java.sql.Date fechaFin;
	
	public Integer getCod_cotizacion() {
		return cod_cotizacion;
	}
	public void setCod_cotizacion(Integer cod_cotizacion) {
		this.cod_cotizacion = cod_cotizacion;
	}
//	public String getObs_cotizacion() {
//		return obs_cotizacion;
//	}
//	public void setObs_cotizacion(String obs_cotizacion) {
//		this.obs_cotizacion = obs_cotizacion;
//	}
	public Integer getCod_trabajador() {
		return cod_trabajador;
	}
	public void setCod_trabajador(Integer cod_trabajador) {
		this.cod_trabajador = cod_trabajador;
	}
	public java.sql.Date getFecharegistro_cotizacion() {
		return fecharegistro_cotizacion;
	}
	public void setFecharegistro_cotizacion(java.sql.Date fecharegistro_cotizacion) {
		this.fecharegistro_cotizacion = fecharegistro_cotizacion;
	}
	public Integer getInicio() {
		return inicio;
	}
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}
	public Integer getTamano() {
		return tamano;
	}
	public void setTamano(Integer tamano) {
		this.tamano = tamano;
	}
	public Integer getCod_proveedor() {
		return cod_proveedor;
	}
	public void setCod_proveedor(Integer cod_proveedor) {
		this.cod_proveedor = cod_proveedor;
	}
	public String getRaz_social() {
		return raz_social;
	}
	public void setRaz_social(String raz_social) {
		this.raz_social = raz_social;
	}
	public String getNom_trabajador() {
		return nom_trabajador;
	}
	public void setNom_trabajador(String nom_trabajador) {
		this.nom_trabajador = nom_trabajador;
	}
	public java.sql.Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(java.sql.Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public java.sql.Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(java.sql.Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getTipo_cotizacion() {
		return tipo_cotizacion;
	}
	public void setTipo_cotizacion(String tipo_cotizacion) {
		this.tipo_cotizacion = tipo_cotizacion;
	}
	public java.sql.Date getFecha_devolucion_cotizacion() {
		return fecha_devolucion_cotizacion;
	}
	public void setFecha_devolucion_cotizacion(
			java.sql.Date fecha_devolucion_cotizacion) {
		this.fecha_devolucion_cotizacion = fecha_devolucion_cotizacion;
	}
	public java.sql.Date getFecha_entrega_cotizacion() {
		return fecha_entrega_cotizacion;
	}
	public void setFecha_entrega_cotizacion(java.sql.Date fecha_entrega_cotizacion) {
		this.fecha_entrega_cotizacion = fecha_entrega_cotizacion;
	}
	
	
	
	
	
	
	
}

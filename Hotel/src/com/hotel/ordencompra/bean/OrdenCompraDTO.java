/**
 * OrdenCompraDTO 28/07/2013
 */
package com.hotel.ordencompra.bean;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class OrdenCompraDTO {

	private Integer cod_OrdenCompra;
	private java.sql.Date fecharegistro_ordencompra;
	private Integer cod_trabajador;
	private Integer cod_cotizacion;
	private Integer cod_proveedor;
	private String estado_ordencompra;
	private	String	tipo_ordencompra	; 
	private	java.sql.Date	fecha_devolucion_ordencompra	;
	private	java.sql.Date	fecha_entrega_ordencompra 	;
	
	
	
	public String getTipo_ordencompra() {
		return tipo_ordencompra;
	}
	public void setTipo_ordencompra(String tipo_ordencompra) {
		this.tipo_ordencompra = tipo_ordencompra;
	}
	public java.sql.Date getFecha_devolucion_ordencompra() {
		return fecha_devolucion_ordencompra;
	}
	public void setFecha_devolucion_ordencompra(
			java.sql.Date fecha_devolucion_ordencompra) {
		this.fecha_devolucion_ordencompra = fecha_devolucion_ordencompra;
	}
	public java.sql.Date getFecha_entrega_ordencompra() {
		return fecha_entrega_ordencompra;
	}
	public void setFecha_entrega_ordencompra(java.sql.Date fecha_entrega_ordencompra) {
		this.fecha_entrega_ordencompra = fecha_entrega_ordencompra;
	}
	private String raz_social;
	private String nom_trabajador;
	
	private Integer inicio;
	private Integer tamano; 
	private java.sql.Date fechaInicio;
	private java.sql.Date fechaFin;

	public Integer getCod_OrdenCompra() {
		return cod_OrdenCompra;
	}
	public void setCod_OrdenCompra(Integer cod_OrdenCompra) {
		this.cod_OrdenCompra = cod_OrdenCompra;
	}
	public java.sql.Date getFecharegistro_ordencompra() {
		return fecharegistro_ordencompra;
	}
	public void setFecharegistro_ordencompra(java.sql.Date fecharegistro_ordencompra) {
		this.fecharegistro_ordencompra = fecharegistro_ordencompra;
	}
	public Integer getCod_trabajador() {
		return cod_trabajador;
	}
	public void setCod_trabajador(Integer cod_trabajador) {
		this.cod_trabajador = cod_trabajador;
	}
	public Integer getCod_cotizacion() {
		return cod_cotizacion;
	}
	public void setCod_cotizacion(Integer cod_cotizacion) {
		this.cod_cotizacion = cod_cotizacion;
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
	public String getEstado_ordencompra() {
		return estado_ordencompra;
	}
	public void setEstado_ordencompra(String estado_ordencompra) {
		this.estado_ordencompra = estado_ordencompra;
	} 
 
	
	
	
	
	
}

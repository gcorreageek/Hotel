package com.hotel.solicitudreq.bean;

public class DetalleSolicitudReqDTO {

	
	private	Integer	cod_detalleRequerimiento	;
	private	Integer	cod_solicitudRequerimiento	;
	private	Integer	cod_articulo	;
	private	Integer	cantidad	;
	private	String	desc_articulo	;
	private	String	unidadMedida	; 
	
	   
	private	Integer	stock_articulo	; 
	
	
	public Integer getCod_detalleRequerimiento() {
		return cod_detalleRequerimiento;
	}
	public void setCod_detalleRequerimiento(Integer cod_detalleRequerimiento) {
		this.cod_detalleRequerimiento = cod_detalleRequerimiento;
	}
	public Integer getCod_solicitudRequerimiento() {
		return cod_solicitudRequerimiento;
	}
	public void setCod_solicitudRequerimiento(Integer cod_solicitudRequerimiento) {
		this.cod_solicitudRequerimiento = cod_solicitudRequerimiento;
	}
	public Integer getCod_articulo() {
		return cod_articulo;
	}
	public void setCod_articulo(Integer cod_articulo) {
		this.cod_articulo = cod_articulo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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
	public Integer getStock_articulo() {
		return stock_articulo;
	}
	public void setStock_articulo(Integer stock_articulo) {
		this.stock_articulo = stock_articulo;
	}
	
	
	
	 
	
	
	
	
	
}

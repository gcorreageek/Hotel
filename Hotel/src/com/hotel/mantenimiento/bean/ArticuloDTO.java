package com.hotel.mantenimiento.bean;

import java.io.Serializable;

public class ArticuloDTO implements Serializable {
 
	private static final long serialVersionUID = 841008064908519638L;
	private	Integer	cod_articulo	;
	private	String	desc_articulo	;
	private	String	unidadMedida	;  
	private	String	tipo_articulo	;
	private	Integer	stock_articulo	; 
	private	Integer	inicio	;
	private	Integer	tamano	;
	private String habilitado;
	
	private Integer cod_proveedor;
	
	
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
 
	public Integer getStock_articulo() {
		return stock_articulo;
	}
	public void setStock_articulo(Integer stock_articulo) {
		this.stock_articulo = stock_articulo;
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
	public String getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}
	public String getTipo_articulo() {
		return tipo_articulo;
	}
	public void setTipo_articulo(String tipo_articulo) {
		this.tipo_articulo = tipo_articulo;
	}
 
	
	
	
	
	
}

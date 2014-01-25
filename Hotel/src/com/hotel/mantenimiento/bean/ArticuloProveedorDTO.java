/**
 * ProveedorDTO 24/07/2013
 */
package com.hotel.mantenimiento.bean;

import java.io.Serializable;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class ArticuloProveedorDTO  implements
 Serializable{   
	private static final long serialVersionUID = 6444869094378899037L;
	private Integer cod_articulo_proveedor;
	private	Integer	cod_articulo	;
	private	String	desc_articulo	;
	private	String	unidadMedida	;
	private	String	marca	;
	private String 	categoria;
	private Integer cod_proveedor;
	private String raz_social;  
	private String habilitado;
	
	private Integer inicio;
	private Integer tamano;
	
	
	
	
	
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
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	public String getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	} 
	
	
	

}

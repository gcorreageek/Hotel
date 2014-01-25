package com.hotel.seguridad.bean;

import java.io.Serializable;

public class TrabajadorDTO  implements Serializable{
 
	private static final long serialVersionUID = 3028111602034688467L;
	private Integer cod_trabajador;
	private String nom_trabajador; 
	private String correo_trabajador;
	private Integer cod_cargo; 
	private String usu_trabajador;
	private String pass_trabajador;  
	private String habilitado; 
	
	private   Integer inicio;
	private   Integer tamano;
	private   String desc_cargo;
	private   Integer cod_area;
	private   String desc_area;
  
	public Integer getCod_trabajador() {
		return cod_trabajador;
	}
	public void setCod_trabajador(Integer cod_trabajador) {
		this.cod_trabajador = cod_trabajador;
	}
	public String getNom_trabajador() {
		return nom_trabajador;
	}
	public void setNom_trabajador(String nom_trabajador) {
		this.nom_trabajador = nom_trabajador;
	}
	public String getCorreo_trabajador() {
		return correo_trabajador;
	}
	public void setCorreo_trabajador(String correo_trabajador) {
		this.correo_trabajador = correo_trabajador;
	}
	public Integer getCod_cargo() {
		return cod_cargo;
	}
	public void setCod_cargo(Integer cod_cargo) {
		this.cod_cargo = cod_cargo;
	}
	public String getUsu_trabajador() {
		return usu_trabajador;
	}
	public void setUsu_trabajador(String usu_trabajador) {
		this.usu_trabajador = usu_trabajador;
	}
	public String getPass_trabajador() {
		return pass_trabajador;
	}
	public void setPass_trabajador(String pass_trabajador) {
		this.pass_trabajador = pass_trabajador;
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
	public String getDesc_cargo() {
		return desc_cargo;
	}
	public void setDesc_cargo(String desc_cargo) {
		this.desc_cargo = desc_cargo;
	}
	public Integer getCod_area() {
		return cod_area;
	}
	public void setCod_area(Integer cod_area) {
		this.cod_area = cod_area;
	}
	public String getDesc_area() {
		return desc_area;
	}
	public void setDesc_area(String desc_area) {
		this.desc_area = desc_area;
	}
	
	
		
		
		
}
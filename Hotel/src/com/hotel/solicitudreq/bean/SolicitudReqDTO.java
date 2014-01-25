package com.hotel.solicitudreq.bean;

import java.io.Serializable;
import java.sql.Date;

public class SolicitudReqDTO  implements Serializable{
 
	private static final long serialVersionUID = 645338127658125217L;
	private	Integer	cod_solicitudRequerimiento	; //objPedido.cod_solicitudRequerimiento
	private	Integer	cod_trabajador	;
	private	String	comentario_solicitud_requerimiento	;
	private	String	tipo_solicitud_requerimiento	;//objPedido.tipo_solicitud_requerimiento
	private	java.sql.Date	fechaDevolucion_solicitud_requerimiento	;
	private	java.sql.Date	fechaRegistro_solicitud_requerimiento	;//objPedido.fechaRegistro_solicitud_requerimiento
	private	java.sql.Date	fechaEntrega_solicitud_requerimiento	;
	private String    estado_solicitud_requerimiento;
	private	java.sql.Date	fechaEvaluacion_solicitud_requerimiento	;
	private String comentarioevaluacion_solicitud_requerimiento;
	 
	private String nom_trabajador;//objPedido.nom_trabajador
	private String apePat_usuario;
	private String apeMat_usuario;
	private Integer cod_area;//objPedido.cod_area
	private String desc_area;
	private Integer cod_cargo;
	private String desc_cargo;
	private Integer inicio;
	private Integer tamano;
	private java.sql.Date fechaInicio;
	private java.sql.Date fechaFin;
	
//	cod_solicitudRequerimiento
	public Integer getCod_solicitudRequerimiento() {
		return cod_solicitudRequerimiento;
	}
	public void setCod_solicitudRequerimiento(Integer cod_solicitudRequerimiento) {
		this.cod_solicitudRequerimiento = cod_solicitudRequerimiento;
	}
	public Integer getCod_usuario() {
		return cod_trabajador;
	}
	public void setCod_usuario(Integer cod_trabajador) {
		this.cod_trabajador = cod_trabajador;
	}
//	comentario_solicitud_requerimiento
	public String getComentario_solicitud_requerimiento() {
		return comentario_solicitud_requerimiento;
	}
	public void setComentario_solicitud_requerimiento(String comentario_solicitud_requerimiento) {
		this.comentario_solicitud_requerimiento = comentario_solicitud_requerimiento;
	}
	public String getTipo_solicitud_requerimiento() {
		return tipo_solicitud_requerimiento;
	}
	public void setTipo_solicitud_requerimiento(String tipo_solicitud_requerimiento) {
		this.tipo_solicitud_requerimiento = tipo_solicitud_requerimiento;
	}

	public java.sql.Date getFechaDevolucion_solicitud_requerimiento() {
		return fechaDevolucion_solicitud_requerimiento;
	}
	public void setFechaDevolucion_solicitud_requerimiento(java.sql.Date fechaDevolucion_solicitud_requerimiento) {
		this.fechaDevolucion_solicitud_requerimiento = fechaDevolucion_solicitud_requerimiento;
	}
	public java.sql.Date getFechaRegistro_solicitud_requerimiento() {
		return fechaRegistro_solicitud_requerimiento;
	}
	public void setFechaRegistro_solicitud_requerimiento(java.sql.Date fechaRegistro_solicitud_requerimiento) {
		this.fechaRegistro_solicitud_requerimiento = fechaRegistro_solicitud_requerimiento;
	}
	public java.sql.Date getFechaEntrega_solicitud_requerimiento() {
		return fechaEntrega_solicitud_requerimiento;
	}
	public void setFechaEntrega_solicitud_requerimiento(java.sql.Date fechaEntrega_solicitud_requerimiento) {
		this.fechaEntrega_solicitud_requerimiento = fechaEntrega_solicitud_requerimiento;
	}
	public String getEstado_solicitud_requerimiento() {
		return estado_solicitud_requerimiento;
	}
	public void setEstado_solicitud_requerimiento(String estado_solicitud_requerimiento) {
		this.estado_solicitud_requerimiento = estado_solicitud_requerimiento;
	}
	public String getNom_trabajador() {
		return nom_trabajador;
	}
	public void setNom_trabajador(String nom_trabajador) {
		this.nom_trabajador = nom_trabajador;
	}
	public String getApePat_usuario() {
		return apePat_usuario;
	}
	public void setApePat_usuario(String apePat_usuario) {
		this.apePat_usuario = apePat_usuario;
	}
	public String getApeMat_usuario() {
		return apeMat_usuario;
	}
	public void setApeMat_usuario(String apeMat_usuario) {
		this.apeMat_usuario = apeMat_usuario;
	}
	public String getDesc_area() {
		return desc_area;
	}
	public void setDesc_area(String desc_area) {
		this.desc_area = desc_area;
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
	public Integer getCod_cargo() {
		return cod_cargo;
	}
	public void setCod_cargo(Integer cod_cargo) {
		this.cod_cargo = cod_cargo;
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
	public String getComentarioevaluacion_solicitud_requerimiento() {
		return comentarioevaluacion_solicitud_requerimiento;
	}
	public void setComentarioevaluacion_solicitud_requerimiento(String comentarioevaluacion_solicitud_requerimiento) {
		this.comentarioevaluacion_solicitud_requerimiento = comentarioevaluacion_solicitud_requerimiento;
	}
	public java.sql.Date getFechaEvaluacion_solicitud_requerimiento() {
		return fechaEvaluacion_solicitud_requerimiento;
	}
	public void setFechaEvaluacion_solicitud_requerimiento(java.sql.Date fechaEvaluacion_solicitud_requerimiento) {
		this.fechaEvaluacion_solicitud_requerimiento = fechaEvaluacion_solicitud_requerimiento;
	}
	
	
	
	
	
	
	
}

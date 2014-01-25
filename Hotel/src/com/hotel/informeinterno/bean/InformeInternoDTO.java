/**
 * InformeInternoDTO 29/07/2013
 */
package com.hotel.informeinterno.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class InformeInternoDTO  implements Serializable{ 
	private static final long serialVersionUID = 338366121961987591L;
	//	select cod_informe_interno, nro_informe_interno,fecha_informe_interno,
//	tipo_informe_interno, cod_trabajador, cod_pedido from tb_informe_interno
	private Integer cod_informe_interno;
	private java.sql.Date fecha_informe_interno;
	private String tipo_informe_interno;
	private Integer cod_trabajador;
	private Integer cod_solicitud_requerimiento;
	private String obs_informeinterno;
	
	private String nom_trabajador;
	private Integer inicio;
	private Integer tamano;
	private java.sql.Date fechaInicio;
	private java.sql.Date fechaFin;
	
	/**
	 * 
	 */
//	private String nom_trabajador;
	private String desc_cargo;
	private String desc_area;
	private	String	comentario_solicitud_requerimiento	;
	private	String	tipo_solicitud_requerimiento	;//objPedido.tipo_solicitud_requerimiento
	private	java.sql.Date	fechaDevolucion_solicitud_requerimiento	;
	private	java.sql.Date	fechaRegistro_solicitud_requerimiento	;//objPedido.fechaRegistro_solicitud_requerimiento
	private	java.sql.Date	fechaEntrega_solicitud_requerimiento	;
	private String    estado_solicitud_requerimiento;
	private	java.sql.Date	fechaEvaluacion_solicitud_requerimiento	;
	private String comentarioevaluacion_solicitud_requerimiento;
    //Detalle
	private	Integer	cantidad	;
	private	String	desc_articulo	;
	private	String	unidadMedida	; 
	
	/**
	 * 
	 */
	
	
	
	
	
	
	
	
	
	List<InformeInternoDetalleDTO> detalle ;
	
	
	
	public Integer getCod_informe_interno() {
		return cod_informe_interno;
	}
	public void setCod_informe_interno(Integer cod_informe_interno) {
		this.cod_informe_interno = cod_informe_interno;
	}
	public java.sql.Date getFecha_informe_interno() {
		return fecha_informe_interno;
	}
	public void setFecha_informe_interno(java.sql.Date fecha_informe_interno) {
		this.fecha_informe_interno = fecha_informe_interno;
	}
	public String getTipo_informe_interno() {
		return tipo_informe_interno;
	}
	public void setTipo_informe_interno(String tipo_informe_interno) {
		this.tipo_informe_interno = tipo_informe_interno;
	}
	public Integer getCod_trabajador() {
		return cod_trabajador;
	}
	public void setCod_trabajador(Integer cod_trabajador) {
		this.cod_trabajador = cod_trabajador;
	}
	public Integer getCod_solicitud_requerimiento() {
		return cod_solicitud_requerimiento;
	}
	public void setCod_solicitud_requerimiento(Integer cod_pedido) {
		this.cod_solicitud_requerimiento = cod_pedido;
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
	public String getObs_informeinterno() {
		return obs_informeinterno;
	}
	public void setObs_informeinterno(String obs_informeinterno) {
		this.obs_informeinterno = obs_informeinterno;
	}
	/**
	 * @return the detalle
	 */
	public List<InformeInternoDetalleDTO> getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(List<InformeInternoDetalleDTO> detalle) {
		this.detalle = detalle;
	}
	/**
	 * @return the desc_cargo
	 */
	public String getDesc_cargo() {
		return desc_cargo;
	}
	/**
	 * @param desc_cargo the desc_cargo to set
	 */
	public void setDesc_cargo(String desc_cargo) {
		this.desc_cargo = desc_cargo;
	}
	/**
	 * @return the desc_area
	 */
	public String getDesc_area() {
		return desc_area;
	}
	/**
	 * @param desc_area the desc_area to set
	 */
	public void setDesc_area(String desc_area) {
		this.desc_area = desc_area;
	}
	/**
	 * @return the comentario_solicitud_requerimiento
	 */
	public String getComentario_solicitud_requerimiento() {
		return comentario_solicitud_requerimiento;
	}
	/**
	 * @param comentario_solicitud_requerimiento the comentario_solicitud_requerimiento to set
	 */
	public void setComentario_solicitud_requerimiento(String comentario_solicitud_requerimiento) {
		this.comentario_solicitud_requerimiento = comentario_solicitud_requerimiento;
	}
	/**
	 * @return the tipo_solicitud_requerimiento
	 */
	public String getTipo_solicitud_requerimiento() {
		return tipo_solicitud_requerimiento;
	}
	/**
	 * @param tipo_solicitud_requerimiento the tipo_solicitud_requerimiento to set
	 */
	public void setTipo_solicitud_requerimiento(String tipo_solicitud_requerimiento) {
		this.tipo_solicitud_requerimiento = tipo_solicitud_requerimiento;
	}
	/**
	 * @return the fechaDevolucion_solicitud_requerimiento
	 */
	public java.sql.Date getFechaDevolucion_solicitud_requerimiento() {
		return fechaDevolucion_solicitud_requerimiento;
	}
	/**
	 * @param fechaDevolucion_solicitud_requerimiento the fechaDevolucion_solicitud_requerimiento to set
	 */
	public void setFechaDevolucion_solicitud_requerimiento(java.sql.Date fechaDevolucion_solicitud_requerimiento) {
		this.fechaDevolucion_solicitud_requerimiento = fechaDevolucion_solicitud_requerimiento;
	}
	/**
	 * @return the fechaRegistro_solicitud_requerimiento
	 */
	public java.sql.Date getFechaRegistro_solicitud_requerimiento() {
		return fechaRegistro_solicitud_requerimiento;
	}
	/**
	 * @param fechaRegistro_solicitud_requerimiento the fechaRegistro_solicitud_requerimiento to set
	 */
	public void setFechaRegistro_solicitud_requerimiento(java.sql.Date fechaRegistro_solicitud_requerimiento) {
		this.fechaRegistro_solicitud_requerimiento = fechaRegistro_solicitud_requerimiento;
	}
	/**
	 * @return the fechaEntrega_solicitud_requerimiento
	 */
	public java.sql.Date getFechaEntrega_solicitud_requerimiento() {
		return fechaEntrega_solicitud_requerimiento;
	}
	/**
	 * @param fechaEntrega_solicitud_requerimiento the fechaEntrega_solicitud_requerimiento to set
	 */
	public void setFechaEntrega_solicitud_requerimiento(java.sql.Date fechaEntrega_solicitud_requerimiento) {
		this.fechaEntrega_solicitud_requerimiento = fechaEntrega_solicitud_requerimiento;
	}
	/**
	 * @return the estado_solicitud_requerimiento
	 */
	public String getEstado_solicitud_requerimiento() {
		return estado_solicitud_requerimiento;
	}
	/**
	 * @param estado_solicitud_requerimiento the estado_solicitud_requerimiento to set
	 */
	public void setEstado_solicitud_requerimiento(String estado_solicitud_requerimiento) {
		this.estado_solicitud_requerimiento = estado_solicitud_requerimiento;
	}
	/**
	 * @return the fechaEvaluacion_solicitud_requerimiento
	 */
	public java.sql.Date getFechaEvaluacion_solicitud_requerimiento() {
		return fechaEvaluacion_solicitud_requerimiento;
	}
	/**
	 * @param fechaEvaluacion_solicitud_requerimiento the fechaEvaluacion_solicitud_requerimiento to set
	 */
	public void setFechaEvaluacion_solicitud_requerimiento(java.sql.Date fechaEvaluacion_solicitud_requerimiento) {
		this.fechaEvaluacion_solicitud_requerimiento = fechaEvaluacion_solicitud_requerimiento;
	}
	/**
	 * @return the comentarioevaluacion_solicitud_requerimiento
	 */
	public String getComentarioevaluacion_solicitud_requerimiento() {
		return comentarioevaluacion_solicitud_requerimiento;
	}
	/**
	 * @param comentarioevaluacion_solicitud_requerimiento the comentarioevaluacion_solicitud_requerimiento to set
	 */
	public void setComentarioevaluacion_solicitud_requerimiento(String comentarioevaluacion_solicitud_requerimiento) {
		this.comentarioevaluacion_solicitud_requerimiento = comentarioevaluacion_solicitud_requerimiento;
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

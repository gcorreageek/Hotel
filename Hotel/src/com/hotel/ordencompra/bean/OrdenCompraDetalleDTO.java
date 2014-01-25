/**
 * OrdenCompraDetalleDTO 28/07/2013
 */
package com.hotel.ordencompra.bean;

/**
 * @author Gustavo A. Correa C.
 *
 */
public class OrdenCompraDetalleDTO {

	private Integer cod_DetalleOrdenCompra;
	private Integer cod_ordenCompra;
	private Integer cod_articulo_proveedor;
	private Integer cantidad;
	
	private String desc_articulo;
	private String unidadMedida;
	private Integer stock_articulo; 
	
	
	public Integer getCod_DetalleOrdenCompra() {
		return cod_DetalleOrdenCompra;
	}
	public void setCod_DetalleOrdenCompra(Integer cod_DetalleOrdenCompra) {
		this.cod_DetalleOrdenCompra = cod_DetalleOrdenCompra;
	}
	public Integer getCod_ordenCompra() {
		return cod_ordenCompra;
	}
	public void setCod_ordenCompra(Integer cod_ordenCompra) {
		this.cod_ordenCompra = cod_ordenCompra;
	}
	public Integer getCod_articulo_proveedor() {
		return cod_articulo_proveedor;
	}
	public void setCod_articulo_proveedor(Integer cod_articulo_proveedor) {
		this.cod_articulo_proveedor = cod_articulo_proveedor;
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

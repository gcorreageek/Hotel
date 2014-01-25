<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %> 
<%@ page language="java" contentType="text/html"  import="com.hotel.util.UtilSigal"%>
<script type="text/javascript"> 
// seleccionaCotizacion(${row.cod_cotizacion},'${row.nom_trabajador}','${row.fecharegistro_cotizacion}','${row.cod_proveedor}','${row.raz_social}'
function seleccionaCotizacion(codCotizacion,nomUsuario,fechaCotizacion,codProvee,razonSocial){  
	$("#cod_cotizacion").val(codCotizacion);
	$("#inputResponsable").val(nomUsuario);
	$("#inputFecha").val(fechaCotizacion);
	$("#cod_proveedor").val(codProvee);
	$("#inputProveedor").val(razonSocial);
	 
	$.post("getDetalleCotizacion",{"objCotizacion.cod_cotizacion":codCotizacion},function(data){
 		$("#idTableDetalleCotizacion").html(data);
	});  	
} 
function seleccionaProd(codProd,descProd,uMedida){  
	$("#cod_articulo").val(codProd);
	$("#desc_articulo").val(descProd);
	$("#unidadMedida").val(uMedida); 
} 
function eliminarDetalleCotizacion(idProd){ 
	$.post("eliminarDetalleCotizacion",{"idProd":idProd},function(data){
 		$("#idTableDetalleCotizacion").html(data);
	});
}
$(document).ready(function() {  
	var mensaje="<div class='alert alert-error'><h4>Error!</h4>";
	$('#idBuscarCotizacion').click(function(){ 
		$("#txtNombreResponsable").val(""); 
		$("#txtFechaInicio").val("");
		$("#txtFechaFin").val("");
		$.post("listarCotizacionTotal",function(data){
	 		$("#divDatosCotizacionTotal").html(data);
		}); 
		$.post("listarCotizacionPagModal",{inicio:null},function(data){
	 		$("#divTablaCotizacionModal").html(data);
		}); 
	});  
	/**PRODUCTO**/
	$('#idBuscarProducto').click(function(){ 
		var idProvee = $('#cod_proveedor').val(); 
		if(idProvee==null || idProvee=='' ){ 
			$("#divMostrarMensaje").html(mensaje+" Ingrese Proveedor correcto"+ "</div>");
			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
			return;
		} 
		$('#myBuscarProducto').modal({  keyboard: false });  
		$.post("listarArticuloTotalidProve",{idProve:idProvee},function(data){
	 		$("#divDatosProdTotal").html(data);
		}); 
		$.post("listarArticuloPagModalidProve",{idProve:idProvee,inicio:null},function(data){
	 		$("#divTablaProdModal").html(data);
		}); 
	});  
	$('#idBotonBuscarProducto').click(function(){
		var txtProd=$("#txtProducto").val();
		var idProvee = $('#cod_proveedor').val();
		$.post("buscarArticuloTotalidProve",{idProve:idProvee,"objProducto.desc_articulo":txtProd},function(data){
	 		$("#divDatosProdTotal").html(data);
		}); 
		$.post("buscarArticulosXDescProdPagModalidProve",{inicio:null,idProve:idProvee,"objProducto.desc_articulo":txtProd},function(data){
	 		$("#divTablaProdModal").html(data);
		}); 
	});
	/**COTIZACION**/
	$('#idBotonBuscarCotizacion').click(function(){ 
		var txtNombreRespo=$("#txtNombreResponsable").val(); 
		var txtFechaInicio=$("#txtFechaInicio").val();
		var txtFechaFin=$("#txtFechaFin").val(); 
		$.post("buscarCotizacionTotal",{
			"objCotizacion.nom_trabajador":txtNombreRespo, 
			"fechaComienzaInicio":txtFechaInicio,
			"fechaTerminaFin":txtFechaFin
			},function(data){
	 		$("#divDatosCotizacionTotal").html(data);
		}); 
		$.post("buscarCotizacionPagModal",{
			"objCotizacion.nom_trabajador":txtNombreRespo, 
			"fechaComienzaInicio":txtFechaInicio,
			"fechaTerminaFin":txtFechaFin
			},function(data){
	 		$("#divTablaCotizacionModal").html(data);
		}); 
	});  
	$('#btnAgregarDetalleOC').click(function(){
		var idProd= $("#cod_articulo").val();
		var cantidad= $("#inputCantidad").val();
		var idProvee = $('#cod_proveedor').val();
		if(idProvee==null || idProvee=='' ){ 
			$("#divMostrarMensaje").html(mensaje+" Ingrese Proveedor correcto"+ "</div>");
			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
			return;
		}
		if(idProd==null || idProd=='' ){ 
			$("#divMostrarMensaje").html(mensaje+" Ingrese Producto correcto"+ "</div>");
			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
			return;
		}
		if(!/^([0-9])*$/.test(cantidad) || cantidad=='' || cantidad==0){  
			$("#divMostrarMensaje").html(mensaje+" Ingrese Cantidad Valida"+ "</div>");
			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
			return;
		} 
		
		$.post("agregarDetalleCotizacion",{"objCotizacion.cod_proveedor":idProvee,"idProd":idProd,cantidad:cantidad},function(data){
	 		$("#idTableDetalleCotizacion").html(data);
		}); 
	});

	$('#btnGuardar').click(function(){ 
		var codProve = $('#cod_proveedor').val(); 
		var codcotizacion = $('#cod_cotizacion').val();
		var radioSelecionado=$('input:radio[name=optionsRadios]:checked').val();
		var fechaEntrega = $('#idFechaEntrega').val();
		var fechaDevolucion = $('#idFechaDevolucion').val();
		
		if(codcotizacion==null || codcotizacion=='' ){ 
			$("#divMostrarMensaje").html(mensaje+" Ingrese Cotizacion correcto"+ "</div>");
			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
			return;
		} 
		if(radioSelecionado=='Prestamo'){
			if(fechaEntrega=='' || fechaEntrega==null){
				$("#divMostrarMensaje").html(mensaje+" Ingrese una Fecha de Entrega valida"+ "</div>");
	 			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
	 			return;
			}
			if(fechaDevolucion=='' || fechaDevolucion==null){
				$("#divMostrarMensaje").html(mensaje+" Ingrese una Fecha de Devolucion valida"+ "</div>");
	 			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
	 			return;
			}
		}
		
		$.post("guardarOrdenCompra",{ 
		"objOrdenCompra.cod_cotizacion":codcotizacion ,
		"objOrdenCompra.cod_proveedor":codProve,
		"tipoPedido":radioSelecionado,
		"fechaEntrega":fechaEntrega,
		"fechaDevolucion":fechaDevolucion
		},
		function(data){
			var bien = data.indexOf("Error");  
			if(bien<0){
				$("#divMostrarMensajeInterno").html(data);
		 		$('#myOrdenCompra').modal({  keyboard: false });  
		 		setTimeout(function(){ $(location).attr('href','mainOrdenCompra'); }, 4000);
			}else{
				$("#divMostrarMensajeInterno").html(data);
		 		$('#myOrdenCompra').modal({  keyboard: false });  
			}   
		});
	});
	
	$('#divFechaDevolucion').hide();
	$('#optionPrestamo').click(function(){
		$('#divFechaDevolucion').show('slow');
	});
	$('#optionAbastecimiento').click(function(){
		$('#divFechaDevolucion').hide('slow');
	}); 
	$('#idFechaEntrega').datepicker('setStartDate', $('#inputFecha1').val()).on('changeDate', function(ev){  
		$('#idFechaDevolucion').datepicker('setStartDate',$('#idFechaEntrega').val() );
	});

// 	 setTimeout(function(){ $('.alert').hide(1000); }, 3000); 
}); 
</script> 
<div id="divMostrarMensaje"> 
</div>


 

<hr class='hr-normal'>

<div class='row'>
    <div class='col-sm-12'>
      <div class='box'>
        <div class='box-header blue-background'>
          <div class='title'>
            <div class='icon-edit'></div>
            Registrar Orden Compra
          </div>
          <div class='actions'>
            <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
            </a>
            
            <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
            </a>
          </div>
        </div>
        <div class='box-content'>
          <form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="#" accept-charset="UTF-8">
          <s:hidden  name="objCotizacion.cod_cotizacion"  id="cod_cotizacion"    />  
          <input style="display:none"  class='form-control' id="inputFecha1" value="<%=UtilSigal.fechaActual() %>" disabled type='text'>
          
          <div class="form-group">
            <label class="col-md-2 control-label">Tipo Cotizacion</label>
             <div class="col-md-10">
               <label class="radio radio-inline">
                 <input name="optionsRadios" id="optionAbastecimiento" value="Abastecimiento" checked="checked" type="radio">
                 Abastecimiento
               </label>
               <label class="radio radio-inline">
                 <input name="optionsRadios" id="optionPrestamo" value="Prestamo" type="radio">
                 Prestamo
               </label> 
             </div> 
          </div>
          
          <div   id="divFechaDevolucion" >
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputFechaEntrega">Fecha Entrega</label>
            <div class='col-md-5'> 
              <input class='form-control datepicker' type="text" id="idFechaEntrega"  readonly> 
            </div>
          </div>
          
          
          <div class='form-group'   >
            <label class="col-md-2 control-label" for="idFechaDevolucion">Fecha Devoluci&oacute;n</label>
            <div class='col-md-5'> 
              <input class="form-control datepicker" id="idFechaDevolucion"  type="text" readonly> 
            </div>
          </div>
          </div>
          
          <div class='form-group'>
          
          
          
          
          
          
            <label class='col-md-2 control-label' for='inputResponsable'>Responsable</label>
            <div class='col-md-5'>
              <input type="text" id="inputResponsable" class="form-control" placeholder="Responsable" disabled>
            </div>
            <div class='col-md-1'>
              <a class="btn btn-primary"  id="idBuscarCotizacion" href="#myBuscarCotizacion" data-toggle="modal">Buscar Cotizacion</a>
            </div>
          </div>
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputFecha'>Fecha</label>
            <div class='col-md-2'>
              <input type="text" id="inputFecha" class="form-control" placeholder="Fecha" disabled> 
            </div> 
          </div> 
          
		  <s:hidden  name="objCotizacion.cod_proveedor"  id="cod_proveedor"    /> 
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputProveedor'>Proveedor</label>
            <div class='col-md-5'> 
              <input type="text" id="inputProveedor" class="form-control" placeholder="Proveedor" disabled>
            </div> 
          </div> 
          <hr class='hr-normal'>
          <s:hidden  name="objCotizacion.cod_articulo"  id="cod_articulo"    /> 

		  <div class='form-group'>
            <label class='col-md-2 control-label' for='desc_articulo'>Articulo</label>
            <div class='col-md-5'> 
              <input type="text" id="desc_articulo" class="form-control" placeholder="Producto" disabled>
            </div> 
            <div class='col-md-2'> 
              <a class="btn btn-primary" id="idBuscarProducto" data-toggle="modal" >Buscar Articulo</a>
            </div>
          </div> 
          <div class='form-group'>
            <label class='col-md-2 control-label' for='unidadMedida'>U.Medida</label>
            <div class='col-md-3'> 
              <input type="text" id="unidadMedida" class="form-control" placeholder="U.Medida" disabled>
            </div>  
          </div> 
          <hr class='hr-normal'> 
           
			
		  <div class='form-group'>
            <label class='col-md-2 control-label' for='inputCantidad'>Cantidad</label>
            <div class='col-md-3'> 
              <input type="text" id="inputCantidad" class="form-control" placeholder="Cantidad" >
            </div> 
            <div class='col-md-2'> 
            <a class="btn btn-primary"  id="btnAgregarDetalleOC"   >Agregar</a>
            </div> 
          </div> 
          
		<div id="idTableDetalleCotizacion">
		    <table class="table table-striped table-bordered table-hover">
		      <thead>
		        <tr>  
		          <th>Articulo</th> 
		          <th>U.Medida</th>
		          <th>Cantidad</th> 
		          <th>Eliminar</th>
		        </tr>
		      </thead>
		      <tbody> 
		      </tbody>
		    </table>
		</div>            
          
          
<div class="control-group"> 
<div class="controls"  align="center">
<a class="btn  btn-primary" id="btnGuardar">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Guardar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;
<a class="btn  btn-primary"  href="mainOrdenCompra">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cancelar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
</div>
</div> 

          </form>
        </div>
        
       </div>
     </div>
</div>


 


<div class='modal fade' id="myBuscarCotizacion"  tabindex='-1'>
                      <div class='modal-dialog'>
                        <div class='modal-content'> 
                        
                        <div class='row'>
		                <div class='col-sm-12'>
		                  	<div class='box bordered-box blue-border' style='margin-bottom:0;'> 
		                    <div class='box-header blue-background'>
		                      <div class='title'>Buscar Cotizacion</div> 
		                    </div>
		                    <div class='box-content box-no-padding'>
		
								<div class='box-content'>
									<form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="#" accept-charset="UTF-8"> 
										<div class='form-group'> 
											<div class="col-md-5">
												<input type="text" id="txtFechaInicio" class="form-control  datepicker" placeholder="Fecha Inicio" >
											</div>
											<div class="col-md-5">
											    <input type="text" id="txtFechaFin" class="form-control  datepicker" placeholder="Fecha Fin" >
											</div>
										</div> 
										<div class='form-group'> 
											<div class="col-md-10">
												<input type="text" id="txtNombreResponsable" name="objOrdenCompra.nom_trabajador" class="form-control text-inline" placeholder="Nombre de Responsable" >
											</div>
											<div class="col-md-1">
											 <button type="submit" class="btn" id="idBotonBuscarCotizacion" >Buscar</button>
											</div>
										</div>
									</form>
								</div>  

			                   </div>
			                  </div>
			              </div>
			              </div>


                          
                          <div id="divTablaCotizacionModal">
					      </div>
					      <div id="divDatosCotizacionTotal"></div> 
                          
    </div>
  </div>
</div>


















<!-- Modal --> 
 <div class='modal fade' id="myBuscarProducto"  tabindex='-1'>
                      <div class='modal-dialog'>
                        <div class='modal-content'> 
                        
                        <div class='row'>
		                <div class='col-sm-12'>
		                  	<div class='box bordered-box blue-border' style='margin-bottom:0;'> 
		                    <div class='box-header blue-background'>
		                      <div class='title'>Articulo</div> 
		                    </div>
		                    <div class='box-content box-no-padding'>
		
								<div class='box-content'>
									<form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="#" accept-charset="UTF-8"> 
										<div class='form-group'>
											<label class='col-md-2 control-label' for='inputText1'>Articulo</label>
											<div class="col-md-5">
												<input class='form-control text-inline' id="txtProducto" name="objProducto.desc_articulo"  placeholder='Articulo' type='text'>
											</div>
											<div class="col-md-1">
											<button class='btn btn-primary radio-inline' type='button' id="idBotonBuscarProducto" >Buscar</button>
											</div>
										</div> 
									</form>
								</div> 

			                   </div>
			                  </div>
			              </div>
			              </div>


                          
                          <div id="divTablaProdModal">
					      </div>
					      <div id="divDatosProdTotal"></div> 
                          
    </div>
  </div>
</div>


 


<div id="myOrdenCompra" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalCotizacion" aria-hidden="true">
<div class="modal-dialog">
<div class='modal-content'>
      <div id="divMostrarMensajeInterno"></div> 
</div> 
</div>
</div>

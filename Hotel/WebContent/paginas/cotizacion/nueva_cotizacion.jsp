<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %> 
<%@ page language="java" contentType="text/html"  import="com.hotel.util.UtilSigal"%>

<script type="text/javascript">
// cod_articulo desc_articulo unidadMedida cod_proveedor  raz_social
function seleccionaProd(codProd,descProd,uMedida){  
	$("#cod_articulo").val(codProd);
	$("#desc_articulo").val(descProd);
	$("#unidadMedida").val(uMedida); 
}
function seleccionaProvee(codProvee,descProvee){  
	$("#cod_proveedor").val(codProvee);
	$("#raz_social").val(descProvee); 
}
function eliminarDetalleCotizacion(idProd){ 
	$.post("eliminarDetalleCotizacion",{"idProd":idProd},function(data){
 		$("#divDetallePedido").html(data);
	});
}
$(document).ready(function() { 
	var mensaje="<div class='alert alert-error'><h4>Error!</h4>";
	/**PRODCUTO**/
	$('#idBuscarProducto').click(function(){
		$("#txtProducto").val("");
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
	/**PROVEEDOR**/
	$('#idBuscarProveedor').click(function(){
		$("#txtProveedor").val("");
		$.post("listarProveedorTotal",function(data){
	 		$("#divDatosProveTotal").html(data);
		}); 
		$.post("listarProveedorPagModal",{inicio:null},function(data){
	 		$("#divTablaProveModal").html(data);
		}); 
	});  
	$('#idBotonBuscarProveedor').click(function(){
		var txtProve=$("#txtProveedor").val();
		$.post("buscarProveedorTotal",{"objProveedor.raz_social":txtProve},function(data){
	 		$("#divDatosProveTotal").html(data);
		}); 
		$.post("buscarProveedorXRazonSocialPagModal",{inicio:null,"objProveedor.raz_social":txtProve},function(data){
	 		$("#divTablaProveModal").html(data);
		}); 
	}); 
	$('#btnAgregarDetallePedido').click(function(){
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
	 		$("#divDetallePedido").html(data);
		}); 
	});

	$('#btnGuardar').click(function(){ 
		var codprove = $('#cod_proveedor').val(); 
		var radioSelecionado=$('input:radio[name=optionsRadios]:checked').val();
		var fechaEntrega = $('#idFechaEntrega').val();
		var fechaDevolucion = $('#idFechaDevolucion').val();
		
		if(codprove==null || codprove=='' ){ 
			$("#divMostrarMensaje").html(mensaje+" Ingrese Proveedor correcto"+ "</div>");
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
		
		
		$.post("guardarCotizacion",
			{ 
			"tipoPedido":radioSelecionado,
			"fechaEntrega":fechaEntrega,
			"fechaDevolucion":fechaDevolucion,
		"objCotizacion.cod_proveedor":codprove 
			}
		,
		function(data){
			var bien = data.indexOf("Error");  
			if(bien<0){
				$("#divMostrarMensajeInterno").html(data);
		 		$('#myCotizacion').modal({  keyboard: false });  
		 		setTimeout(function(){ $(location).attr('href','mainCotizacion'); }, 4000);
			}else{
				$("#divMostrarMensajeInterno").html(data);
		 		$('#myCotizacion').modal({  keyboard: false });  
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
	$('#idFechaEntrega').datepicker('setStartDate', $('#inputFecha').val()).on('changeDate', function(ev){  
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
            Registrar Cotizacion
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
          <s:hidden  name="objCotizacion.cod_proveedor" id="cod_proveedor"     />
          <input style="display:none"  class='form-control' id="inputFecha" value="<%=UtilSigal.fechaActual() %>" disabled type='text'>
          
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
            <label class='col-md-2 control-label' for='inputFecha'>Proveedor</label>
            <div class='col-md-4'> 
				<input type="text" id="raz_social" class="input-xxlarge form-control" name="raz_social" placeholder="Proveedor" disabled> 
            </div>
            <div class='col-md-1'>
            	<a class="btn btn-primary" id="idBuscarProveedor" href="#myBuscarProveedor" data-toggle="modal">Buscar Proveedor</a>
            </div>
          </div>
          <hr class='hr-normal'> 
          
          <s:hidden  name="cod_articulo"  id="cod_articulo"    />
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputFecha'>Producto</label>
            <div class='col-md-4'>  
				<input type="text" class="input-xxlarge form-control" id="desc_articulo" placeholder="Producto" disabled>
            </div>
            <div class='col-md-1'>
            	<a class="btn btn-primary"  id="idBuscarProducto"   data-toggle="modal" >Buscar Producto</a> 
            </div>
          </div>
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputFecha'>U.Medida</label>
            <div class='col-md-3'>  
				<input type="text" class="input-large form-control" id="unidadMedida"   placeholder="U.Medida" disabled>
            </div> 
          </div>
            <hr class='hr-normal'> 
          
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputResponsable'>Cantidad</label>
            <div class='col-md-3'> 
              <input type="text" class="form-control" id="inputCantidad" placeholder="Cantidad"> 
            </div> 
            <div class='col-md-1'> 
              <a class="btn btn-primary"   id="btnAgregarDetallePedido"  >Agregar</a>  
            </div>
          </div>
          <hr class='hr-normal'> 
          <div id="divDetallePedido">
			    <table class="table table-striped table-bordered table-hover">
			      <thead>
			        <tr> 
			          <th>Producto</th> 
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
<a class="btn  btn-primary"  href="mainCotizacion">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cancelar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
</div>
</div>         

          </form>
        </div>
        
       </div>
     </div>
</div>





 
<!-- Modal --> 
 <div class='modal fade' id="myBuscarProveedor"  tabindex='-1'>
                      <div class='modal-dialog'>
                        <div class='modal-content'> 
                        
                        <div class='row'>
		                <div class='col-sm-12'>
		                  	<div class='box bordered-box blue-border' style='margin-bottom:0;'> 
		                    <div class='box-header blue-background'>
		                      <div class='title'>Buscar Proveedor</div> 
		                    </div>
		                    <div class='box-content box-no-padding'>
		
								<div class='box-content'>
									<form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="#" accept-charset="UTF-8"> 
										<div class='form-group'>
											<label class='col-md-2 control-label' for='inputText1'>Proveedor</label>
											<div class="col-md-5"> 
												<input type="text" id="txtProveedor" name="objProveedor.raz_social" class="form-control text-inline" placeholder="Proveedor" >
											</div>
											<div class="col-md-1"> 
											<button type="submit" class="btn" id="idBotonBuscarProveedor" >Buscar</button>
											</div>
										</div> 
									</form>
								</div> 

			                   </div>
			                  </div>
			              </div>
			              </div> 

                          
                          <div id="divTablaProveModal">
					      </div>
					      <div id="divDatosProveTotal"></div> 
                          
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

<div id="myCotizacion" class="modal fade"  >
<div class="modal-dialog">
<div class='modal-content'>
<div id="divMostrarMensajeInterno"></div> 
</div> 
</div>
</div>



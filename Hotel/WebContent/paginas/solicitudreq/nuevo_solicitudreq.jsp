<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ page language="java" contentType="text/html"  import="com.hotel.util.UtilSigal"%>
<%@ page language="java" contentType="text/html"  import="com.hotel.seguridad.bean.TrabajadorDTO"%>
<%@ page language="java" contentType="text/html"  import="com.hotel.seguridad.bean.CargoDTO"%>
<%@ page language="java" contentType="text/html"  import="com.hotel.seguridad.bean.AreaDTO"%>
<%!TrabajadorDTO  detalleUsuario=null; 
	CargoDTO  cargoUsuario=null;
	AreaDTO  areaUsuario=null;%> 
<%
	detalleUsuario = (TrabajadorDTO)session.getAttribute("objUsuario"); 
	cargoUsuario = (CargoDTO)session.getAttribute("objCargo"); 
	areaUsuario = (AreaDTO)session.getAttribute("objArea");
%>
<script type="text/javascript"  >
function seleccionaProd(codProd,descProd,uMedida){  
	$("#cod_articulo").val(codProd);
	$("#desc_articulo").val(descProd);
	$("#unidadMedida").val(uMedida); 
}
function eliminarDetalleSolicitudReq(idProd){ 
	$.post("eliminarDetalleSolicitudReq",{"idProd":idProd},function(data){
 		$("#divDetallePedido").html(data);
	});
} 
$(document).ready(function() {
	var mensaje="<div class='alert alert-error'><h4>Error!</h4>";
// 	$('.alert').hide(); 
	$('#idBuscarProducto').click(function(){
		$("#txtProducto").val("");
// 		listarArticuloPagModalHabilitados
		$.post("listarArticuloTotalHabilitados",function(data){
	 		$("#divDatosProdTotal").html(data);
		}); 
		$.post("listarArticuloPagModalHabilitados",{inicio:null},function(data){
	 		$("#divTablaProdModal").html(data);
		}); 
	});  
	$('#idBotonBuscarProducto').click(function(){
		var txtProd=$("#txtProducto").val();
		$.post("buscarArticuloTotalHabilitados",{"objProducto.desc_articulo":txtProd},function(data){
	 		$("#divDatosProdTotal").html(data);
		}); 
		$.post("buscarArticulosXDescProdPagModalHabilitados",{inicio:null,"objProducto.desc_articulo":txtProd},function(data){
	 		$("#divTablaProdModal").html(data);
		}); 
	}); 
	$('#divFechaDevolucion').hide();
	$('#optionPrestamo').click(function(){
		$('#divFechaDevolucion').show('slow');
	});
	$('#optionAbastecimiento').click(function(){
		$('#divFechaDevolucion').hide(1000);
	});
	$('#checkNroPedido').click(function(){
		if($("#checkNroPedido").is(':checked')){
			$("#inputNroPedido").prop('disabled', true);
		}else{
			$("#inputNroPedido").prop('disabled', false);
		} 
	}); 
	$('#btnAgregarDetallePedido').click(function(){ 
		var idProd= $("#cod_articulo").val();
		var cantidad= $("#inputCantidad").val();  
		var str = '<h4>Error!</h4>';
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
		$.post("agregarDetalleSolicitudReq",{"idProd":idProd,cantidad:cantidad},function(data){
	 		$("#divDetallePedido").html(data);
		}); 
	});
	$('#btnGuardar').click(function(){
		var radioSelecionado=$('input:radio[name=optionsRadios]:checked').val();
		var fechaEntrega = $('#idFechaEntrega').val();
		var fechaDevolucion = $('#idFechaDevolucion').val();
		var observacion = $('#inputObservacion').val();  
		var iChars = "#$%^&*()+=-[]\\'/{}|\"<>"; 
		for (var i = 0; i < observacion.length; i++) {
		    if (iChars.indexOf(observacion.charAt(i)) != -1) {
		    	$("#divMostrarMensaje").html(mensaje+" Ingrese una Observacion valida"+ "</div>");
	 			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
	 			return;
		    }
		} 
		//validar fechas 
		if(fechaEntrega=='' || fechaEntrega==null){
			$("#divMostrarMensaje").html(mensaje+" Ingrese una Fecha de Entrega valida"+ "</div>");
 			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
 			return;
		}
		if(radioSelecionado=='Prestamo'){
			if(fechaDevolucion=='' || fechaDevolucion==null){
				$("#divMostrarMensaje").html(mensaje+" Ingrese una Fecha de Devolucion valida"+ "</div>");
	 			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
	 			return;
			}
		}
		 
		
		$.post("guardarSolicitudReq",
			{
			"tipoPedido":radioSelecionado,
			"fechaEntrega":fechaEntrega,
			"fechaDevolucion":fechaDevolucion,
			"obsDevolucion":observacion
			}
		,
		function(data){ 
			var bien = data.indexOf("Error");  
			if(bien<0){
				$("#idMensajeInterno").html(data);
		 		$('#myMensaje').modal({  keyboard: false });  
		 		setTimeout(function(){ $(location).attr('href','nuevoSolicitudReq'); }, 4000);
			}else{
				$("#idMensajeInterno").html(data);
		 		$('#myMensaje').modal({  keyboard: false });  
			} 
		});  
	});
	
	$('#idFechaEntrega').datepicker('setStartDate', $('#inputFecha').val()).on('changeDate', function(ev){  
		$('#idFechaDevolucion').datepicker('setStartDate',$('#idFechaEntrega').val() );
	});  
	
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
            Registrar Solicitud Requerimiento
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
          
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputFecha'>Fecha</label>
            <div class='col-md-5'>
              <input class='form-control' id="inputFecha" value="<%=UtilSigal.fechaActual() %>" disabled type='text'>
            </div>
          </div>
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputResponsable'>Responsable</label>
            <div class='col-md-5'>
              <input class='form-control' id="inputResponsable" value="<%=detalleUsuario.getNom_trabajador()%>"  disabled type='text'>
            </div> 
          </div>
          
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputArea">Area</label>
            <div class='col-md-5'>
              <input class='form-control' type="text" id="inputArea" value="<%=areaUsuario.getDesc_area() %>" disabled>
            </div>
          </div>
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputCargo">Cargo</label>
            <div class='col-md-5'>
              <input class='form-control' type="text" id="inputCargo" value="<%=cargoUsuario.getDesc_cargo() %>" disabled> 
            </div>
          </div>
          
          <div class='form-group'>
            <label class="col-md-2 control-label">Tipo Pedido</label>
             <div class="col-md-10">
               <label class="radio radio-inline">
                 <input  type="radio" name="optionsRadios" id="optionAbastecimiento" value="Abastecimiento" checked="checked"  >
                 Abastecimiento
               </label>
               <label class="radio radio-inline">
                 <input type="radio" name="optionsRadios" id="optionPrestamo" value="Prestamo"  >
                 Prestamo
               </label> 
             </div> 
          </div>
          
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputFechaEntrega">Fecha Entrega</label>
            <div class='col-md-5'> 
              <input class='form-control datepicker' type="text" id="idFechaEntrega"  readonly> 
            </div>
          </div>
          
          <div   id="divFechaDevolucion" >
          <div class='form-group'   >
            <label class="col-md-2 control-label" for="idFechaDevolucion">Fecha Devoluci&oacute;n</label>
            <div class='col-md-5'> 
              <input class="form-control datepicker" id="idFechaDevolucion"  type="text" readonly> 
            </div>
          </div>
          </div>
          <hr class='hr-normal'>
          <div class='form-group'   >
          	<s:hidden  name="objProductoProveedor.cod_articulo"  id="cod_articulo"    />
            <label class="col-md-2 control-label" for="desc_articulo">Articulo</label>
            <div class='col-md-4'> 
              <input class="form-control" id="desc_articulo" name="objProductoProveedor.desc_articulo" value="${objProductoProveedor.desc_articulo}" placeholder="Articulo"  type="text" disabled> 
            </div>
            <label class="col-md-1 control-label" for="unidadMedida">U.Medida</label>
            <div class='col-md-2'> 
              <input class="form-control" id="unidadMedida" name="objProductoProveedor.unidadMedida" value="${objProductoProveedor.unidadMedida}" placeholder="U.Medida"  type="text" disabled> 
            </div>
            <div class='col-md-1'>
              <a class="btn btn-primary" id="idBuscarProducto" href="#myBuscarProducto" data-toggle="modal" >Buscar Articulo</a>
            </div>
          </div>
          
          <div class='form-group'   > 
            <label class="col-md-2 control-label" for="inputCantidad">Cantidad</label>
            <div class='col-md-3'> 
              <input class="form-control" id="inputCantidad"  placeholder="Cantidad"  type="text" > 
            </div>
            <div class='col-md-1'> 
              <a class="btn btn-primary" id="btnAgregarDetallePedido"  >Agregar</a>
            </div>
          </div>  
  
		<div id="divDetallePedido"> 
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
		<div class="form-group">
		  <label class="col-md-2 control-label" for="inputObservacion">Observaci&oacute;n</label>
		  <div class="col-md-5">
		    <textarea class="form-control" id="inputObservacion" placeholder="Observaci&oacute;n" rows="3"></textarea>
		  </div>
		</div> 
		<div class="control-group"> 
			<div class="controls"  align="center">
			<a class="btn  btn-primary" id="btnGuardar" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Guardar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;
			<a class="btn  btn-primary"  onclick="javascript:location='nuevoSolicitudReq'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cancelar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</div>
		</div> 

          </form>
        </div>
        
       </div>
     </div>
</div>
                 


  


 



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





























               


 
  
  
<div id="myMensaje" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalBuscarProducto" aria-hidden="true">
<div class="modal-dialog">
<div class='modal-content'>
<div id="idMensajeInterno"></div> 
</div> 
</div>
</div>

 
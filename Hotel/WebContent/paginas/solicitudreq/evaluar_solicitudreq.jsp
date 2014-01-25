<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %> 
<script type="text/javascript"> 
// seleccionaPedido(${row.cod_solicitudRequerimiento},'${row.nom_trabajador}','${row.desc_area}','${row.desc_cargo}','${row.fechaRegistro_solicitud_requerimiento}','${row.fechaDevolucion_solicitud_requerimiento}','${row.fechaEntrega_solicitud_requerimiento}','${row.tipo_solicitud_requerimiento}','${row.comentario_solicitud_requerimiento}')
function seleccionaPedido(codPedido,nomUsuario,area,cargo,fechaRegistro,fechaDevolucion,fechaEntrega,tipo,obs){  
// 	inputFecha  inputResponsable inputArea inputCargo optionsRadios(optionAbastecimiento,optionPrestamo) inputFechaEntrega inputFechaDevolucion inputObservacionPedido
	$("#cod_pedido").val(codPedido);
	$("#inputFecha").val(fechaRegistro);
	$("#inputResponsable").val(nomUsuario);
	$("#inputArea").val(area);
	$("#inputCargo").val(cargo);
	$("#inputFechaEntrega").val(fechaEntrega);
	$("#inputFechaDevolucion").val(fechaDevolucion);
	$("#inputObservacionPedido").val(obs);  
	if('Abastecimiento'==tipo){
		var $radios = $('input:radio[name=optionsRadios]');
		$radios.filter('[value=Abastecimiento]').prop('checked', true);	
		$('#divFechaDevolucion').hide('slow');
	}else if('Prestamo'==tipo){
		var $radios = $('input:radio[name=optionsRadios]');
		$radios.filter('[value=Prestamo]').prop('checked', true); 
		$('#divFechaDevolucion').show('slow');
	} 
	$.post("getDetalleSolicitudReq",{"objPedido.cod_solicitudRequerimiento":codPedido},function(data){
 		$("#idTableDetallePedido").html(data);
	});  	
} 
function guardarEvaluacion(evaluacion){
	var mensaje="<div class='alert alert-error'><h4>Error!</h4>";
	var codPedido=$("#cod_pedido").val();
	var observacion=$("#inputObservacion").val();
	
	var r='';
	if(evaluacion==1){
		 r='Aprobado';
	}else if(evaluacion==2){
		 r='Desaprobado';
	}
	if(codPedido==null || codPedido==''){
		$("#divMostrarMensaje").html(mensaje+" Ingrese un Pedido "+ "</div>");
		setTimeout(function(){ $('.alert').hide(1000); }, 2000); 	
		return;
	}
	var iChars = "#$%^&*()+=-[]\\'/{}|\"<>"; 
	for (var i = 0; i < observacion.length; i++) {
	    if (iChars.indexOf(observacion.charAt(i)) != -1) {
	    	$("#divMostrarMensaje").html(mensaje+" Ingrese una Observacion valida"+ "</div>");
 			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
 			return;
	    }
	}  
	
	$.post("guardarEvaluacionSolicitudReq",{"objPedido.cod_solicitudRequerimiento":codPedido,"objPedido.comentarioevaluacion_solicitud_requerimiento":observacion,"objPedido.estado_solicitud_requerimiento":r},function(data){
 		var bien = data.indexOf("Error");  
		if(bien<0){
			$("#divMensajeEvaluacion").html(data);
	 		$('#myEvaluacionPedido').modal({  keyboard: false });  
	 		setTimeout(function(){ $(location).attr('href','evaluarSolicitudReq'); }, 4000);
		}else{
			$("#divMensajeEvaluacion").html(data);
	 		$('#myEvaluacionPedido').modal({  keyboard: false });  
		}  
	}); 
}

$(document).ready(function() {  
	
	$('#idBuscarPedido').click(function(){
		$("#txtNombreResponsable").val("");
		$("#txtFechaInicio").val("");
		$("#txtFechaFin").val(""); 
		$.post("listarSolicitudReqTotal",function(data){
	 		$("#divDatosPedidoTotal").html(data);
		}); 
		$.post("listarSolicitudReqPagModal",{inicio:null},function(data){
	 		$("#divTablaPedidoModal").html(data);
		}); 
	});  
	$('#idBotonBuscarPedido').click(function(){
// 		txtNombreResponsable cboArea txtFechaInicio txtFechaFin cboTipo 
		var txtNombreRespo=$("#txtNombreResponsable").val();
		var cboArea=$("#cboArea").val();
		var txtFechaInicio=$("#txtFechaInicio").val();
		var txtFechaFin=$("#txtFechaFin").val();
		var cboTipo=$("#cboTipo").val();
// 		objPedido.fechaRegistro_solicitud_requerimiento objPedido.cod_area objPedido.nom_trabajador objPedido.tipo_solicitud_requerimiento
		$.post("buscarSolicitudReqTotal",{
			"objPedido.nom_trabajador":txtNombreRespo,
			"objPedido.cod_area":cboArea,
			"objPedido.fechaInicio":txtFechaInicio,
			"objPedido.fechaFin":txtFechaFin,
			"objPedido.tipo_solicitud_requerimiento":cboTipo
			},function(data){
	 		$("#divDatosPedidoTotal").html(data);
		}); 
		$.post("buscarSolicitudReqPagModal",{
			"objPedido.nom_trabajador":txtNombreRespo,
			"objPedido.cod_area":cboArea,
			"objPedido.fechaInicio":txtFechaInicio,
			"objPedido.fechaFin":txtFechaFin,
			"objPedido.tipo_solicitud_requerimiento":cboTipo
			},function(data){
	 		$("#divTablaPedidoModal").html(data);
		}); 
	});  


// 	 setTimeout(function(){ $('.alert').hide(1000); }, 3000); 
}); 
</script>
 
<div id="divMostrarMensaje"> 
</div>
<!-- <form> -->
<%-- <s:hidden  name="objPedido.cod_solicitudRequerimiento"  id="cod_pedido"    /> --%>
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline">  -->
<!-- 		<label class="control-label" for="inputFecha">Fecha</label> -->
<!-- 		<input type="text" id="inputFecha" disabled> -->
<!-- 		<a class="btn btn-primary" href="#myBuscarPedido" id="idBuscarPedido" data-toggle="modal" >Buscar Pedido</a>  -->
<!-- 	</div> -->
<!-- </div> -->

<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputResponsable">Responsable</label>	 -->
<!-- 		<input type="text" class="span4" id="inputResponsable"    disabled> -->
<!-- 		<label class="control-label" for="inputArea">Area</label> -->
<!-- 		<input type="text" id="inputArea"  disabled>  -->
<!-- 		<label class="control-label" for="inputCargo">Cargo</label> -->
<!-- 		<input type="text" id="inputCargo"   disabled>  -->
<!-- 	</div> -->
<!-- </div> -->

<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="input-small">Tipo Pedido</label> -->
<!-- 		<label class="radio"> -->
<!-- 		<input type="radio" name="optionsRadios" id="optionAbastecimiento" value="Abastecimiento" disabled  >Abastecimiento -->
<!-- 		</label>	  -->
<!-- 		<label class="radio"> -->
<!-- 		<input type="radio" name="optionsRadios" id="optionPrestamo" value="Prestamo" disabled  >Prestamo -->
<!-- 		</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 		<label class="input-medium">Fecha Entrega</label> -->
<!-- 		<div class="input-prepend"> -->
<%-- 		<span class="add-on"><i class="icon-calendar"></i></span> --%>
<!-- 		<input class="span2 datepicker" id="inputFechaEntrega"  type="text" disabled> -->
<!-- 		</div>  -->
		
<!-- 	</div> -->
<!-- </div> -->

<!-- <div class="control-group"  id="divFechaDevolucion" > -->
<!-- 	<div  class="form-inline ">  -->
  
<!-- 		<label class="control-label" for="inputIcon">Fecha Devolucion</label> -->
<!-- 		<div class="input-prepend"> -->
<%-- 		<span class="add-on"><i class="icon-calendar"></i></span> --%>
<!-- 		<input class="span2 datepicker" id="inputFechaDevolucion" type="text" disabled> -->
<!-- 		</div>  -->
		
<!-- 	</div> -->
<!-- </div> -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputObservacionPedido">Observacion Pedido</label> -->
<!-- 		<textarea rows="1" class="span9"  id="inputObservacionPedido" placeholder="Observacion Pedido"   disabled></textarea>   -->
<!-- 	</div> -->
<!-- </div> -->
<!-- <div id="idTableDetallePedido" >  -->
<!-- </div>           -->
             
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputObservacion">Observacion</label> -->
<!-- 		<textarea rows="1" class="span11"  id="inputObservacion" placeholder="Observacion"   ></textarea>   -->
<!-- 	</div> -->
<!-- </div> -->


<!-- <div class="control-group">  -->
<!-- <div class="controls"  align="center"> -->
<!-- <a class="btn  btn-primary" onclick="guardarEvaluacion(1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Aprobar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp; -->
<!-- <a class="btn  btn-primary" onclick="guardarEvaluacion(2)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Desaprobar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
<!-- </div> -->
<!-- </div> -->
<!-- </form> -->
<hr class='hr-normal'>

<div class='row'>
    <div class='col-sm-12'>
      <div class='box'>
        <div class='box-header blue-background'>
          <div class='title'>
            <div class='icon-edit'></div>
            Evaluar Solicitud de Requerimiento
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
          <s:hidden  name="objPedido.cod_solicitudRequerimiento"  id="cod_pedido"    /> 
          
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputFecha'>Fecha</label>
            <div class='col-md-3'>
              <input class='form-control' id="inputFecha"  disabled type='text'>
            </div>
            <div class='col-md-1'>
            	<a class="btn btn-primary" href="#myBuscarPedido" id="idBuscarPedido" data-toggle="modal" >Buscar Pedido</a>
            </div>
            
          </div>
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputResponsable'>Responsable</label>
            <div class='col-md-5'>
              <input class='form-control' id="inputResponsable"    disabled type='text'>
            </div> 
          </div>
          
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputArea">Area</label>
            <div class='col-md-5'>
              <input class='form-control' type="text" id="inputArea"   disabled>
            </div>
          </div>
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputCargo">Cargo</label>
            <div class='col-md-5'>
              <input class='form-control' type="text" id="inputCargo"   disabled> 
            </div>
          </div>
          
          <div class='form-group'>
            <label class="col-md-2 control-label">Tipo Pedido</label>
             <div class="col-md-10">
               <label class="radio radio-inline">
                 <input  type="radio" name="optionsRadios" id="optionAbastecimiento" value="Abastecimiento" disabled >
                 Abastecimiento
               </label>
               <label class="radio radio-inline"> 
                 <input type="radio" name="optionsRadios" id="optionPrestamo" value="Prestamo"  disabled >
                 Prestamo
               </label> 
             </div> 
          </div>
          
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputFechaEntrega">Fecha Entrega</label>
            <div class='col-md-5'> 
              <input class='form-control datepicker' type="text" id="inputFechaEntrega"   disabled> 
            </div>
          </div>
<!--           inputFechaEntrega inputFechaDevolucion -->
          <div   id="divFechaDevolucion" >
          <div class='form-group'   >
            <label class="col-md-2 control-label" for="inputFechaDevolucion">Fecha Devoluci&oacute;n</label>
            <div class='col-md-5'> 
              <input class="form-control datepicker" id="inputFechaDevolucion"  type="text" disabled> 
            </div>
          </div>
          </div>
          
          
              
		<div class="form-group">
		  <label class="col-md-2 control-label" for="inputObservacionPedido">Observaci&oacute;n Pedido</label>
		  <div class="col-md-5">
		    <textarea class="form-control" id="inputObservacionPedido" placeholder="Observaci&oacute;n Pedido" rows="3" disabled></textarea>
		  </div>
		</div>  
		
		<div id="idTableDetallePedido" ></div> 
		
		<div class="form-group">
		  <label class="col-md-2 control-label" for="inputObservacion">Observaci&oacute;n</label>
		  <div class="col-md-5">
		    <textarea class="form-control" id="inputObservacion" placeholder="Observaci&oacute;n" rows="3" ></textarea>
		  </div>
		</div> 


<!-- 		<hr class='hr-normal'>  -->
		
		<div class="control-group"> 
			<div class="controls"  align="center">
				<a class="btn  btn-primary" onclick="guardarEvaluacion(1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Aprobar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;
				<a class="btn  btn-primary" onclick="guardarEvaluacion(2)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Desaprobar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</div>
		</div>

          </form>
        </div>
        
       </div>
     </div>
</div>







<!-- Modal -->
<!-- <div id="myBuscarPedido" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalBuscarPedido" aria-hidden="true"> -->
<!-- <div class="modal-header">  -->
<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
<!-- <h3 id="myModalBuscarPedido">Buscar Pedido</h3> -->
<!-- </div> -->
<!-- <div class="modal-body"> -->
<!-- 	<div class="form-search"   > -->
<!-- 	    <input type="text" id="txtNombreResponsable" name="objPedido.nom_trabajador" class="input-medium search-query" placeholder="Nombre de Responsable" > -->
		  
<%-- 		<s:select headerKey="0" headerValue="Seleccionar"  list="lstArea" listValue="desc_area" listKey="cod_area" id="cboArea" />   --%>
    
<!-- 		<input type="text" id="txtFechaInicio" class="input-medium search-query datepicker" placeholder="Fecha Inicio" > -->
<!-- 	    <input type="text" id="txtFechaFin" class="input-medium search-query datepicker" placeholder="Fecha Fin" > -->
<%-- 	    <s:select   headerKey="0" headerValue="Seleccionar" list="#{'Abastecimiento':'Abastecimiento', 'Prestamo':'Prestamo'}"  name="objPedido.tipo_solicitud_requerimiento"  value="objPedido.tipo_solicitud_requerimiento" id="cboTipo"  />     --%>
<!-- 	    <button type="submit" class="btn" id="idBotonBuscarPedido" >Buscar</button> -->
<!--     </div>  -->
<!--       <div id="divTablaPedidoModal"> -->
<!--       </div> -->
<!--       <div id="divDatosPedidoTotal"></div>   -->
<!-- </div>  -->
<!-- </div>   -->


 <div class='modal fade' id="myBuscarPedido"  tabindex='-1'>
                      <div class='modal-dialog'>
                        <div class='modal-content'> 
                        
                        <div class='row'>
		                <div class='col-sm-12'>
		                  	<div class='box bordered-box blue-border' style='margin-bottom:0;'> 
		                    <div class='box-header blue-background'>
		                      <div class='title'>Buscar Pedido</div> 
		                    </div>
		                    <div class='box-content box-no-padding'>
		
								<div class='box-content'>
									<form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="#" accept-charset="UTF-8"> 
										
										<div class='form-group'> 
											<div class="col-md-6">
											    <input type="text" id="txtNombreResponsable" name="objPedido.nom_trabajador" class='form-control text-inline' placeholder="Responsable" >
											</div>
											<div class="col-md-6">
												<s:select headerKey="0" headerValue="Seleccionar"  list="lstArea" listValue="desc_area" listKey="cod_area" id="cboArea" cssClass="form-control" />
											</div>  
										</div> 
										<div class='form-group'> 
											<div class="col-md-6">
											    <input type="text" id="txtFechaInicio" class="form-control datepicker" placeholder="Fecha Inicio" >
											</div>
											<div class="col-md-6">
												<input type="text" id="txtFechaFin" class="form-control datepicker" placeholder="Fecha Fin" > 
											</div>  
										</div> 
										<div class='form-group'> 
											<div class="col-md-6">
											    <s:select   headerKey="0" headerValue="Seleccionar" list="#{'Abastecimiento':'Abastecimiento', 'Prestamo':'Prestamo'}" 
 									  		    name="objPedido.tipo_solicitud_requerimiento"  value="objPedido.tipo_solicitud_requerimiento" id="cboTipo" cssClass="form-control" />  
											</div>
											<div class="col-md-6">
											<button type="submit" class="btn" id="idBotonBuscarPedido" >Buscar</button>
											</div>
											
										</div> 
											
									</form>
								</div> 

			                   </div>
			                  </div>
			              </div>
			              </div>  
                          <div id="divTablaPedidoModal">
					      </div>
					      <div id="divDatosPedidoTotal"></div> 
                          
    </div>
  </div>
</div>






<div id="myEvaluacionPedido" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalEvaluacionPedido" aria-hidden="true">
<div class="modal-dialog">
<div class='modal-content'>
      <div id="divMensajeEvaluacion">
      </div> 
</div>
</div> 

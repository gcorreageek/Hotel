<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %> 
<%@ page language="java"   import="com.hotel.util.UtilSigal"%>
<script type="text/javascript"> 
// ${row.cod_OrdenCompra},'${row.nom_trabajador}','${row.fecharegistro_ordencompra}','${row.raz_social}'
function seleccionaOrdenCompra(codOrdenCompra,nomUsuario,fechaOrdenCompra,razonSocial,tipoOrdenCompra,fechaDevolucionOrdenCompra,fechaEntregaOrdenCompra){  
	$("#cod_OrdenCompra").val(codOrdenCompra);
	$("#inputResponsable").val(nomUsuario);
	$("#inputFecha").val(fechaOrdenCompra); 
	$("#inputProveedor").val(razonSocial);
	
	$("#inputFechaDevolucion").val(fechaDevolucionOrdenCompra);
	$("#inputFechaEntrega").val(fechaEntregaOrdenCompra);
	
	
	if('Abastecimiento'==tipoOrdenCompra){
		var $radios = $('input:radio[name=optionsRadios]');
		$radios.filter('[value=Abastecimiento]').prop('checked', true);	
		$('#divFechaDevolucion').hide('slow');
	}else if('Prestamo'==tipoOrdenCompra){
		var $radios = $('input:radio[name=optionsRadios]');
		$radios.filter('[value=Prestamo]').prop('checked', true); 
		$('#divFechaDevolucion').show('slow');
	}else{
		var $radios = $('input:radio[name=optionsRadios]');
		$radios.filter('[value=Abastecimiento]').prop('checked', true);	
		$('#divFechaDevolucion').hide('slow'); 
	} 
	
	$.post("getDetalleOrdenCompra",{"objOrdenCompra.cod_OrdenCompra":codOrdenCompra},function(data){
 		$("#idTableDetalleOrdenCompra").html(data);
	});  	
} 
function guardarIEE(){//objInformeInterno.cod_pedido  obs_informeinterno
	var mensaje="<div class='alert alert-error'><h4>Error!</h4>";
	var codOrdenCompra=$("#cod_OrdenCompra").val(); 
	var observacion=$("#inputObservacion").val(); 
	var iChars = "#$%^&*()+=-[]\\'/{}|\"<>"; 
	if(codOrdenCompra==null || codOrdenCompra=='' ){ 
		$("#divMostrarMensaje").html(mensaje+" Ingrese Orden de Compra correcto"+ "</div>");
		setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
		return;
	}
	for (var i = 0; i < observacion.length; i++) {
	    if (iChars.indexOf(observacion.charAt(i)) != -1) {
	    	$("#divMostrarMensaje").html(mensaje+" Ingrese una Observacion valida"+ "</div>");
 			setTimeout(function(){ $('.alert').hide(1000); }, 2000); 
 			return;
	    }
	}
	$.post("guardarIES",{"objInformeExterno.cod_ordencompra":codOrdenCompra,"objInformeExterno.obs_informeexterno":observacion},function(data){
		var bien = data.indexOf("Error");  
		if(bien<0){
			$("#idMensajeInterno").html(data);
	 		$('#myIE').modal({  keyboard: false });  
	 		setTimeout(function(){ $(location).attr('href','mainInformeExternoSalida'); }, 4000);
		}else{
			$("#idMensajeInterno").html(data);
	 		$('#myIE').modal({  keyboard: false });  
		} 
	}); 
}
$(document).ready(function() {  
	$('#idBuscarOrdenCompra').click(function(){
		$("#txtNombreResponsable").val(""); 
		$("#txtFechaInicio").val("");
		$("#txtFechaFin").val(""); 
		$.post("listarOrdenCompraTotalEntregada",function(data){
	 		$("#divDatosOrdenCompraTotal").html(data);
		}); 
		$.post("listarOrdenCompraPagModalEntregada",{inicio:null},function(data){
	 		$("#divTablaOrdenCompraModal").html(data);
		}); 
	});   
	/**COTIZACION**/
	$('#idBotonBuscarOrdenCompra').click(function(){ 
		var txtNombreRespo=$("#txtNombreResponsable").val(); 
		var txtFechaInicio=$("#txtFechaInicio").val();
		var txtFechaFin=$("#txtFechaFin").val();  
		$.post("buscarOrdenCompraTotalEntregada",{
			"objOrdenCompra.nom_trabajador":txtNombreRespo, 
			"objOrdenCompra.fechaInicio":txtFechaInicio,
			"objOrdenCompra.fechaFin":txtFechaFin
			},function(data){
	 		$("#divDatosOrdenCompraTotal").html(data);
		}); 
		$.post("buscarOrdenCompraPagModalEntregada",{
			"objOrdenCompra.nom_trabajador":txtNombreRespo, 
			"objOrdenCompra.fechaInicio":txtFechaInicio,
			"objOrdenCompra.fechaFin":txtFechaFin
			},function(data){
	 		$("#divTablaOrdenCompraModal").html(data);
		}); 
	});    

// 	 setTimeout(function(){ $('.alert').hide(1000); }, 3000); 
}); 
</script>
<!-- <h3>Informe Externo de Salida</h3> -->
<div id="divMostrarMensaje"> 
</div>
<!-- <form> -->
<%-- <s:hidden  name="objOrdenCompra.cod_OrdenCompra"  id="cod_OrdenCompra"    /> --%>
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline">  -->
<!-- 		<label class="control-label" for="inputFecha"  >Fecha</label> -->
<%-- 		<input type="text" id="inputFechaActual"   value="<%=UtilSigal.fechaActual() %>"   disabled>  --%>
<!-- 	</div> -->
<!-- </div> -->
<!-- <h5>Datos de la Orden Compra</h5>  -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputResponsable">Responsable</label> -->
<!-- 		<input type="text" id="inputResponsable" class="span5" placeholder="Responsable" disabled> -->
<!-- 		<a class="btn btn-primary"  id="idBuscarOrdenCompra" href="#myBuscarOrdenCompra" data-toggle="modal">Buscar Orden de Compra</a> -->
<!-- 	</div> -->
<!-- </div>  -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline ">  -->
<!-- 		<label class="control-label" for="inputFecha">Fecha</label> -->
<!-- 		<input type="text" id="inputFecha" placeholder="Fecha" disabled> -->
<!-- 	</div>  -->
<!-- </div>   -->
<!-- <h5>Datos del Proveedor</h5> -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputProveedor">Proveedor</label> -->
<!-- 		<input type="text" id="inputProveedor" class="input-xxlarge" placeholder="Proveedor" disabled>  -->
<!-- 	</div>  -->
<!-- </div>    -->
<!-- <div id="idTableDetalleOrdenCompra"> -->
<!--     <table class="table table-striped table-bordered table-hover"> -->
<!--       <thead> -->
<!--         <tr>  -->
<!--           <th>Producto</th>  -->
<!--           <th>U.Medida</th> -->
<!--           <th>Cantidad</th>   -->
<!--         </tr> -->
<!--       </thead> -->
<!--       <tbody>  -->
<!--       </tbody> -->
<!--     </table>  -->
<!-- </div> -->

<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputObservacion">Observaci&oacute;n</label> -->
<!-- 		<textarea rows="1" class="span11"  id="inputObservacion" placeholder="Observaci&oacute;n"   ></textarea>   -->
<!-- 	</div> -->
<!-- </div> -->


<!-- <div class="control-group">  -->
<!-- <div class="controls"  align="center"> -->
<!-- <a class="btn  btn-primary"  onclick="javascript:guardarIEE();" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Guardar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp; -->
<!-- <a class="btn  btn-primary"  href="inicio">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cancelar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
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
            Informe Externo de Salida
          </div>
          <div class='actions'>
            <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
            </a>
            
            <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
            </a>
          </div>
        </div>
        <div class='box-content'>
          <div class="form form-horizontal" style="margin-bottom: 0;"  >
			<s:hidden  name="objOrdenCompra.cod_OrdenCompra"  id="cod_OrdenCompra"    />
          
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputFechaActual'>Fecha</label>
            <div class='col-md-5'>
              <input type="text"   class="form-control" id="inputFechaActual" value="<%=UtilSigal.fechaActual() %>"   disabled> 
            </div> 
          </div>
          <hr class='hr-normal'> 
          
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputResponsable'>Responsable</label>
            <div class='col-md-3'>
              <input type="text" id="inputResponsable" class="form-control" placeholder="Responsable" disabled> 
            </div>
            <div class='col-md-1'> 
            	<a class="btn btn-primary"  id="idBuscarOrdenCompra" href="#myBuscarOrdenCompra" data-toggle="modal">Buscar Orden de Compra</a>
            </div> 
          </div> 
          
          
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputFecha'>Fecha</label>
            <div class='col-md-5'>  
              <input type="text" id="inputFecha"  class="form-control" placeholder="Fecha"   disabled> 
            </div> 
          </div> 
          
          <div class='form-group'>
            <label class="col-md-2 control-label">Tipo Pedido</label>
             <div class="col-md-10">
               <label class="radio radio-inline">
                 <input type="radio" name="optionsRadios" id="optionAbastecimiento" value="Abastecimiento" disabled  >
                 Abastecimiento
               </label>
               <label class="radio radio-inline"> 
                 <input type="radio" name="optionsRadios" id="optionPrestamo" value="Prestamo" disabled  >
                 Prestamo
               </label> 
             </div> 
          </div>
          <div   id="divFechaDevolucion" >
          <div class='form-group'>
            <label class="col-md-2 control-label" for="inputFechaEntrega">Fecha Entrega</label>
            <div class='col-md-5'> 
              <input class='form-control datepicker' type="text" id="inputFechaEntrega"   disabled>  
            </div>
          </div> 

          
          <div class='form-group'   >
            <label class="col-md-2 control-label" for="inputFechaDevolucion">Fecha Devoluci&oacute;n</label>
            <div class='col-md-5'> 
              <input class="form-control datepicker" id="inputFechaDevolucion"  type="text" disabled> 
            </div>
          </div>
          </div>
          
          
          
          
          
          
          <div class='form-group'>
            <label class='col-md-2 control-label' for='inputProveedor'>Proveedor</label>
            <div class='col-md-5'>  
              <input type="text" id="inputProveedor" class="form-control" placeholder="Proveedor" disabled>
            </div> 
          </div> 
          
          
          
          
		<div id="idTableDetalleOrdenCompra">
		    <table class="table table-striped table-bordered table-hover">
		      <thead>
		        <tr> 
		          <th>Producto</th> 
		          <th>U.Medida</th>
		          <th>Cantidad</th>  
		        </tr>
		      </thead>
		      <tbody> 
		      </tbody>
		    </table> 
		</div>
 
		<div class="form-group">
		  <label class="col-md-2 control-label" for="inputObservacion">Observaci&oacute;n</label>
		  <div class="col-md-5"> 
		   <textarea rows="1" class="form-control"  id="inputObservacion" placeholder="Observaci&oacute;n"   ></textarea>
		  </div>
		</div> 

 
		
		<div class="control-group"> 
			<div class="controls"  align="center">
				<a class="btn  btn-primary"  onclick="javascript:guardarIEE();" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Guardar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;
				<a class="btn  btn-primary"  href="inicio">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cancelar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</div>
		</div>

          </div>
        </div>
        
       </div>
     </div>
</div>






<!-- Modal -->
 <div class='modal fade' id="myBuscarOrdenCompra"  tabindex='-1'>
                      <div class='modal-dialog'>
                        <div class='modal-content'> 
                        
                        <div class='row'>
		                <div class='col-sm-12'>
		                  	<div class='box bordered-box blue-border' style='margin-bottom:0;'> 
		                    <div class='box-header blue-background'>
		                      <div class='title'>Buscar Orden Compra</div> 
		                    </div>
		                    <div class='box-content box-no-padding'>
		
								<div class='box-content'>
									<div class="form form-horizontal" style="margin-bottom: 0;"  >  
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
											     <input type="text" id="txtNombreResponsable" name="objOrdenCompra.nom_trabajador" class="form-control search-query" placeholder="Nombre de Responsable" >
											</div>
											<div class="col-md-6">
												 <button type="submit" class="btn" id="idBotonBuscarOrdenCompra" >Buscar</button>
											</div>  
										</div>   
											
									</div>
								</div> 

			                   </div>
			                  </div>
			              </div>
			              </div>  
                          <div id="divTablaOrdenCompraModal">
					      </div>
					      <div id="divDatosOrdenCompraTotal"></div> 
                          
    </div>
  </div>
</div>
 

<div id="myIE" class="modal fade" tabindex="-1"  >
<div class="modal-dialog">
<div class='modal-content'>
<div id="idMensajeInterno"></div> 
</div> 
</div>
</div>
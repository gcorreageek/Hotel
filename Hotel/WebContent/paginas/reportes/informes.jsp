<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %> 
<script type="text/javascript"  > 
$(document).ready(function() {
// 		$.post("listarArticuloTotal",function(data){
// 	 		$("#divDatosProdTotal").html(data);
// 		}); 
// 		$.post("listarArticuloPagModal",{inicio:null},function(data){
// 	 		$("#divTablaProdModal").html(data);
// 		}); 

		$("#idInforme").change(function () { 
			  $("select#idInforme option:selected").each(function () {  
// 				  alert('Cogiste:'+$(this).val());
				  if($(this).val()=='Interno'){
					  $('#idForm').attr('action','reporteInformeInterno'); 
				  }else{
					  $('#idForm').attr('action','reporteInformeExterno'); 
				  } 
	    	  }); 
			}).trigger('change'); 
// 	$('#idBotonBuscarProducto').click(function(){
// 		var txtProd=$("#desc_articulo").val();
// 		var uMedida=$("#idUMedida").val(); 
		
// 		$.post("buscarArticuloTotal",{"objProducto.desc_articulo":txtProd,"objProducto.unidadMedida":uMedida},function(data){
// 	 		$("#divDatosProdTotal").html(data);
// 		}); 
// 		$.post("buscarArticulosXDescProdPagModal",{inicio:null,"objProducto.desc_articulo":txtProd,"objProducto.unidadMedida":uMedida},function(data){
// 	 		$("#divTablaProdModal").html(data);
// 		}); 
// 	});    
});  
</script>
<!-- <h3>Reportes de Informes</h3>   -->
<div id="divMostrarMensaje"> 
</div>
<!-- <form id="idForm"  method="post" > -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputProducto"  >Informe</label> -->
<%-- 		<s:select  id="idInforme"   --%>
<%-- 			list="#{ 'Interno':'Interno', 'Externo':'Externo'}" --%>
<%-- 			name="informe"  /> --%>
<!-- 	</div> -->
<!-- </div> -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 	<label class="control-label" for="unidadMedida" class="span4">Tipo</label> -->
<%-- 	<s:select  id="idTipo"   --%>
<%-- 			list="#{'Seleccionar':'Seleccionar','Entrada':'Entrada', 'Salida':'Salida' }" --%>
<%-- 			name="tipo"   /> --%>
<!-- 	<button type="submit"   class="btn  btn-primary" id="btnExportar" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Exportar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>&nbsp;&nbsp;&nbsp; -->
<!-- 	</div>  -->
<!-- </div> -->
<!-- </form> -->

<hr class='hr-normal'>

<div class='row'>
                <div class='col-sm-12'>
                  <div class='box'>
                    <div class='box-header blue-background'>
                      <div class='title'>
                        <div class='icon-edit'></div>  
							Reportes de Informes
							
                      </div>
                      <div class='actions'>
                        <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
                        </a>
                        
                        <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
                        </a>
                      </div>
                    </div>
                    <div class='box-content'>
                              <form class="form form-horizontal" style="margin-bottom: 0;" method="post" id="idForm" >
 
                              <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='idInforme'>Informe</label>
		                        <div class='col-md-5'> 
								<s:select  id="idInforme" cssClass="form-control"   list="#{ 'Interno':'Interno', 'Externo':'Externo'}"  name="informe"  /> 	                          

		                        </div> 
		                      </div>    
		                      
		                      <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='idTipo'>Tipo</label>
		                        <div class='col-md-5'>
									<s:select  id="idTipo"  cssClass="form-control"
											list="#{'Seleccionar':'Seleccionar','Entrada':'Entrada', 'Salida':'Salida' }"
											name="tipo"   />
		                           
		                        </div>
		                      </div>    
                     
                      <hr class='hr-normal'>
                       
                       
                      <div class='form-actions form-actions-padding-sm'>
                        <div class='row'>
                          <div class='col-md-10 col-md-offset-2'>
<button type="submit"   class="btn  btn-primary" id="btnExportar" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Exportar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>&nbsp;&nbsp;&nbsp;
                          </div>
                        </div>
                      </div>   
                      
                      </form>
              
                    </div>
                  </div>
                </div>
              </div>



<div id="divTablaProdModal"></div>
<div id="divDatosProdTotal"></div>
 
 

 
 

  
   
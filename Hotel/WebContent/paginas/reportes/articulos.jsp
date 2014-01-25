<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %> 
<script type="text/javascript"  > 
$(document).ready(function() {
// 	$('#idBuscarProducto').click(function(){
// 		$("#txtProducto").val("");
		$.post("listarArticuloTotal",function(data){
	 		$("#divDatosProdTotal").html(data);
		}); 
		$.post("listarArticuloPagModal",{inicio:null},function(data){
	 		$("#divTablaProdModal").html(data);
		}); 
// 	});  
	$('#idBotonBuscarProducto').click(function(){
		var txtProd=$("#desc_articulo").val();
		var uMedida=$("#idUMedida").val(); 
		
		$.post("buscarArticuloTotal",{"objProducto.desc_articulo":txtProd,"objProducto.unidadMedida":uMedida},function(data){
	 		$("#divDatosProdTotal").html(data);
		}); 
		$.post("buscarArticulosXDescProdPagModal",{inicio:null,"objProducto.desc_articulo":txtProd,"objProducto.unidadMedida":uMedida},function(data){
	 		$("#divTablaProdModal").html(data);
		}); 
	});    
});  
</script>  
<div id="divMostrarMensaje"> 
</div>
<!-- <form action="reporteArticulos" method="post" > -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 		<label class="control-label" for="inputProducto">Producto</label> -->
<!-- 		<input type="text" class="span5" id="desc_articulo" name="objProducto.desc_articulo"  placeholder="Producto"  > -->
<!-- 	</div> -->
<!-- </div> -->
<!-- <div class="control-group"> -->
<!-- 	<div  class="form-inline "> -->
<!-- 	<label class="control-label" for="unidadMedida">U.Medida</label> -->
<%-- 	<s:select  id="idUMedida"   --%>
<%-- 			list="#{'Seleccionar':'Seleccionar','Unidad':'Unidad', 'Caja':'Caja', 'Docena':'Docena',  'Millar':'Millar'}" --%>
<%-- 			name="objProducto.unidadMedida"   /> --%>
<!-- 	<a class="btn   btn-primary" id="idBotonBuscarProducto" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Consultar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp; -->
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
							Reportes de Articulos
							
                      </div>
                      <div class='actions'>
                        <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
                        </a>
                        
                        <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
                        </a>
                      </div>
                    </div>
                    <div class='box-content'>
                              <form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="reporteArticulos" accept-charset="UTF-8">
 
                              <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='desc_articulo'>Articulo</label>
		                        <div class='col-md-5'> 
		                          <input type="text" class="form-control" id="desc_articulo" name="objProducto.desc_articulo"   placeholder="Articulo"   >
		                        </div> 
		                      </div>    
		                      
		                      <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='idUMedida'>U.Medida</label>
		                        <div class='col-md-5'>
									<s:select  id="idUMedida"  cssClass="form-control"
						 			list="#{'Seleccionar':'Seleccionar','Unidad':'Unidad', 'Caja':'Caja', 'Docena':'Docena',  'Millar':'Millar'}" 
						 			name="objProducto.unidadMedida"   /> 
		                           
		                        </div>
		                      </div>    
                     
                      <hr class='hr-normal'>
                       
                       
                      <div class='form-actions form-actions-padding-sm'>
                        <div class='row'>
                          <div class='col-md-10 col-md-offset-2'>
<!--                             <button class='btn btn-primary' type='submit'> -->
<!--                               <i class='icon-save'></i> -->
<!--                               Guardar -->
<!--                             </button>  -->
<!-- 							<a class='btn'   href="mainArticuloProveedor">Listar</a> -->
							
								<a class="btn   btn-primary" id="idBotonBuscarProducto" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Consultar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;
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
 
 

 
 

  
  
  
<div id="myMensaje" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalBuscarProducto" aria-hidden="true">
<div class="modal-body">
<div id="idMensajeInterno"></div> 
</div> 
</div>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
// cod_articulo desc_articulo unidadMedida cod_proveedor  raz_social
function seleccionaProd(codProd,descProd,uMedida){  
	$("#cod_articulo").val(codProd);
	$("#desc_articulo").val(descProd);
	$("#unidadMedida").val(uMedida); 
}
function seleccionaProvee(codProvee,descProvee){ 
	console.log('ddddd:'+codProvee+'|'+descProvee);
	$("#cod_proveedor").val(codProvee);
	$("#raz_social").val(descProvee); 
}

$(document).ready(function() { 
	/**PRODCUTO**/
	$('#idBuscarProducto').click(function(){
		$("#txtProducto").val("");
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


	 setTimeout(function(){ $('.alert').hide(1000); }, 3000); 
}); 
</script>
</head>
<body>



<c:if test="${requestScope.rsult!=null}"   >
<c:if test="${requestScope.rsult=='0'}"   >
	<div class="alert alert-success"> 
	<h4>Bien!</h4>
	${requestScope.mensaje}
	</div> 
</c:if>
<c:if test="${requestScope.rsult=='1'}"   >
	<div class="alert alert-error"> 
	<h4>Error!</h4>
	${requestScope.mensaje}
	</div> 
</c:if>

</c:if>

<!-- <form class="form-horizontal" action="actuarArticuloProveedor" method="post"  > -->
<%-- <s:hidden  name="objProductoProveedor.cod_articulo_proveedor"      /> --%>

<%-- <s:hidden  name="objProductoProveedor.cod_articulo"  id="cod_articulo"    /> --%>
<!-- <div class="control-group"> -->
<!-- <label class="control-label" for="desc_articulo">Nombre</label> -->
<!-- <div class="controls"> -->
<%-- <input type="text" id="desc_articulo" name="objProductoProveedor.desc_articulo" value="${objProductoProveedor.desc_articulo}" placeholder="Nombre"  disabled> --%>
<!-- <a class="btn btn-primary" id="idBuscarProducto" href="#myBuscarProducto" data-toggle="modal" >Buscar Producto</a> -->
<!-- </div> -->
<!-- </div> -->
 
<!-- <div class="control-group"> -->
<!-- <label class="control-label" for="unidadMedida">U.Medida</label> -->
<!-- <div class="controls"> -->
<%-- <input type="text" id="unidadMedida"  name="objProductoProveedor.unidadMedida" value="${objProductoProveedor.unidadMedida}" placeholder="U.Medida"  disabled> --%>
<!-- </div> -->
<!-- </div> -->

<%-- <s:hidden  name="objProductoProveedor.cod_proveedor" id="cod_proveedor"     /> --%>
<!-- <div class="control-group"> -->
<!-- <label class="control-label" for="raz_social">Proveedor</label> -->
<!-- <div class="controls"> -->
<%-- <input type="text" id="raz_social"  name="objProductoProveedor.raz_social" value="${objProductoProveedor.raz_social}" placeholder="Proveedor" disabled> --%>
<!-- <a class="btn btn-primary" id="idBuscarProveedor" href="#myBuscarProveedor" data-toggle="modal">Buscar Proveedor</a> -->
<!-- </div> -->
<!-- </div> -->
 
<!-- <div class="control-group">  -->
<!-- <div class="controls"> -->
<!-- <button class="btn btn-primary">Guardar</button> -->
<!-- <a class="btn btn-primary"  href="mainArticuloProveedor">Listar</a> -->
<!-- </div> -->
<!-- </div> -->

<!-- </form>  -->

<hr class='hr-normal'>

<div class='row'>
                <div class='col-sm-12'>
                  <div class='box'>
                    <div class='box-header blue-background'>
                      <div class='title'>
                        <div class='icon-edit'></div> 
							
							<c:if test="${requestScope.objProductoProveedor.cod_articulo_proveedor!=null}"   >
							Modificar Producto Proveedor 
							</c:if>
							<c:if test="${requestScope.objProductoProveedor.cod_articulo_proveedor==null}"   >
							Nuevo Producto Proveedor  
							</c:if>
							
                      </div>
                      <div class='actions'>
                        <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
                        </a>
                        
                        <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
                        </a>
                      </div>
                    </div>
                    <div class='box-content'>
                              <form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="actuarArticuloProveedor" accept-charset="UTF-8">
                              <s:hidden  name="objProductoProveedor.cod_articulo_proveedor"      />

							  <s:hidden  name="objProductoProveedor.cod_articulo"  id="cod_articulo"    />
                              <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='desc_articulo'>Nombre*</label>
		                        <div class='col-md-5'>
		                          <input type="text" class="form-control" id="desc_articulo" name="objProductoProveedor.desc_articulo" value="${objProductoProveedor.desc_articulo}" placeholder="Nombre"  disabled>
		                        </div>
		                        
		                        <div class='col-md-2'>
		                          <a class="btn btn-primary" id="idBuscarProducto" href="#myBuscarProducto" data-toggle="modal" >Buscar Producto</a>
		                        </div>
		                      </div>    
		                      
		                      <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='unidadMedida'>U.Medida</label>
		                        <div class='col-md-5'>
		                           <input type="text"  class="form-control" id="unidadMedida"  name="objProductoProveedor.unidadMedida" value="${objProductoProveedor.unidadMedida}" placeholder="U.Medida"  disabled>
		                        </div>
		                      </div>   
		                      
		                      <s:hidden  name="objProductoProveedor.cod_proveedor" id="cod_proveedor"     />
		                      
		                      <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='raz_social'>Proveedor</label>
		                        <div class='col-md-5'>
		                          <input type="text"  class="form-control" id="raz_social"  name="objProductoProveedor.raz_social" value="${objProductoProveedor.raz_social}" placeholder="Proveedor" disabled>
		                        </div>
		                        
		                        <div class='col-md-1'>
		                          <a class="btn btn-primary" id="idBuscarProveedor" href="#myBuscarProveedor" data-toggle="modal">Buscar Proveedor</a>
		                        </div>
		                      </div> 
		                      
		                      
		                       
		                     
                       
                      
                     
                      <hr class='hr-normal'>
                       
                       
                      <div class='form-actions form-actions-padding-sm'>
                        <div class='row'>
                          <div class='col-md-10 col-md-offset-2'>
                            <button class='btn btn-primary' type='submit'>
                              <i class='icon-save'></i>
                              Guardar
                            </button> 
							<a class='btn'   href="mainArticuloProveedor">Listar</a>

                          </div>
                        </div>
                      </div>   
                      
                      </form>
              
                    </div>
                  </div>
                </div>
              </div>





</body>
</html>



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
											<div class="col-md-10">
												<input class='form-control text-inline' id="txtProducto" name="objProducto.desc_articulo"  placeholder='Articulo' type='text'>
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



<!-- Modal -->
<!-- <div id="myBuscarProveedor" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalBuscarProveedor" aria-hidden="true"> -->
<!-- <div class="modal-header"> -->
<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
<!-- <h3 id="myModalBuscarProveedor">Buscar Proveedor</h3> -->
<!-- </div> -->
<!-- <div class="modal-body"> -->
<!-- 	<div class="form-search"   > -->
<!-- 	    <input type="text" id="txtProveedor" name="objProveedor.raz_social" class="input-medium search-query" placeholder="Proveedor" > -->
<!-- 	    <button type="submit" class="btn" id="idBotonBuscarProveedor" >Buscar</button> -->
<!--     </div>  -->
<!--       <div id="divTablaProveModal"> -->
<!--       </div> -->
<!--       <div id="divDatosProveTotal"></div>   -->
<!-- </div>  -->
<!-- </div> -->


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


  
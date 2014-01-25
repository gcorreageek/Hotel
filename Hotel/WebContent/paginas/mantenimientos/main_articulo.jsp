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
function generarQR(idProd){ 
	var url = document.URL; 
	url = url.substring(0,url.indexOf("/",8));
	url = url + "/Sigal/leerQR?codProd="+idProd; 
	console.log('utl:'+url);
	var imagen= "<img src='http://www.codigos-qr.com/qr/php/qr_img.php?d="+url+"&s=4&e=' alt='Códigos QR / Qr Codes'/>";
	$("#divMostrarMensaje").html(imagen);
	$('#myImagenQr').modal({
	  keyboard: false
	});  
	
	
	
}
$(document).ready(function() { 
	var numeroPaginas = $("#numeroPaginas").val(); 
    var options = {
     currentPage: 1,
     totalPages: numeroPaginas,
     onPageClicked: function(e,originalEvent,type,page){ 
    	 var txt = $("#idBuscarProducto").val();
    	if($("#tagTipoListado").val()==1){
    		$.post("listarArticuloPag",{inicio:page},function(data){
         		$("#divTablaPag").html(data);
     		}); 	
    	}else{
    		$.post("buscarArticulosXDescProdPag",{inicio:page,"objProducto.desc_articulo":txt},function(data){
         		$("#divTablaPag").html(data);
     		}); 	
    	} 
     	
     } 
 	};
// 	$("#idButonProducto").click(function(){ 
//  		var txt = $("#idBuscarProducto").val();
// 		$.post("buscarArticulosXDescProdPag",{inicio:null,"objProducto.desc_articulo":txt},function(data){
//      		$("#divTablaPag").html(data);
//  		});  
// 	});
    
    $('#divPaginador').bootstrapPaginator(options); 
	setTimeout(function(){ $('.alert').hide(1000); }, 2000);
	
	 
}); 
</script>
</head>
<body>  


<c:if test="${requestScope.rsult!=null}"   >
<c:if test="${requestScope.rsult=='1'}"   >
	<div class="alert alert-success"> 
	<h4>Bien!</h4>
	${requestScope.mensaje}
	</div> 
</c:if>
<c:if test="${requestScope.rsult=='0'}"   >
	<div class="alert alert-error"> 
	<h4>Error!</h4>
	${requestScope.mensaje}
	</div> 
</c:if>

</c:if>
<hr class='hr-normal'> 
    
     
		<div id="divTablaPag">
		<div class='row'>
                <div class='col-sm-12'>
                  <div class='box bordered-box blue-border' style='margin-bottom:0;'>
                  
                  
                    <div class='box-header blue-background'>
                      <div class='title'>Articulo</div>
                      <div class='actions'>
                        <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
                        </a>
                        
                        <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
                        </a>
                      </div>
                    </div>
                    <div class='box-content box-no-padding'>
                      <div class='responsive-table'>
                        <div class='scrollable-area'>
                          <table class='table' style='margin-bottom:0;'>
                            <thead>
                              <tr>
                                  <th>Articulo</th> 
				                  <th>U.Medida</th> 
				                  <th>Tipo</th> 
				                  <th></th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:if test="${requestScope.lstProducto!=null}"   >
							     <c:forEach  items="${requestScope.lstProducto}"  var="row"  >
							     	 <tr> 
					                  <td>${row.desc_articulo}</td> 
					                  <td>${row.unidadMedida}</td>
					                  <td>${row.tipo_articulo}</td> 
					                  <td>
					                  <div class='text-right'>
	                                    <a class='btn btn-success btn-xs' href='accionArticulo?codProd=${row.cod_articulo}'>
	                                      <i class='icon-ok'></i>
	                                    </a>
	                                    <a class='btn btn-danger btn-xs' href='eliminarArticulo?codProd=${row.cod_articulo}'>
	                                      <i class='icon-remove'></i>
	                                    </a>
	                                  </div>
					                  </td> 
					                </tr>
							     </c:forEach> 
						      </c:if>
                             
                              
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
		
		
		 
		</div>	
		 
		 <s:hidden id="tagTipoListado" name="tagTipoListado"      /> 
         <s:hidden id="numeroPaginas" name="numeroPaginas"      /> 
 	     <div id="divPaginador"></div>     
 	     <hr class='hr-normal'>
         <a class="btn btn-primary"  href="accionArticulo">Nuevo Articulo</a>
            
</body>
</html>





<div id="myImagenQr" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalImagenQR" aria-hidden="true">
<div class="modal-body"> 
      <div id="divMostrarMensaje" align="center">
      </div>  
</div> 
</div>

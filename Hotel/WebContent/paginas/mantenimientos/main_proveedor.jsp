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

$(document).ready(function() { 
	var numeroPaginas = $("#numeroPaginas").val(); 
    var options = {
     currentPage: 1,
     totalPages: numeroPaginas,
     onPageClicked: function(e,originalEvent,type,page){ 
    	if($("#tagTipoListado").val()==1){
    		$.post("listarProveedorPag",{inicio:page},function(data){
         		$("#divTablaPag").html(data);
     		}); 	
    	}else{
    		var txt = $("#txtProveedorBuscar").val();
    		$.post("buscarProveedorXRazonSocialPag",{inicio:page,"objProveedor.raz_social":txt},function(data){
         		$("#divTablaPag").html(data);
     		}); 	
    	}
     	
     } 
 	} 
	

    $('#divPaginador').bootstrapPaginator(options); 
	setTimeout(function(){ $('.alert').hide(1000); }, 3000); 
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



<!-- 	<form class="form-search"  action="buscarProveedorXRazonSocial" method="post"> -->
<%-- 	    <input type="text" id="txtProveedorBuscar" name="objProveedor.raz_social"  value="${objProveedor.raz_social}" class="input-medium search-query" placeholder="Proveedor" > --%>
<!-- 	    <button type="submit" class="btn">Buscar</button> -->
<!--     </form> -->
     
		
		<hr class='hr-normal'> 
    
     
		<div id="divTablaPag">
		<div class='row'>
                <div class='col-sm-12'>
                  <div class='box bordered-box blue-border' style='margin-bottom:0;'>
                  
                  
                    <div class='box-header blue-background'>
                      <div class='title'>Proveedor</div>
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
                                  <th>Razon Social</th>
				                  <th>NumeroDoc</th>
				                  <th>Telefono</th> 
				                  <th>Correo</th>
				                  <th></th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:if test="${requestScope.lstProveedor!=null}"   >
							     <c:forEach  items="${requestScope.lstProveedor}"  var="row"  >
							     	 <tr> 
					                  <td>${row.raz_social}</td> 
					                  <td>${row.ruc}</td>
					                  <td>${row.telefono}</td> 
					                  <td>${row.correo}</td> 
					                  <td>
					                  <div class='text-right'>
	                                    <a class='btn btn-success btn-xs' href='accionProveedor?codProvee=${row.cod_proveedor}'>
	                                      <i class='icon-ok'></i>
	                                    </a>
	                                    <a class='btn btn-danger btn-xs' href='eliminarProveedor?codProvee=${row.cod_proveedor}'>
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
         <a class="btn btn-primary"  href="accionProveedor">Nuevo Proveedor</a> 

</body>
</html>
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


<hr class='hr-normal'>

<div class='row'>
                <div class='col-sm-12'>
                  <div class='box'>
                    <div class='box-header blue-background'>
                      <div class='title'>
                        <div class='icon-edit'></div> 
							
							<c:if test="${requestScope.objProveedor.cod_proveedor!=null}"   >
							 Modificar Proveedor
							</c:if>
							<c:if test="${requestScope.objProveedor.cod_proveedor==null}"   >
							 Nuevo Proveedor   
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
                              <form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="actuarProveedor" accept-charset="UTF-8">
                              <s:hidden  name="objProveedor.cod_proveedor"      />
                              <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='inputRazonSocial'>Razon Social*</label>
		                        <div class='col-md-5'>
		                          <input type="text" class="form-control" id="inputRazonSocial" name="objProveedor.raz_social" value="${objProveedor.raz_social}" placeholder="Rason Social">
		                        </div>
		                      </div>    
		                      
		                      <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='inputRuc'>Ruc</label>
		                        <div class='col-md-5'>
		                          <input type="text" class="form-control" id="inputRuc"  name="objProveedor.ruc" value="${objProveedor.ruc}" placeholder="Ruc">
		                        </div>
		                      </div>   
		                      
		                      <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='inputTelefono'>Telefono</label>
		                        <div class='col-md-5'>
		                          <input type="text" class="form-control" id="inputTelefono"  name="objProveedor.telefono" value="${objProveedor.telefono}" placeholder="Telefono">
		                        </div>
		                      </div> 
		                      
		                      <div class='form-group'> 
		                        <label class='col-md-2 control-label' for='inputCorreo'>Correo</label>
		                        <div class='col-md-5'>
		                          <input type="email" class="form-control" id="inputCorreo"  name="objProveedor.correo" value="${objProveedor.correo}" placeholder="Correo">
		                        </div>
		                      </div> 
		                       
		                       <div class="control-group"  style="display:none" >
								<label class="control-label"  >Habilitaci&oacute;n</label>
								<div class="controls">
								<s:select    cssStyle="display:none"
										list="#{'Habilitado':'Habilitado', 'Desabilitado':'Desabilitado'}" 
										name="objProveedor.habilitado"  value="objProveedor.habilitado" />
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
							<a class='btn'   href="mainProveedor">Listar</a>

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
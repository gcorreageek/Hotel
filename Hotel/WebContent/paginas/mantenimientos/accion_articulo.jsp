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

<div class='row'>
                <div class='col-sm-12'>
                  <div class='box'>
                    <div class='box-header blue-background'>
                      <div class='title'>
                        <div class='icon-edit'></div>
                        <c:if test="${requestScope.objProducto.cod_articulo!=null}"   >
							 Modificar Articulo   
							</c:if>
							<c:if test="${requestScope.objProducto.cod_articulo==null}"   >
							 Nuevo Articulo  
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
                              <form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="actuarArticulo" accept-charset="UTF-8"><input name="authenticity_token" type="hidden" /><div class='form-group'>
                        <s:hidden  name="objProducto.cod_articulo"      />
                        <label class='col-md-2 control-label' for='inputText1'>Nombre*</label>
                        <div class='col-md-5'>
                          <input class='form-control' id='inputNombre' placeholder='Nombre' type='text' value="${objProducto.desc_articulo}" name="objProducto.desc_articulo"  >
                        </div>
                      </div>    
                      <div class='form-group'>
                        <label class='col-md-2 control-label' for='inputSelect'>U.Medida</label>
                        <div class='col-md-5'>
                        <s:select     cssClass="form-control"
		list="#{'Unidad':'Unidad', 'Caja':'Caja', 'Docena':'Docena',  'Millar':'Millar'}"
		name="objProducto.unidadMedida"  value="objProducto.unidadMedida" />
                           
                        </div>
                      </div>  
                      <div class='form-group'>
                        <label class='col-md-2 control-label' for='inputSelect'>Tipo</label>
                        <div class='col-md-5'>
                        <s:select     cssClass="form-control"
		list="#{'Perecible':'Perecible', 'No Perecible':'No Perecible', 'Insumo':'Insumo'}"
		name="objProducto.tipo_articulo"  value="objProducto.tipo_articulo" />
                           
                        </div>
                      </div>
                      <div class='form-group' style='display:none' >
                        <label class='col-md-2 control-label' for='inputSelect' style='display:none'  >Estado</label>
                        <div class='col-md-5' style='display:none'  > 
		
		 <s:select   cssStyle="display:none"   cssClass="form-control"
		list="#{'Habilitado':'Habilitado', 'Desabilitado':'Desabilitado'}"
		name="objProducto.habilitado"  value="objProducto.habilitado" />
                           
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
							<a class='btn'   href="mainArticulo">Listar</a>

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
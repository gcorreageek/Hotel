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
<hr class='hr-normal'>

<div class='row'>
                <div class='col-sm-12'>
                  <div class='box'>
                    <div class='box-header blue-background'>
                      <div class='title'>
                        <div class='icon-edit'></div>
                        <c:if test="${requestScope.objCargo.cod_cargo!=null}"   >
						Modificar Cargo
						</c:if>
						<c:if test="${requestScope.objCargo.cod_cargo==null}"   >
						Nuevo Cargo  
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
                              <form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="actuarCargo" accept-charset="UTF-8">
                              <s:hidden  name="objCargo.cod_cargo"      /> 
                              
                              <div class='form-group'> 
                        <label class='col-md-2 control-label' for='cboArea'>Area*</label>
                        <div class='col-md-5'>
                          <s:select name="objCargo.cod_area"  list="lstArea" listValue="desc_area" listKey="cod_area" id="cboArea"  cssClass="form-control" /> 
                        </div>
                      </div>    
                              <div class='form-group'> 
                        <label class='col-md-2 control-label' for='inputDesc_cargo'>Cargo</label>
                        <div class='col-md-5'>
                          <s:textfield id="inputDesc_cargo"  name="objCargo.desc_cargo" placeholder="Cargo"  cssClass="form-control" ></s:textfield>
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
							<a class='btn'   href="mainCargo">Listar</a>

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
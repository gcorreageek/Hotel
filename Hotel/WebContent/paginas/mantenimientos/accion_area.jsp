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

<!-- <form class="form-horizontal" action="actuarArea" method="post"  > -->
<%-- <s:hidden  name="objArea.cod_area"      /> --%>
<!-- <div class="control-group"> -->
<!-- <label class="control-label" for="inputNombre">Area</label> -->
<!-- <div class="controls"> -->

<%-- <s:textfield id="inputDesc_area"  name="objArea.desc_area" placeholder="Area"  ></s:textfield>  --%>
<!-- </div> -->
<!-- </div>  -->
 
<!-- <div class="control-group">  -->
<!-- <div class="controls"> -->
<!-- <button class="btn btn-primary">Guardar</button> -->
<!-- <a class="btn btn-primary"  href="mainArea">Listar</a> -->
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
                        <c:if test="${requestScope.objArea.cod_area!=null}"   >
						Modificar Area  
						</c:if>
						<c:if test="${requestScope.objArea.cod_area==null}"   >
						Nuevo Area 
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
                              <form class="form form-horizontal" style="margin-bottom: 0;" method="post" action="actuarArea" accept-charset="UTF-8">
                              <s:hidden  name="objArea.cod_area"      />
                              <div class='form-group'>
                        <s:hidden  name="objProducto.cod_articulo"      />
                        <label class='col-md-2 control-label' for='inputText1'>Area</label>
                        <div class='col-md-5'>
                          <s:textfield id="inputDesc_area"  name="objArea.desc_area" placeholder="Area"  cssClass="form-control"  ></s:textfield>
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
                            
							<a class='btn'   href="mainArea">Listar</a>

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
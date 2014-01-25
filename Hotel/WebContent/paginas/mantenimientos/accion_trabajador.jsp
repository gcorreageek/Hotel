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
var nuevo =0;
$(document).ready(function() {  
	
	 $("#cod_area").change(function () { 
		  $("select#cod_area option:selected").each(function () {  
			  $.post("cboCargo",{"objUsuario.cod_area":$(this).val(),"objUsuario.cod_cargo":$("#inputCargo").val()},function(data){
			 	$("#cboCargo").html(data);
			  }); 
    	  }); 
		}).trigger('change'); 
	 $("#btnGuardar").click(function(){
// 		 objUsuario.cod_trabajador nom_trabajador correo_trabajador cod_area cod_cargo usu_trabajador habilitado
		 var cod_trabajador=$("#cod_trabajador").val();
		 var nom_trabajador=$("#nom_trabajador").val();
		 var correo_trabajador=$("#correo_trabajador").val();
		 var cod_area=$("#cod_area").val();
		 var cod_cargo=$("#cod_cargo").val();
		 var usu_trabajador=$("#usu_trabajador").val();
		 var pass_trabajador=$("#pass_trabajador").val();
		 var habilitado=$("#habilitado").val(); 
		 $.post("actuarTrabajador",{
			 "objUsuario.cod_trabajador":cod_trabajador,
			 "objUsuario.nom_trabajador":nom_trabajador,
			 "objUsuario.correo_trabajador":correo_trabajador,
			 "objUsuario.cod_area":cod_area,
			 "objUsuario.cod_cargo":cod_cargo,
			 "objUsuario.usu_trabajador":usu_trabajador,
			 "objUsuario.pass_trabajador":pass_trabajador,
			 "objUsuario.habilitado":habilitado
		 },function(data){
			 	$("#divMensaje").html(data);
// 			 	setTimeout(function(){ $('.alert').hide(1000); }, 3000); 
		 }); 
	 });
	 $("#btnModificaPass").click(function(){
		 var cod_trabajador=$("#cod_trabajador").val();
		 var pass_trabajador=$("#pass_trabajador").val();
		 $.post("actualizaPassTrabajador",{
			 "objUsuario.cod_trabajador":cod_trabajador, 
			 "objUsuario.pass_trabajador":pass_trabajador
		 },function(data){
			 	$("#divMensaje").html(data);
			 	setTimeout(function(){ $('.alert').hide(1000); }, 3000); 
		 }); 
	 });
	 
// 	 $(function () { $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); } );
}); 
</script>
</head>
<body>



<div id="divMensaje">
</div>
 


<hr class='hr-normal'>

<div class='row'>
                <div class='col-sm-12'>
                  <div class='box'>
                    <div class='box-header blue-background'>
                      <div class='title'>
                        <div class='icon-edit'></div>
                        <c:if test="${requestScope.objUsuario.cod_trabajador!=null}"   >
						Modificar Trabajador 
						<script type="text/javascript">
						nuevo=1;
						</script>
						 
						</c:if>
						<c:if test="${requestScope.objUsuario.cod_trabajador==null}"   >
						Nuevo Trabajador
						<script type="text/javascript">
						nuevo=2;
						
					
						</script>
						</c:if>
                      </div>
                      <script type="text/javascript">
						alert('hola:'+nuevo);
						
					
						</script>
                      <div class='actions'>
                        <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
                        </a>
                        
                        <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
                        </a>
                      </div>
                    </div>
                    <div class='box-content'>
                              <div class="form form-horizontal" style="margin-bottom: 0;"  >
                              <s:hidden  name="objUsuario.cod_trabajador" id="cod_trabajador"     />
                      <div class='form-group'>
                        <s:hidden  name="objProducto.cod_articulo"      />
                        <label class='col-md-2 control-label' for='nom_trabajador'>Nombre*</label>
                        <div class='col-md-5'>
                           <s:textfield id="nom_trabajador"  name="objUsuario.nom_trabajador" placeholder="Nombre"  cssClass="form-control" ></s:textfield>
                        </div>
                      </div>   
                      <div class='form-group'>
                        <label class='col-md-2 control-label' for='correo_trabajador'>Correo*</label>
                        <div class='col-md-5'>
                          <input type="email" class="form-control"  id="correo_trabajador" value="${objUsuario.correo_trabajador}" name="objUsuario.correo_trabajador" placeholder="Correo"  required>
                        </div>
                      </div>   
                      <div class='form-group'>
                        <label class='col-md-2 control-label' for='cod_area'>Area*</label>
                        <div class='col-md-5'>
                          <s:select name="objUsuario.cod_area"  list="lstArea" listValue="desc_area" cssClass="form-control" listKey="cod_area" id="cod_area" /> 
                        </div>
                      </div>   
                      <div class='form-group'>
                        <label class='col-md-2 control-label' for='inputCargo'>Cargo*</label>
                        <div class='col-md-5'>
                        <div id="cboCargo">
                          <s:hidden  name="objUsuario.cod_cargo" id="inputCargo"     />
                        </div>  
                        </div>
                      </div>   
                       <div class='form-group'>
                        <label class='col-md-2 control-label' for='usu_trabajador'>Usuario*</label>
                        <div class='col-md-5'> 
                         <s:textfield id="usu_trabajador"  name="objUsuario.usu_trabajador" placeholder="Usuario" cssClass="form-control"  ></s:textfield>
                        </div>
                      </div>  
                       <div class='form-group'>
                        <label class='col-md-2 control-label' for='pass_trabajador'>Contraseña*</label>
                        <div class='col-md-5'>
                        <s:password id="pass_trabajador"  name="objUsuario.pass_trabajador" placeholder="Contraseña" cssClass="form-control"  ></s:password> 
                        </div>
                        
                        <c:if test="${requestScope.objUsuario.cod_trabajador!=null}"   >
                        <div class='col-md-2'>
						<a class="btn btn-primary"  href="javascript:void(0)"  id="btnModificaPass"  >Modificar Contraseña</a>
						</div>
						</c:if>
					</div> 
						

					  <div class='form-group'>
                        <label class='col-md-2 control-label' for='habilitado'>Habilitar</label>
                        <div class='col-md-5'> 
                         <s:select 
						 	list="#{'Habilitado':'Habilitado', 'Desabilitado':'Desabilitado'}" cssClass="form-control" 
						 	name="objUsuario.habilitado"  
						 	id="habilitado" /> 
                        </div>
                      </div>
                        
                       
                       
                      
                     
                      <hr class='hr-normal'>
                       
                       
                      <div class='form-actions form-actions-padding-sm'>
                        <div class='row'>
                          <div class='col-md-10 col-md-offset-2'>
                            <button class="btn btn-primary" id="btnGuardar"  >Guardar</button>
							<a class="btn btn-primary"  href="mainTrabajador">Listar</a>

                          </div>
                        </div>
                      </div>
                      </div>
              
                    </div>
                  </div>
                </div>
              </div>

 
 



</body>
</html>
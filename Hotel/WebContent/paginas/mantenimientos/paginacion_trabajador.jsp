<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %>
 
<hr class='hr-normal'> 
    
     
		<div id="divTablaPag">
		<div class='row'>
                <div class='col-sm-12'>
                  <div class='box bordered-box blue-border' style='margin-bottom:0;'>
                  
                  
                    <div class='box-header blue-background'>
                      <div class='title'>Trabajador</div>
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
                                  <th>Nombre</th>
					                <th>Correo</th>
					                <th>Area</th>   
					                <th>Cargo</th> 
					                <th>Usuario</th> 
					                <th>Habilitaci&oacute;n</th>
				                  <th></th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:if test="${requestScope.lstUsuario!=null}"   >
							     <c:forEach  items="${requestScope.lstUsuario}"  var="row"  >
							     	 <tr>  
					                  <td>${row.nom_trabajador}</td> 
					                  <td>${row.correo_trabajador}</td>
					                  <td>${row.desc_area}</td> 
					                  <td>${row.desc_cargo}</td> 
					                  <td>${row.usu_trabajador}</td> 
					                  <td>${row.habilitado}</td> 
					                  <td>
					                  <div class='text-right'>
	                                    <a class='btn btn-success btn-xs' href='accionTrabajador?codUsuario=${row.cod_trabajador}'>
	                                      <i class='icon-ok'></i>
	                                    </a>
	                                    <a class='btn btn-danger btn-xs' href='eliminarTrabajador?codUsuario=${row.cod_trabajador}'>
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
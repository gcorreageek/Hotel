<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %>
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
<%-- 					                  <td><a href="accionArticulo?codProd=${row.cod_articulo}">[Modificar]</a></td> --%>
<%-- 					                  <td><a href="eliminarArticulo?codProd=${row.cod_articulo}">[Eliminar]</a></td> --%>
<%-- 					                  <td><a href="#myImagenQr" onclick="javascript:generarQR(${row.cod_articulo});">[Ver QR]</a></td> --%>
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
              
               
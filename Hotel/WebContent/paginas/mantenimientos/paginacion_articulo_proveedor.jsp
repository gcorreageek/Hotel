<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %>
 
<div class='row'>
                <div class='col-sm-12'>
                  <div class='box bordered-box blue-border' style='margin-bottom:0;'>
                  
                  
                    <div class='box-header blue-background'>
                      <div class='title'>Articulo Proveedor</div>
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
                                  <th>Producto</th>
				                  <th>U.Medida</th>
				                  <th>Proveedor</th>
				                  <th></th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:if test="${requestScope.lstProductoProveedor!=null}"   >
							     <c:forEach  items="${requestScope.lstProductoProveedor}"  var="row"  >
							     	 <tr> 
					                  <td>${row.desc_articulo}</td>
					                  <td>${row.unidadMedida}</td>
					                  <td>${row.raz_social}</td> 
					                  <td>
					                  <div class='text-right'>
	                                    <a class='btn btn-success btn-xs' href='accionArticuloProveedor?codProdProvee=${row.cod_articulo_proveedor}'>
	                                      <i class='icon-ok'></i>
	                                    </a>
	                                    <a class='btn btn-danger btn-xs' href='eliminarArticuloProveedor?codProdProvee=${row.cod_articulo_proveedor}'>
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


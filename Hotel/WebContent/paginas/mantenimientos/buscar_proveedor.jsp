<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %>

					<div class='responsive-table'>
                        <div class='scrollable-area'>
                          <table class='table' style='margin-bottom:0;'>
                            <thead>
                              <tr>
                                  <th>Proveedor</th> 
				                  <th>Ruc</th>
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
					                  <td>${row.correo}</td> 
					                  <td align="center"><button onclick="javascript:seleccionaProvee(${row.cod_proveedor},'${row.raz_social}');" type="button" class="btn btn-link" data-dismiss="modal" aria-hidden="true">[S]</button></td> 
					                </tr>
							     </c:forEach> 
						      </c:if>  
                            </tbody>
                          </table>
                        </div>
                      </div>  
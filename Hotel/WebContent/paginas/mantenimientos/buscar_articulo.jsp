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
                                  <th>Articulo</th> 
				                  <th>U.Medida</th>  
				                  <th></th>
                              </tr>
                            </thead>
                            <tbody>  
						      <c:if test="${requestScope.lstProducto!=null}"   > 
							     <c:forEach  items="${requestScope.lstProducto}"  var="row"  >
							     	<tr> 
					                  <td>${row.desc_articulo}</td> 
					                  <td>${row.unidadMedida}</td> 
					                  <td align="center"><button onclick="javascript:seleccionaProd(${row.cod_articulo},'${row.desc_articulo}','${row.unidadMedida}')" type="button" class="btn btn-link" data-dismiss="modal" aria-hidden="true">[S]</button></td> 
					                </tr>
							     </c:forEach> 
						      </c:if>  
                            </tbody>
                          </table>
                        </div>
                      </div>
                      
<!--                     </div> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
            

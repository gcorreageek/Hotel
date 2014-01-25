<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %>
			<table class="table table-striped table-bordered table-hover">
              <thead>
                <tr> 
                  <th>Responsable</th>
                  <c:if test="${requestScope.codProd==null}"   >
                  <th>Area</th>
                  </c:if>  
                  <th>Fecha</th> 
                  <c:if test="${requestScope.codProd==null}"   >
                  <th>T.Pedido</th>
                  </c:if>
                  <c:if test="${requestScope.codProd==null}"   >
                  <th>Seleccionar</th>
                  </c:if>
                  <c:if test="${requestScope.codProd!=null}"   >
                  <th>Sel.</th>
                  </c:if>
                </tr>
              </thead>
              <tbody>
              <c:if test="${requestScope.lstPedido!=null}"   > 
			     <c:forEach  items="${requestScope.lstPedido}"  var="row"  >
			     	<tr> 
	                  <td>${row.nom_trabajador}</td> 
	                  <c:if test="${requestScope.codProd==null}"   >
	                  <td>${row.desc_area}</td> 
	                  </c:if>
	                  <td>${row.fechaRegistro_solicitud_requerimiento}</td> 
	                  <c:if test="${requestScope.codProd==null}"   >
	                  <td>${row.tipo_solicitud_requerimiento}</td> 
	                  </c:if> 
	                  <td align="center"><button onclick="javascript:seleccionaPedido(${row.cod_solicitudRequerimiento},'${row.nom_trabajador}','${row.desc_area}','${row.desc_cargo}','${row.fechaRegistro_solicitud_requerimiento}','${row.fechaDevolucion_solicitud_requerimiento}','${row.fechaEntrega_solicitud_requerimiento}','${row.tipo_solicitud_requerimiento}','${row.comentario_solicitud_requerimiento}')" type="button" class="btn btn-link" data-dismiss="modal" aria-hidden="true">[S]</button></td>
	                  
	                   
	                </tr>
			     </c:forEach> 
		      </c:if>   
              </tbody>
            </table>
            


<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib   prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="s"  uri="/struts-tags" %> 

<table class="table table-striped table-bordered table-hover">
      <thead>
        <tr> 
          <th>Producto</th>  
          <th>U.Medida</th>
          <th>Cantidad</th> 
          <th>Stock</th>
        </tr>
      </thead>
      <tbody>
      		  <c:if test="${requestScope.lstDetPed!=null}"   > 
			     <c:forEach  items="${requestScope.lstDetPed}"  var="row"  >
			     	<tr> 
	                  <td>${row.desc_articulo}</td> 
	                  <td>${row.unidadMedida}</td> 
	                  <td>${row.cantidad}</td> 
	                  <td>${row.stock_articulo}</td> 
	                </tr>
			     </c:forEach> 
		      </c:if>  
      </tbody>
    </table>
 
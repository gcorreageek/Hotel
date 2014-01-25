<%@ page language="java" contentType="text/html" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html"  import="com.hotel.seguridad.bean.MenuDTO"%>
<%@ page language="java" contentType="text/html"  import="com.hotel.seguridad.bean.TrabajadorDTO"%>
<%@ taglib   prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
 

   <%! List<MenuDTO>  lista=null; %>
   <%!TrabajadorDTO  detalleUsuario=null;%>
   <%
   
   	   detalleUsuario = (TrabajadorDTO)session.getAttribute("objUsuario");
	   lista=(List<MenuDTO>)session.getAttribute("listaMenu");
   %>

  <body class='contrast-red '>
    <header>
      <nav class='navbar navbar-default'>
        <a class='navbar-brand' href='#'>
<%--           <img width="81" height="21" class="logo" alt="Flatty" src="<%=request.getContextPath()%>/assets/flatty/assets/images/logo.svg" /> --%>
<%--           <img width="21" height="21" class="logo-xs" alt="Flatty" src="<%=request.getContextPath()%>/assets/flatty/assets/images/logo_xs.svg" /> --%>
          <img  width="21px" height="21px" src="<%=request.getContextPath()%>/assets/mio/images/hotel.png" class="img-polaroid">&nbsp;&nbsp;Hotel
        </a>
        <a class='toggle-nav btn pull-left' href='#'>
          <i class='icon-reorder'></i>
        </a>
        <ul class='nav'>
          <li class='dropdown light only-icon'>
            <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
              <i class='icon-cog'></i>
            </a>
            <ul class='dropdown-menu color-settings'>
<!--               <li class='color-settings-body-color'> -->
<!--                 <div class='color-title'>Change body color</div> -->
<%--                 <a data-change-to='<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/light-theme.css' href='#'> --%>
<!--                   Light -->
<%--                   <small>(default)</small> --%>
<!--                 </a> -->
<%--                 <a data-change-to='<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/dark-theme.css' href='#'> --%>
<!--                   Dark -->
<!--                 </a> -->
<%--                 <a data-change-to='<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/dark-blue-theme.css' href='#'> --%>
<!--                   Dark blue -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class='divider'></li> -->
						<li class='color-settings-contrast-color'>
							<div class='color-title'>Change contrast color</div> <!--                             <a data-change-to="contrast-red" href="#"><i class='icon-cog text-red'></i> -->
							<!--                 Red --> <!--                 <small>(default)</small> -->
							<!--                 </a> --> <a data-change-to="contrast-blue"
							href="#"><i class='icon-cog text-blue'></i> Blue </a> <a
							data-change-to="contrast-orange" href="#"><i
								class='icon-cog text-orange'></i> Orange </a> <a
							data-change-to="contrast-purple" href="#"><i
								class='icon-cog text-purple'></i> Purple </a> <a
							data-change-to="contrast-green" href="#"><i
								class='icon-cog text-green'></i> Green </a> <a
							data-change-to="contrast-muted" href="#"><i
								class='icon-cog text-muted'></i> Muted </a> <a
							data-change-to="contrast-fb" href="#"><i
								class='icon-cog text-fb'></i> Facebook </a> <a
							data-change-to="contrast-dark" href="#"><i
								class='icon-cog text-dark'></i> Dark </a> <a
							data-change-to="contrast-pink" href="#"><i
								class='icon-cog text-pink'></i> Pink </a> <a
							data-change-to="contrast-grass-green" href="#"><i
								class='icon-cog text-grass-green'></i> Grass green </a> <a
							data-change-to="contrast-sea-blue" href="#"><i
								class='icon-cog text-sea-blue'></i> Sea blue <small>(default)</small>
						</a> <a data-change-to="contrast-banana" href="#"><i
								class='icon-cog text-banana'></i> Banana </a> <a
							data-change-to="contrast-dark-orange" href="#"><i
								class='icon-cog text-dark-orange'></i> Dark orange </a> <a
							data-change-to="contrast-brown" href="#"><i
								class='icon-cog text-brown'></i> Brown </a>

						</li>
					</ul>
          </li>
<!--           <li class='dropdown medium only-icon widget'> -->
<!--             <a class='dropdown-toggle' data-toggle='dropdown' href='#'> -->
<!--               <i class='icon-rss'></i> -->
<!--               <div class='label'>5</div> -->
<!--             </a> -->
<!--             <ul class='dropdown-menu'> -->
<!--               <li> -->
<!--                 <a href='#'> -->
<!--                   <div class='widget-body'> -->
<!--                     <div class='pull-left icon'> -->
<!--                       <i class='icon-user text-success'></i> -->
<!--                     </div> -->
<!--                     <div class='pull-left text'> -->
<!--                       John Doe signed up -->
<%--                       <small class='text-muted'>just now</small> --%>
<!--                     </div> -->
<!--                   </div> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class='divider'></li> -->
<!--               <li> -->
<!--                 <a href='#'> -->
<!--                   <div class='widget-body'> -->
<!--                     <div class='pull-left icon'> -->
<!--                       <i class='icon-inbox text-error'></i> -->
<!--                     </div> -->
<!--                     <div class='pull-left text'> -->
<!--                       New Order #002 -->
<%--                       <small class='text-muted'>3 minutes ago</small> --%>
<!--                     </div> -->
<!--                   </div> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class='divider'></li> -->
<!--               <li> -->
<!--                 <a href='#'> -->
<!--                   <div class='widget-body'> -->
<!--                     <div class='pull-left icon'> -->
<!--                       <i class='icon-comment text-warning'></i> -->
<!--                     </div> -->
<!--                     <div class='pull-left text'> -->
<!--                       America Leannon commented Flatty with veeery long text. -->
<%--                       <small class='text-muted'>1 hour ago</small> --%>
<!--                     </div> -->
<!--                   </div> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class='divider'></li> -->
<!--               <li> -->
<!--                 <a href='#'> -->
<!--                   <div class='widget-body'> -->
<!--                     <div class='pull-left icon'> -->
<!--                       <i class='icon-user text-success'></i> -->
<!--                     </div> -->
<!--                     <div class='pull-left text'> -->
<!--                       Jane Doe signed up -->
<%--                       <small class='text-muted'>last week</small> --%>
<!--                     </div> -->
<!--                   </div> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class='divider'></li> -->
<!--               <li> -->
<!--                 <a href='#'> -->
<!--                   <div class='widget-body'> -->
<!--                     <div class='pull-left icon'> -->
<!--                       <i class='icon-inbox text-error'></i> -->
<!--                     </div> -->
<!--                     <div class='pull-left text'> -->
<!--                       New Order #001 -->
<%--                       <small class='text-muted'>1 year ago</small> --%>
<!--                     </div> -->
<!--                   </div> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class='widget-footer'> -->
<!--                 <a href='#'>All notifications</a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
          <li class='dropdown dark user-menu'>
            <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
              <img width="23" height="23" alt="Mila Kunis" src="<%=request.getContextPath()%>/assets/flatty/assets/images/avatar.jpg" />
              <span class='user-name'><%=detalleUsuario.getNom_trabajador()%></span>
              <b class='caret'></b>
            </a>
            <ul class='dropdown-menu'>
<!--               <li> -->
<!--                 <a href='user_profile.html'> -->
<!--                   <i class='icon-user'></i> -->
<!--                   Profile -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li> -->
<!--                 <a href='user_profile.html'> -->
<!--                   <i class='icon-cog'></i> -->
<!--                   Settings -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class='divider'></li> -->
 


              <li>
                <a class="navbar-link"   href='#'>
                  <i class='icon-signout'></i>
                  Salir
                </a>
              </li>
            </ul>
          </li>
        </ul>
<!--         <form action='#' class='navbar-form navbar-right hidden-xs' method='get'> -->
<!--           <button class='btn btn-link icon-search' name='button' type='submit'></button> -->
<!--           <div class='form-group'> -->
<!--             <input value="" class="form-control" placeholder="Search..." autocomplete="off" id="q_header" name="q" type="text" /> -->
<!--           </div> -->
<!--         </form> -->
      </nav>
    </header>

	<script>
        $(document).on("click", ".navbar-link", function(e) {
        	bootbox.confirm("Desea cerrar la sesion?", function(result) {

					if(result==true){
						document.logout.submit();	
					} 
					else{
						
					}
					
        		}); 
        });
    </script>
    
<!-- <div id="myCotizacion" class="modal fade"  > -->
<!-- <div class="modal-dialog"> -->
<!-- <div class='modal-content'> -->
<!-- <div id="divMostrarMensajeInterno"></div>  -->
<!-- </div>  -->
<!-- </div> -->
<!-- </div> -->

<!-- <div class="modal-dialog"> -->
<!-- <div class='modal-content'> -->
    <form action="logout" name="logout" method="post"  >
 
    </form>
<!--     </div></div> -->


    <div id='wrapper'>
      <div id='main-nav-bg'></div>
      <nav id='main-nav'>
        <div class='navigation'> 
          
          <!-- Menu incio -->
          <ul class='nav nav-stacked'> 
<!--           	<li class='active'> -->
<%--               <a href='index.html'><i class='icon-dashboard'></i><span>Dashboard</span></a> --%>
<!--             </li> -->
            
<!--             <li class=''> -->
<%--               <a class="dropdown-collapse" href="#"><i class='icon-edit'></i><span>Forms</span><i class='icon-angle-down angle-down'></i></a>  --%>
<!--               <ul class='nav nav-stacked'> -->
<!--                 <li class=''> -->
<%--                   <a href='form_styles.html'><i class='icon-caret-right'></i><span>Form styles and features</span></a> --%>
<!--                 </li>    -->
<!--               </ul> -->
<!--             </li> -->
          
               <%
               	detalleUsuario = (TrabajadorDTO)session.getAttribute("objUsuario");
               	   lista=(List<MenuDTO>)session.getAttribute("listaMenu");
               	  if(lista!=null){ 
               	   		for(int i=0;i<lista.size();i++){
               	   			if(lista.get(i).getTipo_menu()==1 && lista.get(i).getMaster_menu()==0){
               %>
			   				<li class=''><a href='<%=lista.get(i).getUrl_menu() %>'><i class='<%=lista.get(i).getIcono_menu()%>'></i><span><%=lista.get(i).getNom_menu()%></span></a></li> 
			   			<% }else if(lista.get(i).getMaster_menu()==1){ 
			   				if( lista.get(i).getTipo_menu()==1 ){ %>
			   				<li class=''>
              				<a class="dropdown-collapse" href="<%=lista.get(i).getUrl_menu() %>"><i class='<%=lista.get(i).getIcono_menu()%>'></i><span><%=lista.get(i).getNom_menu() %></span><i class='icon-angle-down angle-down'></i></a> 
			   				<ul class='nav nav-stacked'>
			   				 
			   				
			   					
			   				<% }else{ %>
			   					<li class=''><a href='<%=request.getContextPath()%>/<%=lista.get(i).getUrl_menu() %>'><i class='icon-caret-right'></i><span><%=lista.get(i).getNom_menu() %></span></a></li> 
			   				 
			   					
			   				<%}
			   				
			   				if(lista.size()==i+1){%>
			   						   </ul>
			              	</li>	
			   					
			   				<% }else{
			   					if(lista.get(i+1).getTipo_menu()==1){ %>
			   					   </ul>
			              	</li>	
			   						
			   					<%}
			   					
			   					
			   				}
			   				
			   				
			   				%> 
			   				
			   				
			   				<%
			   				
			   			}
			   		%>
			   			
			   			
			   		<%} 
			  }%>
            
               
              
              
              
              
              
          </ul>
          <!-- Menu fin -->
          
          
        </div>
      </nav>
      <section id='content'>
        <div class='container'>
          <div class='row' id='content-wrapper'>
            <div class='col-xs-12'>
 
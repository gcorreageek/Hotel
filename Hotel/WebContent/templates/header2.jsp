<!DOCTYPE html>
<html>
  

<head>
    <title>Hotel</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta content='text/html;charset=utf-8' http-equiv='content-type'>
    <meta content='Flat administration template for Twitter Bootstrap. Twitter Bootstrap 3 template with Ruby on Rails support.' name='description'>
    

 	
    
    <link href='<%=request.getContextPath()%>/assets/mio/images/hotel.ico' rel='shortcut icon' type='image/x-icon'>
    <link href='<%=request.getContextPath()%>/assets/flatty/assets/images/meta_icons/apple-touch-icon.png' rel='apple-touch-icon-precomposed'>
    <link href='<%=request.getContextPath()%>/assets/flatty/assets/images/meta_icons/apple-touch-icon-57x57.png' rel='apple-touch-icon-precomposed' sizes='57x57'>
    <link href='<%=request.getContextPath()%>/assets/flatty/assets/images/meta_icons/apple-touch-icon-72x72.png' rel='apple-touch-icon-precomposed' sizes='72x72'>
    <link href='<%=request.getContextPath()%>/assets/flatty/assets/images/meta_icons/apple-touch-icon-114x114.png' rel='apple-touch-icon-precomposed' sizes='114x114'>
    <link href='<%=request.getContextPath()%>/assets/flatty/assets/images/meta_icons/apple-touch-icon-144x144.png' rel='apple-touch-icon-precomposed' sizes='144x144'>
    <!-- / START - page related stylesheets [optional] -->
    <link href="<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.css" media="all" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/plugins/fullcalendar/fullcalendar.css" media="all" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/plugins/common/bootstrap-wysihtml5.css" media="all" rel="stylesheet" type="text/css" />
    <!-- / END - page related stylesheets [optional] -->
    <!-- / bootstrap [required] -->
    <link href="<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/bootstrap/bootstrap.css" media="all" rel="stylesheet" type="text/css" />
    <!-- / theme file [required] -->
    <link href="<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/light-theme.css" media="all" id="color-settings-body-color" rel="stylesheet" type="text/css" />
    <!-- / coloring file [optional] (if you are going to use custom contrast color) -->
    <link href="<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/theme-colors.css" media="all" rel="stylesheet" type="text/css" />
    <!-- / demo file [not required!] -->
    <link href="<%=request.getContextPath()%>/assets/flatty/assets/stylesheets/demo.css" media="all" rel="stylesheet" type="text/css" />
    <!--[if lt IE 9]>
      <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/ie/html5shiv.js" type="text/javascript"></script>
      <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/ie/respond.min.js" type="text/javascript"></script>
    <![endif]-->
        <!-- / jquery [required] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/jquery/jquery.min.js" type="text/javascript"></script>
    <!-- / jquery mobile (for touch events) -->
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/jquery/jquery.mobile.custom.min.js" type="text/javascript"></script> --%>
    <!-- / jquery migrate (for compatibility with new jquery) [required] -->
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/jquery/jquery-migrate.min.js" type="text/javascript"></script> --%>
    <!-- / jquery ui -->
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/jquery/jquery-ui.min.js" type="text/javascript"></script> --%>
    <!-- / jQuery UI Touch Punch -->
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/jquery_ui_touch_punch/jquery.ui.touch-punch.min.js" type="text/javascript"></script> --%>
    <!-- / bootstrap [required] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/bootstrap/bootstrap.js" type="text/javascript"></script>
    <!-- / modernizr -->
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/modernizr/modernizr.min.js" type="text/javascript"></script> --%>
    <!-- / retina -->
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/retina/retina.js" type="text/javascript"></script> --%>
    <!-- / theme file [required] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/theme.js" type="text/javascript"></script>
    <!-- / demo file [not required!] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/demo.js" type="text/javascript"></script>
    <!-- / START - page related files and scripts [optional] -->
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/flot/excanvas.js" type="text/javascript"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/flot/flot.min.js" type="text/javascript"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/flot/flot.resize.js" type="text/javascript"></script> --%>
     
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/common/moment.min.js" type="text/javascript"></script> --%>
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/bootstrap_daterangepicker/bootstrap-daterangepicker.js" type="text/javascript"></script>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/bootbox/bootbox.min.js" type="text/javascript"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/timeago/jquery.timeago.js" type="text/javascript"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/common/wysihtml5.min.js" type="text/javascript"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/common/bootstrap-wysihtml5.js" type="text/javascript"></script> --%>
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<%--     <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/bootbox/bootbox.min.js" type="text/javascript"></script> --%>
    
     
    
     
    <!-- / END - page related files and scripts [optional] -->
    <link href="<%=request.getContextPath()%>/assets/mio/css/bootstrap-combined.css" rel="stylesheet">
<%--     <link href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css" rel="stylesheet"> --%>
<%--     <link href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"> --%>
    <link href="<%=request.getContextPath()%>/assets/bootstrap/css/datepicker.css" rel="stylesheet"> 
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap-datepicker.es.js" charset="UTF-8"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap-paginator.min.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/bootstrap/js/bootbox.js"></script>
<%--  	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/bootstrap/js/jqBootstrapValidation.js"></script> --%>
 	
 	  <style>

	  ul
{ 
list-style-type: none; 
}

  ul li { display: inline; }

  ul li a
{
text-decoration: none; 
}
      .letras {
      font-family: 'Kaushan Script', cursive;
      font-size: 200px;
      }
  </style>  
 	<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
 	 	<script type="text/javascript"  >

		$(document).ready(function() {   
		    $('.datepicker').datepicker({
		    	format: 'dd/mm/yyyy',
		        language: 'es' 
			});
// 		    idFechaEntrega idFechaDevolucion
	        // disabling dates
		    
		 
		});  
	</script>
 	
 	
  </head>
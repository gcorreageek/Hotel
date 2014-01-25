<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-jquery-tags"  prefix="sj"%>
<%@taglib uri="/struts-tags" prefix="s" %>
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
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/jquery/jquery.mobile.custom.min.js" type="text/javascript"></script>
    <!-- / jquery migrate (for compatibility with new jquery) [required] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/jquery/jquery-migrate.min.js" type="text/javascript"></script>
    <!-- / jquery ui -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/jquery/jquery-ui.min.js" type="text/javascript"></script>
    <!-- / jQuery UI Touch Punch -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/jquery_ui_touch_punch/jquery.ui.touch-punch.min.js" type="text/javascript"></script>
    <!-- / bootstrap [required] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/bootstrap/bootstrap.js" type="text/javascript"></script>
    <!-- / modernizr -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/modernizr/modernizr.min.js" type="text/javascript"></script>
    <!-- / retina -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/retina/retina.js" type="text/javascript"></script>
    <!-- / theme file [required] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/theme.js" type="text/javascript"></script>
    <!-- / demo file [not required!] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/demo.js" type="text/javascript"></script>
    <!-- / START - page related files and scripts [optional] -->
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/flatty/assets/javascripts/plugins/validate/additional-methods.js" type="text/javascript"></script>
    <!-- / END - page related files and scripts [optional] -->
    
  </head>
  <body class='contrast-red login contrast-background'>
    <div class='middle-container'>
      <div class='middle-row'>
        <div class='middle-wrapper'>
          <div class='login-container-header'>
            <div class='container'>
              <div class='row'>
                <div class='col-sm-12'>
                  <div class='text-center'> 
                     <p class="title" >
                     <span>
                      <img  width="31px" height="31px" src="<%=request.getContextPath()%>/assets/mio/images/hotel.png" class="img-polaroid">&nbsp;&nbsp;Hotel 
                     </span>
                    </p>
 
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class='login-container'>
            <div class='container'>
              <div class='row'>
                <div class='col-sm-4 col-sm-offset-4'>
                  <h1 class='text-center title'>Inicie sesi&oacute;n</h1>
                  <form action='login' class='validate-form' method='post'>
                   
                  
                    <div class='form-group'>
                      <div class='controls with-icon-over-input'>
                        <input   placeholder="Usuario" class="form-control" data-rule-required="true" name="objUsu.usu_trabajador" type="text" />
                        <i class='icon-user text-muted'></i>
                      </div>
                    </div>
                    <div class='form-group'>
                      <div class='controls with-icon-over-input'>
                        <input   placeholder="Password" class="form-control" data-rule-required="true" name="objUsu.pass_trabajador" type="password" />
                        <i class='icon-lock text-muted'></i>
                      </div>
                    </div>
                    <div class='checkbox'>
                      <label for='remember_me'>
                        <input id='remember_me' name='remember_me' type='checkbox' value='1'>
                         Record&aacute;rmelo
                      </label>
                    </div>
                    <p align="center" class="text-error">${requestScope.mensaje}</p>
                    <button class='btn btn-block'>Ingresar</button>
                  </form>
                  <div class='text-center'>
                    <hr class='hr-normal'>
<!--                     <a href='forgot_password.html'>Forgot your password?</a> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class='login-container-footer'>
            <div class='container'>
              <div class='row'>
                <div class='col-sm-12'>
                  <div class='text-center'>
<!--                     <a href='sign_up.html'><i class='icon-user'></i>New to Flatty?<strong>Sign up</strong></a> -->
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

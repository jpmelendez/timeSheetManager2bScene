<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<title>2bScene Time Sheet Manager - Login</title>
	<!-- Core Style -->
	<link type="text/css" rel="stylesheet" href="/timeSheetManager2bScene/html/css/style-2bsceneAll.css">
	

</head>

<div id="topsection">
		<div class="nav-innertube">
			<div class="header-logo">
		
			<img class="logo" src="/timeSheetManager2bScene/html/img/2BS_SmallLogoYellow.png" alt="2bScene logo" title="2bscene">
		
			</div>
		</div>
</div>
<body class="woodbg">
<s:if test="hasActionMessages()">
   <div class="dataSaved">
   	  <span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
      <s:actionmessage/>
   </div>
</s:if>
<s:if test="hasActionErrors()">
   <div class="errors">
   	  <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
      <s:actionerror/>
   </div>
</s:if>

	<div class="smart-wrap">
	
    	<div class="smart-forms smart-container wrap-3">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-sign-in"></i>Time Sheet - Login</h4>
          </div><!-- end .form-header section -->
            
           <s:form namespace="/jsp" action="loginUser" method="post">
            	<div class="form-body theme-yellow">
                    
                    <div class="spacer-t30 spacer-b30">
                    	<div class="tagline"><span> Login </span></div><!-- .tagline -->
                    </div>
                    
                    <div class="section">
                        <label class="field">
                            <input type="text" name="id" id="id" class="gui-input" placeholder="Enter username">
                            <span class="field-icon"><i class="fa fa-user"></i></span>  
                        </label>
                    </div><!-- end section -->                    
                    
                	<div class="section">
                    	<label class="field">
                        	<input type="password" name="pwd" id="pwd" class="gui-input" placeholder="Enter password">
                            <span class="field-icon"><i class="fa fa-lock"></i></span>  
                        </label>
                    </div><!-- end section -->  
                    
                	<!-- <div class="section">
                        <label class="switch switch-yellow block">
                            <input type="checkbox" name="remember" id="remember" checked>
                            <span class="switch-label" for="remember" data-on="YES" data-off="NO"></span> 
                            <span> Keep me logged in ?</span>
                        </label> 
                    </div> --><!-- end section -->                                                             
                    
                </div><!-- end .form-body section -->
                <div class="form-footer">
                	<button type="submit" class="button btn-yellow">Login</button>
                </div><!-- end .form-footer section -->
            </s:form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->

</body>
    
</html>
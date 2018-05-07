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
	<style type="text/css">
.dataSaved {
	background-color:#DDFFDD;
	border:1px solid #009900;
	width:100%;
}
.dataSaved li{ 
	list-style: none; 
}
.dataErrors {
	background-color:#FFCCCC;
	border:1px solid #CC0000;
	width:100%;
	margin-bottom:8px;
}
.dataErrors li{ 
	list-style: none; 
}
</style>

</head>

<body class="woodbg">
<s:if test="hasActionMessages()">
   <div class="alert" style="padding: 20px;background-color: #4CAF50; /* green */color: white;margin-bottom: 15px">
  	<span class="closebtn" style="margin-left: 15px;color: white;font-weight: bold;float: right;font-size: 22px;line-height: 20px;cursor: pointer;transition: 0.3s;" onclick="this.parentElement.style.display='none';">&times;</span> 
  	<s:actionmessage/>
   </div>
</s:if>
<s:if test="hasActionErrors()">
   <div class="alert" style="padding: 20px;background-color: #f44336; /* Red */color: white;margin-bottom: 15px">
  		<span class="closebtn" style="margin-left: 15px;color: white;font-weight: bold;float: right;font-size: 22px;line-height: 20px;cursor: pointer;transition: 0.3s;" onclick="this.parentElement.style.display='none';">&times;</span> 
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
                        <label class="field prepend-icon">
                            <input type="text" name="id" id="id" class="gui-input" placeholder="Enter username">
                             
                        </label>
                    </div><!-- end section -->                    
                    
                	<div class="section">
                    	<label class="field prepend-icon">
                        	<input type="password" name="pwd" id="pwd" class="gui-input" placeholder="Enter password">
                            
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
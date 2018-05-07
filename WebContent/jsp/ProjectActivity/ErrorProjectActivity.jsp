<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

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
    	<div class="smart-forms smart-container wrap-2">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-pencil-square"></i>Add Project Tasks</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="editListPTasks" id="smart-form">
            <s:hidden name="selectedProjectID" value="%{selectedProjectID}"></s:hidden>
            <s:hidden name="selectedProjetName" value="%{selectedProjetName}"></s:hidden>
           
            <s:set var="countAct" value="0" />
            
            	<div class="form-body">
            	
            	 	
                    <div class="section">
                            <h3>Project: <s:property value="selectedProjetName" /></h3>
                    </div><!-- end section -->   
                    
                    <div class="section">
                            <p class="small-text fine-grey">Please go back and / tick / check the tasks that you need to be done.</p>
                    </div><!-- end section -->  
            		
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<div style="float: right;">
                    <a href="/twobSceneWebApp/projectTasks/addProjectActivity.action?selectedProjectID=<s:property value="selectedProjectID"/>&selectedProjetName=<s:property value="selectedProjetName"/>"class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
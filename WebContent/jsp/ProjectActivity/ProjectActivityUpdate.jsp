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
    	<form method="post" action="addProject" id="smart-form">
    	<s:hidden name="selectedProjectName" value="%{selectedProjectName}"></s:hidden>
        <s:iterator value="activityDetail" var="activityDetail">
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-pencil-square"></i>Activity: <s:property value="selectedActivityName"/></h4>
            </div><!-- end .form-header section -->

            	<div class="form-body">
            	
            	<div class="section">
		  			<label for="names" class="field-label"><strong>Project: </strong> <s:property value="selectedProjectName"/></label>
				</div><!-- end section -->
            	<div class="spacer-b30">
                    	<div class="tagline"><span> Activity Details </span></div><!-- .tagline -->
                    </div>
            	
		         <div class="frm-row">
                        <div class="section colm colm6">
                        	<label class="field-label"><Strong>Activity Status: </Strong><s:property value="paStatus"/></label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        <label class="field-label"><Strong>Activity Priority: </Strong><s:property value="paPriority"/></label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->  
                    
                    <div class="section">
		              <label for="names" class="field-label"><strong>Activity details: </strong> <s:property value="mapActDesc"/></label>
		         </div><!-- end section -->
		         
            		<div class="spacer-t40 spacer-b40">
                    	<div class="tagline"><span> Who & When  </span></div><!-- .tagline -->
                    </div> 
                    
                    <div class="section">
                    <label class="field-label"><Strong>Who should do this activity?: </Strong><s:property value="nameStaffCharge"/></label>
                    </div><!-- end section --> 
                    
                    <div class="frm-row">
                        <div class="section colm colm6">
                        	<label class="field-label"><Strong>Activity start date: </Strong><s:property value="startDate"/></label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                       <label class="field-label"><Strong>Activity due date: </Strong><s:property value="dueDate"/></label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->                                    
                 	
                 	<div class="section">
                   <label class="field-label"><Strong>Activity estimated time: </Strong><s:property value="estimatedTime"/></label>
                    </div><!-- end section --> 
                	
                             
                     <div class="spacer-t20 spacer-b30">
                    	<div class="tagline"><span> Progress  </span></div><!-- .tagline -->
                    </div>                                         

                    <div class="section">
                    <label class="field-label"><Strong>Progress so far:  </Strong><s:property value="progress"/></label>
                    </div><!-- end section -->    

            	</div><!-- end .form-body section -->
                
               </s:iterator>     
                <div class="form-footer">
                	<a href="/twobSceneWebApp/projectTasks/editMain.action?selectedProjectID=<s:property value="%{selectedProjectID}"/>&selectedProjetName=<s:property value="%{selectedProjetName}"/>" 
                	class="button btn-primary btn-rounded">Keep editing</a> 
                    <div style="float: right;">
                    <!--<a href="#" class="button btn-primary button-left">Overview tasks</a>  -->
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
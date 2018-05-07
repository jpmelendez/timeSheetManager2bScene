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
        <s:iterator value="showProject" var="showProject">
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-pencil-square"></i>Project</h4>
            </div><!-- end .form-header section -->

            	<div class="form-body">
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Project Information </span></div><!-- .tagline -->
                    </div>
                    
                    <div class="section">
                    <label class="field-label"><Strong>Project State: </Strong><s:property value="projectStatus"/></label>
                    </div><!-- end section --> 
                    
                    <div class="frm-row">
                        <div class="section colm colm6">
                        	<label class="field-label"><Strong>Number of units: </Strong><s:property value="numEA"/></label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        <label class="field-label"><Strong>Project Category: </Strong><s:property value="projecCat"/></label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->                                    
                
                	<div class="section">
                	<label class="field-label"><Strong>Project name:  </Strong><s:property value="projectName"/></label>
                    	
                    </div><!-- end section -->
                    
                    <div class="section">
                    <label class="field-label"><Strong>Project description:  </Strong><s:property value="projectDescription"/></label>
                    </div><!-- end section --> 
                    
                    <div class="section">
                    <label class="field-label"><Strong>Short name:  </Strong><s:property value="shortName"/></label>           
                    </div><!-- end section -->
                    
                    <div class="section">
                    <label class="field-label"><Strong>Budget:  </Strong><s:property value="budget"/></label>  
                    	
                    </div><!-- end section -->
                    
                     <div class="spacer-t40 spacer-b40">
                    	<div class="tagline"><span> Schedule Information  </span></div><!-- .tagline -->
                    </div>        
                    
                	
                    <div class="section">
                     <label class="field-label"><Strong>Priority:  </Strong><s:property value="priority"/></label>
                        
                    </div><!-- end section -->                   
                    
                     <div class="section">
                     <label class="field-label"><Strong>Person in charge:  </Strong><s:property value="staffInCharge"/></label>
                      
                    </div><!-- end section -->                        
                    
                    <div class="section">
                        <div class="section colm colm6">
                         <label class="field-label"><Strong>Project time:  </Strong><s:property value="projectTime"/></label>  
                           
                        </div><!-- end section -->
                    </div><!-- end .frm-row section --> 
                    
                   <div class="frm-row">
                        <div class="section colm colm6">
                        <label class="field-label"><Strong>Start date:  </Strong><s:property value="jobStart"/></label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                            <label class="field-label"><Strong>Due date:  </Strong><s:property value="jobDueDate"/></label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->   

                    <div class="section">
                        <div class="section colm colm6">
                            <label class="field-label"><Strong>Spent time:  </Strong><s:property value="spentTime"/></label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section --> 
                             
                     <div class="spacer-t20 spacer-b30">
                    	<div class="tagline"><span> Client Information  </span></div><!-- .tagline -->
                    </div>                                         

                    <div class="section">
                    <label class="field-label"><Strong>Customer:  </Strong><s:property value="client"/></label>
                    </div><!-- end section -->    

            	</div><!-- end .form-body section -->
                
               </s:iterator>     
                <div class="form-footer">
                	<a href="/twobSceneWebApp/project/captureProject.action" class="button btn-primary btn-rounded">+ New Project</a> 
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/project/overviewProjects.action" class="button btn-primary button-left">Overview Projects</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
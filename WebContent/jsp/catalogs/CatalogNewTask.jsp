<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

	 <script type="text/javascript">
	
		$(function() {
		
				/* @custom validation method (smartCaptcha) 
				------------------------------------------------------------------ */
					
				$.validator.methods.smartCaptcha = function(value, element, param) {
						return value == param;
				};
						
				$( "#smart-form" ).validate({
				
						/* @validation states + elements 
						------------------------------------------- */
						
						errorClass: "state-error",
						validClass: "state-success",
						errorElement: "em",
						
						/* @validation rules 
						------------------------------------------ */
							
						rules: {
								taskName: {
										required: true
								},
								taskDescription: {
									required: true,
									maxlength: 399
							},	
								taskPriority: {
										required: true,
										min: 0,
										digits: true
								}																	
					
						},
						
						/* @validation error messages 
						---------------------------------------------- */
							
						messages:{
								taskName: {
										required: 'Enter task name'
								},
								taskDescription: {
										required: 'Enter task description',
										maxlength: 'Enter not more than 400 characters'
										
								},	
								taskPriority: {
									required: 'Enter Order of priority',
									min: 'Must be minimum 1',
									digits: 'Must be only digits (1, 2, 3... 30'
							}																
						 
						},

						/* @validation highlighting + error placement  
						---------------------------------------------------- */	
						
						highlight: function(element, errorClass, validClass) {
								$(element).closest('.field').addClass(errorClass).removeClass(validClass);
						},
						unhighlight: function(element, errorClass, validClass) {
								$(element).closest('.field').removeClass(errorClass).addClass(validClass);
						},
						errorPlacement: function(error, element) {
						   if (element.is(":radio") || element.is(":checkbox")) {
									element.closest('.option-group').after(error);
						   } else {
									error.insertAfter(element.parent());
						   }
						}
								
				});		

				
				/* Reset form 
				-------------------------------------*/
				 $("#formname").resetForm();
		
		});				
    
    </script>
    
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
            	<h4><i class="fa fa-pencil-square"></i>New Task</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="addTasks" id="smart-form">
            <input type="hidden" name="selectedStageID" value=<s:property value="selectedStageID"/> />
            <s:hidden  value="%{selectedStageName}" name="selectedStageName" />
            	<div class="form-body">
            	
            		<div>
            		<h3>Stage: <s:property value="selectedStageName"/></h3>
            		</div>
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Task Information </span></div><!-- .tagline -->
                    </div>
                  
                	<div class="section">
                    	<label for="taskName" class="field prepend-icon">
                        	<input type="text" name="taskName" id="taskName" class="gui-input" placeholder="Task name">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                    <div class="section">
                    	<label for="taskDescription" class="field prepend-icon">
                        	<textarea class="gui-textarea" id="taskDescription" name="taskDescription" placeholder="Task Description"></textarea>
                            <span class="field-icon"><i class="fa fa-comments"></i></span>
                            <span class="input-hint"> 
                            	Length between 1 and 400 characters. Only lower case letters (a-z), numbers, dashes and underscores are allowed. 
                            </span>   
                        </label>
                    </div><!-- end section --> 
                    
                    <div class="section">
                    	<label for="taskPriority" class="field prepend-icon">
                        	<input type="text" name="taskPriority" id="taskPriority" class="gui-input" placeholder="Order Of Priority">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
     
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="addTasks"> Save </button>
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/tasks/tasksIndex.action?selectedStageID=<s:property value="selectedStageID"/>&selectedStageName=<s:property value="selectedStageName"/>" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
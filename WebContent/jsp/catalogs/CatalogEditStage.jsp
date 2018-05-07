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
								stageName: {
										required: true
								},
								stagePriority: {
										required: true
								}																	
					
						},
						
						/* @validation error messages 
						---------------------------------------------- */
							
						messages:{
								stageName: {
										required: 'Enter stage name'
								},
								stagePriority: {
										required: 'Enter stage priority'
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
            	<h4><i class="fa fa-pencil-square"></i>Edit Stage</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="updateStage" id="smart-form">
            <s:hidden  value="%{stageByID.stageID}" name="stageID" />

            	<div class="form-body">
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Stage Information </span></div><!-- .tagline -->
                    </div>
                  
                	<div class="section">
                    	<label for="stageName" class="field prepend-icon">
                        	<input type="text" name="stageName" id="stageName" class="gui-input" placeholder="Stage name" value="<s:property value="stageByID.stageName" />">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                    <div class="section">
                    	<label for="stagePriority" class="field prepend-icon">
                        	<input type="text" name="stagePriority" id="stagePriority" class="gui-input" placeholder="Order Of Priority" value="<s:property value="stageByID.stagePriority" />">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
     
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="updateStage"> Save </button>
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/stages/stagesIndex.action" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
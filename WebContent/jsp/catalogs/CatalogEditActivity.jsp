<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

	 <script type="text/javascript">
	
	 $(document).ready(function(){
		 
				 /* Show/hide alert
					-------------------------------------*/
					 $('#actAlert').change(function(){
						 if(this.checked)
						 $('#alertFields').show();
						 else
						 $('#alertFields').hide();
						 });
			
		
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
							actName: {
										required: true
								},
								actDescription: {
									required: true,
									maxlength: 399
							},	
							actPriority: {
										required: true,
										min: 1,
										digits: true
								},	
							alertDays: {
								required: true,
								min: 1,
								digits: true,
						        depends: function(element) {
						          return $("#actAlert").is(":checked");
						        }
						      },
						      alertTrigger: {
									required: true,
							        depends: function(element) {
							          return $("#actAlert").is(":checked");
							        }
							      },																	
					
						},
						
						/* @validation error messages 
						---------------------------------------------- */
							
						messages:{
							actName: {
										required: 'Enter activity name'
								},
								actDescription: {
										required: 'Enter activity description',
										maxlength: 'Enter not more than 400 characters'
										
								},	
								actPriority: {
									required: 'Enter Order of priority',
									min: 'Must be minimum 1',
									digits: 'Must be only digits (1, 2, 3... 30)'
								},
								alertDays: {
									required: 'Enter number of days for the alert',
									min: 'Must be minimum 1',
									digits: 'Must be only digits (1, 2, 3... 30)'
							      },	
							    alertTrigger: {
										required: 'Select an Alert Timing',
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
            	<h4><i class="fa fa-pencil-square"></i>Edit Activity</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="updateActivity" id="smart-form">
			<s:hidden  value="%{selectedStageID}" name="selectedStageID" />
			<s:hidden  value="%{selectedTaskID}" name="selectedTaskID" />
			<s:hidden  value="%{activityById.actID}" name="actID" />
			<s:hidden  value="%{selectedStageName}" name="selectedStageName" />
			<s:hidden  value="%{selectedTaskName}" name="selectedTaskName" />
			<s:hidden  value="0" name="actChecked" />
            	<div class="form-body">
            		<div>
            		<h3>Stage: <s:property value="selectedStageName"/> > Task: <s:property value="selectedTaskName"/></h3>
            		</div>
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Activity Information </span></div><!-- .tagline -->
                    </div>
                  
                	<div class="section">
                    	<label for="actName" class="field prepend-icon">
                        	<input type="text" name="actName" id="actName" class="gui-input" placeholder="Activity name" value="<s:property value="activityById.actName" />">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                    <div class="section">
                    	<label for="actDescription" class="field prepend-icon">
                        	<textarea class="gui-textarea" id="actDescription" name="actDescription" placeholder="Activity Description"><s:property value="activityById.actDescription" /></textarea>
                            <span class="field-icon"><i class="fa fa-comments"></i></span>
                            <span class="input-hint"> 
                            	Length between 1 and 400 characters. Only lower case letters (a-z), numbers, dashes and underscores are allowed. 
                            </span>   
                        </label>
                    </div><!-- end section --> 
                    
                    <div class="section">
                    	<label for="actPriority" class="field prepend-icon">
                        	<input type="text" name="actPriority" id="actPriority" class="gui-input" placeholder="Order Of Priority" value="<s:property value="activityById.actPriority" />">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                    <div class="section">
			                    	
			                   <s:checkbox name="actAlert" id="actAlert" value="%{activityById.actAlert}" ></s:checkbox>
                              
                              <span> Activate activity alert </span>
                            
                    </div><!-- end section -->
                    <s:if test="%{activityById.actAlert != 0}">
                     
                    <div class="frm-row">
                        <div class="section colm colm6">
                            <label for="alertDays" class="field prepend-icon">
                        	<input type="text" name="alertDays" id="alertDays" class="gui-input" placeholder="Alert Days" value="<s:property value="activityById.alertDays" />">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        	</label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        	<label class="field select">
	                        <s:select 
	                           id="alertTrigger"
								headerKey="" headerValue="Select Alert Timing..."
								list="#{'1':'After Activity Start Date', '2':'Before Activity Due Date'}" 
								name="alertTrigger" 
								value="activityById.alertTrigger" />
						       <i class="arrow double"></i>
							<i class="arrow double"></i>                  
                        	</label>  
                        </div><!-- end section -->
                    </div><!-- end .frm-row section --> 
                    
                    </s:if><s:else>
                     <div class="frm-row" id ="alertFields" style="display: none">
                        <div class="section colm colm6">
                            <label for="alertDays" class="field prepend-icon">
                        	<input type="text" name="alertDays" id="alertDays" class="gui-input" placeholder="Alert Days">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        	</label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        	<label class="field select">
	                        <s:select 
	                           id="alertTrigger"
								headerKey="" headerValue="Select Alert Timing..."
								list="#{'1':'After Activity Start Date', '2':'Before Activity Due Date'}" 
								name="alertTrigger"/>
						       <i class="arrow double"></i>
							<i class="arrow double"></i>                  
                        	</label>  
                        </div><!-- end section -->
                    </div><!-- end .frm-row section --> 
                    
                    </s:else>
                   
     
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="updateActivity"> Save </button>
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/activities/activitiesIndex.action?
									selectedStageID=<s:property value="selectedStageID"/>&
									selectedTaskID=<s:property value="selectedTaskID"/>&
									selectedStageName=<s:property value="selectedStageName"/>&
									selectedTaskName=<s:property value="selectedTaskName"/>" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
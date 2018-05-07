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
								projectStatus: {
										required: true
								},
								numberOfEandA: {
									required: true,
									digits: true,
									min: 1
									
								},
								projectCatID: {
										required: true
								},
								projectName: {
										required: true
								},					
								projectDescription: {
										required: false,
										maxlength: 399
								},
								shortName: {
										required: false
								},								
								currentBudget: {
										required: false,
										number: true,
										min: 0
										
								},								
								priority:  {
										required: true
								},
								staffID:  {
										required: false
								},
								projectTime:  {
										required: false,
										digits: true,
										min: 1
										
								},
								masurementTime:{
										required: false				
								},
								startDateSTR:{
										required: false
								},
								dueDateSTR:{
										required: false
								},
								hoursSpend:{
										required: false,
										digits: true,
										min: 1
										
								},			
								clientID:{
										required:true
								}																			
							
						},
						
						/* @validation error messages 
						---------------------------------------------- */
							
						messages:{
								projectStatus: {
										required: 'Please select Project State'
								},
								numberOfEandA: {
									required: 'Enter Number of units',
									digits: 'Must be only digits (1, 2, 3... 30)',
									min: 'Must be minimum 1'
									
								},
								projectCatID: {
										required: 'Please select Extension & alteration'
								},					
								projectName: {
										required: 'Enter Project name'
										
								},
								projectDescription: {
										required: 'Enter Project description',
										maxlength: 'Enter not more than 400 characters'
								},														
								shortName: {
										required: 'Enter Short name'
								},						
								currentBudget:  {
									required: 'Enter Budget (Minimum value 0)',
									number: 'Please enter a valid number',
									min: 'Must be minimum 0'
										
										
								},
								priority:  {
										required: 'Please select Priority'
								},								
								staffID:  {
										required: 'Please select Person in charge'
										
								},
																											
								projectTime:{
										required: 'Enter Estimated Time',
										digits: 'Must be only digits (1, 2, 3... 30)',
										min: 'Must be minimum 1'
										
											
								},
								masurementTime:{
										required: 'Please select Hours/Weeks/Months'
								},
								startDateSTR:{
										required: 'Please select a start date'
								},
								dueDateSTR:{
										required: 'Please select a due date'
								},																		
								hoursSpend:{
										required: 'Please enter spend hours',
										digits: 'Must be only digits (1, 2, 3... 30)',
										min: 'Must be minimum 1'
										
								},
								clientID: {
										required: 'Please select a customer'
										
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

				/* @date picker
				------------------------------------------------------------------ */					
				$("#startDateSTR").datepicker({
					dateFormat: "dd/mm/yy",
					
					onSelect: function(dateStr, inst) {
			        var nights = parseInt($('#projectTime').val()) * 7;
			        var depart = $.datepicker.parseDate('dd/mm/yy', dateStr);
			        depart.setDate(depart.getDate('dd/mm/yy') + nights);
			        $('#dueDateSTR').val(depart.toLocaleDateString());
			    	},

					onClose: function( selectedDate ) {
						$( "#dueDateSTR" ).datepicker( "option", "minDate", selectedDate );
					}
				});
				
				$("#dueDateSTR").datepicker({
					dateFormat: "dd/mm/yy",
					defaultDate: "+1w",			
					onClose: function( selectedDate ) {
						$( "#startDateSTR" ).datepicker( "option", "maxDate", selectedDate );
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
            	<h4><i class="fa fa-pencil-square"></i>New Project</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="addProject" id="smart-form">
            
            	<div class="form-body">
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Project Information </span></div><!-- .tagline -->
                    </div>
                    
                    
                    
                    <div class="section">
                        	<label class="field select">
	                        <select name="projectStatus" class="field select" id="projectStatus" size="1">
	                        	<option value="">Select Project State...</option>
								<option value="1">Planned</option>
								<option value="2">In Progress</option>
								<option value="3">On Hold</option>
								<option value="4">Cancelled</option>
								<option value="5">Closed</option>
							</select>
							<i class="arrow double"></i>                    
                        	</label>  
                        
                    </div><!-- end section -->
                    <div class="frm-row">
                        <div class="section colm colm6">
                        	<label for="numberOfEandA" class="field prepend-icon">
                        	<input type="text" name="numberOfEandA" id="numberOfEandA" class="gui-input" placeholder="Number of units">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
						
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        
						<label class="field select">
                            <s:select
						       name="projectCatID"
						       id="projectCatID"
						       cssClass="field select"
						       list="categoList"
							   listKey="categoryID"
							   listValue="extention"
						       multiple="false"
						       headerKey = "" 
		    				   headerValue = "Select Category..."
						       size="1"/>
						       <i class="arrow double"></i>                    
                        	</label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->                                    
                
                	<div class="section">
                    	<label for="projectName" class="field prepend-icon">
                        	<input type="text" name="projectName" id="projectName" class="gui-input" placeholder="Project name">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                    <div class="section">
                    	<label for="projectDescription" class="field prepend-icon">
                        	<textarea class="gui-textarea" id="projectDescription" name="projectDescription" placeholder="Description"></textarea>
                            <span class="field-icon"><i class="fa fa-comments"></i></span>
                            <span class="input-hint"> 
                            	Length between 1 and 400 characters. Only lower case letters (a-z), numbers, dashes and underscores are allowed. 
                            </span>   
                        </label>
                    </div><!-- end section --> 
                    
                    <div class="section">
                    	<label for="shortName" class="field prepend-icon">
                        	<input type="text" name="shortName" id="shortName" class="gui-input" placeholder="Project short name">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                    <div class="section">
                    	<label for="currentBudget" class="field prepend-icon">
                        	<input type="text" name="currentBudget" id="currentBudget" class="gui-input" placeholder="Budget $">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                     <div class="spacer-t40 spacer-b40">
                    	<div class="tagline"><span> Schedule Information  </span></div><!-- .tagline -->
                    </div>        
                    
                	
                    <div class="section">
                        <label class="field select">
                            <select id="priority" name="priority">
                                <option value="">Select priority...</option>
                                <option value="Immediate">Immediate</option>
                                <option value="High">High</option>
                                <option value="Normal">Normal</option>
                                <option value="Low">Low</option>
                            </select>
                            <i class="arrow double"></i>                    
                        </label>  
                    </div><!-- end section -->                   
                    
                     <div class="section">
                        <label class="field select">
                            <s:select
						       name="staffID"
						       id="staffID"
						       cssClass="field select"
						       list="staffList"
							   listKey="id"
							   listValue="initials + ' - ' + firstName + ' ' + lastName + ' <' + position + '>' "
						       multiple="false"
						       headerKey = "" 
		    				   headerValue = "Select person in charge ..."
						       size="1"/>
                            <i class="arrow double"></i>                    
                        </label>  
                    </div><!-- end section -->                        
                    
                    <div class="frm-row">
                        <div class="section colm colm6">
                            <label for="projectTime" class="field prepend-icon">
                                <input type="text" name="projectTime" id="projectTime" class="gui-input" placeholder="Estimated Time...">
                                <span class="field-icon"><i class="fa fa-user"></i></span>  
                            </label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        	<label class="field select">
	                        <select name="masurementTime" class="field select" id="masurementTime" size="1">
								<option selected="selected" value="Weeks">Weeks</option>
							</select>
							<i class="arrow double"></i>                    
                        	</label>  
                        </div><!-- end section -->
                    </div><!-- end .frm-row section --> 
                    
                   <div class="frm-row">
                        <div class="section colm colm6">
                            <label for="datepicker1" class="field prepend-icon">
                            	<input type="text" id="startDateSTR" name="startDateSTR" class="gui-input" placeholder="Start date...">
                                <span class="field-icon"><i class="fa fa-calendar"></i></span>  
                            </label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                            <label for="datepicker2" class="field prepend-icon">
                            	<input type="text" id="dueDateSTR" name="dueDateSTR" class="gui-input" placeholder="Due date...">
                                <span class="field-icon"><i class="fa fa-calendar"></i></span>  
                            </label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->   

                    <div class="frm-row">
                        <div class="section colm colm6">
                            <label for="hoursSpend" class="field prepend-icon">
                        	<input type="text" name="hoursSpend" id="hoursSpend" class="gui-input" placeholder="Spent time">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        	<label class="field select">
	                        <select class="field select" id="hrsSpended" size="1">
	                        	<option selected="selected" value="Hrs">Hours</option>
							</select>
							<i class="arrow double"></i>                    
                        	</label>  
                        </div><!-- end section -->
                    </div><!-- end .frm-row section --> 
                             
                     <div class="spacer-t20 spacer-b30">
                    	<div class="tagline"><span> Client Information  </span></div><!-- .tagline -->
                    </div>                                         

                    <div class="section">
                        <label class="field select">
                            <s:select
						       name="clientID"
						       id="selectClient"
						       cssClass="field select"
						       list="clientsList"
							   listKey="clientID"
							   listValue="clientName + ' <' + clientEmail + '>' "
						       multiple="false"
						       headerKey = "" 
		    				   headerValue = "Select customer..."
						       size="1"/>
                            <i class="arrow double"></i>              
                        </label>  
                    </div><!-- end section -->  
                    
                    <div class="spacer-t20 spacer-b30">
                    	<div class="tagline"><span> Council Information  </span></div><!-- .tagline -->
                    </div>  
                    
                     <div class="frm-row">
                        <div class="section colm colm6">
                            <label for="councilName" class="field prepend-icon">
                                <input type="text" name="councilName" id="councilName" class="gui-input" placeholder="Town Planner Name...">
                                <span class="field-icon"><i class="fa fa-user"></i></span>  
                            </label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                            <label for="councilPh" class="field prepend-icon">
                                <input type="text" name="councilPh" id="councilPh" class="gui-input" placeholder="Town Planner Phone Number...">
                                <span class="field-icon"><i class="fa fa-user"></i></span>  
                            </label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section --> 
                    
                    <div class="section">
                    	<label for="councilEmail" class="field prepend-icon">
                        	<input type="text" name="councilEmail" id="councilEmail" class="gui-input" placeholder="Town Planner Email">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->

            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="addProject"> Save </button>
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/project/overviewProjects.action" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
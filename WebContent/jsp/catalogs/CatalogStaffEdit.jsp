<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<script type="text/javascript">
	
 				$(document).ready( function () {
		
				/* @custom validation method (smartCaptcha) 
				------------------------------------------------------------------ */
					
				$.validator.methods.smartCaptcha = function(value, element, param) {
						return value == param;
				};
						
				$( "#register-form" ).validate({
				
						/* @validation states + elements 
						------------------------------------------- */
						
						errorClass: "state-error",
						validClass: "state-success",
						errorElement: "em",
						
						/* @validation rules 
						------------------------------------------ */
							
						rules: {
								firstName: {
										required: true
								},
								lastName: {
										required: true
								},					
								email: {
										required: true,
										email: true
								},
								address:  {
										required: false,
										
								},
								phone: {
										required: false,
								},
								initials: {
									required: true
								},
								
								status:{
										required: true
								},
								colorpick:{
										required: false
								},			
								position:{
										required:true,
								},
																														
							
						},
						
						/* @validation error messages 
						---------------------------------------------- */
							
						messages:{
								firstName: {
										required: 'Enter first name'
								},
								lastName: {
										required: 'Enter last name'
								},					
								email: {
										required: 'Enter email address',
										email: 'Enter a VALID email address'
								},								
								address:  {
										required: 'Oops you forgot to comment',
										minlength: 'Enter at least 30 characters or more'
								},
								phone: {
										require_from_group: 'Fill at least a mobile contact'
								},
								initials: {
									required: 'Enter name initials'
								},
								status:{
										required: 'Please select a status'
								},
								position:{
										required: 'Select the Staff position'
								},																		
																								
						 
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

				/* Function for changing button color 
				------------------------------------------------- */		
				function updateButtons(color) {
					var hexColor = "transparent";
					if(color) {
						hexColor = color.toHexString();
					}
					$(".btn-primary").css("background", hexColor);
				}		
		
				/* Type paste - pickers
				------------------------------------------------- */
				$("#typepicker, #pastepicker").spectrum({ 
					preferredFormat: "hex6",
					showButtons: false,
					clickoutFiresChange: true
				});
				
				$("#typepicker, #pastepicker").blur(function() {
					$(this).spectrum("set", $(this).val());
				});		
				
				/* Bind event handlers for focus and mouseup
				--------------------------------------------------------------- */		
				$('#typepicker, #pastepicker').focus(function () { $(this).select(); });
				$('#typepicker, #pastepicker').mouseup(function (e) { e.preventDefault(); });
				
				/* Show all inputs with a colorpicker || hidden by default
				--------------------------------------------------------------- */			
				$("#typepicker, #pastepicker").show();
				
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
            	<h4><i class="fa fa-pencil-square"></i>Edit Staff</h4>
            </div><!-- end .form-header section -->
            
            <form action="updateStaff" method="post" id="register-form" >
            <s:hidden name="id" value="%{staffById.id}"></s:hidden>
            
            	<div class="form-body">
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Personal Information </span></div><!-- .tagline -->
                    </div>
                  
                   <div class="frm-row">
                        <div class="section colm colm6">
                            <label for="firstname" class="field prepend-icon">
                                <input type="text" name="firstName" id="firstname" class="gui-input" value="<s:property value="staffById.firstName" />">
                                <span class="field-icon"><i class="fa fa-user"></i></span>  
                            </label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                            <label for="lastName" class="field prepend-icon">
                                <input type="text" name="lastName" id="lastname" class="gui-input" value="<s:property value="staffById.lastName" />">
                                <span class="field-icon"><i class="fa fa-user"></i></span>  
                            </label>
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->                                     
                
                	<div class="section">
                    	<label for="email" class="field prepend-icon">
                        	<input type="text" name="email" id="useremail" class="gui-input" value="<s:property value="staffById.email" />">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                    <div class="section">
                    	<label for="address" class="field prepend-icon">
                        	<textarea class="gui-textarea" id="address" name="address"><s:property value="staffById.address" /></textarea>
                            <span class="field-icon"><i class="fa fa-comments"></i></span>
                            <span class="input-hint"> 
                            	Length between 1 and 400 characters. Only lower case letters (a-z), numbers, dashes and underscores are allowed. 
                            </span>   
                        </label>
                    </div><!-- end section --> 
                    
                    <div class="section">
                    	<label for="mobile_phone" class="field prepend-icon">
                                <input type="text" name="phone" id="mobile_phone" class="gui-input phone-group" placeholder="Ph. Number" value="<s:property value="staffById.phone" />">
                                <span class="field-icon"><i class="fa fa-mobile-phone"></i></span>  
                            </label>
                    </div><!-- end section -->
                    
                     <div class="spacer-t40 spacer-b40">
                    	<div class="tagline"><span> Extra Information  </span></div><!-- .tagline -->
                    </div>     
                    
                    <div class="frm-row">
                        <div class="section colm colm6">
                            <label for="initials" class="field prepend-icon">
                                <input type="text" name="initials" id="initials" class="gui-input" placeholder="Name Initials..." value="<s:property value="staffById.initials" />">
                                <span class="field-icon"><i class="fa fa-user"></i></span>  
                            </label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                            <label class="field select">
	                           <s:select 
								headerKey="" headerValue="Select status"
								list="#{'active':'Active', 'inactive':'Inactive'}" 
								name="status" 
								value="staffById.status" />
						       <i class="arrow double"></i>  
	                            <b class="tooltip tip-left-top"><em> Select status</em></b>                    
	                        </label>    
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->    
                    
                    
                    <div class="frm-row">
                        <div class="section colm colm6">
                        	<!-- <label for="typepicker" class="field-label"></label> -->
                            <label class="field sfcolor">
                            	<input type="text" name="colorCode" value="<s:property value="staffById.colorCode" />" id="typepicker" class="gui-input">
                            </label>
                        </div><!-- end section -->
                        
                        <div class="section colm colm6">
                        
                        <label class="field select">
	                           <s:select 
	                            id="position"
								headerKey="" headerValue="Select position..."
								list="#{'Designer':'Designer', 'Manager':'Manager', 'Director':'Director', 
								'Other':'Other'}" 
								name="position" 
								value="staffById.position" />
						       <i class="arrow double"></i>  
	                            <b class="tooltip tip-left-top"><em>Select position</em></b>                    
	                        </label> 
                        </div><!-- end section -->
                    </div><!-- end .frm-row section -->
                
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="updateStaff"> Save </button>
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/staff/staffIndex.action" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
             </div>
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
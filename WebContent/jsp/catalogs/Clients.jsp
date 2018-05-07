<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
 <script type="text/javascript">
	
 				$(document).ready( function () {

 					/* multiemail method */
 					$.validator.addMethod(
 					        "multiemails",
 					         function(value, element) {
 					             if (this.optional(element)) // return true on optional element
 					                 return true;
 					             var emails = value.split(/[;,]+/); // split element by , and ;
 					             valid = true;
 					             for (var i in emails) {
 					                 value = emails[i];
 					                 valid = valid && $.validator.methods.email.call(this, $.trim(value), element);
 					             }
 					             return valid;
 					         },
 					        $.validator.messages.email
 					    );
						
				$( "#register-form" ).validate({
				
						/* @validation states + elements 
						------------------------------------------- */
						
						errorClass: "state-error",
						validClass: "state-success",
						errorElement: "em",
						
						/* @validation rules 
						------------------------------------------ */
							
						rules: {
							clientName: {
										required: true
								},				
								clientEmail: {
										required: true,
										multiemails: true
								},
								clientAddress:  {
										required: false,
										
								},
								phone: {
										required: false,
								},
								clientStatus:{
										required: true
								}
								
																														
							
						},
						
						/* @validation error messages 
						---------------------------------------------- */
							
						messages:{
							clientName: {
										required: 'Enter customer name'
								},
													
								clientEmail: {
										required: 'Enter email address',
										multiemails: 'You must enter a valid email, or comma separate multiple'
								},								
								clientAddress:  {
										required: 'Oops you forgot to comment',
										minlength: 'Enter no more than 400 characters'
								},
								phone: {
										require_from_group: 'Fill at least a mobile contact'
								},
								
								clientStatus:{
										required: 'Please select a status'
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
            	<h4><i class="fa fa-pencil-square"></i>New Customer</h4>
            </div><!-- end .form-header section -->
            
            <form action="addClient" method="post" id="register-form" >
            
            	<div class="form-body">
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Customer Information </span></div><!-- .tagline -->
                    </div>
                    
                   <div class="section"><!--Start Customer Name -->
                       
                           <label for="clientName" class="field prepend-icon">
                                <input type="text" name="clientName" id="clientName" class="gui-input" placeholder="Customer Name...">
                                <span class="field-icon"><i class="fa fa-user"></i></span>  
                            </label>
                     
                    </div><!-- end Customer NAme .frm-row section -->                                    
                
                	<div class="section">
                    	<label for="clientEmail" class="field prepend-icon">
                        	<input type="text" name="clientEmail" id="clientEmail" class="gui-input" placeholder="Customer Email">
                            <span class="field-icon"><i class="fa fa-envelope"></i></span>  
                        </label>
                    </div><!-- end section -->
                    
                   <div class="section">
                    	<label for="clientAddress" class="field prepend-icon">
                        	<textarea class="gui-textarea" id="clientAddress" name="clientAddress" placeholder="Customer Address"></textarea>
                            <span class="field-icon"><i class="fa fa-comments"></i></span>
                            <span class="input-hint"> 
                            	Length between 1 and 400 characters. Only lower case letters (a-z), numbers, dashes and underscores are allowed. 
                            </span>   
                        </label>
                    </div><!-- end section --> 
               <div class="frm-row">    
              <div class="section colm colm6">
	                        <label class="field select">
	                            <select id="clientStatus" name="clientStatus">
	                                <option value="">Select Client Status..</option>
	                                <option value="Active">Active</option>
	                                <option value="Inactive">Inactive</option>
	                            </select>
	                            <i class="arrow double"></i>
	                            <b class="tooltip tip-left-top"><em> Select one status</em></b>                    
	                        </label>  
                    	</div><!-- end section -->
                       
                        
                        <div class="section colm colm6">
                            <label for="clientPhone" class="field prepend-icon">
                                <input type="text" name="clientPhone" id="clientPhone" class="gui-input phone-group" placeholder="Ph. Number">
                                <span class="field-icon"><i class="fa fa-phone"></i></span>  
                            </label>
                        </div><!-- end section -->
                     
                    </div><!-- end .frm-row section -->
                
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="addClient"> Save </button>
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/customers/clientsIndex.action" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
             </div>
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
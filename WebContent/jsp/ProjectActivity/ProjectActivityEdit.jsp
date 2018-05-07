<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <script type="text/javascript">
		$(function(){
			$("#smart-form").steps({
				bodyTag: "fieldset",
				headerTag: "h2",
				bodyTag: "fieldset",
				transitionEffect: "slideLeft",
				titleTemplate: "<span class='number'>#index#</span> #title#",
				labels: {
					finish: "Save",
					next: "Continue",
					previous: "Go Back",
					loading: "Loading..." 
				},
				onStepChanging: function (event, currentIndex, newIndex){
					if (currentIndex > newIndex){return true; }
					var form = $(this);
					if (currentIndex < newIndex){}
					return form.valid();
				},
				onStepChanged: function (event, currentIndex, priorIndex){
				},
				onFinishing: function (event, currentIndex){
					var form = $(this);
					form.validate().settings.ignore = ":disabled";
					return form.valid();
				},
				onFinished: function (event, currentIndex){
					var form = $(this);
					// Submit form input
				    form.submit();
				}
			}).validate({
				errorClass: "state-error",
				validClass: "state-success",
				errorElement: "em",
				onkeyup: false,
				onclick: false,
				rules: {
					firstname: {
						required: true
					},
					emailaddress: {
						required: true
					},
					telephone: {
						required: true,
						number: true
					},
					lastname: {
						required: true
					},					
					project_title: {
						required: true
					},
					contact_person:{
						required: true
					},
					generalTerms:{
						required: true
					}					
				},
				messages: {
					firstname: {
						required: "Please enter firstname"
					},
					lastname: {
						required: "Please enter lastname"
					},
					emailaddress: {
						required: 'Please enter email'
					},
					telephone: {
						required: 'Please enter telephone',
						number: 'Please enter numbers only'
					},					
					project_title: {
						required: "Please enter the project title"
					},
					contact_person:{
						required: 'Please enter contact person'
					},
					generalTerms:{
						required: 'Please agree to our terms'
					}					
				},
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
			
			/* activity datepicker duedate
			----------------------------------------------- */			

			$("#strStartDate").datepicker({
				dateFormat: "dd/mm/yy",
				
				onClose: function( selectedDate ) {
					$( "#strDueDate" ).datepicker( "option", "minDate", selectedDate );
				}
			});
			
			$("#strDueDate").datepicker({
				dateFormat: "dd/mm/yy",
					
				onClose: function( selectedDate ) {
					$( "#strStartDate" ).datepicker( "option", "maxDate", selectedDate );
				}
			});
			
			
			/* The progress slider 
			------------------------------------------------------ */
			var select = $("#strProgress");
			var guestnumber = $("#slider4").slider({
				min: 1,
				max: 11,
				range: "min",
				value: select[0].selectedIndex + 1,
				slide: function(event, ui) {
					select[0].selectedIndex = ui.value - 1;
				}
			});
			
			$("#strProgress").change(function() {
				guestnumber.slider("value", this.selectedIndex + 1);
			});

			
			
			
					
		});    
    </script>        

<body class="woodbg">

	<div class="smart-wrap">
    	<div class="smart-forms smart-container wrap-1">
    	<div class="form-header header-primary">
            	<h4><i class="fa fa-pencil-square"></i>Activity: <s:property value="selectedActivityName"/></h4>
        </div><!-- end .form-header section -->
        
            	<div class="form-body smart-steps steps-progress steps-theme-primary">
            	<div class="section">
		              <label for="names" class="field-label"><strong>Project: </strong> <s:property value="selectedProjectName"/></label>
		         </div><!-- end section -->
		         <div class="section">
		              <p class="small-text fine-grey">STAGE: <s:property value="selectedStageName"/> <strong> > </strong> 
		              TASK: <s:property value="selectedTaskName"/> <strong> > </strong> Activity: <s:property value="selectedActivityName"/> </p>
		         </div><!-- end section -->
                 <form method="post" action="updateActivity" id="smart-form"> 
                 
                
                        
                 		<h2>Activity Details</h2>
                            <fieldset>
                            	<s:hidden name="selectedMapActID" value="%{selectedMapActID}"></s:hidden>
                            	<s:hidden name="selectedProjectID" value="%{selectedProjectID}"></s:hidden>
	                            <s:hidden name="projectActivity.projectActivityID" value="%{getProjectAct.projectActivityID}"></s:hidden>
	                            <s:hidden name="projectActivity.projectID" value="%{getProjectAct.projectID}"></s:hidden>
	                            <s:hidden name="projectActivity.stageID" value="%{getProjectAct.stageID}"></s:hidden>
	                            <s:hidden name="projectActivity.taskID" value="%{getProjectAct.taskID}"></s:hidden>
	                            <s:hidden name="projectActivity.actID" value="%{getProjectAct.actID}"></s:hidden>
	                            <s:hidden name="selectedStageName" value="%{selectedStageName}"></s:hidden>
	                            <s:hidden name="selectedTaskName" value="%{selectedTaskName}"></s:hidden>
	                            <s:hidden name="selectedProjectName" value="%{selectedProjectName}"></s:hidden>
	                            <s:hidden name="selectedActivityName" value="%{selectedActivityName}"></s:hidden> 
                            
                            
                               <div class="frm-row">
                                    <div class="section colm colm6">
                                        <label class="field select">
                                       
                                        <s:select
									       name="projectActivity.statusID"
									       id="statusID"
									       cssClass="field select"
									       list="paStatus"
										   listKey="ptStatusID"
										   listValue="ptStatusName"
									       multiple="false"
									       headerKey = "" 
					    				   headerValue = "Activity Status"
					    				   value="getProjectAct.statusID"
									       size="1"/>
			                            <i class="arrow double"></i>
          
			                        </label>  
                                    </div><!-- end section -->
                                	
                                    <div class="section colm colm6">
                                        <label class="field select">
				                           <s:select 
				                           id="priorityPA"
											headerKey="" headerValue="Select Activity Priority..."
											list="#{'Immediate':'Immediate', 'High':'High', 'Normal':'Normal', 
											'Low':'Low'}" 
											name="projectActivity.priorityPA" 
											value="getProjectAct.priorityPA" />
									       <i class="arrow double"></i>  
				                            <b class="tooltip tip-left-top"><em>Select Activity Priority</em></b>                    
				                        </label>  
                                    </div><!-- end section -->               
                                </div><!-- end frm-row section -->
                            	<div class="section">
                                    <label class="field prepend-icon">
                                    
                                     <s:if test="%{getProjectAct.descPA != null}">
                                     <textarea class="gui-textarea" id="descPA" name="projectActivity.descPA" placeholder="Activity details"><s:property value="getProjectAct.descPA"/></textarea>
                                        <span class="field-icon"><i class="fa fa-comments"></i></span>
                                        <span class="input-hint"> 
                                            <strong>More Details:</strong> add more specific details about the activity
                                        </span>   
                                     </s:if><s:else>
                                      <textarea class="gui-textarea" id="descPA" name="projectActivity.descPA" placeholder="Activity details"><s:property value="getActByIDAct.actDescription"/></textarea>
                                        <span class="field-icon"><i class="fa fa-comments"></i></span>
                                        <span class="input-hint"> 
                                            <strong>More Details:</strong> add more specific details about the activity
                                        </span>   
                                     </s:else>
                                        
                                    </label>
                                </div><!-- end section -->     
                            </fieldset>
                 
                            <h2>Who & When</h2>
                            <fieldset>
                            
                            	 <div class="section" style="margin-top: 20px;">
                            	 	<label for="names" class="field-label">Who should do this activity?</label>
			                        <label class="field select">
			                        <s:if test="%{getProjectAct.staffInCharge != null}">
			                        <s:select
								       name="projectActivity.staffInCharge"
								       id="staffID"
								       cssClass="field select"
								       list="staffList"
									   listKey="id"
									   listValue="initials + ' - ' + firstName + ' ' + lastName + ' <' + position + '>' "
								       multiple="false"
								       headerKey = "" 
				    				   headerValue = "Select person in charge ..."
				    				   value="%{getProjectAct.staffInCharge}"
								       size="1"/>
		                            <i class="arrow double"></i>
			                        
			                        
			                        </s:if><s:else>
			                        
			                        <s:select
								       name="projectActivity.staffInCharge"
								       id="staffID"
								       cssClass="field select"
								       list="staffList"
									   listKey="id"
									   listValue="initials + ' - ' + firstName + ' ' + lastName + ' <' + position + '>' "
								       multiple="false"
								       headerKey = "" 
				    				   headerValue = "Select person in charge ..."
				    				   value="%{project.staffID}"
								       size="1"/>
		                            <i class="arrow double"></i>
			                        
			                        </s:else>
		                                        
		                       		</label>  
			                        <span class="input-hint"> 
                                            Please select one from the list.
                                    </span> 
			                    </div><!-- end section --> 
			                    
			                    <div class="frm-row">
                                    <div class="section colm colm6">
                                        <label class="field prepend-icon">
                                            <input type="text" name="strStartDate" id="strStartDate" class="gui-input" value="<s:property value="strStartDate" />" placeholder="Activity start date">
                                            <span class="field-icon"><i class="fa fa-calendar"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                	
                                    <div class="section colm colm6">
                                        <label class="field prepend-icon">
                                            <input type="text" name="strDueDate" id="strDueDate" class="gui-input" value="<s:property value="strDueDate" />" placeholder="Activity due date">
                                            <span class="field-icon"><i class="fa fa-calendar"></i></span>  
                                        </label>
                                    </div><!-- end section -->               
                                </div><!-- end frm-row section --> 
                                
                                <div class="section">
                                    <label class="field prepend-icon">
                                            <input type="text" name="projectActivity.estimatedHrs" id="estimatedHrs" class="gui-input" value="<s:property value="getProjectAct.estimatedHrs" />" placeholder="Activity estimated time (HRS)">
                                            <span class="field-icon"><i class="fa fa-calendar-o"></i></span>  
                                    </label>
                                </div><!-- end section -->    
                            </fieldset>
                            
                            <h2>Progress</h2>
                            <fieldset>
                            
                                <div class="section">
                                    
                                
                                
                                <div class="spacer-b20">
                                <label for="strProgress" class="field-label">Please update progress so far.</label>
                                <label class="field select">
                                     <s:select 
				                           	id="strProgress"
											list="#{'0':'0%', '10':'10%', '20':'20%', '30':'30%', 
											'40':'40%', '50':'50%', '60':'60%', '70':'70%', '80':'80%', '90':'90%', '100':'100%'}" 
											name="strProgress" 
											value="getProjectAct.progress" />
									       <i class="arrow double"></i>  
                                </label> 
                            </div><!-- end .spacer -->                  
                            <div class="slider-wrapper">
                                <div id="slider4"></div>
                            </div><!-- end .slider-wrapper -->
                                
                            </div><!-- end section -->
                                
                                
                                   
                                
                                <div class="result"></div>
                            </fieldset>                            
                    </form>                                                                                   
                </div><!-- end .form-body section -->
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->

</body>
</html>
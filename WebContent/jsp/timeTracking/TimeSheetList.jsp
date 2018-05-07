<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

	 <script type="text/javascript">
	
	 $(document).ready(function() {
		
		 $(document).ready( function () {
			    $('#viewTimes').DataTable();
			} );
		
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
    	<div class="smart-forms smart-container wrap-4">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-clock-o"></i>Activity Times</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="timeSheetAddUpdate" id="smart-form">
            
            
            <s:if test="%{detailedInfoActivityByID.isEmpty()}">
				<div class="form-body">
					<div>
						There is no information for this activity. Try again please. 
					</div>
				</div>
			</s:if>
            <s:else>
            	<div class="form-body">
            		<div class="section">
                            <p class="small-text fine-grey">Please update a time or add a new one.</p>
                    </div><!-- end section -->                	
	            	<s:iterator value="detailedInfoActivityByID" var="listActivityByID">
	            	<s:hidden name="projectSelected" value="%{projectID}"></s:hidden>
            		<s:hidden name="selectedMapActID" value="%{mapProjectActID}"></s:hidden>
	            		<div class="spacer-b30">
							<div class="tagline"><span> Activity Details </span></div><!-- .tagline -->
						</div>
						<div class="section">
							<label for="names" class="field-label"><strong>Project Name: </strong> <s:property value="projectName"/></label>
						</div><!-- end section -->
	            		<div class="section">
							<label for="names" class="field-label"><strong>Task: </strong> <s:property value="stageName"/>/<s:property value="taskName"/></label>
						</div><!-- end section -->
						<div class="section">
							<label for="names" class="field-label"><strong>Activity: </strong> <s:property value="actName"/></label>
						</div><!-- end section -->
						<div class="frm-row">
						    <div class="section colm colm6">
						          <label class="field-label"><Strong>Activity start date: </Strong><s:property value="mapStartDate"/></label>
						    </div><!-- end section -->
						                        
						    <div class="section colm colm6">
						         <label class="field-label"><Strong>Activity due date: </Strong><s:property value="mapDueDate"/></label>
						    </div><!-- end section -->
						</div><!-- end .frm-row section -->    
						<div class="section">
							<label for="names" class="field-label"><strong>Activity description: </strong> <s:property value="mapActDesc"/></label>
						</div><!-- end section -->
						<div class="section">
							<label for="names" class="field-label"><strong>Total Time:  <s:property value="totalTimeFormated"/></label></strong>
						</div><!-- end section --> 
	            	</s:iterator>
              	
                
                  <div class="spacer-b30">
                    	<div class="tagline"><span> Time List </span></div><!-- .tagline -->
                    </div>
                  
                    					<div class="section" >
											
											<a href="/timeSheetManager2bScene/timeSheet/timeSheetForm?selectedMapActID=<s:property value="selectedMapActID"/>
											&projectSelected=<s:property value="projectSelected"/>" class="button" style="margin-top: 5px; margin-left : 10px">
											<img src="/timeSheetManager2bScene/html/img/add_list.png" alt="update" style="margin-top: 7px" data-toggle="tooltip" data-placement="bottom" title="Add Time">
											</a>
					                        <a href="/timeSheetManager2bScene/timeSheet/timeSheetListActivities.action?projectSelected=<s:property value="projectSelected"/>" class="button" style="margin-top: 5px"><img src="/timeSheetManager2bScene/html/img/undo.png" alt="project activities" style="margin-top: 7px" data-toggle="tooltip" data-placement="bottom" title="Back to activity list"></a>				                        
					                        <!-- <a href="#" class="button" style="margin-top: 5px"><img src="/timeSheetManager2bScene/html/img/empty_trash.png" alt="project activities" style="margin-top: 3px" data-toggle="tooltip" data-placement="bottom" title="Delete Activity"></a> -->
					                    
										</div><!-- end section -->
                    
                     <!-- Start Project Table -->
	                    <div style="position: relative; margin-top: 20px; margin-left: 10px">
							<table id="viewTimes" class="display" cellspacing="0" width="100%">
						    <thead>
						        <tr>
									<th>Employee Name</th>
									<th>Note</th>
									<th>Approved</th>
									<th>Date</th>
									<th>Time (Hrs)</th>
									<th>Edit Time</th>
									<th>Delete</th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="listTimeActivities" var="listTimeActivities">
						    <tr>
								<td><s:property value="nameCharge"/></td>
								<td><s:property value="trackingNote"/></td>
								<td><s:property value="trackingApproved"/></td>
								<td><s:property value="trackingDate"/></td>
								<td><s:property value="trackingTime"/></td>
								<td>
									  		<a href="staffEditForm?staffIdSelected=<s:property value="id"/>">
									  		<span class="ui-icon ui-icon-pencil" data-toggle="tooltip" data-placement="bottom" title="Edit Time">Edit</span>
									  		</a>
								</td>
								<td>
									  		<a href="deleteStaff?id=<s:property value="id"/>">
									  		<span class="ui-icon ui-icon-trash" data-toggle="tooltip" data-placement="bottom" title="Delete Time">Delete</span>
									  		</a>
								</td>
						  </tr>
						  </s:iterator>
						
						    </tbody>
						</table>
					</div> 
					
					   
					              
                    
                    
                    
                </div><!-- end .form-body section -->
            
            </s:else>
            
                <div class="form-footer">
                	<!--  <button type="submit" class="button btn-purple"> Submit </button>
                    <button type="reset" class="button"> Cancel </button> -->
                    <s:set var="referer" value="request.getHeader("Referer")"></s:set>
                     <div style="float: right;">
                    <a href="#" onclick="history.back(-1)" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
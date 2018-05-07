<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

	 <script type="text/javascript">
	
	 $(document).ready(function() {

		 $('#viewTimes').dataTable( {
			 paging: false,
			 "jQueryUI": true,
			 searching: false,
			 "columns": [
			             { "width": "11%" },
			             { "width": "11%" },
			             { "width": "11%" },
			             { "width": "15%" },
			             { "width": "6.5%" },
			             { "width": "6.5%" },
			             { "width": "6.5%" },
			             { "width": "6.5%" },
			             { "width": "6.5%" },
			             { "width": "6.5%" },
			             { "width": "6.5%" },
			             { "width": "6.5%" }
			           ]
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
    	<div class="smart-forms smart-container wrap-5">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-clock-o"></i>Time Sheet</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="timeSheetAddUpdate" id="smart-form">
            
            	<div class="form-body">
	            	<s:hidden name="projectSelected" value="%{projectID}"></s:hidden>
            		<s:hidden name="selectedMapActID" value="%{mapProjectActID}"></s:hidden>
            		<s:hidden name="dateSelected" value="%{dateSelected}"></s:hidden>
	            		<div class="spacer-b30">
							<div class="tagline"><span>Time sheet Details </span></div><!-- .tagline -->
						</div>
						<div class="section">
							<label for="names" class="field-label"><strong>Person in charge: </strong> <s:property value="pCharge"/></label>
						</div><!-- end section -->
	            		<div class="section">
							<label for="names" class="field-label"><strong>Week:&nbsp;</strong><s:property value="weekTime"/></label>
						</div><!-- end section -->
                  	<div class="spacer-b30">
                    	<div class="tagline"><span> Time Information </span></div><!-- .tagline -->
                    </div>         
                   <!-- Start Table -->
	                    <div style="position: relative; margin-top: 20px; margin-left: 10px">
							<table id="viewTimes" class="display" cellspacing="0" width="100%">
						    <thead>
						        <tr>
									<th>Project</th>
									<th>Stage</th>
									<th>Task:Activity</th>
									<th>Note</th>
									<th><s:property value="mondayStr"/></th>
									<th><s:property value="tuesdayStr"/></th>
									<th><s:property value="wednesdayStr"/></th>
									<th><s:property value="thursdayStr"/></th>
									<th><s:property value="fridayStr"/></th>
									<th><s:property value="saturdayStr"/></th>
									<th><s:property value="sundayStr"/></th>
									<th>Total Week</th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="listtotalTime" var="listtotalTime">
						    <tr>
								<td><s:property value="projectName"/></td>
								<td><s:property value="stageName"/></td>
								<td><s:property value="taskAndActName"/></td>
								<td><s:property value="mapActDesc"/></td>
								<td><s:property value="monTime"/></td>
								<td><s:property value="tueTime"/></td>
								<td><s:property value="wedTime"/></td>
								<td><s:property value="thuTime"/></td>
								<td><s:property value="friTime"/></td>
								<td><s:property value="satTime"/></td>
								<td><s:property value="sunTime"/></td>
								<td><s:property value="totalTime"/></td>
						  </tr>
						  </s:iterator>
						    </tbody>
						</table>
					</div>                                    
                    
                </div><!-- end .form-body section -->
            
                <div class="form-footer">
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/timeSheetManager2bScene/timeSheet/timeSheetHome.action?dateSelected=<s:property value="%{dateSelected}"/>" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
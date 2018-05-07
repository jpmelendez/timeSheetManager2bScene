<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<script type="text/javascript">
$(document).ready( function () {
    $('#viewProjects').DataTable( {
    	"scrollX": true,
        "jQueryUI": true
        });
} );
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
            	<h4><i class="fa fa-pencil-square"></i>Add Project Tasks</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="addProjectTasks" id="smart-form">
            <s:set var="countAct" value="0" />
            
            	<div class="form-body">
            	
            	 	<div class="section">
                            <p class="small-text fine-grey">Step 1: Please search and select a project.</p>
                    </div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Search a project</span></div><!-- .tagline -->
	                    </div>
	       
	                    <!-- Start Project Table -->
	                    <div style="position: relative; margin-top: 20px; margin-left: 10px">
							<table id="viewProjects" class="display" cellspacing="0" width="auto">
						    <thead>
						        <tr>
						        	
									<th>ID</th>
						            <th>Person in Charge</th>
						            <th>Project Name</th>
						            <th>Customer</th>
						            <th>Status</th>
						            <th>Project Category</th>
						            <th>Start Date</th>
						            <th>Due Date</th>
						            <th>Add Tasks</th>
									
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="allProjects" var="allProjects">
						    <tr>
						    	
								<td><s:property value="projectID"/></td>
								<td><s:property value="staffInCharge"/></td>
								<td><s:property value="projectName"/></td>
								<td><s:property value="client"/></td>
								<td><s:property value="projectStatus"/></td>
								<td><s:property value="projecCat"/></td>
								<td><s:property value="jobStart"/></td>
								<td><s:property value="jobDueDate"/></td>
								<td>	
									  		<a href="/twobSceneWebApp/projectTasks/editMain.action?selectedProjectID=<s:property value="projectID"/>&selectedProjetName=<s:property value="projectName"/>">
									  		<img src="/twobSceneWebApp/html/img/pencil-icon.png" alt="edit" title="editIcon"> Edit tasks
									  		</a>
								</td>
						  </tr>
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
	                   
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                    <button type="reset" class="button"> Cancel </button>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
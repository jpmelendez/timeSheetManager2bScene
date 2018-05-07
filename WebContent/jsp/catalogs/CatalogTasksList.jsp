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
            	<h4><i class="fa fa-pencil-square"></i>Task Settings</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="#" id="smart-form">
            <s:set var="countAct" value="0" />
            
            	<div class="form-body">
            		<div class="section"><!-- Stage Name Section -->
                            <h3>Stage: <s:property value="selectedStageName"/></h3>
                    </div><!-- end section -->  
            	 	<div class="section">
                            <p class="small-text fine-grey">Please search and edit a task or add a new one.</p>
                    </div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span><s:property value="selectedStageName"/> tasks list</span></div><!-- .tagline -->
	                    </div>
	                    <div style="margin-left: 20px; width: 90%"><!-- Start section ADD BUTTON  -->
	                    	<div style="width: 120px;">
	                    		
	                    			<a href="/twobSceneWebApp/tasks/iniTasks.action?selectedStageID=<s:property value="selectedStageID"/>&selectedStageName=<s:property value="selectedStageName"/>">
	                    			<img src="/twobSceneWebApp/html/img/add-icon.png" alt="add" title="addIcon">  Add Task
	                    			</a>
	                    	</div>
	                    </div><!-- End section -->
	                    <!-- Start Project Table -->
	                    <div style="position: relative; margin-top: 20px; margin-left: 10px">
							<table id="viewProjects" class="display" cellspacing="0" width="auto">
						    <thead>
						        <tr>
						        	
									<th>Task Name</th>
									<th>Task Description</th>
									<th>Order Of Priority</th>
						            <th></th>
									<th></th>
									
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="ListTasks" var="ListTasks">
						    <tr>
						    	
								<td>
									<a href="/twobSceneWebApp/activities/activitiesIndex.action?
									selectedStageID=<s:property value="stageID"/>&
									selectedTaskID=<s:property value="taskID"/>&
									selectedStageName=<s:property value="selectedStageName"/>&
									selectedTaskName=<s:property value="taskName"/>">
									<s:property value="taskName"/></a>
								</td>
								<td><s:property value="taskDescription"/></td>
								<td><s:property value="taskPriority"/></td>
								<td>
									  		<a href="taskEditForm?selectedTaskId=<s:property value="taskID"/>&selectedStageID=<s:property value="selectedStageID"/>&selectedStageName=<s:property value="selectedStageName"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
								</td>
								<td>
									  		<a href="deleteTasks?taskID=<s:property value="taskID"/>&selectedStageID=<s:property value="selectedStageID"/>&selectedStageName=<s:property value="selectedStageName"/>">
									  		<span class="ui-icon ui-icon-trash">Delete</span>
									  		</a>
								</td>
						  </tr>
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
	                   
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                    <a href="/twobSceneWebApp/stages/stagesIndex.action" class="button btn-primary button-left">Back</a>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
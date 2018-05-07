<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

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
            	<h4><i class="fa fa-pencil-square"></i>Add Project Tasks</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="editListPTasks" id="smart-form">
           	<input type="hidden" name="selectedProjectID" value="<s:property value="%{selectedProjectID}"/>">
            <s:set var="countAct" value="0" />
            
            	<div class="form-body">
            	
            	 	<div class="section">
                            <p class="small-text fine-grey">Step 2: Please / tick / check the tasks that you need to be done.</p>
                    </div><!-- end section -->  
                    <div class="section">
                            <h3>Project: <s:property value="selectedProjetName" /></h3>
                    </div><!-- end section -->     
            		<s:iterator value="listAllStages">
            			<s:set name="webStageID" value="stageID" />
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span><s:property value="stageName"/></span></div><!-- .tagline -->
	                    </div>
	                    <s:iterator value="listAllTasks"><!-- Start Tsk itertion  -->
	                    <s:set name="taskStageID" value="stageID" />
	                    <s:set name="taskTaskID" value="taskID" /> 
	                    	<s:if test="%{#webStageID==#taskStageID}">
	                    	
	                    	 	<div class="spacer-b20"><!-- Start Task div -->
	                            	<label class="field-label"><strong>Task name: </strong><s:property value="taskName"/></label>
	                        	</div>
	                        	<s:iterator value="listAllActivities" status="listActivitiesStat"><!-- Start Activities itertion  -->
	                        	<s:set name="activityTaskID" value="task_id" />
		                        	<s:if test="%{#activityTaskID==#taskTaskID}">
		                        	
		                        	<s:set var="indexList" value="%{'listActSelected[' + #countAct + '].'}"/>
		                        	<s:set var="iterStageIDValue" value="stage_id"/>
		                        	<s:set var="iterTaskIDValue" value="task_id"/>
		                        	<s:set var="iterActIDValue" value="act_id"/>
		                        	<s:set var="iterChecked" value="checked"/>
		                        		
		                        		 <div class="frm-row">
					                            <div class="option-group field">
					                                <div class="section colm colm6">
					                                		<s:hidden name="%{'listActSelected[' + #countAct + '].' + 'projectID'}" value="%{selectedProjectID}"></s:hidden>
					                                        <s:hidden name="%{'listActSelected[' + #countAct + '].' + 'stageID'}" value="%{#iterStageIDValue}"></s:hidden>
					                                        <s:hidden name="%{'listActSelected[' + #countAct + '].' + 'taskID'}" value="%{#iterTaskIDValue}"></s:hidden>
					                                        <s:hidden name="%{'listActSelected[' + #countAct + '].' + 'actID'}" value="%{#iterActIDValue}"></s:hidden> 
					                                        <s:if test="%{#iterChecked == true}">
					                                        <s:checkbox cssClass="checkbox" name="%{'listActSelected[' + #countAct + '].' + 'checkedPA'}" label="%{act_name}" value="%{checked}" disabled="true"/>   
					                                        <s:hidden name="%{'listActSelected[' + #countAct + '].' + 'checkedPA'}" value="true"></s:hidden>                                 
					                                        </s:if> 
					                                        <s:else>
					                                        <s:checkbox cssClass="checkbox" name="%{'listActSelected[' + #countAct + '].' + 'checkedPA'}" label="%{act_name}" value="%{checked}" />            
					                                        </s:else>                                  	
					                                </div>
				                       			</div>
				                       	</div>
				                    <s:set var="countAct" value="%{#countAct+1}" />
		                        	</s:if>
	                        	</s:iterator><!-- End Activities itertion  -->
	                        	
	                    	</s:if>
		                   
	                    </s:iterator><!-- End Tsk itertion  -->
                    </s:iterator>

            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="editListPTasks"> Save Tasks </button>
                    <button type="reset" class="button"> Cancel </button>
                    <div style="float: right;">
                    <a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="%{selectedProjectID}"/>" class="button btn-primary button-left">Back</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
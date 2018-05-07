<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<script type="text/javascript">
	$(document).ready(function() {
		$( "#accordion" ).accordion({
		     heightStyle: 'fill',
		     autoHeight: false
		   });
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
            	<h4><i class="fa fa-pencil-square"></i>Edit Project Activities</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="editListPTasks" id="smart-form">
            <s:set var="listStageSize" value="listStagesByProjectID.size()"></s:set>
            <s:set var="countAct" value="0" />
           
            <s:if test="%{#listStageSize > 0}">  
            	<div class="form-body">
            		<div class="section">
		            	<p class="small-text fine-grey">Step 3: Please edit the activities information.</p>
		            </div><!-- end section --> 
                    
			       	<div class="section">
			            <h3>Project: <s:property value="selectedProjetName" /></h3>
			        </div><!-- end section -->
			        
			        <div id="accordion">						
									<s:iterator value="listStagesByProjectID" var="listStagesByProjectID">
										<h3><a href="#"><s:property value="stageName" /></a></h3>
										<div>
										<s:iterator value="listTasksByProjectID" var="listTasksByProjectID">
											<s:if test="%{#listTasksByProjectID.stageID == #listStagesByProjectID.stageID}">
								    		
												<p><strong>Task: </strong><s:property value="taskName"/></p>
												<!--<p><strong>Description: </strong><s:property value="#"/></p>  -->
												<div id="navcontainer">
													<ol id="navlist">
														<s:iterator value="listActivityByProjectID" var="listActivityByProjectID">
														<s:if test="%{#listActivityByProjectID.task_id == #listTasksByProjectID.taskID}">
															<li>
															<div style="float: left;width: 95%;padding-left: 20px;">
																<div style="float: left;width: 75%;overflow: hidden; padding-top: 10px" >
																	<s:property value="%{#listActivityByProjectID.act_name}"/>
																</div>
																<div style="float: right;width: 25%;text-align: right;clear: right; padding-top: 15px">
																	<a href="showActivity?selectedMapActID=<s:property value="%{#listActivityByProjectID.mapProjectActID}"/>
																	&selectedProjectID=<s:property value="%{#listActivityByProjectID.projectId}"/>
																	&selectedTaskName=<s:property value="%{#listTasksByProjectID.taskName}"/>
																	&selectedProjectName=<s:property value="selectedProjetName"/>
																	&selectedActivityID=<s:property value="%{#listActivityByProjectID.act_id}"/>
																	&selectedActivityName=<s:property value="%{#listActivityByProjectID.act_name}"/>
																	&selectedStageName=<s:property value="%{#listStagesByProjectID.stageName}"/>" class="button btn-rounded">
																	Edit</a>
																</div>
																<s:if test="%{#listActivityByProjectID.mapActDesc == 'n/d'}">
																<div style="float: left;width: 75%;overflow: hidden;">
																	<p class="small-text fine-grey"><s:property value="%{#listActivityByProjectID.actDescription}"/></p>
																</div>
																</s:if>
																<s:else>
																<div style="float: left;width: 75%;overflow: hidden;">
																	<p class="small-text fine-grey"><s:property value="%{#listActivityByProjectID.mapActDesc}"/></p>
																</div>
																</s:else>
															</div>
															
															</li>
														</s:if>
														</s:iterator>
													</ol>
												</div>	
											
								    		</s:if>
										</s:iterator>
										</div>
									</s:iterator>
								</div>

            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	
                    <div style="float: right;">
                    <a href="/twobSceneWebApp/projectTasks/addProjectActivity.action?selectedProjectID=<s:property value="%{selectedProjectID}"/>&selectedProjetName=<s:property value="%{selectedProjetName}"/>" 
                    class="button btn-primary button-left">Add tasks</a>
                    
                    </div>
                </div><!-- end .form-footer section -->
            </s:if>
            <s:else>
            <div class="form-body">
								<div class="section">
	                        		<h3>Project: <s:property value="selectedProjetName" /></h3>
	                			</div><!-- end section --> 
	                			
	                			<div class="section">
                            
                            	<div class="price-box">                                
                                	<h4>No Tasks</h4>
                                	<h5> No tasks in this list </h5>
                                    <div class="spacer spacer-t20 spacer-b20"></div>
                                	<p>There are no tasks in this project yet.</p>
                                	<p>Add tasks by clicking the button below.</p>
                                    <div class="spacer spacer-t20 spacer-b20"></div>
                                    <a href="/twobSceneWebApp/projectTasks/addProjectActivity.action?selectedProjectID=<s:property value="%{selectedProjectID}"/>&selectedProjetName=<s:property value="%{selectedProjetName}"/>" 
                                    class="button block pushed expand"> Add Tasks </a>
                                </div><!-- end .price-box section -->
                                </div>
                              
	        </div><!-- end form-body (else) section -->
            
            </s:else>
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>

 </html> 
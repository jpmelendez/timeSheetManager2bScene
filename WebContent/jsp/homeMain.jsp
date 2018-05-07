<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>
<script>
$(document).ready(function() {

	 $( "#tabs" ).tabs({ 
	        
	        });
    

    /******** Start canvas **********/
    $('.profile-pic').initial({width:56,height:56,fontSize:20,fontWeight:400,charCount:3});
  });
</script>

<body class="woodbg">
<div class="loader"></div>
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
    	<div class="smart-forms smart-container wrap-1" style="width: 1000px;">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-pencil-square"></i>Upcoming Tasks</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="#" id="smart-form">
            
            
            	<div class="form-body">
            		<div class="section" style="margin-top: -35px; margin-left: 0px; margin-right: 0px">
		            	<!-- Tabs -->
					<div id="tabs">
						<ul>
							<li><a href="#inboxtab">Inbox</a></li>
							<li><a href="#todaytab">Today</a></li>
							<li><a href="#thisweektab">This Week</a></li>
							<li><a href="#nextweektab">Next Week</a></li>
							<li><a href="#monthtab">This Month</a></li>
						</ul>
						<!-- INBOX -->
						<div id="inboxtab" style="min-height: 600px">
						<div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="listOverdueActivities" var="listOverdueActivities">
															<li id="row">
															<a href="/timeSheetManager2bScene/manager/mainManager?alertFlag=1&alertNameSel=<s:property value="%{#listOverdueActivities.alertName}"/>&alertDateSel=<s:property value="%{#listOverdueActivities.alertDate}"/>&projectIDSelected=<s:property value="%{#listOverdueActivities.projectID}"/>#alert-section">
															<div id="activitybox">
																<s:div id="%{'staffIcon' + #countAct}" style="float: left;width: 10%;overflow: hidden; padding-top: 10px; padding-bottom: 10px" ><!-- Start canvas div -->
																	<img class="profile-pic media-object pull-left img-rounded" data-name='<s:property value="%{#listOverdueActivities.mapPChargeIni}"/>' data-background-color='<s:property value="%{#listOverdueActivities.mapColorCode}"/>'>
																</s:div><!-- End Canvas -->
																<div style="float: left;width: 58%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#listOverdueActivities.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Alert: <strong><s:property value="%{#listOverdueActivities.alertName}"/></strong>&nbsp;<em style="color: #8B0000">(Overdue)</em></p>
																</div><!-- End date -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par">Person in Charge: <s:property value="%{#listOverdueActivities.mapPCharge}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par">Alert Date: <s:property value="%{#listOverdueActivities.alertDate}"/></p>
																</div><!-- End progress -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par">Town Planner:&nbsp;<s:property value="%{#listOverdueActivities.councilName}"/>&nbsp;&nbsp;&nbsp;Email:&nbsp;<s:property value="%{#listOverdueActivities.councilEmail}"/></p>
																</div><!-- End Stage name -->
															</div>
															</a>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
														<s:iterator value="listAlertedActivities" var="listAlertedActivities">
															<li id="row">
															<a href="/timeSheetManager2bScene/manager/mainManager?alertFlag=1&alertNameSel=<s:property value="%{#listAlertedActivities.alertName}"/>&alertDateSel=<s:property value="%{#listAlertedActivities.alertDate}"/>&projectIDSelected=<s:property value="%{#listAlertedActivities.projectID}"/>#alert-section">
															<div id="activitybox">
																<s:div id="%{'staffIcon' + #countAct}" style="float: left;width: 10%;overflow: hidden; padding-top: 10px; padding-bottom: 10px" ><!-- Start canvas div -->
																	<img class="profile-pic media-object pull-left img-rounded" data-name='<s:property value="%{#listAlertedActivities.mapPChargeIni}"/>' data-background-color='<s:property value="%{#listAlertedActivities.mapColorCode}"/>'>
																</s:div><!-- End Canvas -->
																<div style="float: left;width: 58%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#listAlertedActivities.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Alert: <strong><s:property value="%{#listAlertedActivities.alertName}"/></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par">Person in Charge: <s:property value="%{#listAlertedActivities.mapPCharge}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par">Last time updated: <s:property value="%{#listAlertedActivities.alertDate}"/></p>
																</div><!-- End progress -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par">Town Planner:&nbsp;<s:property value="%{#listAlertedActivities.councilName}"/>&nbsp;&nbsp;&nbsp;Email:&nbsp;<s:property value="%{#listAlertedActivities.councilEmail}"/></p>
																</div><!-- End Stage name -->
															</div>
															</a>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
						</div><!-- Ends INBOX -->
						
						<!-- TODAY ACTIVITIES -->
						<div id="todaytab" style="min-height: 600px">
							<div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="listStartTodayActivities" var="listStartTodayActivities">
															<li id="row">
															<a href="/timeSheetManager2bScene/manager/mainManager?alertFlag=1&alertNameSel=<s:property value="%{#listStartTodayActivities.alertName}"/>&alertDateSel=<s:property value="%{#listStartTodayActivities.alertDate}"/>&projectIDSelected=<s:property value="%{#listStartTodayActivities.projectID}"/>#alert-section">
															<div id="activitybox">
																<s:div id="%{'staffIcon' + #countAct}" style="float: left;width: 10%;overflow: hidden; padding-top: 10px; padding-bottom: 10px" ><!-- Start canvas div -->
																	<img class="profile-pic media-object pull-left img-rounded" data-name='<s:property value="%{#listStartTodayActivities.mapPChargeIni}"/>' data-background-color='<s:property value="%{#listStartTodayActivities.mapColorCode}"/>'>
																</s:div><!-- End Canvas -->
																<div style="float: left;width: 58%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#listStartTodayActivities.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Alert: <strong><s:property value="%{#listStartTodayActivities.alertName}"/></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par">Person in Charge: <s:property value="%{#listStartTodayActivities.mapPCharge}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par">Alert Date: <s:property value="%{#listStartTodayActivities.alertDate}"/></p>
																</div><!-- End progress -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par">Town Planner:&nbsp;<s:property value="%{#listStartTodayActivities.councilName}"/>&nbsp;&nbsp;&nbsp;Email:&nbsp;<s:property value="%{#listStartTodayActivities.councilEmail}"/></p>
																</div><!-- End Stage name -->
															</div>
															</a>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							
						</div> <!-- Ends TODAY ACTIVITIES -->	
						
						<!-- THIS WEEK ACTIVITIES -->
						<div id="thisweektab" style="min-height: 600px">
						<div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="listStartWeekActivities" var="listStartWeekActivities">
															<li id="row">
															<a href="/timeSheetManager2bScene/manager/mainManager?alertFlag=1&alertNameSel=<s:property value="%{#listStartWeekActivities.alertName}"/>&alertDateSel=<s:property value="%{#listStartWeekActivities.alertDate}"/>&projectIDSelected=<s:property value="%{#listStartWeekActivities.projectID}"/>#alert-section">
															<div id="activitybox">
																<s:div id="%{'staffIcon' + #countAct}" style="float: left;width: 10%;overflow: hidden; padding-top: 10px; padding-bottom: 10px" ><!-- Start canvas div -->
																	<img class="profile-pic media-object pull-left img-rounded" data-name='<s:property value="%{#listStartWeekActivities.mapPChargeIni}"/>' data-background-color='<s:property value="%{#listStartWeekActivities.mapColorCode}"/>'>
																</s:div><!-- End Canvas -->
																<div style="float: left;width: 58%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#listStartWeekActivities.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Alert: <strong><s:property value="%{#listStartWeekActivities.alertName}"/></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par">Person in Charge: <s:property value="%{#listStartWeekActivities.mapPCharge}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par">Alert Date: <s:property value="%{#listStartWeekActivities.alertDate}"/></p>
																</div><!-- End progress -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par">Town Planner:&nbsp;<s:property value="%{#listStartWeekActivities.councilName}"/>&nbsp;&nbsp;&nbsp;Email:&nbsp;<s:property value="%{#listStartWeekActivities.councilEmail}"/></p>
																</div><!-- End Stage name -->
															</div>
															</a>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
														
													</ul>
										</div>
						</div>
						
						<!-- NEXT WEEK -->
						<div id="nextweektab" style="min-height: 600px">
						<div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="listStartNextWeekActivities" var="listStartNextWeekActivities">
															<li id="row">
															<a href="/timeSheetManager2bScene/manager/mainManager?alertFlag=1&alertNameSel=<s:property value="%{#listStartNextWeekActivities.alertName}"/>&alertDateSel=<s:property value="%{#listStartNextWeekActivities.alertDate}"/>&projectIDSelected=<s:property value="%{#listStartNextWeekActivities.projectID}"/>#alert-section">
															<div id="activitybox">
																<s:div id="%{'staffIcon' + #countAct}" style="float: left;width: 10%;overflow: hidden; padding-top: 10px; padding-bottom: 10px" ><!-- Start canvas div -->
																	<img class="profile-pic media-object pull-left img-rounded" data-name='<s:property value="%{#listStartNextWeekActivities.mapPChargeIni}"/>' data-background-color='<s:property value="%{#listStartNextWeekActivities.mapColorCode}"/>'>
																</s:div><!-- End Canvas -->
																<div style="float: left;width: 58%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#listStartNextWeekActivities.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Alert: <strong><s:property value="%{#listStartNextWeekActivities.alertName}"/></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par">Person in Charge: <s:property value="%{#listStartNextWeekActivities.mapPCharge}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par">Alert Date: <s:property value="%{#listStartNextWeekActivities.alertDate}"/></p>
																</div><!-- End progress -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par">Town Planner:&nbsp;<s:property value="%{#listStartNextWeekActivities.councilName}"/>&nbsp;&nbsp;&nbsp;Email:&nbsp;<s:property value="%{#listStartNextWeekActivities.councilEmail}"/></p>
																</div><!-- End Stage name -->
															</div>
															</a>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
						</div>
						
						<!-- THIS MONTH -->
						<div id="monthtab" style="min-height: 600px">
						<div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="listStartMonthActivities" var="listStartMonthActivities">
															<li id="row">
															<a href="/timeSheetManager2bScene/manager/mainManager?alertFlag=1&alertNameSel=<s:property value="%{#listStartMonthActivities.alertName}"/>&alertDateSel=<s:property value="%{#listStartMonthActivities.alertDate}"/>&projectIDSelected=<s:property value="%{#listStartMonthActivities.projectID}"/>#alert-section">
															<div id="activitybox">
																<s:div id="%{'staffIcon' + #countAct}" style="float: left;width: 10%;overflow: hidden; padding-top: 10px; padding-bottom: 10px" ><!-- Start canvas div -->
																	<img class="profile-pic media-object pull-left img-rounded" data-name='<s:property value="%{#listStartMonthActivities.mapPChargeIni}"/>' data-background-color='<s:property value="%{#listStartMonthActivities.mapColorCode}"/>'>
																</s:div><!-- End Canvas -->
																<div style="float: left;width: 58%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#listStartMonthActivities.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Alert: <strong><s:property value="%{#listStartMonthActivities.alertName}"/></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par">Person in Charge: <s:property value="%{#listStartMonthActivities.mapPCharge}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 30%;height: 20px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par">Alert Date: <s:property value="%{#listStartMonthActivities.alertDate}"/></p>
																</div><!-- End progress -->
																<div style="float: left;width: 58%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par">Town Planner:&nbsp;<s:property value="%{#listStartMonthActivities.councilName}"/>&nbsp;&nbsp;&nbsp;Email:&nbsp;<s:property value="%{#listStartMonthActivities.councilEmail}"/></p>
																</div><!-- End Stage name -->
															</div>
															</a>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
						
						</div>

		            
		            </div><!-- end section --> 
            	
            	</div>
            	
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<!--  
                    <div style="float: right;">
                    <a href="/timeSheetManager2bScene/projectTasks/addProjectActivity.action?selectedProjectID=<s:property value="%{selectedProjectID}"/>&selectedProjetName=<s:property value="%{selectedProjetName}"/>" 
                    class="button btn-primary button-left">Add tasks</a>
                    
                    </div>
                    -->
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>

 </html> 
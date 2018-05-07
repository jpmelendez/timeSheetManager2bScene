<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<style>
.alert {
    padding: 20px;
    background-color: #2196F3;
    color: white;
}

.closebtn {
    margin-left: 15px;
    color: white;
    font-weight: bold;
    float: right;
    font-size: 22px;
    line-height: 20px;
    cursor: pointer;
    transition: 0.3s;
}

.closebtn:hover {
    color: black;
}

.fixed {
    position:fixed;
    top:0;
}

.smart-forms .section {
    margin-bottom: 10px;
}
</style>
<script>
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
var mapActivityManager = {
		 basePath: function () { return '/timeSheetManager2bScene'; },

		 // Loads the activity data requested from the app server via ajax request and Shows a form with activity details,
		 showActivityDetails: function (mapActId) {
			 $("#target").html("");
		        if (mapActId == null) return;
		        var activitySelected = {
                        "activitySelected": mapActId
                    };
		        $.ajax({
		        	url: "/timeSheetManager2bScene/readActivity.action",
                    data: JSON.stringify(activitySelected),
		            cache: false,
		            dataType: 'json',
		            contentType: 'application/json',
                    type: 'POST',
                    async: true,
		            success: function (res) {
						var jsonOb = $.parseJSON(res.activityDataJson);
						var checkedTmp = jsonOb.activityDone;
						$("#activityDetailsDiv").css("display", "block");
						$('#activityDueDate').attr('value', res.strDueDate);
		                $('#activityNote').attr('value', jsonOb.descPA);
		                $('#activityIDhidden').attr('value', jsonOb.projectActivityID);
		                if (checkedTmp != false)
				        {
		                	$('#activityDone').attr('checked', true);
				        }else{$('#activityDone').attr('checked', false);}
		            }
		        });
		    },

		    saveActivityDetails: function () {
		        if (!confirm('Do you want to save changes?')) return
		        if ($('#activityDone').is(":checked"))
		        {
		          var checkedVal = "true";
		        }else{var checkedVal = "false";}
		        var DataTmp = {
		        		"strDueDate": $('#activityDueDate').val(),
                        "strActNote": $('#activityNote').val(),
                        "activityDone": checkedVal,
                        "activitySelected": $('#activityIDhidden').val()
                    };
		        $.ajax({
		            url: "/timeSheetManager2bScene/updateActivity.action",
		            data: JSON.stringify(DataTmp),
		            dataType: 'json',
		            contentType: 'application/json',
		            type: 'POST',
		            async: true,
		            success: function (res) {
		            	var jsonObMsg = res.msgApp;
		                alert(jsonObMsg);
		                $("#activityDetailsDiv").css("display", "none");
		            }
		        });
		    },

}


$(document).ready(function() {


	$(window).bind('scroll', function () {
	    if ($(window).scrollTop() > 50) {
	        $('.alert').addClass('fixed');
	    } else {
	        $('.alert').removeClass('fixed');
	    }
	});
	/****Table consultants*****/
	$('#viewprojectStages').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	 "bSort" : false,
    	 "bFilter" : false
        });

	/****Table utility*****/
	$('#utility-table').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	 "bSort" : false,
    	 "bFilter" : false
        });

    /***** open alert tab ***/
    
     var alertTab = function() {

           var currentUrl = window.location.href;

           if (currentUrl.indexOf("utilityCheckList") != -1) {
        	   $( "#alert-content-9" ).css({"display": ""});
        	   return;
           } else if ((currentUrl.indexOf("consultantCheckList") != -1) || (currentUrl.indexOf("Consultant") != -1)) {
        	   $( "#alert-content-8" ).css({"display": ""});
        	   return;
           } else if (currentUrl.indexOf("RFI") != -1) {
        	   $( "#alert-content-1" ).css({"display": ""});
        	   return;
           }  else if (currentUrl.indexOf("Advertising") != -1) {
        	   $( "#alert-content-2" ).css({"display": ""});
        	   return;
           } else if (currentUrl.indexOf("NOD") != -1) {
        	   $( "#alert-content-3" ).css({"display": ""});
        	   return;
           }else if (currentUrl.indexOf("Condition") != -1) {
        	   $( "#alert-content-4" ).css({"display": ""});
        	   return;
           }else if (currentUrl.indexOf("VCAT") != -1) {
        	   $( "#alert-content-5" ).css({"display": ""});
        	   return;
           } else {
        	   return;
           }

     };

     alertTab();

	/********Tooltip **********/
	$('[data-toggle=tooltip]').tooltip(); 
	/********Alerts Calendars **********/
	$("#rfiConditionDate").datepicker({
		dateFormat: "dd/mm/yy"
	});
	$("#rfiConditionDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#rfiDueDate").datepicker({
		dateFormat: "dd/mm/yy"
	});
	$("#rfiDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#adverReceivedDate").datepicker({
		dateFormat: "dd/mm/yy"
		}).on('change', function(dateStr, inst) {
			//var nights = parseInt($('#projectTime').val()) * 7;
			var nights = parseInt("14");
			var depart = $(this).datepicker('getDate'); 		
	        //var depart = $.datepicker.parseDate('dd/mm/yy', dateText);
	        depart.setDate(depart.getDate() + nights);
	        $("#adverDueDate").datepicker('setDate', depart);
	   });
	$("#adverReceivedDate").inputmask({ alias: "date", "clearIncomplete": true});
	$("#adverDueDate").datepicker({
		dateFormat: "dd/mm/yy"
	});
	$("#adverDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#adverAlertDate").datepicker({
		dateFormat: "dd/mm/yy"
	});
	$("#adverAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#nodeReceiveDate").datepicker({
		dateFormat: "dd/mm/yy"
	}).on('change', function(dateStr, inst) {
			//var nights = parseInt($('#projectTime').val()) * 7;
			var nights = parseInt("30");		
			var depart = $(this).datepicker('getDate'); 		
	        //var depart = $.datepicker.parseDate('dd/mm/yy', dateText);
	        depart.setDate(depart.getDate() + nights);
	        $("#nodAlertDate").datepicker({
	            dateFormat: 'dd/mm/yy'
	        }).datepicker('setDate', depart)
	       // $('#dueDateSTR').val($.datepicker.formatDate( dateFormat, depart));
	});
	$("#nodeReceiveDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#nodAlertDate").datepicker({
		dateFormat: "dd/mm/yy"
	});
	$("#nodAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#condReceiveDate").datepicker({
		dateFormat: "dd/mm/yy"
	}).on('change', function(dateStr, inst) {
			//var nights = parseInt($('#projectTime').val()) * 7;
			var nights = parseInt("730");		
			var depart = $(this).datepicker('getDate'); 		
	        //var depart = $.datepicker.parseDate('dd/mm/yy', dateText);
	        depart.setDate(depart.getDate() + nights);
	        $("#condAlertDate").datepicker({
	            dateFormat: 'dd/mm/yy'
	        }).datepicker('setDate', depart)
	       // $('#dueDateSTR').val($.datepicker.formatDate( dateFormat, depart));
	});
	$("#condReceiveDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#condAlertDate").datepicker({
		dateFormat: "dd/mm/yy"
	});
	$("#condAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#vcatReceiveDate").datepicker({
		dateFormat: "dd/mm/yy"
	});
	$("#vcatReceiveDate").inputmask({ alias: "date", "clearIncomplete": true });
	$("#vcatHearing").datepicker({
		dateFormat: "dd/mm/yy"
	}).on('change', function(dateStr, inst) {
			//var nights = parseInt($('#projectTime').val()) * 7;
			var nights = parseInt("180");		
			var depart = $(this).datepicker('getDate'); 		
	        //var depart = $.datepicker.parseDate('dd/mm/yy', dateText);
	        depart.setDate(depart.getDate() + nights);
	        $("#vcatAlertDate").datepicker({
	            dateFormat: 'dd/mm/yy'
	        }).datepicker('setDate', depart)
	       // $('#dueDateSTR').val($.datepicker.formatDate( dateFormat, depart));
	});
	$("#vcatHearing").inputmask({ alias: "date", "clearIncomplete": true });
	$( "#accordion" ).accordion({
	     heightStyle: 'fill',
	     autoHeight: false
	   });
	$('#photoAndBoardData').change(function() {
		if($(this).is(":checked")) {
			$('.myCheckbox').attr('checked', true);
		      return;
		   }
}); 

	$(".activity-control, .shown").click(function(){
		var trIDtmp = $(this).attr('id');
		var actCtrl = '#' + trIDtmp;
    	var activityRows = '.target-stage-' + trIDtmp;
		
    	if ( $(this).hasClass( "shown" )) {
            // This row is already open - close it
           $(this).removeClass( "shown" ).addClass( "activity-control" );
           $( activityRows ).css({"display": "none", "border-bottom": "1px solid #ddd"});
           
        }
        else {
            // Open this row
        	$(this).removeClass( "activity-control" ).addClass( "shown" );
        	$( activityRows ).css({"display": "", "border-bottom": "1px solid #ddd"});
        }
    });
	
	 $('.editActivitybutton').click(function (e) {
	        e.preventDefault();
	        var actIDtmp = $(this).attr('id');
	        mapActivityManager.showActivityDetails(actIDtmp);
	    });

	 $("#activityDueDate").datepicker({
			dateFormat: "dd/mm/yy",
			defaultDate: "+1w"
		});

	 $("#activityDueDate").inputmask({ alias: "date", "clearIncomplete": true });

	 $("#cancel, #close").click(function() {
		 $(this).parent().parent().hide();
		 });

	 $('#sendActivity').click(function (e) {
	        e.preventDefault();
	        mapActivityManager.saveActivityDetails();
	    });

	 $('#addTask', '#addTaskLink', '#addTaskName').click(function (e) {
	        e.preventDefault();
	        document.getElementById('addTaskLink').click();	
	    });

	 $('.alert-trigger').click(function (e) {
	        e.preventDefault();
	        var targetID = $(this).attr('id');
	        var targetContent = "#alert-content-"+targetID
	        if($(targetContent).css('display') == 'none')
	        {
	        	$( targetContent ).css({"display": ""});
	        }else{
	        	$( targetContent ).css({"display": "none"});
		        }
	    });
	 $("#vcatAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#rfiSubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#conSubmitDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#condSubmitAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#condSubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#planSubmitDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#planSubmitAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#planSubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
     /**** Delete pp condition date **/
     $( "#planSubmitDueDate" ).change(function() {
    	 $("#condAlertDate").val("");
		 $("#planSubmitAlertDate").val("");
		});
	 $("#landscapeSubmitDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#landscapeSubmitAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#landscapeSubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
	 $( "#landscapeSubmitDueDate" ).change(function() {
		 $("#landscapeSubmitAlertDate").val("");
		});
	 $("#drainageSubmitDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#drainageSubmitAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#drainageSubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
	 $( "#drainageSubmitDueDate" ).change(function() {
		 $("#drainageSubmitAlertDate").val("");
		});
	 $("#wasteSubmitDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#wasteSubmitAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#wasteSubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
	 $( "#wasteSubmitDueDate" ).change(function() {
		 $("#wasteSubmitAlertDate").val("");
		});
	 $("#sustainabilitySubmitDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#sustainabilitySubmitAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#sustainabilitySubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
	 $( "#sustainabilitySubmitDueDate" ).change(function() {
		 $("#sustainabilitySubmitAlertDate").val("");
		});
	 $("#reportSubmitDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#reportSubmitAlertDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#reportSubmitDueDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#vcatAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#rfiSubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#conSubmitDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#condSubmitAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#condSubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#planSubmitDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#planSubmitAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#planSubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#landscapeSubmitDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#landscapeSubmitAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#landscapeSubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#drainageSubmitDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#drainageSubmitAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#drainageSubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#wasteSubmitDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#wasteSubmitAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#wasteSubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#sustainabilitySubmitDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#sustainabilitySubmitAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#sustainabilitySubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#vcatResultDate").datepicker({dateFormat: "dd/mm/yy"});
	 $("#vcatResultDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#reportSubmitDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#reportSubmitAlertDate").inputmask({ alias: "date", "clearIncomplete": true });
	 $("#reportSubmitDueDate").inputmask({ alias: "date", "clearIncomplete": true });
   		
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
<s:set var="flag" value="1"></s:set>
<s:if test="%{alertFlag == #flag}">
	 <div class="alert">
  		<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
  		<strong>Alert!</strong>
  		<p>Selected Alert: <s:property value="%{alertNameSel}"/></p>
        <p>Date: <s:property value="%{alertDateSel}"/></p>
	</div>
</s:if>

	<div class="smart-wrap">
    	<div class="smart-forms smart-container wrap-2">
    	<form method="post" action="saveAdditionalData" id="smart-form">		
							<div class="form-header header-primary">
								<h4><i class="fa fa-pencil-square"></i>Project Overview</h4>
							</div><!-- end .form-header section -->
										
							<div class="form-body" style="padding: 40px 30px 20px">
									
										<s:set value="%{#projectUniqueResult.projectID}" var="pID"></s:set>
										<s:set value="%{#projectUniqueResult.projectName}" var="pName"></s:set>
											 <div class="spacer-b30">
						                    	<div class="tagline"><span> Project Details </span></div><!-- .tagline -->
						               		 </div>
						               		 <!--  
						               		 <div style="display: inline-block; float: right;" id="editProjectMenu">
					                        <div id="editProject" style="height: 25px; width: 25px; display: inline-block; float: left;">
					                        	<a id="editProjectLink" style="height: 24px; width: 24px" href="#">
													<img src="/timeSheetManager2bScene/html/img/edit_black_20px.png" alt="edit" title="edit project">
												</a>
					                        </div>
					                      
					                        <div id="addProject" style="height: 25px; width: 75px; display: inline-block; float: right;">
					                        	<span>Edit Project</span>
					                        </div>
					                    </div><!-- end section -->
						                		<div class="section">
						                		<label class="field-label"><Strong>Project name:  <s:property value="projectUniqueResult.projectName"/></Strong></label>
						                		<s:hidden name="projectIDSelected" value="%{projectUniqueResult.projectID}"></s:hidden>	
						                    </div><!-- end section -->
						                    <div class="section">
						                    <label class="field-label"><Strong>Customer:  </Strong><s:property value="projectUniqueResult.client"/></label>
						                    </div><!-- end section --> 
						                    <div class="frm-row">
				                    
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Person in charge:  </Strong></label>
				                                <label class="field-label">
								                 <s:iterator  value="pChargeList" var="pChargeList">
								                     	<s:property value="%{#pChargeList.nameInitials}"/>,&nbsp;
								                 </s:iterator>
								                 </label>
				                            </div><!-- end section -->                     
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Project State: </Strong><s:property value="projectUniqueResult.projectStatus"/></label>
				                            </div><!-- end section -->
				                            <div class="section colm colm4">
				                                 <label class="field-label"><Strong>Priority:  </Strong><s:property value="projectUniqueResult.priority"/></label>
				                            </div><!-- end section -->
				                    		</div><!-- end .frm-row section --> 
						                   
						                    <div class="frm-row">
					                            <div class="section colm colm4">
					                                <label class="field-label"><Strong>Project Category: </Strong><s:property value="projectUniqueResult.numEA"/>-<s:property value="projectUniqueResult.projecCat"/></label>
					                            </div><!-- end section -->                     
					                            <div class="section colm colm4">
					                                 <label class="field-label"><Strong>Start date:  </Strong><s:property value="projectUniqueResult.jobStart"/></label>
					                            </div><!-- end section -->
					                            <div class="section colm colm4">
					                                <label class="field-label"><Strong>Due date:  </Strong><s:property value="projectUniqueResult.jobDueDate"/></label>
					                            </div><!-- end section -->
                    						</div><!-- end .frm-row section --> 
										
										<div class="spacer-b30">
							                    	<div class="tagline"><span> Tasks </span></div><!-- .tagline -->
							            </div>	
							            <div style="display: inline-block; float: right;" id="addActivityMenu">
					                        <div id="addTask" style="height: 25px; width: 25px; display: inline-block; float: left;">
					                        	<a id="addTaskLink" style="height: 24px; width: 24px" href="/twobSceneWebApp/projectTasks/addProjectActivity.action?selectedProjectID=<s:property value="projectUniqueResult.projectID"/>&selectedProjetName=<s:property value="projectUniqueResult.projectName"/>">
													<img src="/twobSceneWebApp/html/img/add_black.png" alt="add" title="add task">
												</a>
					                        </div>
					                        <div id="addTaskName" style="height: 25px; width: 75px; display: inline-block; float: right;">
					                        	<span>Add Tasks</span>
					                        </div>
					                    </div><!-- end section -->   <br/> <br/>
							            <s:if test="%{!listStagesByProjectID.isEmpty()}"> 
										<s:iterator value="listStagesByProjectID" var="listStagesByProjectID">
											<div id="stage-content">
											<table id="activity-content" class="activity-table">
												<tr>
													<td class="activity-control" id="<s:property value="%{#listStagesByProjectID.stageID}"/>"></td>
													<td><s:property value="stageName" /></td>
													<td></td>
												</tr>
												<s:iterator value="listTasksByProjectID" var="listTasksByProjectID">
													<s:if test="%{#listTasksByProjectID.stageID == #listStagesByProjectID.stageID}">
														
															<s:iterator value="listActivityByProjectID" var="listActivityByProjectID">
															<s:if test="%{#listActivityByProjectID.task_id == #listTasksByProjectID.taskID}">
															<tr class ="target-stage-<s:property value="%{#listActivityByProjectID.stage_id}"/>" style="display: none; border-bottom: 1px solid #ddd">
																<td class=""><s:hidden id="mapActID" value="%{#listActivityByProjectID.mapProjectActID}"></s:hidden>  </td>
																<td class="activity-row"><strong><s:property value="taskName"/> : </strong><s:property value="%{#listActivityByProjectID.act_name}"/></td>
																<td class="activity-row">&nbsp&nbsp&nbsp&nbsp&nbsp</td>
																<td class="activity-row"><a class="editActivitybutton" href="#" id='<s:property value="%{#listActivityByProjectID.mapProjectActID}"/>'>Edit</a></td>
															</tr>
															</s:if>
															</s:iterator>
														
													</s:if>
												</s:iterator>
											</table>
											</div>
										</s:iterator>
										<br/><br/>
										</s:if> 
										<s:else>
										  <div class="section">
			                                	<p>There are no tasks in this project yet.</p>
			                                </div>
										</s:else>
										<div class="spacer-b30">
							             <div class="tagline"><span> Additional Information </span></div><!-- .tagline -->
							        </div>
							        
							         <div class="frm-row">
								         <div class="section colm colm6">
				                                <s:if test="%{projectUniqueResult.photoSite}">
							         	 	<label class="switch block">
							         		  <input type="checkbox" name="photoAndBoardData" id="photoAndBoardData" value="1" checked="checked">
				                              <span class="switch-label" data-on="YES" data-off="NO"></span>
				                              <span> Photo Site </span>
				                            </label>
							         	</s:if><s:else>
							         		<label class="switch block">
							         		  <input type="checkbox" name="photoAndBoardData" id="photoAndBoardData" value="1">
				                              <span class="switch-label" data-on="YES" data-off="NO"></span>
				                              <span> Photo Site </span>
				                            </label>
							         	</s:else>
	                            		</div><!-- end section -->
			                            <div class="section colm colm6">
			                                <label class="field select">
                    							<s:select 
												id="phaseData"
												name="phaseData"
												list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
												value="projectUniqueResult.phaseID" />
												<i class="arrow double"></i>  
											</label>  
			                            </div><!-- end section -->                     
                    				</div><!-- end .frm-row section -->
                    				
							      <div class="section">
				                    	<label class="field-label"><Strong>Project Note: </Strong>
				                        	<textarea class="gui-textarea" id="noteMain" name="noteMain" placeholder="note"><s:property value="%{projectUniqueResult.projectDescription}" /></textarea>
				                        </label>
			                    	</div><!-- end section -->
			                    	 <div class="section">
			                    	 <label class="field-label"><Strong>Town Planner Note: </Strong>
				                        	<textarea class="gui-textarea" id="townPlannerNote" name="townPlannerNote" placeholder="Town Planner Note"><s:property value="%{projectUniqueResult.councilNote}" /></textarea>
				                      </label>
			                    	</div><!-- end section -->
			                    	<div class="spacer-b30" id="alert-section">
							             <div class="tagline"><span> Alerts </span></div><!-- .tagline -->
							        </div>
							        
							        <!--********* Start New Submit to Council *********-->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="6" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to edit alert"></a>
		                            </div><!-- end section -->     
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">Submit to Council  </label>
		                                <input type="hidden" name="condOneSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="condOneSubmitStringData.alertID" value="<s:property value="%{condOneSubmitAlertModel.alertID}"/>">
		                                <input type="hidden" name="condOneSubmitStringData.alertCategoryID" value="9">
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
		                                <s:if test="%{condOneSubmitAlertModel.alertStatusBoolean}">
										         	 	<label class="switch block">
										         		  <input type="checkbox" name="condOneSubmitStringData.alertStatus" id="alertStatusSubmitNew" value="1" checked="checked">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:if><s:else>
										         		<label class="switch block">
										         		  <input type="checkbox" name="condOneSubmitStringData.alertStatus" id="alertStatusSubmitNew" value="1">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:else>	
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->
		                    		<div id="alert-content-6" class="display-content" style="display: none">
			                    		<div class="frm-row">
				                            <div class="section colm colm6">
				                                <label class="field-label"><Strong>Submit:</Strong></label>
				                        							<input type="text" name="condOneSubmitStringData.receivedDate" id="conSubmitDate" class="gui-input" value="<s:property value="%{condOneSubmitAlertModel.receivedDate}" />"> 
				                            </div>   
				                            <div class="section colm colm6">
				                             <label class="field-label"><Strong>Alert Date:</Strong></label>
				                               <input type="text" name="condOneSubmitStringData.alertDate" id="condSubmitAlertDate" class="gui-input" value="<s:property value="%{condOneSubmitAlertModel.alertDate}" />"> 
				                            </div>                
				                            <!-- Jade change only need alert date -->   
				                            <!--           
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Due date:</Strong></label>
				                        							<input type="text" name="condOneSubmitStringData.dueDate" id="condSubmitDueDate" class="gui-input" value="<s:property value="%{condOneSubmitAlertModel.dueDate}" />"> 
				                            </div>
				                            --> 
	                    				</div> 
	                    				<div class="section">
	                    				<label class="field-label"><Strong>Note:</Strong></label>
				                    	<label for="rfi-note" class="field">
				                        	<textarea class="gui-textarea" id="submitNewnote" name="condOneSubmitStringData.alertNote" placeholder="note"><s:property value="%{condOneSubmitAlertModel.alertNote}" /></textarea>
				                        </label>
			                    	</div><!-- end section -->
		                    		</div>
							        
							        <!-- Start RFI -->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="1" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to edit alert"></a>
		                            </div><!-- end section -->              
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">RFI  </label>
		                                <input type="hidden" name="rfiStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="rfiStringData.alertID" value="<s:property value="%{rfiAlertModel.alertID}"/>">
		                                <input type="hidden" name="rfiStringData.alertCategoryID" value="1">
		                                
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
		                                <s:if test="%{rfiAlertModel.alertStatusBoolean}">
										         	 	<label class="switch block">
										         		  <input type="checkbox" name="rfiStringData.alertStatus" id="alertStatusRFI" value="1" checked="checked">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:if><s:else>
										         		<label class="switch block">
										         		  <input type="checkbox" name="rfiStringData.alertStatus" id="alertStatusRFI" value="1">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:else>	
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->
		                    		<div id="alert-content-1" class="display-content" style="display: none">
			                    		<div class="frm-row">
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>RFI Received:</Strong></label>
				                        							<input type="text" name="rfiStringData.receivedDate" id="rfiConditionDate" class="gui-input" value="<s:property value="%{rfiAlertModel.receivedDate}" />"> 
				                            </div><!-- end section -->                     
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Lapse Date:</Strong></label>
				                        							<input type="text" name="rfiStringData.dueDate" id="rfiDueDate" class="gui-input" value="<s:property value="%{rfiAlertModel.dueDate}" />"> 
				                            </div><!-- end section -->
				                            <div class="section colm colm4">
				                             <label class="field-label"><Strong>Alert time:</Strong></label>
				                                <label class="field select">
												                        <s:select 
												                           id="rfiConditionWeeksData"
																			headerKey="" headerValue="Select RFI Timing..."
																			list="#{'7':'7 Days', '14':'14 Days', '28':'28 Days', '36':'36 Days'}" 
																			name="rfiStringData.timeAlert" 
																			value="%{rfiAlertModel.timeAlert}" />
																	       <i class="arrow double"></i>
																		              
											                        </label>  
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section --> 
	                    				<div class="section">
	                    				<label class="field-label"><Strong>Note:</Strong></label>
				                    		<label for="rfi-note" class="field ">
				                        		<textarea class="gui-textarea" id="rfinote" name="rfiStringData.alertNote" placeholder="note"><s:property value="%{rfiAlertModel.alertNote}" /></textarea>
				                        	</label>
			                    		</div><!-- end section -->
			                    	  <!-- ##### NEW ALERT ### -->
			                    	   <div class="section">
				                    		<label class="field-label"><Strong>Submit to Council:</Strong></label>
				                        	<input type="text" name="rfiSubmitStringData.dueDate" id="rfiSubmitDueDate" class="gui-input" value="<s:property value="%{rfiSubmitAlertModel.dueDate}" />"> 
				                        	<input type="hidden" name="rfiSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                	<input type="hidden" name="rfiSubmitStringData.alertID" value="<s:property value="%{rfiSubmitAlertModel.alertID}"/>">
		                                	<input type="hidden" name="rfiSubmitStringData.alertCategoryID" value="8">
			                    		</div><!-- end section -->
			                    	
		                    		</div>
		                    		<!-- Start Advertising -->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="2" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to edit alert"></a>
		                            </div><!-- end section -->     
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">Advertising  </label>
		                                <input type="hidden" name="adverStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="adverStringData.alertID" value="<s:property value="%{advertisingAlertModel.alertID}"/>">
		                                <input type="hidden" name="adverStringData.alertCategoryID" value="2">
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
		                                <s:if test="%{advertisingAlertModel.alertStatusBoolean}">
										         	 	<label class="switch block">
										         		  <input type="checkbox" name="adverStringData.alertStatus" id="alertStatusAdver" value="1" checked="checked">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:if><s:else>
										         		<label class="switch block">
										         		  <input type="checkbox" name="adverStringData.alertStatus" id="alertStatusAdver" value="1">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:else>	
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->
		                    		<div id="alert-content-2" class="display-content" style="display: none">
			                    		<div class="frm-row">
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Advertising Received:</Strong></label>
				                        							<input type="text" name="adverStringData.receivedDate" id="adverReceivedDate" class="gui-input" value="<s:property value="%{advertisingAlertModel.receivedDate}" />"> 
				                            </div><!-- end section -->                     
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Due Date:</Strong></label>
				                        							<input type="text" name="adverStringData.dueDate" id="adverDueDate" class="gui-input" value="<s:property value="%{advertisingAlertModel.dueDate}" />"> 
				                            </div><!-- end section -->
				                            <div class="section colm colm4">
				                             <label class="field-label"><Strong>Alert Date:</Strong></label>
				                               <input type="text" name="adverStringData.alertDate" id="adverAlertDate" class="gui-input" value="<s:property value="%{advertisingAlertModel.alertDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section --> 
	                    				<div class="section">
	                    				<label class="field-label"><Strong>Note:</Strong></label>
				                    	<label for="rfi-note" class="field">
				                        	<textarea class="gui-textarea" id="advernote" name="adverStringData.alertNote" placeholder="note"><s:property value="%{advertisingAlertModel.alertNote}" /></textarea>
				                        </label>
			                    	</div><!-- end section -->
		                    		</div>
		                    		
			                    	<!-- Start NOD -->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="3" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to edit alert"></a>
		                            </div><!-- end section -->              
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">NOD  </label>
		                                <input type="hidden" name="nodStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="nodStringData.alertID" value="<s:property value="%{nodAlertModel.alertID}"/>">
		                                <input type="hidden" name="nodStringData.alertCategoryID" value="3">
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
		                                <s:if test="%{nodAlertModel.alertStatusBoolean}">
										         	 	<label class="switch block">
										         		  <input type="checkbox" name="nodStringData.alertStatus" id="nodStatus" value="1" checked="checked">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:if><s:else>
										         		<label class="switch block">
										         		  <input type="checkbox" name="nodStringData.alertStatus" id="nodStatus" value="1">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:else>	
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->
		                    		<div id="alert-content-3" class="display-content" style="display: none">
			                    		<div class="frm-row">
				                            <div class="section colm colm6">
				                                <label class="field-label"><Strong>NOD Received:</Strong></label>
				                        							<input type="text" name="nodStringData.receivedDate" id="nodeReceiveDate" class="gui-input" value="<s:property value="%{nodAlertModel.receivedDate}" />"> 
				                            </div><!-- end section -->                     
				                            <div class="section colm colm6">
				                            <label class="field-label"><Strong>Alert Date:</Strong></label>
				                               <input type="text" name="nodStringData.dueDate" id="nodAlertDate" class="gui-input" value="<s:property value="%{nodAlertModel.dueDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section --> 
	                    				<div class="section">
	                    				<label class="field-label"><Strong>Note:</Strong></label>
				                    	<label for="rfi-note" class="field">
				                        	<textarea class="gui-textarea" id="nodenote" name="nodStringData.alertNote" placeholder="note"><s:property value="%{nodAlertModel.alertNote}" /></textarea>
				                        </label>
			                    	</div><!-- end section -->
		                    		</div>
		                    		
		                    		<!--********* Start New Council Report *********-->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="7" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to edit alert"></a>
		                            </div><!-- end section -->     
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">Council Report  </label>
		                                <input type="hidden" name="reportSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="reportSubmitStringData.alertID" value="<s:property value="%{reportSubmitAlertModel.alertID}"/>">
		                                <input type="hidden" name="reportSubmitStringData.alertCategoryID" value="15">
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
		                                <s:if test="%{reportSubmitAlertModel.alertStatusBoolean}">
										         	 	<label class="switch block">
										         		  <input type="checkbox" name="reportSubmitStringData.alertStatus" id="reportStatusSubmitNew" value="1" checked="checked">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:if><s:else>
										         		<label class="switch block">
										         		  <input type="checkbox" name="reportSubmitStringData.alertStatus" id="reportStatusSubmitNew" value="1">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:else>	
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->
		                    		<div id="alert-content-7" class="display-content" style="display: none">
			                    		<div class="frm-row">
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Start:</Strong></label>
				                        							<input type="text" name="reportSubmitStringData.receivedDate" id="reportSubmitDate" class="gui-input" value="<s:property value="%{reportSubmitAlertModel.receivedDate}" />"> 
				                            </div>   
				                            <div class="section colm colm4">
				                             <label class="field-label"><Strong>Alert Date:</Strong></label>
				                               <input type="text" name="reportSubmitStringData.alertDate" id="reportSubmitAlertDate" class="gui-input" value="<s:property value="%{reportSubmitAlertModel.alertDate}" />"> 
				                            </div>                
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Received:</Strong></label>
				                        							<input type="text" name="reportSubmitStringData.dueDate" id="reportSubmitDueDate" class="gui-input" value="<s:property value="%{reportSubmitAlertModel.dueDate}" />"> 
				                            </div>
	                    				</div> 
	                    				<div class="section">
	                    				<label class="field-label"><Strong>Note:</Strong></label>
				                    	<label for="rfi-note" class="field">
				                        	<textarea class="gui-textarea" id="reportNewnote" name="reportSubmitStringData.alertNote" placeholder="note"><s:property value="%{reportSubmitAlertModel.alertNote}" /></textarea>
				                        </label>
			                    	</div><!-- end section -->
		                    		</div>
		                    		
		                    		<!-- Start Condition 1 -->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="4" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to edit alert"></a>
		                            </div><!-- end section -->              
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">PP - Conditions / Endorsment  </label>
		                            	<input type="hidden" name="conditionStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="conditionStringData.alertID" value="<s:property value="%{condOneAlertModel.alertID}"/>">
		                                <input type="hidden" name="conditionStringData.alertCategoryID" value="4">
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
		                                <s:if test="%{condOneAlertModel.alertStatusBoolean}">
										         	 	<label class="switch block">
										         		  <input type="checkbox" name="conditionStringData.alertStatus" id="condStatus" value="1" checked="checked">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:if><s:else>
										         		<label class="switch block">
										         		  <input type="checkbox" name="conditionStringData.alertStatus" id="condStatus" value="1">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:else>	
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->
		                    		<div id="alert-content-4" class="display-content" style="display: none">
			                    		<div class="frm-row">
				                            <div class="section colm colm6">
				                                <label class="field-label"><Strong>PP-Condition Received:</Strong></label>
				                        							<input type="text" name="conditionStringData.receivedDate" id="condReceiveDate" class="gui-input" value="<s:property value="%{condOneAlertModel.receivedDate}" />"> 
				                            </div><!-- end section -->                     
				                            <div class="section colm colm6">
				                            <label class="field-label"><Strong>Alert Date:</Strong></label>
				                               <input type="text" name="conditionStringData.dueDate" id="condAlertDate" class="gui-input" value="<s:property value="%{condOneAlertModel.dueDate}" />"> 
				                            </div><!-- end section -->
		                    				</div><!-- end .frm-row section --> 
		                    				<div class="section">
		                    				<label class="field-label"><Strong>Note:</Strong></label>
					                    	<label for="rfi-note" class="field">
					                        	<textarea class="gui-textarea" id="condnote" name="conditionStringData.alertNote" placeholder="note"><s:property value="%{condOneAlertModel.alertNote}" /></textarea>
					                        </label>
			                    		</div><!-- end section -->
			                    		<!-- Header Alerts -->
			                    		<div class="frm-row">
				                    		<div class="section colm colm4">
				                    			<label class="field-label"><Strong>Submit to Council:</Strong></label>
				                    		</div>
				                    		<div class="section colm colm4">
				                    			<label class="field-label"><Strong>Alert:</Strong></label>
				                    		</div>
				                    		<div class="section colm colm4">
				                    			<label class="field-label"><Strong>Endorsed:</Strong></label>
				                    		</div>
			                    		</div>
			                    		 <!-- Extra Alert --> 
	                    				<!-- Submit new Alert -->
	                    				 <!--  
			                    		<div class="frm-row">
			                    		<input type="hidden" name="condOneSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="condOneSubmitStringData.alertID" value="<s:property value="%{condOneSubmitAlertModel.alertID}"/>">
		                                <input type="hidden" name="condOneSubmitStringData.alertCategoryID" value="9">
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Submit:</Strong></label>
				                        							<input type="text" name="condOneSubmitStringData.receivedDate" id="conSubmitDate" class="gui-input" value="<s:property value="%{condOneSubmitAlertModel.receivedDate}" />"> 
				                            </div>   
				                            <div class="section colm colm4">
				                             <label class="field-label"><Strong>Alert Date:</Strong></label>
				                               <input type="text" name="condOneSubmitStringData.alertDate" id="condSubmitAlertDate" class="gui-input" value="<s:property value="%{condOneSubmitAlertModel.alertDate}" />"> 
				                            </div>                
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Endorsed:</Strong></label>
				                        							<input type="text" name="condOneSubmitStringData.dueDate" id="condSubmitDueDate" class="gui-input" value="<s:property value="%{condOneSubmitAlertModel.dueDate}" />"> 
				                            </div>
	                    				</div> --> 
	                    				<!-- Plans new Alert -->
			                    		<div class="frm-row">
			                    		<input type="hidden" name="plansSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="plansSubmitStringData.alertID" value="<s:property value="%{plansSubmitAlertModel.alertID}"/>">
		                                <input type="hidden" name="plansSubmitStringData.alertCategoryID" value="10">
				                            <div class="section colm colm4">
				                                <label class="field-label">Plans:</label>
				                        							<input type="text" name="plansSubmitStringData.receivedDate" id="planSubmitDate" class="gui-input" value="<s:property value="%{plansSubmitAlertModel.receivedDate}" />"> 
				                            </div><!-- end section -->    
				                            <div class="section colm colm4">
				                             <label class="field-label">Plans:</label>
				                               <input type="text" name="plansSubmitStringData.alertDate" id="planSubmitAlertDate" class="gui-input" value="<s:property value="%{plansSubmitAlertModel.alertDate}" />"> 
				                            </div><!-- end section -->                 
				                            <div class="section colm colm4">
				                                <label class="field-label">Plan:</label>
				                        							<input type="text" name="plansSubmitStringData.dueDate" id="planSubmitDueDate" class="gui-input" value="<s:property value="%{plansSubmitAlertModel.dueDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section -->
	                    				<!-- Landscape new Alert -->
			                    		<div class="frm-row">
			                    		<input type="hidden" name="lanscapeSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="lanscapeSubmitStringData.alertID" value="<s:property value="%{lanscapeSubmitAlertModel.alertID}"/>">
		                                <input type="hidden" name="lanscapeSubmitStringData.alertCategoryID" value="11">
				                            <div class="section colm colm4">
				                                <label class="field-label">Landscape:</label>
				                        							<input type="text" name="lanscapeSubmitStringData.receivedDate" id="landscapeSubmitDate" class="gui-input" value="<s:property value="%{lanscapeSubmitAlertModel.receivedDate}" />"> 
				                            </div><!-- end section -->    
				                            <div class="section colm colm4">
				                             <label class="field-label">Landscape:</label>
				                               <input type="text" name="lanscapeSubmitStringData.alertDate" id="landscapeSubmitAlertDate" class="gui-input" value="<s:property value="%{lanscapeSubmitAlertModel.alertDate}" />"> 
				                            </div><!-- end section -->                 
				                            <div class="section colm colm4">
				                                <label class="field-label">Landscape:</label>
				                        							<input type="text" name="lanscapeSubmitStringData.dueDate" id="landscapeSubmitDueDate" class="gui-input" value="<s:property value="%{lanscapeSubmitAlertModel.dueDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section -->
	                    				<!-- Drainage new Alert -->
			                    		<div class="frm-row">
			                    		<input type="hidden" name="drainageSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="drainageSubmitStringData.alertID" value="<s:property value="%{drainageSubmitAlertModel.alertID}"/>">
		                                <input type="hidden" name="drainageSubmitStringData.alertCategoryID" value="12">
				                            <div class="section colm colm4">
				                                <label class="field-label">Drainage:</label>
				                        							<input type="text" name="drainageSubmitStringData.receivedDate" id="drainageSubmitDate" class="gui-input" value="<s:property value="%{drainageSubmitAlertModel.receivedDate}" />"> 
				                            </div><!-- end section -->    
				                            <div class="section colm colm4">
				                             <label class="field-label">Drainage:</label>
				                               <input type="text" name="drainageSubmitStringData.alertDate" id="drainageSubmitAlertDate" class="gui-input" value="<s:property value="%{drainageSubmitAlertModel.alertDate}" />"> 
				                            </div><!-- end section -->                 
				                            <div class="section colm colm4">
				                                <label class="field-label">Drainage:</label>
				                        							<input type="text" name="drainageSubmitStringData.dueDate" id="drainageSubmitDueDate" class="gui-input" value="<s:property value="%{drainageSubmitAlertModel.dueDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section -->
	                    				<!-- Waste new Alert -->
			                    		<div class="frm-row">
			                    		<input type="hidden" name="wasteSubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="wasteSubmitStringData.alertID" value="<s:property value="%{wasteSubmitAlerModel.alertID}"/>">
		                                <input type="hidden" name="wasteSubmitStringData.alertCategoryID" value="13">
				                            <div class="section colm colm4">
				                                <label class="field-label">Waste:</label>
				                        							<input type="text" name="wasteSubmitStringData.receivedDate" id="wasteSubmitDate" class="gui-input" value="<s:property value="%{wasteSubmitAlerModel.receivedDate}" />"> 
				                            </div><!-- end section -->    
				                            <div class="section colm colm4">
				                             <label class="field-label">Waste:</label>
				                               <input type="text" name="wasteSubmitStringData.alertDate" id="wasteSubmitAlertDate" class="gui-input" value="<s:property value="%{wasteSubmitAlerModel.alertDate}" />"> 
				                            </div><!-- end section -->                 
				                            <div class="section colm colm4">
				                                <label class="field-label">Waste:</label>
				                        							<input type="text" name="wasteSubmitStringData.dueDate" id="wasteSubmitDueDate" class="gui-input" value="<s:property value="%{wasteSubmitAlerModel.dueDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section -->
	                    				<!-- Sustainability new Alert -->
			                    		<div class="frm-row">
			                    		<input type="hidden" name="sustainabilitySubmitStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="sustainabilitySubmitStringData.alertID" value="<s:property value="%{sustainabilitySubmitAlerModel.alertID}"/>">
		                                <input type="hidden" name="sustainabilitySubmitStringData.alertCategoryID" value="14">
				                            <div class="section colm colm4">
				                                <label class="field-label">Sustainability:</label>
				                        							<input type="text" name="sustainabilitySubmitStringData.receivedDate" id="sustainabilitySubmitDate" class="gui-input" value="<s:property value="%{sustainabilitySubmitAlerModel.receivedDate}" />"> 
				                            </div><!-- end section -->    
				                            <div class="section colm colm4">
				                             <label class="field-label">Sustainability:</label>
				                               <input type="text" name="sustainabilitySubmitStringData.alertDate" id="sustainabilitySubmitAlertDate" class="gui-input" value="<s:property value="%{sustainabilitySubmitAlerModel.alertDate}" />"> 
				                            </div><!-- end section -->                 
				                            <div class="section colm colm4">
				                                <label class="field-label">Sustainability:</label>
				                        							<input type="text" name="sustainabilitySubmitStringData.dueDate" id="sustainabilitySubmitDueDate" class="gui-input" value="<s:property value="%{sustainabilitySubmitAlerModel.dueDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section -->
		                    		</div>
		                    		
		                    		<!-- Start VCAT 1 -->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="5" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to edit alert"></a>
		                            </div><!-- end section -->              
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">VCAT  </label>
		                            	<input type="hidden" name="vcatStringData.projectID" value="<s:property value="projectUniqueResult.projectID"/>">
		                                <input type="hidden" name="vcatStringData.alertID" value="<s:property value="%{vcatAlerModel.alertID}"/>">
		                                <input type="hidden" name="vcatStringData.alertCategoryID" value="5">
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
		                                <s:if test="%{vcatAlerModel.alertStatusBoolean}">
										         	 	<label class="switch block">
										         		  <input type="checkbox" name="vcatStringData.alertStatus" id="vcatStatus" value="1" checked="checked">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:if><s:else>
										         		<label class="switch block">
										         		  <input type="checkbox" name="vcatStringData.alertStatus" id="vcatStatus" value="1">
							                              <span class="switch-label" data-on="YES" data-off="NO"></span>
							                              <span> Done </span>
							                            </label>
										         	</s:else>	
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->
		                    		<div id="alert-content-5" class="display-content" style="display: none">
		                    		<div class="frm-row">
				                            <div class="section colm colm4">
				                                <label class="field-label"><Strong>Refusal:</Strong></label>
				                        							<input type="text" name="vcatStringData.receivedDate" id="vcatReceiveDate" class="gui-input" value="<s:property value="%{vcatAlerModel.receivedDate}" />"> 
				                            </div><!-- end section -->    
				                            <div class="section colm colm4">
				                             <label class="field-label"><Strong>VCAT Alert:</Strong></label>
				                               <input type="text" name="vcatStringData.alertDate" id="vcatAlertDate" class="gui-input" value="<s:property value="%{vcatAlerModel.alertDate}" />"> 
				                            </div><!-- end section -->                 
				                            <div class="section colm colm4">
				                            <label class="field-label"><Strong>VCAT Hearing:</Strong></label>
				                               <input type="text" name="vcatStringData.dueDate" id="vcatHearing" class="gui-input" value="<s:property value="%{vcatAlerModel.dueDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section -->
		                    		
	                    				<div class="section">
		                    				<label class="field-label"><Strong>Note:</Strong></label>
					                    	<label for="rfi-note" class="field">
					                        	<textarea class="gui-textarea" id="vcatnote" name="vcatStringData.alertNote" placeholder="note"><s:property value="%{vcatAlerModel.alertNote}" /></textarea>
					                        </label>
			                    		</div><!-- end section -->
			                    		<div class="frm-row">
				                            <div class="section colm colm6">
				                            <label class="field-label"><Strong>VCAT Result:</Strong></label>
				                                <label class="field select">
						                           <s:select 
						                           	id="vcatResult"
													list="#{'1':'Win', '2':'Lost'}" 
													name="vcatStringData.vcatResult"
													headerKey="" 
													headerValue="Select VCAT Result..."
													value="vcatAlerModel.vcatResult" />
											       <i class="arrow double"></i>  
						                            <b class="tooltip tip-left-top"><em>VCAT Result</em></b>                    
						                        </label> 
				                            </div><!-- end section -->                     
				                            <div class="section colm colm6">
				                            <label class="field-label"><Strong>VCAT Result Date:</Strong></label>
				                               <input type="text" name="vcatStringData.vcatResultDate" id="vcatResultDate" class="gui-input" value="<s:property value="%{vcatAlerModel.vcatResultDate}" />"> 
				                            </div><!-- end section -->
	                    				</div><!-- end .frm-row section --> 
		                    		</div>
			                    	
			 						<div class="spacer-b30" id="consultant-section">
							             <div class="tagline"><span> Consultant List Information </span></div><!-- .tagline -->
							        </div>		
							        
							        <!-- Start Consultant Table -->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="8" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to display Consultant"></a>
		                            </div><!-- end section -->              
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">Consultant List  </label>   
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
			                    	<label class="block">
		                                <a id="8" href="/timeSheetManager2bScene/consultant/checkListHome.action?projectIDSelected=<s:property value="projectUniqueResult.projectID"/>" class="button" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/edit_black_20px.png" alt="Edit" style="margin-top: 7px; margin-left:-10px" data-toggle="tooltip" data-placement="bottom" title="Click to edit consultant"></a>
		                            </label>
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->  
		                    		<div id="alert-content-8" class="display-content" style="display: none">
			                    	      <div>
	                     <s:set var="count" value="0" />
							<table id="viewprojectStages" cellspacing="0" style="font-family:sans-serif; font-size:10pt;" width="100%">
						    <thead>
						        <tr>
									<th>Consultant</th>
									<th>Quote</th>
									<th>GST</th>
						            <th>Date</th>
						            <th>Approved</th>
						            <th>Received Date</th>
								</tr>
						    </thead>
						    <tbody>
							<s:iterator value="consultantList" var="consultantList">
								<tr>
									<td><strong><s:property value="conCategoryName"/>:&nbsp;
									<s:property value="conCompany"/></strong>
									</td>
									<td>&#x24;<s:property value="%{#consultantList.checkQuote}" /></td>
									<td>
                        			<s:set var="gstVal" value="1"></s:set>
										<s:if test="%{#consultantList.quoteGST == #gstVal}">
										 	<label class="field">
				                              &#10004;               
		                        			</label>
		                            	</s:if>
		                            	<s:else>
		                            		<label class="field">
				                               &nbsp;&nbsp;           
		                        			</label>
		                            	</s:else>
                        			</td>
									<td><s:property value="%{#consultantList.checkDateStart}" /></td>
									<td>
									<s:set var="chApproved" value="1"></s:set>
										<s:if test="%{#consultantList.checkApproved == #chApproved}">
										 	<label class="field">
				                              &#10004;               
		                        			</label>
		                            	</s:if>
		                            	<s:else>
		                            		<label class="field">
				                               &nbsp;&nbsp;           
		                        			</label>
		                            	</s:else>
                        			</td>
									<td><s:property value="%{#consultantList.checkDateReceived}" /></td>
							    </tr>
								</s:iterator>
						    </tbody>
						</table>
					</div>	
		                    		</div> 
		                    		
		                    		<!-- Start Utility Table -->
			                    	<div class="frm-row">
			                    	<div class="section colm colm1">
		                                <a id="9" href="#" class="button alert-trigger" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/show_property.png" alt="Edit" style="margin-top: 3px; margin-left:-14px" data-toggle="tooltip" data-placement="bottom" title="Click to display Utility"></a>
		                            </div><!-- end section -->              
			                    	<div class="section colm colm8">
		                                <label class="field-label colm colm align-right">Utility List  </label>   
		                            </div><!-- end section -->
			                    	<div class="section colm colm3">
			                    	<label class="block">
		                                <a id="9" href="/timeSheetManager2bScene/utility/utilityCheckListHome.action?projectIDSelected=<s:property value="projectUniqueResult.projectID"/>" class="button" style="margin-top: 5px; width:25px; height:35px;"><img src="/twobSceneWebApp/html/img/edit_black_20px.png" alt="Edit" style="margin-top: 7px; margin-left:-10px" data-toggle="tooltip" data-placement="bottom" title="Click to edit utility"></a>
		                            </label>
		                            </div><!-- end section -->    
		                    		</div><!-- end .frm-row section -->  
		                    		<div id="alert-content-9" class="display-content" style="display: none">
			                    	      <div>
	                     <s:set var="count" value="0" />
							<table id="utility-table" cellspacing="0" style="font-family:sans-serif; font-size:10pt;" width="100%">
						    <thead>
						        <tr>
									<th>Category</th>
									<th>Utility Name</th>
									<!--<th>Disbursment</th> -->
						            <th>Date Required</th>
						            <th>Date Received</th>
								</tr>
						    </thead>
						    <tbody>
							<s:iterator value="utilityList" var="utilityList">
								<tr>
									<td><s:property value="utilityCatShortName"/></td>
									<td><s:property value="utilityName" /></td>
									<!--  
									<td><s:property value="priceSTR" /></td>
									-->
									<td><s:property value="dateOneSTR" /></td>
									<td><s:property value="dateTwoSTR" /></td>
							    </tr>
								</s:iterator>
						    </tbody>
						</table>
					</div>	
		                    		</div> 
																   
			</div><!-- end .form-body section -->
			   
                <div class="form-footer">
                	<a href="/timeSheetManager2bScene/jsp/home.action" 
                	class="button btn-primary btn-rounded">Home</a>
                	<button type="submit" class="button btn-primary" value="saveAdditionalData"> Save </button> 
                    <div style="float: right;">
                    <a href="/timeSheetManager2bScene/project/overviewProjects.action?tabFromProject=<s:property value="projectUniqueResult.phaseID"/>" class="button btn-primary button-left">Back to Overview</a>
                    <a href="/timeSheetManager2bScene/consultant/checkListHome.action?projectIDSelected=<s:property value="projectUniqueResult.projectID"/>" class="button btn-rounded">Consultant List</a>
                    <a href="/timeSheetManager2bScene/utility/utilityCheckListHome.action?projectIDSelected=<s:property value="projectUniqueResult.projectID"/>" class="button btn-rounded">Utility</a>
                    </div>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
    
<!--Contact Form -->
<div id="activityDetailsDiv">
	<form action="#" id="activityForm" class="form-activity">
		<img src="../html/img/close_window.png" class="imgClosing" id="close"/>
		<h3>Update Activity Details</h3>
		<hr/><br/>
		<input type="hidden" id="activityIDhidden" value="">
		<label>Due Date: </label>
		<br/>
		<input type="text" id="activityDueDate" name="activityDueDate" class="gui-input" value="" placeholder="Activity Due date">
		<br/>
		<br/>
		<label>Note: </label>
		<br/>
		<textarea class="gui-textarea" id="activityNote" name="activityNote" placeholder="Note"></textarea>
		<br/>
		<br/>
		<s:checkbox name="activityDone" id="activityDone"  ></s:checkbox>
		<span> Activity Finished </span>
		<br/>
		<br/>
		<input type="button" id="sendActivity" value="Send"/>
		<input type="button" id="cancel" value="Cancel"/>
		<br/>
	</form>
</div>

</body>
 </html> 
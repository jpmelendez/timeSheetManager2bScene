/<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<script>
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
var controlManager = {

		    updateProjectPhase: function (phaseIDTmp, ProjectIDTmp, currentPhaseTMP) {
		        if (!confirm("Do you want to change this project's phase?")) return
		        var DataTmp = {
		        		"projectID": ProjectIDTmp,
                        "phaseID": phaseIDTmp
                    };
		        $.ajax({
		            url: "/timeSheetManager2bScene/updatePhase.action",
		            data: JSON.stringify(DataTmp),
		            dataType: 'json',
		            contentType: 'application/json',
		            type: 'POST',
		            async: true,
		            success: function (res) {
		            	var jsonObMsg = res.msgApp;
		                alert(jsonObMsg);
		                controlManager.reloadTables(phaseIDTmp, currentPhaseTMP);
		            }
		        });
		    },
			   reloadTables: function (phaseIDTmp, currentPhaseTMP) {
				   $(".loader").fadeOut("slow");
				   url = "/timeSheetManager2bScene/project/overviewProjects.action?tabFromProject=" + currentPhaseTMP;
				   $(location).attr("href", url);
				   
			    }  
}
$(document).ready(function() {
	var tabIDTMP = $('#tab-target').val();
	var tabID = parseInt(tabIDTMP)-1;
	  /******** Start tabs **********/
    $( "#tabs" ).tabs({ 
        active: tabID 
        });
    /* Json phase select box 
	$(".phase-ctrl").change(function (e) {
        e.preventDefault();
        var phaseIDTmp = $(this).val();
        var idSelect = $(this).attr( "id" );
        var ProjectIDTmp = idSelect.substr(7);
        controlManager.updateProjectPhase(phaseIDTmp, ProjectIDTmp);
    });
	*/
	$(document).on('change','.phase-ctrl', function (e) {                
		 e.preventDefault();
	        var phaseIDTmp = $(this).val();
	        var idSelect = $(this).attr( "id" );
	        var ProjectIDTmp = idSelect.substr(7);
	        var phaseTarget = "#current-phase-"+ProjectIDTmp;
	        var currentPhaseTMP = $(phaseTarget).val();
	        controlManager.updateProjectPhase(phaseIDTmp, ProjectIDTmp, currentPhaseTMP);
    });
	
    
/******* Start Table ***********/
    $('#viewprojectStages').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
        });

    $('#viewcouncil').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
        });
   
    $('#viewvcat').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
        });
    
    $('#viewamend').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
        });
    
    $('#viewBuildingPermit').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
        });
   
    $('#viewUnderConstruction').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
        });
   
    $('#viewTender').DataTable( {
    	 "jQueryUI": true,
    	 "bPaginate": false,
    	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
        });
    $('#viewArchive').DataTable( {
   	 "jQueryUI": true,
   	 "bPaginate": false,
   	"columns": [
		             { "width": "1%" },
		             { "width": "16%" },
		             { "width": "9%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "5.8%" },
		             { "width": "16%" },
		             { "width": "5%" },
		             { "width": "1%" },
		             { "width": "1%" }
		           ]
       });
    
    
    /** 
    $('#viewProjects tbody').on( 'click', 'tr', function () {
        var trIDtmp = $(this).attr('id');
    	location.href = '/timeSheetManager2bScene/manager/mainManager?projectIDSelected=' + trIDtmp;
    } );
    **/
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
    	<div class="smart-forms smart-container wrap-6">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-pencil-square"></i>Overview Projects</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="#" id="smart-form">
            <s:hidden id="tab-target" value="%{tabSelected}"></s:hidden>
            
            	<div class="form-body">
            		<div class="section" style="margin-top: -35px; margin-left: -25px; margin-right: -25px">
		            	<!-- Tabs -->
						<div id="tabs">
							<ul style="font-family:sans-serif; font-size:10pt;">
								<li><a href="#projectStages">CD & TP</a></li>
								<li><a href="#councilLodgment">Council Lodgment</a></li>
								<li><a href="#vcat">VCAT</a></li>
								<li><a href="#amends">Working Drawing</a></li>
								<li><a href="#buldingPermit">Building Permit</a></li>
								<li><a href="#tenderProject">Tender Project</a></li>
								<li><a href="#construction">Under Construction</a></li>
								<li><a href="#archive">Archive</a></li>
							</ul>
							<!-- Start Project Stages Tab -->
							<div id="projectStages" style="padding: .1em .2em">
								<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewprojectStages" class="display" cellspacing="0" style="font-family:sans-serif; font-size:10pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Finish Job</th>
						            <th>Project Time</th>
						            <th>Time Spent</th>
						            <th>Photo Site</th>
						            <th>Target</th>
						            <th>Category</th>
						            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="projectStagesList" var="projectStagesList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									
									<td><s:property value="projectStatus"/></td>
									<td><s:property value="finishJob"/></td>
									<td><s:property value="projectTime"/></td>
									<td><s:property value="spentTime"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td style="background-color: <s:property value="cdtptargetColorCode"/>">
									<s:property value="overdueTime"/>
									</td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #projectStagesList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
									</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
							
							</div><!-- Ends Project Stages div -->
							<!--Ends Project Stages Tab -->
							
							<!--Starts Council Lodgment -->
							<div id="councilLodgment"  style="padding: .1em .2em">
							<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewcouncil" class="display" cellspacing="0" style="font-family:sans-serif; font-size:10pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Finish Job</th>
						            <th>Project Time</th>
						            <th>Time Spent</th>
						            
						            <th>Photo Site</th>
						            <th>Target</th>
						            <th>Category</th>
						            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="councilList" var="councilList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									
									<td><s:property value="projectStatus"/></td>
									<td><s:property value="finishJob"/></td>
									<td><s:property value="projectTime"/></td>
									<td><s:property value="spentTime"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td style="background-color: <s:property value="cdtptargetColorCode"/>">
									<s:property value="overdueTime"/>
									</td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #councilList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
											</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
								
							</div> <!-- Ends Council Lodgment -->
							<!--Ends Council Lodgment -->	
							
							<!--Starts Vcat Hearing -->
							<div id="vcat" style="padding: .1em .2em">
							<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewvcat" class="display" cellspacing="0" style="font-family:sans-serif; font-size:10pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Finish Job</th>
						            <th>Project Time</th>
						            <th>Time Spent</th>
						            
						            <th>Photo Site</th>
						            <th>Target</th>
						            <th>Category</th>
						            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="vcatList" var="vcatList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									
									<td><s:property value="projectStatus"/></td>
									<td><s:property value="vcatFinishJob"/></td>
									<td><s:property value="vcatTimeProject"/></td>
									<td><s:property value="vcatTimeSpent"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td style="background-color: <s:property value="vcattargetColorCode"/>">
									<s:property value="vcatTarget"/>
									</td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #vcatList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
											</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
							</div>
							<!--Ends Vcat Hearing -->
							
							<!-- Starts Working Drawing Plans -->
							<div id="amends" style="padding: .1em .2em">
							<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewamend" class="display" cellspacing="0" style="font-family:sans-serif; font-size:10pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Finish Job</th>
						            <th>Project Time</th>
						            <th>Time Spent</th>
						            
						            <th>Photo Site</th>
						            <th>Target</th>
						            <th>Category</th>
						            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="workingDrawingList" var="workingDrawingList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									<td><s:property value="projectStatus"/></td>
									<td><s:property value="wdFinishJob"/></td>
									<td><s:property value="wdTimeProject"/></td>
									<td><s:property value="wdTimeSpent"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td style="background-color: <s:property value="wdtargetColorCode"/>">
									<s:property value="wdTarget"/>
									</td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #workingDrawingList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
											</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
							</div>
							<!-- Ends Working Drawing -->
							
							<!--Starts Building Permit -->
							<div id="buldingPermit" style="padding: .1em .2em">
							<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewBuildingPermit" class="display" cellspacing="0" style="font-family:sans-serif; font-size:10pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Finish Job</th>
						            <th>Project Time</th>
						            <th>Time Spent</th>
						            
						            <th>Photo Site</th>
						            <th>Target</th>
						            <th>Category</th>
						           <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="buildingPermitList" var="buildingPermitList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									
									<td><s:property value="projectStatus"/></td>
									<td><s:property value="wdFinishJob"/></td>
									<td><s:property value="wdTimeProject"/></td>
									<td><s:property value="wdTimeSpent"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td style="background-color: <s:property value="wdtargetColorCode"/>">
									<s:property value="wdTarget"/>
									</td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #buildingPermitList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
											</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
							</div>
							<!--Ends Building Permit -->
							
							<!--Starts Tender -->
							<div id="tenderProject" style="padding: .1em .2em">
							<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewTender" class="display" cellspacing="0" style="font-family:sans-serif; font-size:9pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Finish Job</th>
						            <th>Project Time</th>
						            <th>Time Spent</th>
						            
						            <th>Photo Site</th>
						            <th>Target</th>
						            <th>Category</th>
						            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="tenderList" var="tenderList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									
									<td><s:property value="projectStatus"/></td>
									<td><s:property value="wdFinishJob"/></td>
									<td><s:property value="wdTimeProject"/></td>
									<td><s:property value="wdTimeSpent"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td style="background-color: <s:property value="wdtargetColorCode"/>">
									<s:property value="wdTarget"/>
									</td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #tenderList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
											</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
							</div>
							<!--Ends Tender -->
				
							<!--Starts Under Construction -->
							<div id="construction" style="padding: .1em .2em">
							<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewUnderConstruction" class="display" cellspacing="0" style="font-family:sans-serif; font-size:10pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Finish Job</th>
						            <th>Project Time</th>
						            <th>Time Spent</th>
						            
						            <th>Photo Site</th>
						            <th>Target</th>
						            <th>Category</th>
						            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="underConstructionList" var="underConstructionList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									<td>
									<s:property value="projectStatus"/></td>
									<td><s:property value="wdFinishJob"/></td>
									<td><s:property value="wdTimeProject"/></td>
									<td><s:property value="wdTimeSpent"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td style="background-color: <s:property value="wdtargetColorCode"/>">
									<s:property value="wdTarget"/>
									</td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #underConstructionList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
											</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
							</div>
							<!--Ends Under Construction -->
							<!--Starts Archive -->
							<div id="archive" style="padding: .1em .2em">
							<div class="section">
                           			 <p class="small-text fine-grey">Please search and edit projects or add a new one.</p>
                    			</div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Projects List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div>
							<table id="viewArchive" class="display" cellspacing="0" style="font-family:sans-serif; font-size:10pt;">
						    <thead>
						        <tr>
									<th>ID</th>
									<th>Project Name</th>
									<th>Stage</th>
									<th>Person in Charge</th>
						            <th>Status</th>
						            <th>Photo Site</th>
						            <th>Category</th>
						            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Notes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						            <th>Project Phase</th>
						            <th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="archiveList" var="archiveList">
						    <s:set var="countAct" value="0" />
						    <s:hidden id="countID" value="%{#countAct}"></s:hidden>
							    <tr class="details-control" id="<s:property value="projectID"/>">
									<td style="background-color: <s:property value="projectStatusColor"/>">
											<s:property value="rowIndexConsecutive"/>
									</td>
									<td>
									<s:property value="projectName"/>
									</td>
									
									<td><s:property value="stage"/></td><td><s:property value="staffInCharge"/></td>
									<td>
									<s:property value="projectStatus"/></td>
									<td><s:property value="strPhotoSite"/></td>
									<td><s:property value="projecCat"/></td>
									<td><s:property value="notes"/></td>
									<td>
									<input type="hidden" id="current-phase-<s:property value="projectID"/>" value="<s:property value="phaseID"/>">
									<!--  
										<select id="phases-<s:property value="projectID"/>" class="phase-ctrl">
										  <option value="1">Project Stages</option>
										  <option value="2">Council Lodgment</option>
										  <option value="3">Vcat Hearing</option>
										  <option value="4">Amend Plans</option>
										  <option value="5">Building Permit</option>
										  <option value="6">Under Construction</option>
										  <option value="7">Tender Project</option>
										</select>
									-->
										<s:select 
											id="%{'phases-' + #archiveList.projectID}"
											list="#{'1':'CD & TP', '2':'Council Lodgment', '3':'VCAT', '4':'Working Drawing', '5':'Building Permit', '6':'Tender Project', '7':'Under Construction', '8':'Archive'}" 
											theme="css_xhtml"
											cssClass="phase-ctrl"
											value="phaseID" />
											</td>
										<td>
									  		<a href="/timeSheetManager2bScene/manager/mainManager?projectIDSelected=<s:property value="projectID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
									</td>
									<td>
										  		<a href="/timeSheetManager2bScene/project/deleteProject?projIDSelected=<s:property value="projectID"/>">
										  		<span class="ui-icon ui-icon-trash">Delete</span>
										  		</a>
									</td>
								
							  </tr>
						  <s:set var="countAct" value="%{#countAct+1}" />
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
							</div>
							<!--Ends Archive -->
							 
							
				
			            </div><!-- end tabs --> 
		            </div><!-- end section --> 
            	</div><!-- end .form-body section -->
                <div class="form-footer">
                	
                   
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>

 </html> 
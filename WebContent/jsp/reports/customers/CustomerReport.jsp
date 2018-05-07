<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<script type="text/javascript">
$(document).ready( function () {
    var initialSearch = $('#customername').val();

    $('#viewProjects').DataTable( {
    	"jQueryUI": true,
      	 "bPaginate": false,
      	 "bSort" : false,
      	 "dom": 'Bfrtip',
           "buttons": [
               {
                   extend: 'pdfHtml5',
                   download: 'open',
                   orientation: 'landscape'
               }
           ]
      } );
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
   <div class="dataErrors">
   	  <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
      <s:actionerror/>
   </div>
</s:if>

	<div class="smart-wrap">
    	<div class="smart-forms smart-container wrap-4">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-pencil-square"></i>Customer Report</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="addProjectTasks" id="smart-form">
            <s:set var="countAct" value="0" />
            <s:set var="moduleIDVal" value="%{moduleID}"></s:set>
            	<div class="form-body">
            	
            	 	<div class="section">
                            <p class="small-text fine-grey">Please search and/or print report.<s:property value="%{moduleIDVal}"/></p>
                            <input type="hidden" id="customername" value='<s:property value="%{customerName}"/>'>
                    </div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Customer List</span></div><!-- .tagline -->
	                    </div>
	                    
	                    <!-- Start Project Table -->
	                    <div style="position: relative; margin-top: 20px; margin-left: 10px">
							<table id="viewProjects" class="display" cellspacing="0" width="100%">
						    <thead>
						        <tr>
									<th>Client Name</th>
									<th>Email</th>
									<th>Address</th>
									<th>Phone</th>
									<th>Status</th>
									
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="clientsList" var="clientsList">
						    <tr>
						        <td><s:property value="clientName"/></a></td>
								<td><s:property value="clientEmail"/></td> 
								<td><s:property value="clientAddress"/></td>
								<td><s:property value="clientPhone"/></td>
								<td><s:property value="clientStatus"/></td>
						  </tr>
						  </s:iterator>
						
						    </tbody>
						</table>
					</div>
	                   
            	</div><!-- end .form-body section -->
                
                 <!--   
                <div class="form-footer">
                <s:set var="module" value="1"></s:set>
                <s:set var="moduleEmpty" value="0"></s:set>
                <s:set var="moduleIDVal" value="%{moduleID}"></s:set>
                    <s:if test="%{#moduleIDVal != #moduleEmpty}">
                    	<s:if test="%{#module == #moduleIDVal}">
	                    	<a href="/twobSceneWebApp/project/captureProject.action?customerIdSelected=<s:property value="customerIdSelected"/>&moduleID=1
							&tmPprojectStatus=<s:property value="tmPprojectStatus"/>
							&tmPnumberOfEandA=<s:property value="tmPnumberOfEandA"/> 
							&tmPprojectCatID=<s:property value="tmPprojectCatID"/>
							&tmPprojectName=<s:property value="tmPprojectName"/>
							&tmPprojectDescription=<s:property value="tmPprojectDescription"/>
							&tmPshortName=<s:property value="tmPshortName"/>
							&tmPcurrentBudget=<s:property value="tmPcurrentBudget"/>
							&tmPpriority=<s:property value="tmPpriority"/>
							&tmPstaffID=<s:property value="tmPstaffID"/>
							&tmPprojectTime=<s:property value="tmPprojectTime"/>
							&tmPstartDateSTR=<s:property value="tmPstartDateSTR"/>
							&tmPdueDateSTR=<s:property value="tmPdueDateSTR"/>
							&tmPhoursSpend=<s:property value="tmPhoursSpend"/>" class="button btn-primary button-left"> Back To Add Project </a>
						</s:if>
						<s:else>
						<a href="/twobSceneWebApp/project/projectEditForm.action?projIDSelected=<s:property value="projectIDSelected"/>" class="button btn-primary button-left"> Back </a>
						</s:else>
                    </s:if>
                    <s:else>
                     <a href="/twobSceneWebApp/jsp/home.action" class="button btn-rounded"> Cancel </a>
                    </s:else>
                       
                </div>--><!-- end .form-footer section -->
                
               
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
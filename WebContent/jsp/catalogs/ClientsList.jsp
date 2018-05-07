<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<script type="text/javascript">
$(document).ready( function () {
    $('#viewProjects').DataTable();
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
            	<h4><i class="fa fa-pencil-square"></i>Customer Manager</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="addProjectTasks" id="smart-form">
            <s:set var="countAct" value="0" />
            
            	<div class="form-body">
            	
            	 	<div class="section">
                            <p class="small-text fine-grey">Please search and edit a customer or add a new one.</p>
                    </div><!-- end section -->                		
	            		<div class="spacer-b30"><!-- Start Stage div -->
	                    	<div class="tagline"><span>Customer List</span></div><!-- .tagline -->
	                    </div>
	                    <div style="margin-left: 20px; width: 90%"><!-- Start section ADD BUTTON  -->
	                    	<div style="width: 120px;">
	                    		
	                    			<a href="/twobSceneWebApp/customers/clientsForm.action">
	                    			<img src="/twobSceneWebApp/html/img/add-icon.png" alt="add" title="addIcon">  Add Customer
	                    			</a>
	                    	</div>
	                    </div><!-- End section -->
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
									<th></th>
									<th></th>
								</tr>
						    </thead>
						    <tbody>
						    <s:iterator value="clientsList" var="clientsList">
						    <tr>
						        <td><s:property value="clientName"/></td>
								<td><s:property value="clientEmail"/></td>
								<td><s:property value="clientAddress"/></td>
								<td><s:property value="clientPhone"/></td>
								<td><s:property value="clientStatus"/></td>
								<td>
									  		<a href="clientsEditForm?customerIdSelected=<s:property value="clientID"/>">
									  		<span class="ui-icon ui-icon-pencil">Edit</span>
									  		</a>
								</td>
								<td>
									  		<a href="deleteClient?clientID=<s:property value="clientID"/>">
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
                    <button type="reset" class="button"> Cancel </button>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
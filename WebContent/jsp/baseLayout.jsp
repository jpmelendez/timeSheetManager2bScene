<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
            	<tiles:insertAttribute name="headContent" ignore="true" />
            	<title>2bScene Time Sheet Web Application</title>
    </head>
<div id="maincontainer" style="width: auto; height: auto;">

	<div id="topsection">
		<div class="nav-innertube">
		<tiles:insertAttribute name="header" ignore="true" />
		<div class=".header-menu"><tiles:insertAttribute name="menu" ignore="true" /></div>
		<tiles:insertAttribute name="logout-button" ignore="true" />
		</div>
	</div>
	<div id="contentwrapper">
		<div id="contentcolumn">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<div id="footer"><tiles:insertAttribute name="footer" /></div>
</div>
</html>
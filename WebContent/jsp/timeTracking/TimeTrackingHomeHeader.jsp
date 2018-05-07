<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0">
	<title>Timesheet Manager</title>
		<!-- Start css3menu.com HEAD section -->
	<link type="text/css" rel="stylesheet" href="/timeSheetManager2bScene/html/css/style-menu.css" /><style type="text/css">._css3m{display:none}</style>
	<link type="text/css" rel="stylesheet" href="/timeSheetManager2bScene/html/css/font-awesome.min.css" />
	<!-- Accordion CSS -->
	<link rel="stylesheet" type="text/css" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	<!-- Core Style -->
	<link type="text/css" rel="stylesheet" href="/timeSheetManager2bScene/html/css/style-2bsceneAll.css">
	<!-- End css3menu.com HEAD section -->	
	<!-- select css -->
	<link type="text/css" rel="stylesheet" href="/timeSheetManager2bScene/html/css/jquery.editable-select.css" />
	
	<!-- JQUERY CORE JS -->
		<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
		<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/additional-methods.min.js"></script>
		
		<!-- Mask extension -->
		<script src="../html/js/jquery.inputmask.bundle.js"></script>
		
		<!-- Textarea autosize extension -->
		<script src="../html/js/jquery.autosize.js"></script>
		
		<!-- global timeshit per day -->
		<script src="../html/js/global/timesheetscripts.js"></script>
		
		
		
		
		<!-- jquery select box -->
		
		<script src="https://rawgithub.com/indrimuska/jquery-editable-select/master/source/jquery.editable-select.js"></script>
<style type="text/css">
.dataSaved {
	background-color:#DDFFDD;
	border:1px solid #009900;
	width:100%;
}
.dataSaved li{ 
	list-style: none; 
}
.dataErrors {
	background-color:#FFCCCC;
	border:1px solid #CC0000;
	width:100%;
	margin-bottom:8px;
}
.dataErrors li{ 
	list-style: none; 
}

.sources { position: absolute; top: 65px; right: 80px; }
		
</style>
		
</head>
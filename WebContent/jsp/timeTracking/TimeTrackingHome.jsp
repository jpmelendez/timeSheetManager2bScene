
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<style>

#time-table tr:hover
{
    border: 1px solid black;
}

</style>

	 <script>
	 $(window).load(function() {
			$(".loader").fadeOut("slow");
		})
	 var controlManager = {

			 // Loads the activity data requested from the app server via ajax request and Shows a form with activity details,
			 showStages: function (ProjectIdTmp, projTarget) {
			        if (ProjectIdTmp == null) return;
			        var projectIDString = {
	                        "projectIDString": ProjectIdTmp
	                    };
			        $.ajax({
			        	url: "/timeSheetManager2bScene/readStages.action",
	                    data: JSON.stringify(projectIDString),
			            cache: false,
			            dataType: 'json',
			            contentType: 'application/json',
	                    type: 'POST',
	                    async: true,
			            success: function (res) {
							//alert("inside ajax ");
							if(res.listStages != null){
								var options = '<option value="' + "" + '">'
	                            + "--Select--" + '</option>';
	                        	for ( var i = 0; i < res.listStages.length; i++) {
	                                options += '<option value="' + res.listStages[i].stageID + '">'
	                                + res.listStages[i].stageName + '</option>';
	                    		}

								}else{
									var options = '<option value="' + "" + '">'
		                            + "--Select--" + '</option>';
									}
                        	
                            var stageTarget = "#stageSelected"+projTarget;
                    $(stageTarget).html(options);
			            }
			        });
			    },

			    showTasks: function (StageIdTmp, Target) {
				    currentProject = "#projectSelected"+Target
			        if (StageIdTmp == null) return;
			        var showTaskData = {
	                        "projectIDString": $(currentProject).val(),
	                        "stageIDString": StageIdTmp
	                    };
			        $.ajax({
			        	url: "/timeSheetManager2bScene/readTasks.action",
	                    data: JSON.stringify(showTaskData),
			            cache: false,
			            dataType: 'json',
			            contentType: 'application/json',
	                    type: 'POST',
	                    async: true,
			            success: function (res) {
							//alert("inside ajax ");
							if(res.listTasks != null){
                        	var options = '<option value="' + "" + '">'
                            + "--Select--" + '</option>';
                        	for ( var i = 0; i < res.listTasks.length; i++) {
                                options += '<optgroup label="' + res.listTasks[i].taskName + '">';
								/*Activity iteration*/
								for ( var j = 0; j < res.allActivities.length; j++) {
									if(res.listTasks[i].taskID == res.allActivities[j].taskID){
										options += '<option value="' + res.allActivities[j].actID + '">'
		                                + res.allActivities[j].actName + '</option>';
										}
                    			}
                                }
							}else{
								var options = '<option value="' + "" + '">'
	                            + "--Select--" + '</option>';
								}
                        	var taskTarget = "#taskSelected"+Target;        
                    $(taskTarget).html(options);
			            }
			        });
			    },

			    showActivities: function (TaskIdTmp, Target) {
			    	currentProject = "#projectSelected"+Target
			    	currentStage = "#stageSelected"+Target
			        if (TaskIdTmp == null) return;
			        var showActivityData = {
	                        "projectIDString": $(currentProject).val(),
	                        "stageIDString": $(currentStage).val(),
	                        "taskIDString": TaskIdTmp
	                    };
			        $.ajax({
			        	url: "/timeSheetManager2bScene/readActivities.action",
	                    data: JSON.stringify(showActivityData),
			            cache: false,
			            dataType: 'json',
			            contentType: 'application/json',
	                    type: 'POST',
	                    async: true,
			            success: function (res) {
							//alert("inside ajax ");
                        	var options = '<option value="' + "" + '">'
                            + "--Select--" + '</option>';
                        	for ( var i = 0; i < res.listActivities.length; i++) {
                                options += '<option value="' + res.listActivities[i].mapProjectActID + '">'
                                + res.listActivities[i].act_name + '</option>';
                    }
                        	var actTarget = "#activitySelected"+Target;     
                    $(actTarget).html(options);
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
			            url: "/twobSceneWebApp/updateActivity.action",
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
			    loadWeek: function () {
			    	var date = new Date();
			    	var shortDateFormat = 'dd/mm/yy';
			    	var LongDateFormat = 'D, d MM';
			    	var monthFormat = 'm';
		            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1);
		            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
		            $('#startDate').text($.datepicker.formatDate( shortDateFormat, startDate));
		            $('#endDate').text($.datepicker.formatDate( shortDateFormat, endDate));
		            /*Monday*/
		            /*$('#MondayLabel').text($.datepicker.formatDate(LongDateFormat, startDate));*/
		            $("#startDateVal").val($.datepicker.formatDate(shortDateFormat, startDate));
		            /*tuesday*/
		            tuesDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate() - startDate.getDay() + 2);
		            /*$('#MondayLabel').text($.datepicker.formatDate(LongDateFormat, tuesDate));*/
		            /*wednesday*/
		            /*thursday*/
		            /*friday*/
		            /*saturday*/
		            /*sunday*/
			    },
			    getTimesPerDay: function (ActIdTmp, Target, staffID, startDate, endDate, projectIDTmp) {
				    //alert("ActIdTmp: " + ActIdTmp + " Target : " + Target);
			    	 if (ActIdTmp == null) return;
				        var Data = {
		                        "activityIDString": ActIdTmp,
		                        "staffID": staffID,
		                        "startDateData": startDate,
		                        "endDateData": endDate,
		                        "projectIDString": projectIDTmp
		                    };
				        $.ajax({
				        	url: "/timeSheetManager2bScene/getWeekTimes.action",
		                    data: JSON.stringify(Data),
				            cache: false,
				            dataType: 'json',
				            contentType: 'application/json',
		                    type: 'POST',
		                    async: true,
				            success: function (res) {

					          var MondayTrackID = "#trackIDMonday" + Target;
					          var MondayDate = "#trackingMonday" + Target;
					          var MondayTime = "#timeMondayDec"+Target;
					          var MondayMonth = "#monthMonday" + Target;
					          var TuesdayTrackID = "#trackIDtuesday" + Target;
					          var TuesdayDate = "#trackingtuesday" + Target;
					          var TuesdayTime = "#timetuesdaDec"+Target;
					          var TuesdayMonth = "#monthTuesday" + Target;
					          var WednesdayTrackID = "#trackIDwednesday" + Target;
					          var WednesdayDate = "#trackingIDwednesday" + Target;
					          var WednesdayTime = "#timewednesDec"+Target;
					          var Wednesdayonth = "#monthWednesday" + Target;
					          var ThursdayTrackID = "#trackIDthursday" + Target;
					          var ThursdayDate = "#trackingIDthursday" + Target;
					          var ThursdayTime = "#timethursdDec"+Target;
					          var ThursdayMonth = "#monthThursday" + Target;
					          var FridayTrackID = "#trackIDfriday" + Target;
					          var FridayDate = "#trackingIDfriday" + Target;
					          var FridayTime = "#timefridayDec"+Target;
					          var FridayMonth = "#monthFriday" + Target;
					          var SaturdayTrackID = "#trackIDSaturday" + Target;
					          var SaturdayDate = "#trackingIDSaturday" + Target;
					          var SaturdayTime = "#timeSaturdDec"+Target;
					          var SaturdayMonth = "#monthSaturday" + Target;
					          var SundayTrackID = "#trackIDsunday" + Target;
					          var SundayDate = "#trackingIDsunday" + Target;
					          var SundayTime = "#timesundayDec"+Target;
					          var SundayMonth = "#monthSunday" + Target;
					          var Note = "#noteActivity" + Target;
					          var totalElement = "#total-sum"+Target;
					          var Total = res.mondayData + res.tuesdayData + res.wednesdayData + res.thursdayData + res.fridayData + res.saturdayData + res.sundayData;
					          //setting values
					          $(MondayTrackID).val(res.mondayTrackID );
					          $(MondayDate).val(res.mondayStr );
					          $(MondayTime).val(res.mondayData );
					          $(TuesdayTrackID).val(res.tuesdayTrackID );
					          $(TuesdayDate).val(res.tuesdayStr );
					          $(TuesdayTime).val(res.tuesdayData );
					          $(WednesdayTrackID).val(res.wednesdayTrackID );
					          $(WednesdayDate).val(res.wednesdayStr );
					          $(WednesdayTime).val(res.wednesdayData );
					          $(ThursdayTrackID).val(res.thursdayTrackID );
					          $(ThursdayDate).val(res.thursdayStr );
					          $(ThursdayTime).val(res.thursdayData );
					          $(FridayTrackID).val(res.fridayTrackID );
					          $(FridayDate).val(res.fridayStr );
					          $(FridayTime).val(res.fridayData );
					          $(SaturdayTrackID).val(res.saturdayTrackID );
					          $(SaturdayDate).val(res.saturdayStr );
					          $(SaturdayTime).val(res.saturdayData );
					          $(SundayTrackID).val(res.sundayTrackID );
					          $(SundayDate).val(res.sundayStr );
					          $(SundayTime).val(res.sundayData );
					          //note
					          $(Note).val(res.activityNoteData);
					          //total
					          $(totalElement).val(controlManager.decimalToMinutes(Total));
					          setTimeout(controlManager.totalSumGeneral, 1000);
				            }
				        });
			    },

			    addRow : function (rowTarget) {
				    var elementTarget = "#row-"+rowTarget;
			    	$(elementTarget).removeAttr("style")
				},
				removeRow : function (currentRowTarget, currentTimeTaret) {
				    var elementTarget = "#row-"+currentRowTarget;
				    var projectTarget = "#projectSelected-" + currentRowTarget;
					var stageTarget = "#stageSelected-" + currentRowTarget;
					var taskTarget = "#taskSelected-" + currentRowTarget;
					var actTarget = ".actID-" + currentRowTarget;
					var noteTarget = "#noteActivity-" + currentRowTarget;
					var monTarget = "#timeMondayDec-" + currentRowTarget;
					var tueTarget = "#timetuesdaDec-" + currentRowTarget;
					var wedTarget = "#timewednesDec-" + currentRowTarget;
					var thurTarget = "#timethursdDec-" + currentRowTarget;
					var friTarget = "#timefridayDec-" + currentRowTarget;
					var SatTarget = "#timeSaturdDec-" + currentRowTarget;
					var sunTarget = "#timesundayDec-" + currentRowTarget;
					var totalWeekTarget = "#total-sum-" + currentRowTarget;
				    $(elementTarget).css("display", "none");
					$("#hiddenCount").val(currentRowTarget);
					/***Setting values to null***/
					$(projectTarget).val("");
					$(stageTarget).val("");
					$(taskTarget).val("");
					$(actTarget).val("");
					$(noteTarget).val("");
					$(monTarget).val("");
					$(tueTarget).val("");
					$(wedTarget).val("");
					$(thurTarget).val("");
					$(friTarget).val("");
					$(SatTarget).val("");
					$(sunTarget).val("");
					$(totalWeekTarget).val("");
				},
				decimalToMinutes : function (TotalDecimal) {
					if (TotalDecimal <= 0) return "0:00";
					var hrs = parseInt(Number(TotalDecimal));
					var min = Math.round((Number(TotalDecimal)-hrs) * 60);
					var minFormat = ("0" + min).slice(-2);
					var TotalFormated = hrs + ":" + minFormat;
					return TotalFormated;
				},
				totalSumGeneral : function () {
					var TotalsumMon = 0;
				    $(".time-input-mon").each(function(){
				    	TotalsumMon += +((!$(this).val())?0:(parseFloat($(this).val())));
				    });
					var TotalsumTue = 0;
				    $(".time-input-tue").each(function(){
				    	TotalsumTue += +((!$(this).val())?0:(parseFloat($(this).val())));
				    });
					 var TotalsumWed = 0;
					    $(".time-input-wed").each(function(){
					    	TotalsumWed += +((!$(this).val())?0:(parseFloat($(this).val())));
					    });
					    var TotalsumThu = 0;
					    $(".time-input-thu").each(function(){
					    	TotalsumThu += +((!$(this).val())?0:(parseFloat($(this).val())));
					    });
					    var TotalsumFri = 0;
					    $(".time-input-fri").each(function(){
					    	TotalsumFri += +((!$(this).val())?0:(parseFloat($(this).val())));
					    });
					    var TotalsumSat = 0;
					    $(".time-input-sat").each(function(){
					    	TotalsumSat += +((!$(this).val())?0:(parseFloat($(this).val())));
					    });
					    var TotalsumSun = 0;
					    $(".time-input-sun").each(function(){
					    	TotalsumSun += +((!$(this).val())?0:(parseFloat($(this).val())));
					    });  
					     var TotalGeneral = 0;
					     var TotalGeneral = +(TotalsumMon + TotalsumTue + TotalsumWed + TotalsumThu + TotalsumFri + TotalsumSat + TotalsumSun);
					//setting totals
			    	$("#timeTotal-Mon").val(controlManager.decimalToMinutes(TotalsumMon.toFixed(2)));
			    	$("#timeTotal-Tue").val(controlManager.decimalToMinutes(TotalsumTue.toFixed(2)));
			    	$("#timeTotal-Wed").val(controlManager.decimalToMinutes(TotalsumWed.toFixed(2)));
			    	$("#timeTotal-Thu").val(controlManager.decimalToMinutes(TotalsumThu.toFixed(2)));
			    	$("#timeTotal-Fri").val(controlManager.decimalToMinutes(TotalsumFri.toFixed(2)));
			    	$("#timeTotal-Sat").val(controlManager.decimalToMinutes(TotalsumSat.toFixed(2)));
			    	$("#timeTotal-Sun").val(controlManager.decimalToMinutes(TotalsumSun.toFixed(2)));
			    	$("#total-general").val(controlManager.decimalToMinutes(TotalGeneral.toFixed(2)));		
				},

			    
	}
	
	 
	 $(document).ready(function(){


				var hasChanged = false;
				var saveflag = "";
			    var startDate;
			    var endDate;
			    
			    var selectCurrentWeek = function() {
			        window.setTimeout(function () {
			            $('#weekpicker').datepicker('widget').find('.ui-datepicker-current-day a').addClass('ui-state-active')
			        }, 1);
			    }
				
			     $("#dialog-confirm").dialog({
            bgiframe: true,
            autoOpen: false,
            minHeight: 200,
            width: 350,
            modal: true,
            closeOnEscape: false,
            draggable: false,
            resizable: false,
            buttons: {
                    'Yes': function(){
                        $(this).dialog('close');
                        $( "#smart-form" ).submit();
                    },
                    'No': function(){
                    	var my_href = $(this).data('param_href');
                    	var my_date = $(this).data('param_date')
                        $(this).dialog('close');
                        window.location.href = my_href + my_date;
                    },
                    'Cancel': function(){
                        $(this).dialog('close');
                        return;
                    }
                }
        });

			     function callback(value){
			        if(value === "1"){
			        	saveflag = "1";
			        	return saveflag;
				        }else if (values === "2"){
				        	saveflag = "2";
				        	return saveflag;
					        }else {
					        	saveflag = "3";
					        	return saveflag;
						        }
			    }
			    
			    $('#weekpicker').datepicker( {
			    	dateFormat: "dd/mm/yy",
			        showOtherMonths: false,
			        selectOtherMonths: false,
			        onSelect: function(dateText, inst) { 
			            var date = $(this).datepicker('getDate');
			            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay()+ 1);
			            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
			            var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
			            $('#weekpicker').val($.datepicker.formatDate( dateFormat, startDate, inst.settings )
			                 + ' - ' + $.datepicker.formatDate( dateFormat, endDate, inst.settings ));
			            
			            selectCurrentWeek();
			        },
			        beforeShow: function() {
			            selectCurrentWeek();
			        },
			        beforeShowDay: function(date) {
			            var cssClass = '';
			            if(date >= startDate && date <= endDate)
			                cssClass = 'ui-datepicker-current-day';
			            return [true, cssClass];
			        },
			        onChangeMonthYear: function(year, month, inst) {
			            selectCurrentWeek();
			        },
				    onClose: function(dateText, inst) { 
				    	var date = $(this).datepicker('getDate');
				    	var LongDateFormat = 'dd/mm/yy';
				    	var dateSelected = $.datepicker.formatDate( LongDateFormat, date);
				    	var hrefData = "/timeSheetManager2bScene/timeSheet/timeSheetHome.action?dateSelected=";
		                if(hasChanged){
		                	window.onbeforeunload = null;
		                	$("#dialog-confirm")
		                	.data('param_href', hrefData)
		                	.data('param_date', dateSelected)
		                	.dialog("open");
		                }else{
		                	 window.location.href = hrefData + dateSelected;
			                }

						/*
				    	var LongDateFormat = 'D, d MM, y';
			            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1);
			            var tuesdayDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate() - startDate.getDay() + 2);
			            var wednesdayDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate() - startDate.getDay() + 3);
			            var thursdayDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate() - startDate.getDay() + 4);
			            var fridayDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate() - startDate.getDay() + 5);
			            var saturdayDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate() - startDate.getDay() + 6);
			            var sundayDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate() - startDate.getDay() + 7);
			            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
			            var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
			            var startFormat = $.datepicker.formatDate( dateFormat, startDate, inst.settings );
			            var endFormat = $.datepicker.formatDate( dateFormat, endDate, inst.settings );
			            $('#startDate').text($.datepicker.formatDate( dateFormat, startDate, inst.settings ));
			            $('#endDate').text($.datepicker.formatDate( dateFormat, endDate, inst.settings ));
			            //setting hidden values and labels for dates
			            $("#startDateVal").val($.datepicker.formatDate( dateFormat, startDate, inst.settings ));
			            $("#endDateVal").val($.datepicker.formatDate( dateFormat, endDate, inst.settings ));
			            $('#MondayLabel').text($.datepicker.formatDate( LongDateFormat, startDate, inst.settings ));
			            $('#TuesdayLabel').text($.datepicker.formatDate( LongDateFormat, tuesdayDate, inst.settings ));
			            $('#WednesdayLabel').text($.datepicker.formatDate( LongDateFormat, wednesdayDate, inst.settings ));
			            $('#ThursdayLabel').text($.datepicker.formatDate( LongDateFormat, thursdayDate, inst.settings ));
			            $('#FridayLabel').text($.datepicker.formatDate( LongDateFormat, fridayDate, inst.settings ));
			            $('#SaturdayLabel').text($.datepicker.formatDate( LongDateFormat, saturdayDate, inst.settings ));
			            $('#SundayLabel').text($.datepicker.formatDate( LongDateFormat, endDate, inst.settings ));
			            $(".projectSelect").val("");
				        // disable live listeners so they dont impact other instances
				        $(".ui-datepicker-calendar tr").die("mousemove");
				        $(".ui-datepicker-calendar tr").die("mouseleave");
				        $('#smart-form').trigger("reset");*/
				    }
			        
			        
			    }).datepicker('widget').addClass('ui-weekpicker');
			    
			    $('.ui-weekpicker .ui-datepicker-calendar tr').live('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
			    $('.ui-weekpicker .ui-datepicker-calendar tr').live('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
		



		 
		 $('.note-tableSelect').each(function () {
			 var noteContent = $(this).val();
			 if (noteContent != null ||  noteContent != ""){
				 this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
				 }
			 else{
				 this.setAttribute('style', 'height:"auto";overflow-y:hidden;');
				 }
			}).on('input', function () {
			  this.style.height = '31px';
			  this.style.height = (this.scrollHeight) + 'px';
			});
		 controlManager.totalSumGeneral();
		 controlManager.loadWeek();
		 $(".time-tableinput").inputmask({
			 'alias': 'decimal', 
			 'groupSeparator': ',', 
			 'autoGroup': true
			 });
		 $( "#startDateVal" ).datepicker();
		 $( "#endDateVal" ).datepicker();
		 /* Json Stages select box */
		 $(".projectSelect").change(function (e) {
		        e.preventDefault();
		        var ProjectIdTmp = $(this).val();
		        var idSelect = $(this).attr( "id" );
		        var projTarget = idSelect.substr(15);
		        controlManager.showStages(ProjectIdTmp, projTarget);
		    });
		 /* Json Tasks select box */
			$(".stageSelect").change(function (e) {
		        e.preventDefault();
		        var StageIdTmp = $(this).val();
		        var idSelect = $(this).attr( "id" );
		        var Target = idSelect.substr(13);
		        controlManager.showTasks(StageIdTmp, Target);
		    });

			 /* Json Activity select box */
			$(".taskSelect").change(function (e) {
		        e.preventDefault();
		        
		        var ActIdTmp = $(this).val();
		        var idSelect = $(this).attr( "id" );
		        var Target = idSelect.substr(12);
		        var staffID = $('#staffID').val();
		        var startDate = $('#dateStrt').val();
		        var endDate = $('#dateEnd').val();
		       	var projbyIDTmp = "#projectSelected"+Target;
		        var projectIDTmp = $(projbyIDTmp).val();
		     	//setting the map activity value for this row
		     	var projectClass = ".projID"+Target;
		        var ActivityClass = ".actID"+Target;
		        $(ActivityClass).val(ActIdTmp);
		        $(projectClass).val(projectIDTmp);
		        //refresh Times ajax
		        controlManager.getTimesPerDay(ActIdTmp, Target, staffID, startDate, endDate, projectIDTmp);
		       
		    });

			 $('.stageSelect').focus(function(){
				
			    });
			 $('.stageSelect').blur(function(){
			           
			    });

			 /* Total Sum */
			$(".time-tableinput").change(function (e) {
				e.preventDefault();
				//Start calc by week
		        var idSelect = $(this).attr( "id" );
		        var Target = idSelect.substr(13);
		        var mondayElement = "#timeMondayDec"+Target;
		        var tuedayElement = "#timetuesdaDec"+Target;
			    var wednesdayElement = "#timewednesDec"+Target;
			    var thusrdayElement = "#timethursdDec"+Target;
				var fridayElement = "#timefridayDec"+Target;
				var SaturdayElement = "#timeSaturdDec"+Target;
				var Sundayelelemnt = "#timesundayDec"+Target;
				var totalElement = "#total-sum"+Target;
				var monVal = (!$(mondayElement).val())?0:(parseFloat($(mondayElement).val()));
				var tueVal = (!$(tuedayElement).val())?0:(parseFloat($(tuedayElement).val()));
				var wedVal = (!$(wednesdayElement).val())?0:(parseFloat($(wednesdayElement).val()));
				var thrVal = (!$(thusrdayElement).val())?0:(parseFloat($(thusrdayElement).val()));
				var friVal = (!$(fridayElement).val())?0:(parseFloat($(fridayElement).val()));
				var satVal = (!$(SaturdayElement).val())?0:(parseFloat($(SaturdayElement).val()));
				var sunVal = (!$(Sundayelelemnt).val())?0:(parseFloat($(Sundayelelemnt).val()));   
				var TotalSum = +((monVal + tueVal + wedVal + thrVal + friVal + satVal + sunVal).toFixed(2));
				
				var TotalHrsWeek =  controlManager.decimalToMinutes(TotalSum);
				//End calc by week
				//Calc by day
			    var TotalsumMon = 0;
			    $(".time-input-mon").each(function(){
			    	TotalsumMon += +((!$(this).val())?0:(parseFloat($(this).val())));
			    });
				var TotalsumTue = 0;
			    $(".time-input-tue").each(function(){
			    	TotalsumTue += +((!$(this).val())?0:(parseFloat($(this).val())));
			    });
				 var TotalsumWed = 0;
				    $(".time-input-wed").each(function(){
				    	TotalsumWed += +((!$(this).val())?0:(parseFloat($(this).val())));
				    });
				    var TotalsumThu = 0;
				    $(".time-input-thu").each(function(){
				    	TotalsumThu += +((!$(this).val())?0:(parseFloat($(this).val())));
				    });
				    var TotalsumFri = 0;
				    $(".time-input-fri").each(function(){
				    	TotalsumFri += +((!$(this).val())?0:(parseFloat($(this).val())));
				    });
				    var TotalsumSat = 0;
				    $(".time-input-sat").each(function(){
				    	TotalsumSat += +((!$(this).val())?0:(parseFloat($(this).val())));
				    });
				    var TotalsumSun = 0;
				    $(".time-input-sun").each(function(){
				    	TotalsumSun += +((!$(this).val())?0:(parseFloat($(this).val())));
				    });  
				     var TotalGeneral = 0;
				     var TotalGeneral = +((TotalsumMon + TotalsumTue + TotalsumWed + TotalsumThu + TotalsumFri + TotalsumSat + TotalsumSun).toFixed(2));
				//setting totals
		    	$("#timeTotal-Mon").val(controlManager.decimalToMinutes(TotalsumMon.toFixed(2)));
		    	$("#timeTotal-Tue").val(controlManager.decimalToMinutes(TotalsumTue.toFixed(2)));
		    	$("#timeTotal-Wed").val(controlManager.decimalToMinutes(TotalsumWed.toFixed(2)));
		    	$("#timeTotal-Thu").val(controlManager.decimalToMinutes(TotalsumThu.toFixed(2)));
		    	$("#timeTotal-Fri").val(controlManager.decimalToMinutes(TotalsumFri.toFixed(2)));
		    	$("#timeTotal-Sat").val(controlManager.decimalToMinutes(TotalsumSat.toFixed(2)));
		    	$("#timeTotal-Sun").val(controlManager.decimalToMinutes(TotalsumSun.toFixed(2)));
		    	$("#total-general").val(controlManager.decimalToMinutes(TotalGeneral.toFixed(2)));		
				$(totalElement).val(TotalHrsWeek);
				hasChanged = true;
				window.onbeforeunload = function (evt) {
					/*$('#dialog-confirm').dialog("open");*/
		            return "";
	            }
		    });


			 /* fill MapActivity by class name */
			 /*
			$(".activitySelect").change(function (e) {
		        e.preventDefault();
		        var MapActivityIdTmp = $(this).val();
		        var idSelect = $(this).attr( "id" );
		        var Target = idSelect.substr(15);
		        var staffID = $('#staffID').val();
		        var startDate = $('#startDateVal').val();
		        var endDate = $('#endDateVal').val();
		        //setting the map activity value for this row
		        var ActivityClass = ".actID"+Target;
		        $(ActivityClass).val(MapActivityIdTmp);
		        controlManager.getTimesPerDay(MapActivityIdTmp, Target, staffID, startDate, endDate);
		    });*/
			$("#addRowLink", "#addRow").click(function(e){
				e.preventDefault();
				var rowTarget = $( "#hiddenCount" ).val();
		        if(parseInt(rowTarget) <= 29){
		        	controlManager.addRow(rowTarget);
		        	var newRowTarget = parseInt(rowTarget)+1;
		        	$( "#hiddenCount" ).val(newRowTarget);
		        	$('.note-tableSelect').trigger('input');
		        } else{
					return;
			        }
		        
			});
			$("#removeRowLink", "#removeRow").click(function(e){
				e.preventDefault();
				var rowTarget = $( "#hiddenCount" ).val();
		        var timeTaret = $( "#dateCount" ).val();
		        if(parseInt(rowTarget) >= 2){
		        var currentRowTarget = parseInt(rowTarget)-1;
				var currentTimeTaret = parseInt(timeTaret)-7;
		        	controlManager.removeRow(currentRowTarget, currentTimeTaret);
		        	controlManager.totalSumGeneral();
		        } else{
					return;
			        }
			});

			$("#disableInput").datepicker({
				prevText: '<i class="fa fa-chevron-left"></i>',
				nextText: '<i class="fa fa-chevron-right"></i>',
				dateFormat: "dd/mm/yy",
				 beforeShow: function(dateText, inst) { 
				    	$( "#disableInput" ).datepicker("setDate", new Date());
				        // for week highighting
				        $("#disableInput tr").live("mousemove", function() { 
				            $(this).find("td a").addClass("ui-state-hover"); 
				            $(this).find("#disableInput").addClass("ui-state-hover");
				        });
				        $("#disableInput tr").live("mouseleave", function() { 
				            $(this).find("td a").removeClass("ui-state-hover");
				            $(this).find("#disableInput").removeClass("ui-state-hover");      
				        });
				    }
			});	

			$("#button-prev").click(function (e) {
				
				var dateSelected = $("#prevWeekValue").val();
				var hrefData = "/timeSheetManager2bScene/timeSheet/timeSheetHome.action?dateSelected=";
				
                if(hasChanged){
                	window.onbeforeunload = null;
                	$("#dialog-confirm")
                	.data('param_href', hrefData)
                	.data('param_date', dateSelected)
                	.dialog("open");
                    }else{
                    	window.location.href = hrefData + dateSelected;
	                    }
               
            });

			$("#button-next").click(function (e) {
				window.onbeforeunload = null;
				var dateSelected = $("#nextWeekValue").val();
				var hrefData = "/timeSheetManager2bScene/timeSheet/timeSheetHome.action?dateSelected=";
				
                if(hasChanged){

                	$("#dialog-confirm")
                	.data('param_href', hrefData)
                	.data('param_date', dateSelected)
                	.dialog("open");
                    }else{
                    	window.location.href = hrefData + dateSelected;
	                    }
               
            });

			$("#day-button").click(function (e) {
				window.onbeforeunload = null;
				var dateSelected = $("#normalDayValue").val();
				var hrefData = "/timeSheetManager2bScene/timeSheet/timeSheetPerDay.action?dateSelected=";
				
                if(hasChanged){

                	$("#dialog-confirm")
                	.data('param_href', hrefData)
                	.data('param_date', dateSelected)
                	.dialog("open");
                    }else{
                    	window.location.href = hrefData + dateSelected;
	                    }
               
            });

			$('button[type=submit]').click(function (e) {
                window.onbeforeunload = null;
            });


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

<div id="dialog-confirm" title="Leaving Timesheet">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>The data has changed. Do you save it before proceeding?</p>
</div>
 
	<div class="smart-wrap">
    	<div class="smart-forms smart-container wrap-5" style="width: 1640px">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-clock-o"></i>Weekly Time Sheet</h4>
            </div><!-- end .form-header section -->
            
            <form method="post" action="timeSheetAddUpdate" id="smart-form">
            
            	<div class="form-body">
            	<input type="hidden" name="selectedMapActID" id="selectedMapActID" class="actID-0">
            	
            		<div class="frm-row">
            		<div class="section colm colm3" style="width: 248px;">
						<label class="field-label"><Strong>Person in charge:  </Strong></label>	 
  							 <label for="projectName" class="field append-icon">
                        		<input type="text" readonly="readonly" name="pCharge" id='<s:property value="uniquePCharge.id"/>' class="gui-input" value='<s:property value="uniquePCharge.firstName"/> <s:property value="uniquePCharge.lastName"/>'> 
                        		<s:hidden type="hidden" id="staffID" name="staffID" value="%{uniquePCharge.id}"></s:hidden>
                        	</label>
					</div>	 
					</div><!-- end section --> 
					 <!--
					<div class="section" style="width: 300px">
						<label class="field-label"><Strong>Week:  </Strong><span id="startDate"></span> - <span id="endDate"></span></label> 
						<input type="hidden" id="week-picker" />  
						
					</div> end section --> 
					 <div class="frm-row">
						 <div class="section colm colm3" style="width: 248px;">
							<label class="field-label"><Strong>Week:  </Strong></label>	 
									<label class="field prepend-icon">
		                            	<input type="text"  id="weekpicker" class="gui-input" value='<s:property value="weekTime"/>' readonly>
		                            	<s:hidden name="dateSelected" value="%{dateSelected}"></s:hidden>
		                                <span class="field-icon" style="top:13px"><i class="fa fa-calendar"></i></span> 
		                            </label>
						</div>
						<div class="section colm colm2" style="padding-top: 24px; max-width: 80px;">
                                <a id="button-prev" href="#" class="button button-left">Prev</a>
                            	<input type="hidden" id="prevWeekValue" value="<s:property  value="%{previousWeekDay}" />">
                            </div><!-- end section -->                     
                            
                            <div class="section colm colm2" style="padding-top: 24px; max-width: 80px;">
                                <a id="button-next" href="#" class="button button-right">Next</a>
                            	<input type="hidden" id="nextWeekValue" value="<s:property  value="%{nextWeekDay}" />">
                            </div><!-- end section -->
					 </div><!-- end section --> 
					
            		<div class="spacer-b30">
                    	<div class="tagline"><span> Please select a project. </span></div><!-- .tagline -->
                    </div>
                    <div style="padding-bottom: 15px;">
                    		<div id="addRow" style="height: 25px; width: 25px; display: inline-block; float: left;">
					             <a id="addRowLink" style="height: 24px; width: 24px" href="#">
										<img src="/twobSceneWebApp/html/img/sign-add.png" alt="add" title="add row">
								 </a>
					        </div>
					        <div id="addRowTag" style="height: 25px; width: 85px; display: inline-block; float: left;">
					              <span>Add row</span>
					        </div>
					        <div id="removeRow" style="height: 25px; width: 25px; display: inline-block; float: left;">
					             <a id="removeRowLink" style="height: 24px; width: 24px" href="#">
										<img src="/twobSceneWebApp/html/img/sign-delete.png" alt="remove" title="remove row">
								 </a>
					        </div>
					        <div id="removeRowTag" style="height: 25px; width: 160px; display: inline-block; float: left;">
					              <span>Remove row</span>
					        </div>
					       
					        <div style="padding-left: 70%; display: inline;">
					        	<a class="button" id="day-button" href="#"> Day </a>
					        	<input type="hidden" id="normalDayValue" value="<s:property  value="%{monNormal}" />">
					        </div>
					        <div style="display: inline;">
					        	<a class="button" href="#"> Week </a>
					        </div>
                    </div>
                    <div>
		                <table class="zebra" frame="box" id="time-table">
						  <thead>
						    <tr>
						      	<th style="width:300px">Project</th>
								<th style="width:200px">Stage</th>
								<th style="width:160px">Activity</th>
								<th style="width:300px">Note</th>
								<th style="width:60px"><span id="MondayLabel" ><s:property value="mondayStr"/></span><input type="hidden" id="dateStrt" name="startDayWeek" value="<s:property  value="%{monNormal}" />"></th>
								<th style="width:60px"><span id="TuesdayLabel"><s:property value="tuesdayStr"/></span></th>
								<th style="width:60px"><span id="WednesdayLabel"><s:property value="wednesdayStr"/></span></th>
								<th style="width:60px"><span id="ThursdayLabel"><s:property value="thursdayStr"/></span></th>
								<th style="width:50px"><span id="FridayLabel"><s:property value="fridayStr"/></span></th>
								<th style="width:60px"><span id="SaturdayLabel"><s:property value="saturdayStr"/></span></th>
								<th style="width:60px"><span id="SundayLabel"><s:property value="sundayStr"/></span><input type="hidden" id="dateEnd" name="endDayWeek" value="<s:property  value="%{sunNormal}" />"></th>
								<th style="width:40px">Total Hours</th>
						      </tr>
						  </thead>
						  <tbody>
						  <s:set var="rowCount" value="0"></s:set>
						  <s:set var="arrayCount" value="0"></s:set>
						  <s:iterator value="listTimesOnWeek" var="listTimesOnWeek" >
							 <!--Start Row <s:property  value="%{#rowCount}" /> -->
						     <tr id="row-<s:property  value="%{#rowCount}" />">
						     	<td style="width:90px">
						     					<s:select 
												     id="%{'projectSelected-' + #rowCount}"
												     theme="css_xhtml"
										        	 list="listActiveProjectsByUser"
										        	 cssClass="projectSelect time-tableSelect"	 
										        	 listKey="projectID"
													 listValue="projectName"
													 value="%{#listTimesOnWeek.projectID}"
												     multiple="false"
												     headerKey = ""
												     disabled="true"
							    				   	 headerValue = "Select project..."
												     size="1">
												</s:select>
						     	</td>
						     	<td style="width:90px">
							        		<s:select
										       name="stageSelected"
										       id="%{'stageSelected-' + #rowCount}"
										       theme="css_xhtml"
										       cssClass="stageSelect time-tableSelect"
										       list="listStages"
											   listKey="stageID"
											   listValue="stageName"
											   value="%{#listTimesOnWeek.stageID}"
										       multiple="false"
										       headerKey = "" 
										       disabled="true"
						    				   headerValue = "Select stage..."
										       size="1"/>
							   </td>
							   <td style="width:90px">	
							        	<s:select
											       name="taskSelected"
											       id="%{'taskSelected-' + #rowCount}"
											       cssClass="taskSelect time-tableSelect"
										       	   theme="css_xhtml"
											       list="listAllActivities"
												   listKey="actID"
												   listValue="actName"
												   value="%{#listTimesOnWeek.actID}"
											       multiple="false"
											       headerKey = "" 
											       disabled="true"
							    				   headerValue = "Select activity..."
											       size="1"/>
							   </td>
							   <!-- Note -->
							        <td>
							        <input type="hidden" name="notePerRow[<s:property  value="%{#rowCount}" />].projectId" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="notePerRow[<s:property  value="%{#rowCount}" />].act_id" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <textarea name="notePerRow[<s:property  value="%{#rowCount}" />].mapActDesc" id="noteActivity-<s:property  value="%{#rowCount}" />" class="note-tableSelect" style="height: 32px;" rows="6" wrap="hard"><s:property  value="%{#listTimesOnWeek.mapActDesc}" /></textarea>
							        </td>
							        <!-- monday -->
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDMonday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDMonday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="sessionUserID" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingMonday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{monNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timeMondayDec-<s:property  value="%{#rowCount}" />" class="time-input-mon time-tableinput" value="<s:property  value="%{#listTimesOnWeek.monTime}" />">
							        </td>
							         <!-- tuesday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDtuesday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDtuesday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingtuesday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{tueNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timetuesdaDec-<s:property  value="%{#rowCount}" />" class="time-input-tue time-tableinput" value="<s:property  value="%{#listTimesOnWeek.tueTime}" />">
							        </td>
							        <!-- wednesday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDwednesday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDwednesday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDwednesday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{wedNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timewednesDec-<s:property  value="%{#rowCount}" />" class="time-input-wed time-tableinput" value="<s:property  value="%{#listTimesOnWeek.wedTime}" />">
							        </td>
							        <!-- thursday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDthursday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDthursday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDthursday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{thuNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timethursdDec-<s:property  value="%{#rowCount}" />" class="time-input-thu time-tableinput" value="<s:property  value="%{#listTimesOnWeek.thuTime}" />">
							        </td>
							        <!-- friday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDfriday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDfriday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDfriday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{friNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timefridayDec-<s:property  value="%{#rowCount}" />" class="time-input-fri time-tableinput" value="<s:property  value="%{#listTimesOnWeek.friTime}" />">
							        </td>
							        <!-- Saturday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDSaturday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDSaturday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDSaturday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{satNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timeSaturdDec-<s:property  value="%{#rowCount}" />" class="time-input-sat time-tableinput" value="<s:property  value="%{#listTimesOnWeek.satTime}" />">
							        </td>
							        <!-- sunday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.projectID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />" value="<s:property  value="%{#listTimesOnWeek.actID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDsunday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDsunday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDsunday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{sunNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timesundayDec-<s:property  value="%{#rowCount}" />" class="time-input-sun time-tableinput" value="<s:property  value="%{#listTimesOnWeek.sunTime}" />">
							        </td>
							        <!-- Total Week -->
							        <td>
							        <input type="text" id="total-sum-<s:property  value="%{#rowCount}" />" class="time-total-week total-tableinput" value="<s:property  value="%{#listTimesOnWeek.totalTime}" />" readonly>
							        </td>
						    </tr>
						    <s:set var="arrayCount" value="%{#arrayCount+1}" />
						   	<s:set var="rowCount" value="%{#rowCount+1}"></s:set>
						     <!-- End Row from DB -->
						     <!-- Start row displayede empty -->
						    </s:iterator>
						     <s:iterator var="counter" begin="%{#rowCount}" end="10" >
						     <tr id="row-<s:property  value="%{#rowCount}" />">
						     	<td style="width:90px">
						     					<s:select 
												     id="%{'projectSelected-' + #rowCount}"
												     theme="css_xhtml"
										        	 list="listActiveProjectsByUser"
										        	 cssClass="projectSelect time-tableSelect"	 
										        	 listKey="projectID"
													 listValue="projectName"
												     multiple="false"
												     headerKey = "" 
							    				   	 headerValue = "Select project..."
												     size="1">
												</s:select>
						     	</td>
						     	<td style="width:90px">
							        		<s:select
										       name="stageSelected"
										       id="%{'stageSelected-' + #rowCount}"
										       theme="css_xhtml"
										       cssClass="stageSelect time-tableSelect"
										       list="{}"
											   listKey="stageID"
											   listValue="stageName"
										       multiple="false"
										       headerKey = "" 
						    				   headerValue = "Select stage..."
										       size="1"/>
							   </td>
							   <td style="width:90px">	
							        	<s:select
											       name="taskSelected"
											       id="%{'taskSelected-' + #rowCount}"
											       cssClass="taskSelect time-tableSelect"
										       	   theme="css_xhtml"
											       list="listAllActivities"
												   listKey="actID"
												   listValue="actName"
											       multiple="false"
											       headerKey = "" 
							    				   headerValue = "Select activity..."
											       size="1"/>
							   </td>
							   <!-- Note -->
							        <td>
							        <input type="hidden" name="notePerRow[<s:property  value="%{#rowCount}" />].projectId" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="notePerRow[<s:property  value="%{#rowCount}" />].act_id" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <textarea name="notePerRow[<s:property  value="%{#rowCount}" />].mapActDesc" id="noteActivity-<s:property  value="%{#rowCount}" />" class="note-tableSelect" style="height: 32px;" rows="6" wrap="hard"></textarea>
							        </td>
							        <!-- monday -->
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDMonday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDMonday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingMonday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{monNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timeMondayDec-<s:property  value="%{#rowCount}" />" class="time-input-mon time-tableinput">
							        </td>
							         <!-- tuesday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDtuesday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDtuesday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingMonday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{tueNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timetuesdaDec-<s:property  value="%{#rowCount}" />" class="time-input-tue time-tableinput">
							        </td>
							        <!-- wednesday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDwednesday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDwednesday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDwednesday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{wedNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timewednesDec-<s:property  value="%{#rowCount}" />" class="time-input-wed time-tableinput">
							        </td>
							        <!-- thursday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDthursday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDthursday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDthursday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{thuNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timethursdDec-<s:property  value="%{#rowCount}" />" class="time-input-thu time-tableinput">
							        </td>
							        <!-- friday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDfriday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDfriday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDfriday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{friNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timefridayDec-<s:property  value="%{#rowCount}" />" class="time-input-fri time-tableinput">
							        </td>
							        <!-- Saturday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDSaturday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDSaturday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDSaturday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{satNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timeSaturdDec-<s:property  value="%{#rowCount}" />" class="time-input-sat time-tableinput">
							        </td>
							        <!-- sunday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDsunday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDsunday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDsunday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{sunNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timesundayDec-<s:property  value="%{#rowCount}" />" class="time-input-sun time-tableinput">
							        </td>
							        <!-- Total Week -->
							        <td>
							        <input type="text" id="total-sum-<s:property  value="%{#rowCount}" />" class="time-total-week total-tableinput" readonly>
							        </td>
						    </tr>
						    <s:set var="arrayCount" value="%{#arrayCount+1}" />
						   	<s:set var="rowCount" value="%{#rowCount+1}"></s:set>
						     <!-- Start row no diplayed -->
						    </s:iterator>
						   	<s:set var="hiddenRow" value="%{#rowCount}" />
						     <s:iterator var="counter" begin="%{#rowCount}" end="29" >
						     <tr id="row-<s:property  value="%{#rowCount}" />" style="display: none;">
						     	<td style="width:90px">
						     					<s:select 
												     id="%{'projectSelected-' + #rowCount}"
												     theme="css_xhtml"
										        	 list="listActiveProjectsByUser"
										        	 cssClass="projectSelect time-tableSelect"	 
										        	 listKey="projectID"
													 listValue="projectName"
												     multiple="false"
												     headerKey = "" 
							    				   	 headerValue = "Select project..."
												     size="1">
												</s:select>
						     	</td>
						     	<td style="width:90px">
							        		<s:select
										       name="stageSelected"
										       id="%{'stageSelected-' + #rowCount}"
										       theme="css_xhtml"
										       cssClass="stageSelect time-tableSelect"
										       list="{}"
											   listKey="stageID"
											   listValue="stageName"
										       multiple="false"
										       headerKey = "" 
						    				   headerValue = "Select stage..."
										       size="1"/>
							   </td>
							   <td style="width:90px">	
							        	<s:select
											       name="taskSelected"
											       id="%{'taskSelected-' + #rowCount}"
											       cssClass="taskSelect time-tableSelect"
										       	   theme="css_xhtml"
											       list="listAllActivities"
												   listKey="actID"
												   listValue="actName"
											       multiple="false"
											       headerKey = "" 
							    				   headerValue = "Select activity..."
											       size="1"/>
							   </td>
							   <!-- Note -->
							        <td>
							        <input type="hidden" name="notePerRow[<s:property  value="%{#rowCount}" />].projectId" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="notePerRow[<s:property  value="%{#rowCount}" />].act_id" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <textarea name="notePerRow[<s:property  value="%{#rowCount}" />].mapActDesc" id="noteActivity-<s:property  value="%{#rowCount}" />" class="note-tableSelect" style="height: 32px;" rows="6" wrap="hard"></textarea>
							        </td>
							        <!-- monday -->
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDMonday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDMonday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingMonday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{monNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timeMondayDec-<s:property  value="%{#rowCount}" />" class="time-input-mon time-tableinput">
							        </td>
							         <!-- tuesday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDtuesday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDtuesday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingMonday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{tueNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timetuesdaDec-<s:property  value="%{#rowCount}" />" class="time-input-tue time-tableinput">
							        </td>
							        <!-- wednesday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDwednesday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDwednesday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDwednesday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{wedNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timewednesDec-<s:property  value="%{#rowCount}" />" class="time-input-wed time-tableinput">
							        </td>
							        <!-- thursday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDthursday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDthursday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDthursday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{thuNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timethursdDec-<s:property  value="%{#rowCount}" />" class="time-input-thu time-tableinput">
							        </td>
							        <!-- friday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDfriday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDfriday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDfriday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{friNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timefridayDec-<s:property  value="%{#rowCount}" />" class="time-input-fri time-tableinput">
							        </td>
							        <!-- Saturday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDSaturday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDSaturday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDSaturday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{satNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timeSaturdDec-<s:property  value="%{#rowCount}" />" class="time-input-sat time-tableinput">
							        </td>
							        <!-- sunday -->
							        <s:set var="arrayCount" value="%{#arrayCount+1}" />
							        <td>
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].projectID" id="projectID-<s:property  value="%{#rowCount}" />" class="projID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].actID" id="activityID-<s:property  value="%{#rowCount}" />" class="actID-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackID" id="trackIDsunday-<s:property  value="%{#rowCount}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].pChargeID" id="pChargeIDsunday-<s:property  value="%{#rowCount}" />" class="pChargeID" value="<s:property  value="%{sessionUserID}" />">
							        <input type="hidden" name="timeData[<s:property  value="%{#arrayCount}" />].trackingDate" id="trackingIDsunday-<s:property  value="%{#rowCount}" />" class="trackingDate" value="<s:property  value="%{sunNormal}" />">
							        <input type="text" name="timeData[<s:property  value="%{#arrayCount}" />].trackingTime" id="timesundayDec-<s:property  value="%{#rowCount}" />" class="time-input-sun time-tableinput">
							        </td>
							        <!-- Total Week -->
							        <td>
							        <input type="text" id="total-sum-<s:property  value="%{#rowCount}" />" class="time-total-week total-tableinput" readonly>
							        </td>
						    </tr>
						     <s:set var="arrayCount" value="%{#arrayCount+1}" />
						   	 <s:set var="rowCount" value="%{#rowCount+1}"></s:set>
							 </s:iterator>
						     <!--Start Row Total Per day -->
						     <tr id="row-Totals">
						      		<td colspan="4" align="right" >
						      			<span><strong>Total per day:&nbsp;&nbsp;&nbsp;&nbsp; </strong></span>
						       		</td>
							        <!-- monday -->
							        <td>
							        <input type="text" id="timeTotal-Mon" class="total-mon total-tableinput" readonly>
							        </td>
							        <!-- tuesday -->
							        <td>
							        <input type="text" id="timeTotal-Tue" class="total-tue total-tableinput" readonly>
							        </td>
							        <!-- wednesday -->
							        <td>
							        <input type="text" id="timeTotal-Wed" class="total-wed total-tableinput" readonly>
							        </td>
							        <!-- thursday -->
							        <td>
							        <input type="text" id="timeTotal-Thu" class="total-thu total-tableinput" readonly>
							        </td>
							        <!-- friday -->
							        <td>
							        <input type="text" id="timeTotal-Fri" class="total-fri total-tableinput" readonly>
							        </td>
							        <!-- Saturday -->
							        <td>
							        <input type="text" id="timeTotal-Sat" class="total-sat total-tableinput" readonly>
							        </td>
							        <!-- sunday -->
							        <td>
							        <input type="text" id="timeTotal-Sun" class="total-sun total-tableinput" readonly>
							        </td>
							        <!-- Total -->
							        <td>
							        <input type="text" id="total-general" class="tot-gen total-tableinput" readonly>
							        </td>
							        
						    </tr>
						    <!-- end row -->
						  </tbody>
						</table>
                   </div>
                   <input type="hidden" id="projectCount" value="<s:property  value="%{#rowCount}" />">
				   <input type="hidden" id="dateCount" value="<s:property  value="%{#arrayCount}" />">
				   <input type="hidden" id="hiddenCount" value="<s:property  value="%{#hiddenRow}" />">
            	</div><!-- end .form-body section -->
                
                    
                <div class="form-footer">
                	<button type="submit" class="button btn-primary" value="timeSheetAddUpdate">Save</button>
                    <button type="reset" class="button"> Clear </button>
                </div><!-- end .form-footer section -->
            </form>
            
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
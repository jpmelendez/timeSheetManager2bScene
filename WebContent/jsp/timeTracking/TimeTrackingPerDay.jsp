
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<style>
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    .gui-textarea { margin-bottom:12px; width:95%; padding: .4em; }
    input.select { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>

	 <script>
	 /*
		var project = $( "#projectSelected" );
	    var stage = $( "#stageSelected" );
	    var act = $( "#taskSelected" );
	    var time = $( "#timeSelected" );
	    var allFields = $( [] ).add( project ).add( stage ).add( act ).add( time );
	    var tips = $( ".validateTips" );
	    
	 var dialogManager = {
	    		var project = $( "#projectSelected" );
	    	    var stage = $( "#stageSelected" );
	    	    var act = $( "#taskSelected" );
	    	    var time = $( "#timeSelected" );
	    	    var allFields = $( [] ).add( project ).add( stage ).add( act ).add( time );
	    	    var tips = $( ".validateTips" );
			updateTips : function ( t ) {
			      tips
			        .text( t )
			        .addClass( "ui-state-highlight" );
			      setTimeout(function() {
			        tips.removeClass( "ui-state-highlight", 1500 );
			      }, 500 );
			    },
			checkLength : function ( o, n, min, max ) {
			        if ( o.val().length > max || o.val().length < min ) {
			          o.addClass( "ui-state-error" );
			          dialogManager.updateTips( n + " can not be empty ");
			          return false;
			        } else {
			          return true;
			        }
			      },
			checkRegexp : function ( o, regexp, n ) {
			          if ( !( regexp.test( o.val() ) ) ) {
			            o.addClass( "ui-state-error" );
			            dialogManager.updateTips( n );
			            return false;
			          } else {
			            return true;
			          }
			        },
			 
			 addTime : function () {
				  var valid = true;
			      allFields.removeClass( "ui-state-error" );
			      valid = valid && dialogManager.checkLength( project, "Projet name", 1, 10 );
			      valid = valid && dialogManager.checkLength( stage, "Stage", 1, 10 );
			      valid = valid && dialogManager.checkLength( act, "Task", 1, 10 );
			      valid = valid && dialogManager.checkLength( time, "Time", 1, 10 );
			      
			      valid = valid && dialogManager.checkRegexp( time, /^\d+(?:\.\d\d?)?$/, "Time may consist of 0-9 digits." );
			      if ( valid ) {
			        $( "#smart-form" ).submit();
			        dialog.dialog( "close" );
			      }
			      return valid;
				}
		    

		 };
	 */
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
				}

				
	}
	
	 
	 $(document).ready(function(){
			
		 	
		 		//var dialog, form;
			    var startDate;
			    var endDate;
			    var tabIDTMP = $('#tab-target').val();
				var tabID = parseInt(tabIDTMP);
			    
			    
			    var selectCurrentWeek = function() {
			        window.setTimeout(function () {
			            $('#weekpicker').datepicker('widget').find('.ui-datepicker-current-day a').addClass('ui-state-active')
			        }, 1);
			    }

			    $( "#tabs" ).tabs({ 
			        active: tabID 
			        });
			    
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
				    	var pathname = window.location.pathname;
				    	var dateString = "?dateSelected="+dateSelected;
				 		alert("Path name: " + pathname)
				    	window.location.href = pathname+dateString;
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
		    /*
			dialog = $( "#dialog-form" ).dialog({
			      autoOpen: false,
			      height: 400,
			      width: 550,
			      modal: true,
			      buttons: {
			        "Submit time": dialogManager.addTime,
			        Cancel: function() {
			          dialog.dialog( "close" );
			        }
			      },
			      close: function() {
			        form[ 0 ].reset();
			        allFields.removeClass( "ui-state-error" );
			      }
			    });
			 
			    form = dialog.find( "form" ).on( "submit", function( event ) {
			      event.preventDefault();
			      dialogManager.addTime
			    });

			    $("#addRowLink", "#addRow").on( "click", function() {
				    var pchargeID = $("#staffID").val();
			    	var titleElement = $(".ui-state-active").find(".date-target");
				    var activeDateVal = titleElement.first().val();
				    var titleVal = "New Time Entry      " + activeDateVal;
				    //setting up values
				    $("#dialog-tdate").val(activeDateVal);
					$("#dialog-pchargeid").val(pchargeID);
				    dialog.dialog({ title: titleVal });
			        dialog.dialog( "open" );
				   /* dialog.dialog({
				    	  open: function( event, ui ) {
									
					    	  }
				    	}); 
			      });
			    */ 

	 });
</script>
    
<body class="woodbg">
<s:hidden id="tab-target" value="%{tabactive}"></s:hidden>
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
<!-- save time form -->
                    <div id="dialog-form" title="New Time Entry">
  						<p class="validateTips"></p>
 						 <form method="post" action="timeSheetAddUpdatePerDay" id="time-form">
						    <fieldset>
						    <input class="dialog_fields" type="hidden" name="timeData[0].trackID" id="dialog-trackid" value="" >
						    <input class="dialog_fields" type="hidden" name="timeData[0].pChargeID" id="dialog-pchargeid" value="" >
						    <input class="dialog_fields" type="hidden" name="timeData[0].trackingDate" id="dialog-tdate" value="" >
						   	<input class="dialog_fields" type="hidden" name="dateSelected" id="dialog-tdate-selected" value="" >
						   	<input class="dialog_fields" type="hidden" name="tabactive" id="dialog-tab-selected" value="" >
						    <div class="section">
						      <label for="project" class="field-label">Project: </label>
						      <s:select 
												     id="projectSelected"
												     name="timeData[0].projectID"
										        	 list="listActiveProjectsByUser"
										        	 cssClass="projectSelect time-tableSelect"	 
										        	 listKey="projectID"
													 listValue="projectName"
													 value=""
												     multiple="false"
												     headerKey = ""
							    				   	 headerValue = "Select project..."
												     size="1">
							  </s:select>
							  </div>
							  <div class="section">
						      <label for="stage" class="field-label">Stage: </label>
						      <s:select
										       id="stageSelected"
										       name="timeData[0].stageID"
										       cssClass="stageSelect time-tableSelect"
										       list="listStages"
											   listKey="stageID"
											   listValue="stageName"
											   value=""
										       multiple="false"
										       headerKey = "" 
						    				   headerValue = "Select stage..."
										       size="1"/>
							  </div>
							  <div class="section">
						      <label for="task" class="field-label">Task: </label>
						     <s:select
											       id="taskSelected"
											        name="timeData[0].actID"
											       cssClass="taskSelect time-tableSelect"
											       list="listAllActivities"
												   listKey="actID"
												   listValue="actName"
												   value=""
											       multiple="false"
											       headerKey = "" 
							    				   headerValue = "Select activity..."
											       size="1"/>
							  </div>
							   <div class="frm-row">
							    <div class="section colm colm6">
							    	<label for="comment" class="field-label">Notes: </label>
                        			<textarea class="gui-textarea" id="comment" name="timeData[0].trackingNote"></textarea>
							    </div>
							     <div class="section colm colm6">
							     <label for="time" class="field-label">Time: </label>
							      <input type="text" name="timeData[0].trackingTime" id="timeSelected" class="text ui-widget-content ui-corner-all" placeholder="0.00">
							     </div>
							   </div>
						      <!-- Allow form submission with keyboard without duplicating the dialog button -->
						      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
						    </fieldset>
  						</form>
					</div>
	<div class="smart-wrap">
    	<div class="smart-forms smart-container wrap-5" style="width: 1000px">
        
        	<div class="form-header header-primary">
            	<h4><i class="fa fa-clock-o"></i>Day Time Sheet</h4>
            </div><!-- end .form-header section -->
            
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
	                            
						</div><!-- end section --> 
						<div class="section colm colm2" style="padding-top: 24px; max-width: 80px;">
                                <a href="/timeSheetManager2bScene/timeSheet/timeSheetPerDay.action?dateSelected=<s:property  value="%{previousWeekDay}" />" class="button button-left">Prev</a>
                            </div><!-- end section -->                     
                            
                            <div class="section colm colm2" style="padding-top: 24px; max-width: 80px;">
                                <a href="/timeSheetManager2bScene/timeSheet/timeSheetPerDay.action?dateSelected=<s:property  value="%{nextWeekDay}" />" class="button button-right">Next</a>
                            </div><!-- end section -->
					</div>
					
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
					              <span>Note</span>
					        </div>
					        <div style="padding-left: 70%; display: inline;">
					        	<a class="button" href="#"> Day </a>
					        </div>
					        <div style="display: inline;">
					        	<a class="button" href="/timeSheetManager2bScene/timeSheet/timeSheetHome.action?dateSelected=<s:property  value="%{monNormal}" />"> Week </a>
					        </div>
                    </div>
                    
                    <div>
						<div id="timecontainer">
							<div id="tabs">
							  <ul>
							    <li><a href="#tabs-1">Monday</a><input type="hidden" id="date-mon" class="date-target" value="<s:property value="%{monNormal}"/>" /><input type="hidden" id="tab-mon" class="tab-target" value="0" /></li>
								<li><a href="#tabs-2">Tuesday</a><input type="hidden" id="date-tue" class="date-target" value="<s:property value="%{tueNormal}"/>" /><input type="hidden" id="tab-tue" class="tab-target" value="1" /></li>
								<li><a href="#tabs-3">Wednesday</a><input type="hidden" id="date-wed" class="date-target" value="<s:property value="%{wedNormal}"/>" /><input type="hidden" id="tab-wed" class="tab-target" value="2" /></li>
								<li><a href="#tabs-4">Thursday</a><input type="hidden" id="date-thu" class="date-target" value="<s:property value="%{thuNormal}"/>" /><input type="hidden" id="tab-thu" class="tab-target" value="3" /></li>
								<li><a href="#tabs-5">Friday</a><input type="hidden" id="date-fri" class="date-target" value="<s:property value="%{friNormal}"/>" /><input type="hidden" id="tab-fri" class="tab-target" value="4" /></li>
								<li><a href="#tabs-6">Saturday</a><input type="hidden" id="date-sat" class="date-target" value="<s:property value="%{satNormal}"/>" /><input type="hidden" id="tab-sat" class="tab-target" value="5" /></li>
								<li><a href="#tabs-7">Sunday</a><input type="hidden" id="date-sun" class="date-target" value="<s:property value="%{sunNormal}"/>" /><input type="hidden" id="tab-sun" class="tab-target" value="6" /></li>
							  </ul>
							  <div id="tabs-1">
							   	<div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="timesMon" var="timesMon">
															<li id="row" style="padding:0 0 10px 0;">
															<div id="activitybox">
																<div style="float: left;width: 90%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#timesMon.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 10%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Time: <strong><span style="font-size:12px;"><s:property value="%{#timesMon.totalTime}"/></span></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par"><strong><s:property value="%{#timesMon.stageName}"/>:</strong> <s:property value="%{#timesMon.taskName}"/> / <s:property value="%{#timesMon.actName}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 10%;height: 50px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par"><input id="<s:property value="%{#timesMon.trackID}"/>" value="Edit" class="button editTime" type="submit"></p>
																	<input type="hidden" id="db-trackid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.trackID}"/>" >
																	<input type="hidden" id="db-mapPAid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.mapPAID}"/>" >
						    										<input type="hidden" id="db-staffid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.pChargeID}"/>" >
						    										<input type="hidden" id="db-tdateid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.trackingDate}"/>" >
						    										<input type="hidden" id="db-projeid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.projectID}"/>" >
						    										<input type="hidden" id="db-stageid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.stageID}"/>" >
						    										<input type="hidden" id="db-activid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.actID}"/>" >
						    										<input type="hidden" id="db-atimeid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.totalTime}"/>" >
						    										<input type="hidden" id="db-anoteid-<s:property value="%{#timesMon.trackID}"/>" value="<s:property value="%{#timesMon.trackingNote}"/>" >
																</div><!-- End progress -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par"><strong>Note:</strong> <s:property value="%{#timesMon.trackingNote}"/></p>
																</div><!-- End Stage name -->
															</div>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							  </div>
							  <div id="tabs-2">
							  <div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="timesTue" var="timesTue">
															<li id="row" style="padding:0 0 10px 0;">
															<div id="activitybox">
																<div style="float: left;width: 90%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#timesTue.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 10%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Time: <strong><span style="font-size:12px;"><s:property value="%{#timesTue.totalTime}"/></span></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par"><strong><s:property value="%{#timesTue.stageName}"/>:</strong> <s:property value="%{#timesTue.taskName}"/> / <s:property value="%{#timesTue.actName}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 10%;height: 50px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par"><input id="<s:property value="%{#timesTue.trackID}"/>" value="Edit" class="button editTime" type="submit"></p>
																	<input type="hidden" id="db-trackid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.trackID}"/>" >
																	<input type="hidden" id="db-mapPAid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.mapPAID}"/>" >
						    										<input type="hidden" id="db-staffid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.pChargeID}"/>" >
						    										<input type="hidden" id="db-tdateid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.trackingDate}"/>" >
						    										<input type="hidden" id="db-projeid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.projectID}"/>" >
						    										<input type="hidden" id="db-stageid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.stageID}"/>" >
						    										<input type="hidden" id="db-activid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.actID}"/>" >
						    										<input type="hidden" id="db-atimeid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.totalTime}"/>" >
						    										<input type="hidden" id="db-anoteid-<s:property value="%{#timesTue.trackID}"/>" value="<s:property value="%{#timesTue.trackingNote}"/>" >
																</div><!-- End progress -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par"><strong>Note:</strong> <s:property value="%{#timesTue.trackingNote}"/></p>
																</div><!-- End Stage name -->
															</div>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							  </div>
							  <div id="tabs-3">
							  <div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="timesWed" var="timesWed">
															<li id="row" style="padding:0 0 10px 0;">
															<div id="activitybox">
																<div style="float: left;width: 90%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#timesWed.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 10%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Time: <strong><span style="font-size:12px;"><s:property value="%{#timesWed.totalTime}"/></span></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par"><strong><s:property value="%{#timesWed.stageName}"/>:</strong> <s:property value="%{#timesWed.taskName}"/> / <s:property value="%{#timesWed.actName}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 10%;height: 50px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par"><input id="<s:property value="%{#timesWed.trackID}"/>" value="Edit" class="button editTime" type="submit"></p>
																	<input type="hidden" id="db-trackid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.trackID}"/>" >
																	<input type="hidden" id="db-mapPAid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.mapPAID}"/>" >
						    										<input type="hidden" id="db-staffid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.pChargeID}"/>" >
						    										<input type="hidden" id="db-tdateid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.trackingDate}"/>" >
						    										<input type="hidden" id="db-projeid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.projectID}"/>" >
						    										<input type="hidden" id="db-stageid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.stageID}"/>" >
						    										<input type="hidden" id="db-activid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.actID}"/>" >
						    										<input type="hidden" id="db-atimeid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.totalTime}"/>" >
						    										<input type="hidden" id="db-anoteid-<s:property value="%{#timesWed.trackID}"/>" value="<s:property value="%{#timesWed.trackingNote}"/>" >
																</div><!-- End progress -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par"><strong>Note:</strong> <s:property value="%{#timesWed.trackingNote}"/></p>
																</div><!-- End Stage name -->
															</div>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							  </div>
							   <div id="tabs-4">
							   <div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="timesThu" var="timesThu">
															<li id="row" style="padding:0 0 10px 0;">
															<div id="activitybox">
																<div style="float: left;width: 90%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#timesThu.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 10%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Time: <strong><span style="font-size:12px;"><s:property value="%{#timesThu.totalTime}"/></span></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par"><strong><s:property value="%{#timesThu.stageName}"/>:</strong> <s:property value="%{#timesThu.taskName}"/> / <s:property value="%{#timesThu.actName}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 10%;height: 50px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par"><input id="<s:property value="%{#timesThu.trackID}"/>" value="Edit" class="button editTime" type="submit"></p>
																	<input type="hidden" id="db-trackid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.trackID}"/>" >
																	<input type="hidden" id="db-mapPAid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.mapPAID}"/>" >
						    										<input type="hidden" id="db-staffid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.pChargeID}"/>" >
						    										<input type="hidden" id="db-tdateid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.trackingDate}"/>" >
						    										<input type="hidden" id="db-projeid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.projectID}"/>" >
						    										<input type="hidden" id="db-stageid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.stageID}"/>" >
						    										<input type="hidden" id="db-activid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.actID}"/>" >
						    										<input type="hidden" id="db-atimeid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.totalTime}"/>" >
						    										<input type="hidden" id="db-anoteid-<s:property value="%{#timesThu.trackID}"/>" value="<s:property value="%{#timesThu.trackingNote}"/>" >
																</div><!-- End progress -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par"><strong>Note:</strong> <s:property value="%{#timesThu.trackingNote}"/></p>
																</div><!-- End Stage name -->
															</div>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							  </div>
							  <div id="tabs-5">
							  <div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="timesFri" var="timesFri">
															<li id="row" style="padding:0 0 10px 0;">
															<div id="activitybox">
																<div style="float: left;width: 90%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#timesFri.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 10%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Time: <strong><span style="font-size:12px;"><s:property value="%{#timesFri.totalTime}"/></span></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par"><strong><s:property value="%{#timesFri.stageName}"/>:</strong> <s:property value="%{#timesFri.taskName}"/> / <s:property value="%{#timesFri.actName}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 10%;height: 50px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par"><input id="<s:property value="%{#timesFri.trackID}"/>" value="Edit" class="button editTime" type="submit"></p>
																	<input type="hidden" id="db-trackid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.trackID}"/>" >
																	<input type="hidden" id="db-mapPAid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.mapPAID}"/>" >
						    										<input type="hidden" id="db-staffid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.pChargeID}"/>" >
						    										<input type="hidden" id="db-tdateid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.trackingDate}"/>" >
						    										<input type="hidden" id="db-projeid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.projectID}"/>" >
						    										<input type="hidden" id="db-stageid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.stageID}"/>" >
						    										<input type="hidden" id="db-activid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.actID}"/>" >
						    										<input type="hidden" id="db-atimeid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.totalTime}"/>" >
						    										<input type="hidden" id="db-anoteid-<s:property value="%{#timesFri.trackID}"/>" value="<s:property value="%{#timesFri.trackingNote}"/>" >
																</div><!-- End progress -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par"><strong>Note:</strong> <s:property value="%{#timesFri.trackingNote}"/></p>
																</div><!-- End Stage name -->
															</div>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							  </div>
							  <div id="tabs-6">
							  <div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="timesSat" var="timesSat">
															<li id="row" style="padding:0 0 10px 0;">
															<div id="activitybox">
																<div style="float: left;width: 90%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#timesSat.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 10%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Time: <strong><span style="font-size:12px;"><s:property value="%{#timesSat.totalTime}"/></span></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par"><strong><s:property value="%{#timesSat.stageName}"/>:</strong> <s:property value="%{#timesSat.taskName}"/> / <s:property value="%{#timesSat.actName}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 10%;height: 50px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par"><input id="<s:property value="%{#timesSat.trackID}"/>" value="Edit" class="button editTime" type="submit"></p>
																	<input type="hidden" id="db-trackid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.trackID}"/>" >
																	<input type="hidden" id="db-mapPAid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.mapPAID}"/>" >
						    										<input type="hidden" id="db-staffid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.pChargeID}"/>" >
						    										<input type="hidden" id="db-tdateid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.trackingDate}"/>" >
						    										<input type="hidden" id="db-projeid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.projectID}"/>" >
						    										<input type="hidden" id="db-stageid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.stageID}"/>" >
						    										<input type="hidden" id="db-activid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.actID}"/>" >
						    										<input type="hidden" id="db-atimeid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.totalTime}"/>" >
						    										<input type="hidden" id="db-anoteid-<s:property value="%{#timesSat.trackID}"/>" value="<s:property value="%{#timesSat.trackingNote}"/>" >
																</div><!-- End progress -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par"><strong>Note:</strong> <s:property value="%{#timesSat.trackingNote}"/></p>
																</div><!-- End Stage name -->
															</div>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							  </div>
							   <div id="tabs-7">
							   <div id="navcontainer">
													<ul id="navlist" style="width:100%">
													<s:set var="countAct" value="0" />
														<s:iterator value="timesSun" var="timesSun">
															<li id="row" style="padding:0 0 10px 0;">
															<div id="activitybox">
																<div style="float: left;width: 90%;height: 20px; overflow: hidden; margin-top: 9px; position: relative;" ><!-- Start projectName -->
																	<p class="didot-header">Project: <s:property value="%{#timesSun.projectName}"/></p>
																</div><!-- Start date -->
																<div style="float: right;width: 10%;height: 20px;overflow: hidden; margin-top: 10px; position: relative;"><!-- Start dates -->
																	<p id="didot-par">Time: <strong><span style="font-size:12px;"><s:property value="%{#timesSun.totalTime}"/></span></strong></p>
																</div><!-- End date -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Activity Name -->
																	<p id="didot-par"><strong><s:property value="%{#timesSun.stageName}"/>:</strong> <s:property value="%{#timesSun.taskName}"/> / <s:property value="%{#timesSun.actName}"/></p>
																</div><!-- End Activity name -->
																<div style="float: right;width: 10%;height: 50px;overflow: hidden; position: relative;"><!-- Start Progress -->
																	<p id="didot-par"><input id="<s:property value="%{#timesSun.trackID}"/>" value="Edit" class="button editTime" type="submit"></p>
																	<input type="hidden" id="db-trackid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.trackID}"/>" >
																	<input type="hidden" id="db-mapPAid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.mapPAID}"/>" >
						    										<input type="hidden" id="db-staffid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.pChargeID}"/>" >
						    										<input type="hidden" id="db-tdateid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.trackingDate}"/>" >
						    										<input type="hidden" id="db-projeid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.projectID}"/>" >
						    										<input type="hidden" id="db-stageid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.stageID}"/>" >
						    										<input type="hidden" id="db-activid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.actID}"/>" >
						    										<input type="hidden" id="db-atimeid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.totalTime}"/>" >
						    										<input type="hidden" id="db-anoteid-<s:property value="%{#timesSun.trackID}"/>" value="<s:property value="%{#timesSun.trackingNote}"/>" >
																</div><!-- End progress -->
																<div style="float: left;width: 90%;height: 20px;overflow: hidden; padding-top: 10px; position: relative;" ><!-- Start Stage Name -->
																	<p id="didot-par"><strong>Note:</strong> <s:property value="%{#timesSun.trackingNote}"/></p>
																</div><!-- End Stage name -->
															</div>
															</li>
															<s:set var="countAct" value="%{#countAct+1}" />
														</s:iterator>
													</ul>
										</div>
							  </div>
							</div>
						</div>
                   </div>
                   <input type="hidden" id="projectCount" value="<s:property  value="%{#rowCount}" />">
				   <input type="hidden" id="dateCount" value="<s:property  value="%{#arrayCount}" />">
				   <input type="hidden" id="hiddenCount" value="<s:property  value="%{#hiddenRow}" />">
            	</div><!-- end .form-body section -->
                
                <div class="form-footer">
                <!--  
                	<button type="submit" class="button btn-primary" value="timeSheetAddUpdate">Save</button>
                    <button type="reset" class="button"> Clear </button>
                -->
                </div><!-- end .form-footer section -->
        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->
    
    <div></div><!-- end section -->
</body>
 </html> 
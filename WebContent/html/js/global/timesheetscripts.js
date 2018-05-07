/**
 * timesheet per day script
 */

$( function() {
    var dialog, form;
 
     var project = $( "#projectSelected" );
	    	    var stage = $( "#stageSelected" );
	    	    var act = $( "#taskSelected" );
	    	    var time = $( "#timeSelected" );
	    	    var allFields = $( [] ).add( project ).add( stage ).add( act ).add( time );
	    	    var tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
      updateTips( n + " can not be empty ");
        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );
        return false;
      } else {
        return true;
      }
    }
 
    function addTime() {
      var valid = true;
			      allFields.removeClass( "ui-state-error" );
			      valid = valid && checkLength( project, "Projet name", 1, 10 );
			      valid = valid && checkLength( stage, "Stage", 1, 10 );
			      valid = valid && checkLength( act, "Task", 1, 10 );
			      valid = valid && checkLength( time, "Time", 1, 10 );
			      
			      valid = valid && checkRegexp( time, /^\d+(?:\.\d\d?)?$/, "Time may consist of 0-9 digits." );
			      if ( valid ) {
			    	  form.submit();
			        dialog.dialog( "close" );
			      }
			      return valid;
    }
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
			      height: 400,
			      width: 550,
			      modal: true,
			      buttons: {
			        "Submit time": addTime,
			        Cancel: function() {
			          $('.dialog_fields').val('');
				      form[ 0 ].reset();
			          dialog.dialog( "close" );
			        }
			      },
			      close: function() {
			        form[ 0 ].reset();
			        $('.dialog_fields').val('');
			        allFields.removeClass( "ui-state-error" );
			      }
    });
   
    form = dialog.find( "form" );
    
     $("#addRowLink", "#addRow").on( "click", function() {
				    var pchargeID = $("#staffID").val();
			    	var titleElement = $(".ui-state-active").find(".date-target");
			    	var tabElement = $(".ui-state-active").find(".tab-target");
				    var activeDateVal = titleElement.first().val();
				    var activeTab = tabElement.first().val();
				    var titleVal = "New Time Entry      " + activeDateVal;
				    //setting up values
				    $("#dialog-tdate").val(activeDateVal);
				    $("#dialog-tdate-selected").val(activeDateVal);
					$("#dialog-pchargeid").val(pchargeID);
					$("#dialog-tab-selected").val(activeTab);
				    dialog.dialog({ title: titleVal });
			        dialog.dialog( "open" );
			      });
				  
	$(".editTime").on( "click", function() {
					var thisTrackID = $(this).attr('id');
					//target variables
					var trackid = "#db-trackid-" + thisTrackID;
                    var staffid = "#db-staffid-" + thisTrackID;
                    var tdateid = "#db-tdateid-" + thisTrackID;
                    var projeid = "#db-projeid-" + thisTrackID;
                    var stageid = "#db-stageid-" + thisTrackID;
                    var activid = "#db-activid-" + thisTrackID;
                    var atimeid = "#db-atimeid-" + thisTrackID;
                    var anoteid = "#db-anoteid-" + thisTrackID;
					//values
					var trackidVal = $(trackid).val();
                    var staffidVal = $(staffid).val();
                    var tdateidVal = $(tdateid).val();
                    var projeidVal = $(projeid).val();
                    var stageidVal = $(stageid).val();
                    var actividVal = $(activid).val();
                    var atimeidVal = $(atimeid).val();
                    var anoteidVal = $(anoteid).val();
					//title values
					var titleElement = $(".ui-state-active").find(".date-target");
					var tabElement = $(".ui-state-active").find(".tab-target");
				    var activeDateVal = titleElement.first().val();
				    var activeTab = tabElement.first().val();
				    var titleVal = "Edit Time Entry      " + activeDateVal;
					//setting values
				    $("#dialog-tdate-selected").val(tdateidVal);
					$("#dialog-trackid").val(trackidVal);
					$("#dialog-pchargeid").val(staffidVal);
					$("#dialog-tdate").val(tdateidVal);
					$("#projectSelected").val(projeidVal);
					$("#stageSelected").val(stageidVal);
					$("#taskSelected").val(actividVal);
					$("#comment").val(anoteidVal);
					$("#timeSelected").val(atimeidVal);
					$("#dialog-tab-selected").val(activeTab);
					//setting title
			    	dialog.dialog({ title: titleVal });
					//opening
			        dialog.dialog( "open" ); 
			      });
  } );
<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String firstname = (String)session.getAttribute("firstname");
	String lastname = (String)session.getAttribute("lastname");
	String isApproval = (String)session.getAttribute("isApproval");
	
 	if (firstname == null || lastname == null) {
		response.sendRedirect("FactoryLogin");
	}
%>
<%
	String machine = (String)session.getAttribute("machine");
	String temperatureFlotData = (String)session.getAttribute("temperatureFlotData");
	String pressureFlotData = (String)session.getAttribute("pressureFlotData");
	String flowRateFlotData = (String)session.getAttribute("flowRateFlotData");
	String pDFOfTemperature = (String)session.getAttribute("pDFOfTemperature");
	String pDFOfPressure = (String)session.getAttribute("pDFOfPressure");
	String pDFOfFlowRate = (String)session.getAttribute("pDFOfFlowRate");
	String normalDistributionOfTemperature = (String)session.getAttribute("normalDistributionOfTemperature");
	String normalDistributionOfPressure = (String)session.getAttribute("normalDistributionOfPressure");
	String normalDistributionOfFlowRate = (String)session.getAttribute("normalDistributionOfFlowRate");

	Object dataLinkedMap = session.getAttribute("dataLinkedMap");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- JQuery UI -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- JQuery UI CSS-->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/dark-hive/jquery-ui.css">
	<!-- Flot -->
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.js"></script>
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.time.js"></script>	
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.axislabels.js"></script>	
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.tooltip.js"></script>
	<script language="javascript" type="text/javascript" src = "flot/curvedLines.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>YOUR DATA</title>
	<style type="text/css">
        body {
    			padding-top: 70px;
    		}
	    	.form-control {
			width:auto;
			display:inline-block;
	    }
    </style>	
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class = "container-fluid">
			<div class = "navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class = "sr-only">Toggle navigation</span>
					<span class = "icon-bar"></span>
					<span class = "icon-bar"></span>
					<span class = "icon-bar"></span>
				</button>
				<a class = "navbar-brand glyphicon glyphicon-home" href = "FactoryMainPage"></a>
			</div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class = "nav navbar-nav" style = "font-size:16px">
        				<li class = "dropdown">
        					<a class = "dropdown-toggle" data-toggle = "dropdown" href = "#">Machine 1<span class = "caret"></span></a>
        					<ul class = "dropdown-menu">
        						<li><a href = "FactoryMainMachine1_RealTime">Real-Time</a></li>
        						<li><a href = "FactoryMainMachine1_History">History</a></li>
        					</ul>
        				</li>
        				<li class = "dropdown">
        					<a class = "dropdown-toggle" data-toggle = "dropdown" href = "#">Machine 2<span class = "caret"></span></a>
        					<ul class = "dropdown-menu">
        						<li><a href = "FactoryMainMachine2_RealTime">Real-Time</a></li>
        						<li><a href = "FactoryMainMachine2_History">History</a></li>
        					</ul>
        				</li>
        				<li class = "dropdown">
        					<a class = "dropdown-toggle" data-toggle = "dropdown" href = "#">Machine 3<span class = "caret"></span></a>
        					<ul class = "dropdown-menu">
        						<li><a href = "FactoryMainMachine3_RealTime">Real-Time</a></li>
        						<li><a href = "FactoryMainMachine3_History">History</a></li>
        					</ul>
        				</li>
        				<li class = "dropdown">
        					<a class = "dropdown-toggle" data-toggle = "dropdown" href = "#">Machine4<span class = "caret"></span></a>
        					<ul class = "dropdown-menu">
        						<li><a href = "FactoryMainMachine4_RealTime">Real-Time</a></li>
        						<li><a href = "FactoryMainMachine4_History">History</a></li>
        					</ul>
        				</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
                    <li style = "font-size:16px"><a href = "#"><span class = "glyphicon glyphicon-user"></span><%= firstname + " " + lastname%></a></li>
                    <li style = "font-size:16px"><a href = "FactoryLogout"><span class = "glyphicon glyphicon-log-out"></span>Logout</a></li>
                </ul>
			</div>
		</div>
	</nav>

    <div class = "container-fluid">
	    <form method = "post" action = "FactoryMainMachine1_History">
	      	<div class = "row">      	
	        		<div class = "col-md-1" align = "center">
		        		<div class = "form-control">
		        			<label style = "font-size:16px">START</label>
		        		</div>
	        		</div>
	        		<div class = "col-md-3" align = "left">
    					<div class = "input-group">
		        			<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>	       
	        				<input class="form-control" type = "text" id = "start-datepicker" name = startDate required>
	        			</div>
	        		</div>
	        		<div class = "col-md-3" align = "left">
	        			<div class = "form-group">
		    				<label class="control-label">HOUR:</label>&nbsp;
		    				<input class = "form-control" type = "text" size = "3" id = "start-input-hour" onchange = "regExpCheckHour()" name = startHour required>
		    			</div>
	     		</div>
	        		<div class = "col-md-3" align = "left">	
	    		    		<div class = "form-group">
	    		    			<label>MINUTE:</label>&nbsp;
	    		    			<input class = "form-control" type = "text" size = "3" id = "start-input-minute" onchange = "regExpCheckMinute()" name = startMinute required>    					
	    		    		</div>
	        		</div>
	        		<div class = "col-md-2">
    		    			<span style = "color:red" align = "center" id = "checkResult"></span>	        		
	        		</div>
	        	</div>
	        			
	        	<hr>
	        			
	        	<div class = "row">
	        		<div class = "col-md-1" align = "center">
		        		<div class = "form-control">
		        			<label class="control-label" style = "font-size:16px">END</label>&nbsp;
		        		</div>
	        		</div>
	        		<div class = "col-md-3" align = "left">
		        		<div class = "input-group">
		        			<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>	       
		        			<input class="form-control" type = "text" id = "end-datepicker" onchange = "checkDate()" name = "endDate" required>
		        		</div>
	        		</div>
	        		<div class = "col-md-3" align = "left">
	        			<div class = "form-group">
		    				<label class="control-label">HOUR:</label>&nbsp;
		    				<input class = "form-control" type = "text" size = "3" id = "end-input-hour" onchange = "checkHour()" name = "endHour" required>		    					    				
		    			</div>
	        		</div>
	        		<div class = "col-md-3" align = "left">	
	    		   		<div class = "form-group">
	    		    			<label class="control-label">MINUTE:</label>&nbsp;
	    		    			<input class = "form-control" type = "text" size = "3" id = "end-input-minute" onchange = "checkMinute()" name = "endMinute" required>
	    		    		</div>
	        		</div>
	        		<div class = "col-md-2" align = "center">
		        		<div class = "form-horizontal">
			        		<div class = "form-group">
			        			<input type = "submit" class = "btn btn-default" style = "font-size:14px" value = "SEARCH"></input>
							<a href = "SaveFile"><button type = "button" class = "btn btn-default" style = "font-size:14px">SAVE</button></a>    					
			        		</div>
		        		</div>
	        		</div>
	        	</div>
		</form>
		
		<div class = "col-sm-12" align = "center"><h3>Machine 1</h3></div>
		        				
        	<div class = "row">	
        		<div class = "col-md-6" align = "center">
        			<hr>
        			<h4>Temperature</h4>
        			<div id = "temperature" style = "width:100%; height:350px"></div>
        		</div>        	
        		<div class = "col-md-6" align = "center">
        			<hr>
        			<h4>PDF - Temperature</h4>
        			<div id = "pdf-temperature" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-md-6" align = "center">
        			<hr>
        			<h4>Pressure</h4>
        			<div id = "pressure" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-md-6" align = "center">
        			<hr>
        			<h4>PDF - Pressure</h4>
        			<div id = "pdf-pressure" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-md-6" align = "center">
        			<hr>
        			<h4>Flow Rate</h4>
        			<div id = "flowrate" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-md-6" align = "center">
        			<hr>
        			<h4>PDF - Flow Rate</h4>
        			<div id = "pdf-flowrate" style = "width:100%; height:350px"></div>
        		</div>        	
        	</div>
	</div>

 
<script>

// Initialize the datepicker(jQuery UI plugin). 
$(function() {
	$("#start-datepicker").datepicker();
	$("#end-datepicker").datepicker();
});
// Set the data format of dataepicker.
$("#start-datepicker").datepicker({
	dateFormat: "yy-mm-dd"
});
$("#end-datepicker").datepicker({
	dateFormat: "yy-mm-dd"
});

$(function() {
	var left = '<option>';
	var right = '</option>';
	for (var i = 0; i < 24; i++) {
		$("#start-slc-hour").append(left + i + right);
		$("#end-slc-hour").append(left + i + right);
	}
});

$(function() {
	var left = '<option>';
	var right = '</option>';
	for (var i = 0; i < 60; i++) {
		$("#start-slc-minute").append(left + i + right);
		$("#end-slc-minute").append(left + i + right);
	}
});

//Set the default value of datepicker is today.
$(function() {
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	month = (month < 10) ? ("0" + month) : (month);
	var date = d.getDate();
	date = (date < 10) ? ("0" + date) : (date);
	var today = year + "-" + month + "-" + date;
	
	$("#start-datepicker").val(today);
	$("#end-datepicker").val(today);
});
 
//The function that plot the chart according to the data which select from user. 
function plotTemperature() {
	var data = <%= temperatureFlotData %>;
	
	$.plot($("#temperature"),[ data ], {
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false}
		},
		colors: ["#ff0000"],
		xaxis: {
			mode: "time",
			timezone: "browser",
			axisLabel: "Time",
			axisLabelPadding: 20,
			axisLabelUseCanvas: true,
			axisLabelFontSizePixels: 16
		},
		yaxis: {
			axisLabel: "Temp. (degree C)",
			axisLabelUseCanvas: true,
			axisLabelPadding: 10,
			axisLabelFontSizePixels: 16
		},
		grid: {
            hoverable: true,
            clickable: true
        },
        tooltip: {
        		show: true,
        		content: "%x | %y"
        }
	});
}

//The function that plot the chart according to the data which select from user.
function plotPressure() {
	var data = <%= pressureFlotData %>;
	
	$.plot($("#pressure"),[ data ], {
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false}
		},
		colors: ["#2a9e3a"],
		xaxis: {
			mode: "time",
			timezone: "browser",
			axisLabel: "Time",
			axisLabelPadding: 20,
			axisLabelUseCanvas: true,
			axisLabelFontSizePixels: 16
		},
		yaxis: {
			axisLabel: "Pres. (kPa)",
			axisLabelUseCanvas: true,
			axisLabelPadding: 10,
			axisLabelFontSizePixels: 16
		},
		grid: {
            hoverable: true,
            clickable: true
        },
        tooltip: {
    			show: true,
    			content: "%x | %y" 
    		}
	});
}

//The function that plot the chart according to the data which select from user.
function plotFlowRate() {
	var data = <%= flowRateFlotData %>;
	
	$.plot($("#flowrate"),[ data ], {
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false}
		},
		colors: ["#2269cc"],
		xaxis: {
			mode: "time",
			timezone: "browser",
			axisLabel: "Time",
			axisLabelPadding: 20,
			axisLabelUseCanvas: true,
			axisLabelFontSizePixels: 16
		},
		yaxis: {
			axisLabel: "Flow Rate (L/min)",
			axisLabelUseCanvas: true,
			axisLabelPadding: 10,
			axisLabelFontSizePixels: 16
		},
		grid: {
            hoverable: true,
            clickable: true
        },
        tooltip: {
    			show: true,
    			content: "%x | %y" 
	    }
	});
}

//The function that plot the PDF chart according to the data which select from user.
function plotPDFTemperature() {
	var data = <%= pDFOfTemperature %>;
	var nd = <%= normalDistributionOfTemperature %>;
	
	$.plot($("#pdf-temperature"),[ data, nd ],{
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false},
			curvedLines: {
				active: true,
				apply: true,
				tension: 1.0
			},
		},
		colors: ["#ff0000", "#c6c6c6"],
		xaxis: {
			axisLabel: "Temperature",
			axisLabelPadding: 20,
			axisLabelUseCanvas: true,
			axisLabelFontSizePixels: 16
		},
		yaxis: {
			axisLabel: "Density",
			axisLabelUseCanvas: true,
			axisLabelPadding: 10,
			axisLabelFontSizePixels: 16
		}
	});
};

//The function that plot the PDF chart according to the data which select from user.
function plotPDFPressure() {
	var data = <%= pDFOfPressure %>;
	var nd = <%= normalDistributionOfPressure %>;
	
	$.plot($("#pdf-pressure"),[ data, nd ],{
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false},
			curvedLines: {
				active: true,
				apply: true,
				tension: 1.0
			},
		},
		colors: ["#2a9e3a", "#c6c6c6"],
		xaxis: {
			axisLabel: "Pressure",
			axisLabelPadding: 20,
			axisLabelUseCanvas: true,
			axisLabelFontSizePixels: 16
		},
		yaxis: {
			axisLabel: "Density",
			axisLabelUseCanvas: true,
			axisLabelPadding: 10,
			axisLabelFontSizePixels: 16
		}
	});
};

//The function that plot the PDF chart according to the data which select from user.
function plotPDFFlowRate() {
	var data = <%= pDFOfFlowRate %>;
	var nd = <%= normalDistributionOfFlowRate %>;
	
	$.plot($("#pdf-flowrate"),[ data, nd ],{
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false},
			curvedLines: {
				active: true,
				apply: true,
				tension: 1.0
			},
		},
		colors: ["#2269cc", "#c6c6c6"],
		xaxis: {
			axisLabel: "Flow Rate",
			axisLabelPadding: 20,
			axisLabelUseCanvas: true,
			axisLabelFontSizePixels: 16
		},
		yaxis: {
			axisLabel: "Density",
			axisLabelUseCanvas: true,
			axisLabelPadding: 10,
			axisLabelFontSizePixels: 16
		}
	});
};

//Active these functions and set interval for these functions when the document is ready.
$(function() {
	plotTemperature();
	plotPDFTemperature();
	plotPressure();
	plotPDFPressure();
	plotPDFFlowRate();
	plotFlowRate();
});

//Re-plot the chart when the size of window is changed.
$(function() {
	window.onresize = function() {
		plotTemperature();
		plotPDFTemperature();
		plotPressure();
		plotPDFPressure();
		plotPDFFlowRate();
		plotFlowRate();
	}	
});

// Reflush the charts.
$(function() {
	<%if (machine != "M1"){%>
		init();
	<%}%>
});
function init() {
	var data = [ [0,0] ];
	$.plot($("#temperature"),[ data ]);
	$.plot($("#pdf-temperature"),[ data ]);
	$.plot($("#pressure"),[ data ]);
	$.plot($("#pdf-pressure"),[ data ]);
	$.plot($("#flowrate"),[ data ]);
	$.plot($("#pdf-flowrate"),[ data ]);
}

// Prevent users choose the wrong date of interval.
function checkDate() {
	var startDate = $("#start-datepicker").val();
	var endDate = $("#end-datepicker").val();
	if (endDate < startDate) {
		$("#checkResult").text("Please Check the Date of Interval!");
	}else {
		$("#checkResult").text("");
	}	
}
		 
// Prevent users choose the wrong hour of interval.
// Put the selected value into the input box.
// Check the input text by Regular Expression.
function checkHour() {
	var startDate = $("#start-datepicker").val();
	var endDate = $("#end-datepicker").val();
	var startHour = parseInt($("#start-input-hour").val());
	var endHour = parseInt($("#end-input-hour").val());
	if (endDate == startDate) {
		if (endHour < startHour) {
			$("#checkResult").text("Please Check the Hour of Interval!");
		}else {
			$("#checkResult").text("");
		}
	}

	var endHH = $("#end-input-hour").val();
	var pattern = /\D/g;
	var result = pattern.test(endHH);
	if (result){
		$("#checkResult").text("Please Input Numbers!");
	}else if (endHH.length > 2){
		$("#checkResult").text("Please Check the Fromat!");
	}else {
		$("#checkResult").text("");		
	}	
}

// Prevent users choose the wrong minute of interval.
// Put the selected value into the input box.
// Check the input text by Regular Expression.
function checkMinute() {
	var startDate = $("#start-datepicker").val();
	var endDate = $("#end-datepicker").val();
	var startHour = parseInt($("#start-input-hour").val());
	var endHour = parseInt($("#end-input-hour").val());
	var startMinute = parseInt($("#start-input-minute").val());
	var endMinute = parseInt($("#end-input-minute").val());
	if (endDate == startDate) {
		if (endHour == startHour) {
			if (endMinute < startMinute) {
				$("#checkResult").text("Please Check the Minute of Interval!");
			}else if (endMinute == startMinute){
				$("#checkResult").text("Please Check the Time Interval!");
			}else {
				$("#checkResult").text("");
			}
		}
	}

	var endMM = $("#end-input-minute").val();
	var pattern = /\D/g;
	var result = pattern.test(endMM);
	if (result){
		$("#checkResult").text("Please Input Numbers!");
	}else if (endMM.length > 2){
		$("#checkResult").text("Please Check the Fromat!");
	}else {
		$("#checkResult").text("");		
	}	
}

//Check the input text by Regular Expression.
function regExpCheckHour() {
	var startHour = $("#start-input-hour").val();
	var pattern = /\D/g;
	var result = pattern.test(startHour);
	
	if (result){
		$("#checkResult").text("Please Input Numbers!");
	}else if (startHour.length > 2){
		$("#checkResult").text("Please Check the Fromat!");
	}else {
		$("#checkResult").text("");
	}
}

//Check the input text by Regular Expression.
function regExpCheckMinute() {
	var startMinute = $("#start-input-minute").val();
	var pattern = /\D/g;
	var result = pattern.test(startMinute);
	
	if (result){
		$("#checkResult").text("Please Input Numbers!");
	}else if (startMinute.length > 2){
		$("#checkResult").text("Please Check the Fromat!");
	}else {
		$("#checkResult").text("");
	}
}

</script>

</body>
</html>


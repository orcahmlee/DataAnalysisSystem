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
	String temperatureFlotData = (String)session.getAttribute("temperatureFlotData");
	String pressureFlotData = (String)session.getAttribute("pressureFlotData");
	String flowRateFlotData = (String)session.getAttribute("flowRateFlotData");
	String pDFOfTemperature = (String)session.getAttribute("pDFOfTemperature");
	String pDFOfPressure = (String)session.getAttribute("pDFOfPressure");
	String pDFOfFlowRate = (String)session.getAttribute("pDFOfFlowRate");
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
        						<li><a href = "#">Real-Time</a></li>
        						<li><a href = "#">History</a></li>
        					</ul>
        				</li>
        				<li class = "dropdown">
        					<a class = "dropdown-toggle" data-toggle = "dropdown" href = "#">Machine 3<span class = "caret"></span></a>
        					<ul class = "dropdown-menu">
        						<li><a href = "#">Real-Time</a></li>
        						<li><a href = "#">History</a></li>
        					</ul>
        				</li>
        				<li class = "dropdown">
        					<a class = "dropdown-toggle" data-toggle = "dropdown" href = "#">Machine4<span class = "caret"></span></a>
        					<ul class = "dropdown-menu">
        						<li><a href = "#">Real-Time</a></li>
        						<li><a href = "#">History</a></li>
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
	        		<div class = "col-sm-2" align = "center">
	        			<label style = "font-size:16px">START TIME</label>&nbsp;
	        		</div>
	        		<div class = "col-sm-3" align = "left">
	        			<label>DATE:</label>&nbsp;
	        			<input type = "text" size = "12" id = "start-datepicker" name = startDate>
	        		</div>
	        		<div class = "col-sm-3" align = "left">
	        			<div class = "form-group">
		    				<label>HOUR:</label>&nbsp; 		    					
		    				<select class = "form-control" id = "start-slc-hour" name = startHour></select>
		    			</div>
	     		</div>
	        		<div class = "col-sm-3" align = "left">	
	    		    		<div class = "form-group">
	    		    			<label>MINUTE:</label>&nbsp; 		    					
	    		    			<select class = "form-control" id = "start-slc-minute" name = startMinute></select>
	    		    		</div>
	        		</div>
	        		<div class = "col-sm-1"></div>
	        	</div>
	        			
	        	<hr>
	        			
	        	<div class = "row">
	        		<div class = "col-sm-2" align = "center">
	        			<label style = "font-size:16px">END TIME</label>&nbsp;
	        		</div>
	        		<div class = "col-sm-3" align = "left">
	        			<label>DATE:</label>&nbsp;
	        			<input type = "text" size = "12" id = "end-datepicker" name = "endDate">
	        		</div>
	        		<div class = "col-sm-3" align = "left">
	        			<div class = "form-group">
		    				<label>HOUR:</label>&nbsp; 		    					
		    				<select class = "form-control" id = "end-slc-hour" name = "endHour"></select>
		    			</div>
	        		</div>
	        		<div class = "col-sm-3" align = "left">	
	    		   		<div class = "form-group">
	    		    			<label>MINUTE:</label>&nbsp;
	    		    			<select class = "form-control" id = "end-slc-minute" name = "endMinute"></select>
	    		    		</div>
	        		</div>
	        		<div class = "col-sm-1" align = "center">
	        			<input type = "submit" class = "btn btn-default" style = "font-size:16px" value = "SEARCH"></input>        					
	        		</div>		
	        	</div>
		</form>    
        				
        	<div class = "row">	
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Temperature</p>
        			<div id = "temperature" style = "width:100%; height:350px"></div>
        		</div>        	
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF - Temperature</p>
        			<div id = "pdf-temperature" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Pressure</p>
        			<div id = "pressure" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF - Pressure</p>
        			<div id = "pdf-pressure" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Flow Rate</p>
        			<div id = "flowrate" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF - Flow Rate</p>
        			<div id = "pdf-flowrate" style = "width:100%; height:350px"></div>
        		</div>        	
        	</div>
	</div>

 
<script>

$(function() {
	<% if (temperatureFlotData == null){ %>
		alert("PLEASE SELECT THE TIME INTERVAL!");
	<% } %>	
})

$(function() {
	$("#start-datepicker").datepicker();
	$("#end-datepicker").datepicker();
});
$("#start-datepicker").datepicker({
	dateFormat: "yy-mm-dd"
});
$("#end-datepicker").datepicker({
	dateFormat: "yy-mm-dd"
});

// Set the default value of datepicker is today.
$(function() {
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	var date = d.getDate();
	var today = year + ":" + month + ":" + date;
	
	$("#start-datepicker").val("2017-09-02");
	$("#end-datepicker").val("2017-09-02");
});


$(function() {
	var left = '<option>';
	var right = '</option>';
	for (var i = 0; i < 24; i++) {
		i = (i < 10) ? (i = "0" + i) : (i);
		$("#start-slc-hour").append(left + i + right);
		$("#end-slc-hour").append(left + i + right);
	}
});

$(function() {
	var left = '<option>';
	var right = '</option>';
	for (var i = 0; i < 60; i++) {
		i = (i < 10) ? (i = "0" + i) : (i);
		$("#start-slc-minute").append(left + i + right);
		$("#end-slc-minute").append(left + i + right);
	}
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
	
	$.plot($("#pdf-temperature"),[ data ],{
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false},
			curvedLines: {
				active: true,
				apply: true				
			},
		},
		colors: ["#ff0000"],
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
	
	$.plot($("#pdf-pressure"),[ data ],{
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false},
			curvedLines: {
				active: true,
				apply: true				
			},
		},
		colors: ["#2a9e3a"],
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
	
	$.plot($("#pdf-flowrate"),[ data ],{
	    series: {
		    lines: {
				show: true,
				align: "center"
			},
			points: {show: false},
			curvedLines: {
				active: true,
				apply: true				
			},
		},
		colors: ["#2269cc"],
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

</script>

</body>
</html>


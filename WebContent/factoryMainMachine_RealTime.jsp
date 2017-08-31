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
                    <li style = "font-size:16px"><a href = "#"><span class = "glyphicon glyphicon-log-out"></span>Logout</a></li>
                </ul>
			</div>
		</div>
	</nav>

    <div class = "container-fluid">        				
        	<div class = "row">
        		<div class = "col-sm-12" align = "center">Machine 1</div>
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Temperature</p>
        			<div id = "temperature" style = "width:100%; height:350px"></div>
        		</div>        	
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF</p>
        			<div id = "placeholder2" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Pressure</p>
        			<div id = "pressure" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF</p>
        			<div id = "placeholder4" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Flow Rate</p>
        			<div id = "flowrate" style = "width:100%; height:350px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF</p>
        			<div id = "placeholder6" style = "width:100%; height:350px"></div>
        		</div>        	
        	</div>
	</div>

</body>

<script>

// The function that plot the chart by FLOT and AJAX
function plotTemperature() {
	$.post(
			"M1TemperatureRealTimeData", 						// URL
			function(data){										// A function to run if the request succeeds. 
				var obj = JSON.parse(data);						// Parse the string that the resulting data from the request to a JSON object.
				var dataset = obj.data;							// Get the "value" which "key" eauals "data"; It returns a array "dataset".
				var flotdata = [];								// Create an array which can recieve the value
				for (var i = 0; i < dataset.length; i++) {		
					if (i % 2 == 0){								
						var timeValue = [];
						timeValue.push(dataset[i]);
						timeValue.push(dataset[i + 1]);
						flotdata.push(timeValue);				// The structure of flotdata is: [ [k,v], [k,v] ,[k, v],[k, v] ].
					}
				}
				$.plot($("#temperature"),[ flotdata ], {			// Plot the chart by FLOT
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
	);
}

//The function that plot the chart by FLOT and AJAX
function plotPressure() {
	$.post(
			"M1PressureRealTimeData", 							// URL
			function(data){										// A function to run if the request succeeds. 
				var obj = JSON.parse(data);						// Parse the string that the resulting data from the request to a JSON object.
				var dataset = obj.data;							// Get the "value" which "key" eauals "data"; It returns a array "dataset".
				var flotdata = [];								// Create an array which can recieve the value
				for (var i = 0; i < dataset.length; i++) {		
					if (i % 2 == 0){								
						var timeValue = [];
						timeValue.push(dataset[i]);
						timeValue.push(dataset[i + 1]);
						flotdata.push(timeValue);				// The structure of flotdata is: [ [k,v], [k,v] ,[k, v],[k, v] ].
					}
				}
				$.plot($("#pressure"),[ flotdata ], {			// Plot the chart by FLOT
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
	);
}

//The function that plot the chart by FLOT and AJAX
function plotFlowRate() {
	$.post(
			"M1FlowRateRealTimeData", 							// URL
			function(data){										// A function to run if the request succeeds. 
				var obj = JSON.parse(data);						// Parse the string that the resulting data from the request to a JSON object.
				var dataset = obj.data;							// Get the "value" which "key" eauals "data"; It returns a array "dataset".
				var flotdata = [];								// Create an array which can recieve the value
				for (var i = 0; i < dataset.length; i++) {		
					if (i % 2 == 0){								
						var timeValue = [];
						timeValue.push(dataset[i]);
						timeValue.push(dataset[i + 1]);
						flotdata.push(timeValue);				// The structure of flotdata is: [ [k,v], [k,v] ,[k, v],[k, v] ].
					}
				}
				$.plot($("#flowrate"),[ flotdata ], {			// Plot the chart by FLOT
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
	);
}

// Active these functions and set interval for these functions when the document is ready.
$(function() {
	plotTemperature();
	setInterval(plotTemperature, 5000);
	plotPressure();
	setInterval(plotPressure, 5000);
	plotFlowRate();
	setInterval(plotFlowRate, 5000);
});

// Re-plot the chart when the size of window is changed.
$(function() {
	window.onresize = function() {
		plotTemperature()
		plotPressure();
		plotFlowRate();
	}	
});

</script>
</html>


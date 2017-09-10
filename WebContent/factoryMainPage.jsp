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
	String m1Temperature = (String)session.getAttribute("m1Temperature");
	String m1Pressure = (String)session.getAttribute("m1Pressure");
	String m1FlowRate = (String)session.getAttribute("m1FlowRate");

	String m2Temperature = (String)session.getAttribute("m2Temperature");
	String m2Pressure = (String)session.getAttribute("m2Pressure");
	String m2FlowRate = (String)session.getAttribute("m2FlowRate");

	String m3Temperature = (String)session.getAttribute("m3Temperature");
	String m3Pressure = (String)session.getAttribute("m3Pressure");
	String m3FlowRate = (String)session.getAttribute("m3FlowRate");

	String m4Temperature = (String)session.getAttribute("m4Temperature");
	String m4Pressure = (String)session.getAttribute("m4Pressure");
	String m4FlowRate = (String)session.getAttribute("m4FlowRate");
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
	<!-- jQuery UI -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- jQuery UI CSS-->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/dark-hive/jquery-ui.css">
	<!-- Flot -->
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.js"></script>
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.time.js"></script>	
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.axislabels.js"></script>	
	<script language="javascript" type="text/javascript" src = "flot/jquery.flot.tooltip.js"></script>
	<script language="javascript" type="text/javascript" src = "flot/curvedLines.js"></script>
	<!-- jQurey Plugin Circle -->
	<script src="circle/circle-progress.js"></script>
	
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
	    .circle {
		  position: relative;
		  display: inline-block;
		}		
		.circle .value {
		  position: absolute;
		  z-index: 1;
		  left: 0;
		  right: 0;
		  top: 50%;
		  margin-top: -15px;
		  height: 30px;
		  font: 24px/30px sans-serif;
		  text-align: center;
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
        	<div class = "row">	
        		<div class = "col-sm-3" align = "center">
        			<h3><a href = "FactoryMainMachine1_RealTime" style = "color: black">Machine 1</a></h3>
	        		<div class="m1 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
        		</div>        		
        		<div class = "col-sm-3" align = "center">
        			<div id = "m1-temperature" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m1-pressure" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m1-flowrate" style = "width:100%; height:350px"></div>
        		</div>
        		
        		<div class = "col-sm-3" align = "center">
        			<h3><a href = "FactoryMainMachine2_RealTime" style = "color: black">Machine 2</a></h3>
	        		<div class="m2 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m2-temperature" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m2-pressure" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m2-flowrate" style = "width:100%; height:350px"></div>
        		</div>

        		<div class = "col-sm-3" align = "center">
        			<h3><a href = "FactoryMainMachine3_RealTime" style = "color: black">Machine 3</a></h3>
	        		<div class="m3 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m3-temperature" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m3-pressure" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m3-flowrate" style = "width:100%; height:350px"></div>
        		</div>

        		<div class = "col-sm-3" align = "center">
        			<h3><a href = "FactoryMainMachine4_RealTime" style = "color: black">Machine 4</a></h3>
	        		<div class="m4 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m4-temperature" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m4-pressure" style = "width:100%; height:350px"></div>
        		</div>
        		<div class = "col-sm-3" align = "center">
        			<div id = "m4-flowrate" style = "width:100%; height:350px"></div>
        		</div>
        		
        	</div>
	</div>

<script>

$('.m1.circle').circleProgress({
	value: 0.95,
	size: 175,
	fill: { gradient: ['#0681c4', '#07c6c1'] }
})
.on('circle-animation-progress', function(event, progress) {
	$(this).find('strong').html(parseInt(95 * progress) + '<i>%</i>');
});

$('.m2.circle').circleProgress({ 
	value: 0.76,
	size: 175,
	fill: { gradient: ['#0681c4', '#07c6c1'] }
})
.on('circle-animation-progress', function(event, progress) {
	$(this).find('strong').html(parseInt(76 * progress) + '<i>%</i>');
});

$('.m3.circle').circleProgress({ 
	value: 0.88,
	size: 175,
	fill: { gradient: ['#0681c4', '#07c6c1'] }
})
.on('circle-animation-progress', function(event, progress) {
	$(this).find('strong').html(parseInt(88 * progress) + '<i>%</i>');
});

$('.m4.circle').circleProgress({ 
	value: 0.16,
	size: 175,
	fill: { gradient: ['#0681c4', '#07c6c1'] }
})
.on('circle-animation-progress', function(event, progress) {
	$(this).find('strong').html(parseInt(16 * progress) + '<i>%</i>');
});

//The function that plot the PDF chart according to the data which select from user.
function plotM1Temperature() {
	var data = <%= m1Temperature %>;
	var sp = [ [200,0],[200, 0.4] ];			// Set Point
	var lsl = [ [195,0],[195, 0.4] ];		// Lower Specification Limit
	var usl = [ [205,0],[205, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m1-temperature"),[ data, sp, lsl, usl ],{
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
		colors: ["#ff0000", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM1Pressure() {
	var data = <%= m1Pressure %>;
	var sp = [ [101.3,0],[101.3, 0.4] ];			// Set Point
	var lsl = [ [98.8,0],[98.8, 0.4] ];			// Lower Specification Limit
	var usl = [ [103.8,0],[103.8, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m1-pressure"),[ data, sp, lsl, usl ],{
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
		colors: ["#2a9e3a", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM1FlowRate() {
	var data = <%= m1FlowRate %>;
	var sp = [ [100,0],[100, 0.4] ];				// Set Point
	var lsl = [ [97.5,0],[97.5, 0.4] ];			// Lower Specification Limit
	var usl = [ [102.5,0],[102.5, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m1-flowrate"),[ data, sp, lsl, usl ],{
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
		colors: ["#2269cc", "#ffbf00", "#ffbf00", "#ffbf00"],
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

//The function that plot the PDF chart according to the data which select from user.
function plotM2Temperature() {
	var data = <%= m2Temperature %>;
	var sp = [ [200,0],[200, 0.4] ];			// Set Point
	var lsl = [ [195,0],[195, 0.4] ];		// Lower Specification Limit
	var usl = [ [205,0],[205, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m2-temperature"),[ data, sp, lsl, usl ],{
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
		colors: ["#ff0000", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM2Pressure() {
	var data = <%= m2Pressure %>;
	var sp = [ [101.3,0],[101.3, 0.4] ];			// Set Point
	var lsl = [ [98.8,0],[98.8, 0.4] ];			// Lower Specification Limit
	var usl = [ [103.8,0],[103.8, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m2-pressure"),[ data, sp, lsl, usl ],{
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
		colors: ["#2a9e3a", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM2FlowRate() {
	var data = <%= m2FlowRate %>;
	var sp = [ [100,0],[100, 0.4] ];				// Set Point
	var lsl = [ [97.5,0],[97.5, 0.4] ];			// Lower Specification Limit
	var usl = [ [102.5,0],[102.5, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m2-flowrate"),[ data, sp, lsl, usl ],{
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
		colors: ["#2269cc", "#ffbf00", "#ffbf00", "#ffbf00"],
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

//The function that plot the PDF chart according to the data which select from user.
function plotM3Temperature() {
	var data = <%= m3Temperature %>;
	var sp = [ [200,0],[200, 0.4] ];			// Set Point
	var lsl = [ [195,0],[195, 0.4] ];		// Lower Specification Limit
	var usl = [ [205,0],[205, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m3-temperature"),[ data, sp, lsl, usl ],{
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
		colors: ["#ff0000", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM3Pressure() {
	var data = <%= m3Pressure %>;
	var sp = [ [101.3,0],[101.3, 0.4] ];			// Set Point
	var lsl = [ [98.8,0],[98.8, 0.4] ];			// Lower Specification Limit
	var usl = [ [103.8,0],[103.8, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m3-pressure"),[ data, sp, lsl, usl ],{
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
		colors: ["#2a9e3a", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM3FlowRate() {
	var data = <%= m3FlowRate %>;
	var sp = [ [100,0],[100, 0.4] ];				// Set Point
	var lsl = [ [97.5,0],[97.5, 0.4] ];			// Lower Specification Limit
	var usl = [ [102.5,0],[102.5, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m3-flowrate"),[ data, sp, lsl, usl ],{
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
		colors: ["#2269cc", "#ffbf00", "#ffbf00", "#ffbf00"],
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

//The function that plot the PDF chart according to the data which select from user.
function plotM4Temperature() {
	var data = <%= m4Temperature %>;
	var sp = [ [200,0],[200, 0.4] ];			// Set Point
	var lsl = [ [195,0],[195, 0.4] ];		// Lower Specification Limit
	var usl = [ [205,0],[205, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m4-temperature"),[ data, sp, lsl, usl ],{
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
		colors: ["#ff0000", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM4Pressure() {
	var data = <%= m4Pressure %>;
	var sp = [ [101.3,0],[101.3, 0.4] ];			// Set Point
	var lsl = [ [98.8,0],[98.8, 0.4] ];			// Lower Specification Limit
	var usl = [ [103.8,0],[103.8, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m4-pressure"),[ data, sp, lsl, usl ],{
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
		colors: ["#2a9e3a", "#ffbf00", "#ffbf00", "#ffbf00"],
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
function plotM4FlowRate() {
	var data = <%= m4FlowRate %>;
	var sp = [ [100,0],[100, 0.4] ];				// Set Point
	var lsl = [ [97.5,0],[97.5, 0.4] ];			// Lower Specification Limit
	var usl = [ [102.5,0],[102.5, 0.4] ];		// Upper Specification Limit
	
	$.plot($("#m4-flowrate"),[ data, sp, lsl, usl ],{
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
		colors: ["#2269cc", "#ffbf00", "#ffbf00", "#ffbf00"],
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
	plotM1Temperature();
	plotM1Pressure();
	plotM1FlowRate();
	
	plotM2Temperature();
	plotM2Pressure();
	plotM2FlowRate();
	
	plotM3Temperature();
	plotM3Pressure();
	plotM3FlowRate();
	
	plotM4Temperature();
	plotM4Pressure();
	plotM4FlowRate();
});

//Re-plot the chart when the size of window is changed.
$(function() {
	window.onresize = function() {
		plotM1Temperature();
		plotM1Pressure();
		plotM1FlowRate();
		
		plotM2Temperature();
		plotM2Pressure();
		plotM2FlowRate();
		
		plotM3Temperature();
		plotM3Pressure();
		plotM3FlowRate();
		
		plotM4Temperature();
		plotM4Pressure();
		plotM4FlowRate();
	}	
});


</script>
	
</body>

</html>


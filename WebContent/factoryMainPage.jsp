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
	<!-- jQuery UI -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- jQuery UI CSS-->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/dark-hive/jquery-ui.css">
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

    <div class = "container">
        	<div class = "row">	
        		<div class = "col-sm-6" align = "center">
        			<h3><a href = "FactoryMainMachine1_RealTime" style = "color: black">Machine 1</a></h3>
	        		<div class="m1 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
        		</div>
        		<div class = "col-sm-6" align = "center">
        			<h3><a href = "FactoryMainMachine2_RealTime" style = "color: black">Machine 2</a></h3>
	        		<div class="m2 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
        		</div>
        		<hr>
        		<div class = "col-sm-6" align = "center">
        			<h3><a href = "FactoryMainMachine3_RealTime" style = "color: black">Machine 3</a></h3>
	        		<div class="m3 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
        		</div>
        		<div class = "col-sm-6" align = "center">
        			<h3><a href = "FactoryMainMachine4_RealTime" style = "color: black">Machine 4</a></h3>
	        		<div class="m4 circle" align = "center">
					<div class="value">				
		       			<strong></strong>	       			
				    </div>
				</div>
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


</script>
	
</body>

</html>


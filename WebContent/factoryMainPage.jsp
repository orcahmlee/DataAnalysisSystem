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
        						<li><a href = "#">Real-Time</a></li>
        						<li><a href = "#">History</a></li>
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
        		<div class = "col-sm-2" align = "center">
        			<label style = "font-size:16px">START TIME</label>&nbsp;
        		</div>
        		<div class = "col-sm-3" align = "left">
        			<label>DATE:</label>&nbsp;
        			<input type = "text" size = "12" id = "start-datepicker">
        		</div>
        		<div class = "col-sm-3" align = "left">
        			<div class = "form-group">
	    				<label>HOUR:</label>&nbsp; 		    					
	    				<select class = "form-control" id = "start-slc-hour"></select>
	    			</div>
     		</div>
        		<div class = "col-sm-3" align = "left">	
    		    		<div class = "form-group">
    		    			<label>MINUTE:</label>&nbsp; 		    					
    		    			<select class = "form-control" id = "start-slc-minute"></select>
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
        			<input type = "text" size = "12" id = "end-datepicker">
        		</div>
        		<div class = "col-sm-3" align = "left">
        			<div class = "form-group">
	    				<label>HOUR:</label>&nbsp; 		    					
	    				<select class = "form-control" id = "end-slc-hour"></select>
	    			</div>
        		</div>
        		<div class = "col-sm-3" align = "left">	
    		   		<div class = "form-group">
    		    			<label>MINUTE:</label>&nbsp; 		    					
    		    			<select class = "form-control" id = "end-slc-minute"></select>
    		    		</div>
        		</div>
        		<div class = "col-sm-1" align = "center">
        			<button type = "button" class = "btn btn-default" style = "font-size:18px" onclick = "getInterval()"><i class="glyphicon glyphicon-search"></i></button>        					
        		</div>		
        	</div>
        			
    		<!-- DEBUG AREA -->
    		<hr>
    		<div class = "col-sm-12" align = "center">
    			<span align = "center" id = "debug">Time</span>
    		</div>        				
    		<hr>
    		<!-- DEBUG AREA -->
        				
        	<div class = "row">	
        		<div class = "col-sm-6" align = "center">
        			<img src = "pics/1.jpg" width = "100%"/>
        		</div>
        		<div class = "col-sm-6" align = "center">
        			<img src = "pics/2.jpg" width = "100%"/>
        		</div>
        		<hr>
        		<div class = "col-sm-6" align = "center">
        			<img src = "pics/3.jpg" width = "80%"/>
        		</div>
        		<div class = "col-sm-6" align = "center">
        			<img src = "pics/4.jpg" width = "80%"/>
        		</div>
        	</div>
	</div>

 
<script>
	$(document).ready(function() {
		$("#start-datepicker").datepicker();
		$("#end-datepicker").datepicker();
	});
	$("#start-datepicker").datepicker({
		dateFormat: "yy-mm-dd"
	});
	$("#end-datepicker").datepicker({
		dateFormat: "yy-mm-dd"
	});

	$(document).ready(function (){
		var left = '<option>';
		var right = '</option>';
		for (var i = 0; i < 25; i++) {
			i = (i < 10) ? (i = "0" + i) : (i);
			$("#start-slc-hour").append(left + i + right);
			$("#end-slc-hour").append(left + i + right);
		}
	});
	
	$(document).ready(function (){
		var left = '<option>';
		var right = '</option>';
		for (var i = 0; i < 60; i++) {
			i = (i < 10) ? (i = "0" + i) : (i);
			$("#start-slc-minute").append(left + i + right);
			$("#end-slc-minute").append(left + i + right);
		}
	});
  	
	function getInterval() {
		var start = $("#start-datepicker").datepicker("getDate");		
 		var startYY = start.getFullYear();
		var startMM = start.getMonth() + 1;
 		startMM = (startMM < 10) ? (startMM = "0" + startMM) : (startMM);
		var startDD = start.getDate();
		startDD = (startDD < 10) ? (startDD = "0" + startDD) : (startDD);
		
		var startDate = startYY + "-" + startMM + "-" + startDD;
		var startHour = $("#start-slc-hour").val();	
		var startMinute = $("#start-slc-minute").val();
		

		var end = $("#end-datepicker").datepicker("getDate");		
 		var endYY = end.getFullYear();
		var endMM = end.getMonth() + 1;
 		endMM = (endMM < 10) ? (endMM = "0" + endMM) : (endMM);
		var endDD = end.getDate();
		endDD = (endDD < 10) ? (endDD = "0" + endDD) : (endDD);		
		
		var endDate = endYY + "-" + endMM + "-" + endDD;
		var endHour = $("#start-slc-hour").val();
		var endMinute = $("#start-slc-minute").val();	
		
		if (end.getTime() < start.getTime()){
			alert("請確認起迄日期");
			return;
		}
		
		$("#debug").html(startDate + ", "+  endHour + ":" + endMinute + " ---> " + endDate + ", "+  endHour + ":" + endMinute);
	}
  
</script>



</body>
</html>


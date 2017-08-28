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

    <div class = "container">        				
        	<div class = "row">
        		<div class = "col-sm-12" align = "center">Machine 1</div>
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Temperature</p>
        			<div id = "placeholder1" style = "width:100%; height:300px"></div>
        		</div>        	
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF</p>
        			<div id = "placeholder2" style = "width:100%; height:300px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Pressure</p>
        			<div id = "placeholder3" style = "width:100%; height:300px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF</p>
        			<div id = "placeholder4" style = "width:100%; height:300px"></div>
        		</div>
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>Flow Rate</p>
        			<div id = "placeholder5" style = "width:100%; height:300px"></div>
        		</div>        		
        		<div class = "col-sm-6" align = "center">
        			<hr>
        			<p>PDF</p>
        			<div id = "placeholder6" style = "width:100%; height:300px"></div>
        		</div>        	
        	</div>
	</div>

</body>

<script>
$(function () {
    var d1 = [];
    for (var i = 0; i < 14; i += 0.5)
        d1.push([i, Math.sin(i)]);
 
    var d2 = [[0, 3], [4, 8], [8, 5], [9, 13]];
 
    // a null signifies separate line segments
    var d3 = [[0, 12], [7, 12], null, [7, 2.5], [12, 2.5]];
    
    $.plot($("#placeholder3"), [ d1, d2, d3 ]);
    $.plot($("#placeholder4"), [ d1, d2, d3 ]);
    $.plot($("#placeholder5"), [ d1, d2, d3 ]);
    $.plot($("#placeholder6"), [ d1, d2, d3 ]);

    window.onresize = function() {
        $.plot($("#placeholder3"), [ d1, d2, d3 ]);
        $.plot($("#placeholder4"), [ d1, d2, d3 ]);
        $.plot($("#placeholder5"), [ d1, d2, d3 ]);
        $.plot($("#placeholder6"), [ d1, d2, d3 ]);
    }
});

$(function() {
	var data = [[0,2], [3,6], [6,9], [9,12], [12,5], [15,8]];
	
	$.plot($("#placeholder1"),[ data ]			
	);
	
	window.onresize = function() {
		$.plot($("#placeholder1"),[ data ]			
		);		
	}
});

<%
	String data2 = (String)session.getAttribute("data2");
	if(data2 == null) {
		data2 = "[0,0]";
	}
%>
$(function() {
	var data = <%= data2 %>;
	
	$.plot($("#placeholder2"),[ data ]			
	);
	
	window.onresize = function() {
		$.plot($("#placeholder2"),[ data ]
		);		
	}
});

</script>

</html>


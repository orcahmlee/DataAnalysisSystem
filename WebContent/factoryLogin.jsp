<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>YOUR DATA</title>
	<style type="text/css">
        body {
    		padding-top: 70px;
    	}
    </style>	
</head>

<body>
	<!-- Navigation Bar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class = "container">
			<div class = "navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class = "sr-only">Toggle navigation</span>
					<span class = "icon-bar"></span>
					<span class = "icon-bar"></span>
					<span class = "icon-bar"></span>
				</button>
				<a class = "navbar-brand glyphicon glyphicon-home" href = "FactoryMainPage"></a>
			</div>			
		</div>
	</nav>
	
	<div class = "container">
		<!-- Jumbotron -->
		<div class = "jumbotron" style = "background:#9effa7">
		    <h2>YOUR DATA FACTORY</h2>      
   			<h3 style = "font-style:italic">“Information is the oil of the 21st century, and analytics is the combustion engine”</h3>
   			<p>Peter Sondergaard, Senior Vice President, Gartner</p>
		</div>
		<!-- Form -->
		<div class = "row">
			<div class = "col-sm-4"></div>
			<div class = "col-sm-4">
				<form method = "post" action = "FactoryMemberLogin">
					<div class = "form-group">
						<label for = "account">ACCOUNT:</label>
						<input type = "text" class = "form-control" placeholder = "ACCOUNT" id = "account" name = "account" required/>
					</div>
					<div class = "form-group">
						<label for = "password">PASSWORD:</label>
						<input type = "password" class = "form-control" placeholder = "PASSWORD" id ="password" name ="password" required/>
					</div>
					<input type = "submit" class = "btn btn-success" value = "SUBMIT"/>
					<a class = "btn btn-link" href = "">Forget your Password?</a>
				</form>
				<hr>
				<div class = "form-group">
					<label>NEW USER? &nbsp</label>
					<a href = "FactoryRegister">
						<input type = "button" class = "btn btn-primary" value = "CREATE ACCOUNT"/>
					</a>
				</div>				
			</div>
			<div class = "col-sm-4"></div>
		</div>
	</div>

<%
	String login = (String)session.getAttribute("login");
	if (login == null){
		return;
	}
	if (login.equals("failure")){
		out.println("<script type = 'text/javascript'>");
		out.println("alert('WRONG ACCOUNT or PASSWORD\\nPLEASE CHECK AGAIN!');");
		out.println("</script>");
	}
%>
</body>
</html>

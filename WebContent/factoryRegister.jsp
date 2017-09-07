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
		<div class = "row">
			<div class = "col-sm-4"></div>
			<div class = "col-sm-4">
				<h2 style = "text-align:center;">New Member Information</h2>
				<form method = "post" action = "FactoryNewMember">
					<div class = "form-group">
						<label for = "account">ACCOUNT:</label>
						<input type = "text" class = "form-control" placeholder = "ACCOUNT" id = "account" name = "account" required/>
					</div>
					<div class = "form-group">
						<label for = "password">PASSWORD:</label>
						<input type = "password" class = "form-control" placeholder = "PASSWORD" id ="password" name ="password" required/>
					</div>
					<div class = "form-group">
						<label for = "firstname">FIRST NAME:</label>
						<input type = "text" class = "form-control" placeholder = "FIRST NAME" id ="firstname" name ="firstname" required/>
					</div>
					<div class = "form-group">
						<label for = "lastname">LAST NAME:</label>
						<input type = "text" class = "form-control" placeholder = "LAST NAME" id ="lastname" name ="lastname" required/>
					</div>			
					<div class = "form-group">
						<input type = "submit" class = "btn btn-success" value = "SUBMIT"/>
					</div>
				</form>			
			</div>
			<div class = "col-sm-4"></div>
		</div>	
	</div>

</body>
</html>
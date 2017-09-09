<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>YOUR FACTORY</title>
	<style type="text/css">
        body {
    		padding-top: 70px;
    	}
    </style>	
</head>
<body>

	<div class = "container">
		<div class = "row">
			<div class = "col-sm-12" align = "center">
				<h3>
					File Already Be Saved, Reload in 3 seconds...!
				</h3>
			</div>
		</div>
	</div>


<script type = 'text/javascript'>
function redirect() {
	window.location.assign('http://localhost:8080/DataAnalysisSystem/FactoryMainMachine1_History');
}

document.ready(setTimeout(redirect, 3000));
</script>
</html>
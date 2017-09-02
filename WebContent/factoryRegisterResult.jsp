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
<%
	boolean result = (boolean)request.getAttribute("result");
%>

<%	if (result){%>
		Register Success, Reload in 3 seconds...!
<%	}else {%>
		Register Failure! <br>
		Please Register Again! <br>
<%	}%>

<script type = 'text/javascript'>
function redirect() {
	window.location.assign('http://localhost:8080/DataAnalysisSystem/FactoryLogin');
}

document.ready(setTimeout(redirect, 3000));
</script>
<!-- 
TODO:
	Failure => redirect to FactoryResgister.java
	Success => wait approval => FactoryLogin.java
 -->


</body>
</html>
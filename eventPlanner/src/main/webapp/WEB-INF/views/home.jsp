<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Manager</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/4.0.0-alpha/css/bootstrap.min.css" />
<script src="webjars/bootstrap/4.0.0-alpha/jquery.min.js"></script>
<script src="webjars/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>

<!-- style sheet -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/> ">
<!-- javascripts -->
<script src="<c:url value="/resources/js/script.js"/>"></script>
</head>
<body>
	<div class="dark-blue-header"></div>
	<div class="light-blue-header"></div>
	<div class="full-nav-bg"></div>

	<div class="container">
		<div class="header">
			<div class="nav-top">
				<ul>
					<li><a href="<c:url value='' />">Contact us</a></li>
				</ul>
			</div>
		</div>
		<nav class="navbar navbar-light center">
			<div class="nav-inner">
				<ul class="nav navbar-nav">
					<li class="nav-item"><a class="nav-link active" href="#">Home </a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/manager' />">Event
							Management </a></li>
					<li class="nav-item"><a class="nav-link" href="#">Registration</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Gallery</a>
					</li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="http://example.com"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Dropdown link </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">Action</a> <a
								class="dropdown-item" href="#">Another action</a> <a
								class="dropdown-item" href="#">Something else here</a>
						</div>
				</ul>
			</div>
		</nav>
	</div>
</body>
</html>

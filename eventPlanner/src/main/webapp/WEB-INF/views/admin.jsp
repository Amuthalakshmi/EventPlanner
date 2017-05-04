<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Planner - Admin</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- style sheet -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/eventPlannerStyle.css"/> ">
<!-- javascripts -->
<script src="<c:url value="/resources/js/action.js"/>">
	
</script>
<script src="<c:url value="/resources/js/validator.js"/>">
	
</script>

</head>
<body>
	<div class="container">
		<div class="admin">
			<div class="page-header">
				<h1>Event Planner - Admin</h1>
			</div>

			<div class="row center">
				<a href="<c:url value='/listEventManagers' />"><input
					class="btn btn-primary btn-lg" value="Event Managers" /></a>
			</div>

			<div class="row center">
				<a href="<c:url value='' />"><input
					class="btn btn-primary btn-lg" value="Organisers" /></a>
			</div>

			<div class="row center">
				<a href="<c:url value='' />"><input
					class="btn btn-primary btn-lg" value="Partcipants" /></a>
			</div>

			<div class="row center">
				<a href="<c:url value='' />"><input
					class="btn btn-primary btn-lg" value="Volunteers" /></a>
			</div>
		</div>
	</div>
</body>
</html>

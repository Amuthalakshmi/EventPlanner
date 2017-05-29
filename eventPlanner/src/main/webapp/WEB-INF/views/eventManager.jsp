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
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="webjars/bootstrap/3.3.7/jquery.min.js"></script>
<script
	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Bootstrap Datepicker -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>


<!-- style sheet -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/eventPlannerStyle.css"/> ">
<!-- javascripts -->
<script src="<c:url value="/resources/js/action.js"/>"></script>
</head>
<body>
	<div class="container">
		<div class="eventManager">
			<div class='page-header'>
				<div class='btn-toolbar pull-right'>
					<div class='btn-group'>
						<a href="<c:url value='/admin' />">
							<button type='button' class='btn btn-primary btn-sm'>admin</button>
						</a>
					</div>
				</div>
				<h1>
					Event Planner <small> (with Registration) </small>
				</h1>
			</div>

			<div class="row center">
				<a href="<c:url value='/listEvents' />">
					<button type="button" class="btn btn-primary btn-lg">
						Events</button>
				</a>
			</div>

			<div class="row center">
				<a href="<c:url value='' />">
					<button type="button" class="btn btn-primary btn-lg">Report</button>
				</a>
			</div>
		</div>
	</div>
</body>
</html>

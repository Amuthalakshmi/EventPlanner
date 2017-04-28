<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Set Up</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
<script src="<c:url value="/resources/js/action.js"/>">
	
</script>


<!--  BootStral Form Validator -->
<!-- Form Validation - To do
<script
	src="http://formvalidation.io/bundles/0a3b9034e109d88d72f83c9e6c9d13b7.js"></script>
<script src="<c:url value="/resources/js/validator.js"/>"> </script> -->

</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>Add Event</h1>
		</div>

		<div id="eventSetUpForm">
			<form:form class="form-horizontal" method="POST"
				modelAttribute="event">
				<fieldset>
					<div class="eventDetails">

						<div class="row form-group center">
							<label class="col-md-2 control-label" for="eventName">Event
								Name</label> &nbsp; &nbsp;
							<div class="col-md-3">
								<form:input id="eventName" class="form-control" type="text"
									path="eventName" placeholder="Name of the event" />
							</div>
						</div>

						<div class="row form-group center">
							<label class="col-md-2 control-label" for="eventLocation">Event
								Location</label> &nbsp; &nbsp;
							<div class="col-md-3">
								<form:select class="form-control" path="eventLocation">
									<form:option value="NONE" label="--- Select a location ---" />
									<form:options items="${locations}" />
								</form:select>
							</div>
						</div>

						<div class="row form-group center">
							<label class="col-md-2 control-label" for="datePicker">Planned
								date</label> &nbsp; &nbsp;
							<div class="col-md-3 date">
								<div class="input-group input-append date" id="datePicker">
									<form:input type="text" class="form-control"
										path="eventPlannedDate" placeholder="Event date" />
									<span class="input-group-addon add-on"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<!-- 
								<form:input class="date-picker form-control" type="text"
									path="eventPlannedDate" data-date-format='yyyy-mm-dd'
									placeholder="Event date" />
								 -->
							</div>
						</div>

						<div class="row form-group center">
							<label class="col-md-2 control-label" for="targetAudience">Target
								Audience</label> &nbsp; &nbsp;
							<div class="col-md-3">
								<form:input class="form-control" type="text" id="targetAudience"
									path="targetAudience" placeholder="Target Audience" />
							</div>
						</div>

						<div class="row form-group center">
							<label class="col-md-2 control-label" for="maxParticipants">Maximum
								participants</label> &nbsp; &nbsp;
							<div class="col-md-3">
								<form:input class="form-control" type="text"
									path="maxParticipants" id="maxParticipants"
									placeholder="Number of maximum participants" />
							</div>
						</div>

						<div class="row right">
							<c:choose>
								<c:when test="${edit}">
									<input class="btn btn-primary btn-lg" type="submit"
										value="Update" />
								</c:when>
								<c:otherwise>
									<input class="btn btn-primary btn-lg" type="submit"
										value="Save" />
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</body>
</html>
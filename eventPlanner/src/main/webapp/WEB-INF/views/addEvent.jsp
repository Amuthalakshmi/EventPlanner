<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:choose>
		<c:when test="${edit}">
						${event.eventName}
					</c:when>
		<c:otherwise>
						Add Event
					</c:otherwise>
	</c:choose></title>
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


<!-- BootStral Form Validator  -->
<!-- Form Validation - To do 
<script
	src="http://formvalidation.io/bundles/0a3b9034e109d88d72f83c9e6c9d13b7.js"></script>
<script src="<c:url value="/resources/js/validator.js"/>"> </script>
-->

</head>
<body>
	<div class="container">

		<div class='page-header'>
			<div class='btn-toolbar pull-right'>
				<div class='btn-group'>
					<a href="<c:url value='/manager' />">
						<button type='button'
							class='btn btn-primary btn-sm dropdown-toggle'>Home</button>
					</a>
					<button class="btn btn-primary btn-sm dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="<c:url value='/listEvents' />">Events</a></li>
						<li><a href="#">Reports</a></li>
					</ul>
				</div>
			</div>

			<h1 style="display: inline-block">
				<c:choose>
					<c:when test="${edit}">
						${event.eventName} 
						<c:if test="${not empty event.eventStatus}">
							<small> (${event.eventStatus}) </small>
						</c:if>
						<form:form method="POST" modelAttribute="event">
							<c:choose>
								<c:when test="${canDelete}">
									<button class="btn btn-success btn-circle" name="eventStatus"
										value="eventStatus" onclick="this.value='start'">Start</button>
								</c:when>
								<c:when test="${canChangeStatus}">
									<button class="btn btn-warning btn-circle" name="eventStatus"
										value="eventStatus" onclick="this.value='finish'">Finish</button>
									<button class="btn btn-danger btn-circle" name="eventStatus"
										value="eventStatus" onclick="this.value='drop'">Drop</button>
								</c:when>
							</c:choose>
						</form:form>
					</c:when>
					<c:otherwise>
						Add Event
					</c:otherwise>
				</c:choose>
			</h1>
		</div>

		<div id="eventSetUpForm">
			<form:form class="form-horizontal well" method="POST"
				modelAttribute="event">
				<fieldset>
					<c:if test="${edit}">
						<legend>
							Event details
							<c:if test="${edit}">
								<c:if test="${canDelete}">
									<a href="<c:url value='/delete-${event.eventId}-event' />">
										<button type="button" class="btn btn-link pull-right">
											Delete</button>
									</a> &nbsp; &nbsp;
								
								<div class="pull-right">|</div>
								</c:if>

								<c:if test="${canChangeStatus}">
									<button id="editBtn" type="button"
										class="btn btn-link pull-right" onClick="editEventToUpdate()">
										Edit</button>
								</c:if>

								<input id="updateBtn" class="btn btn-link pull-right"
									type="submit" value="Update" style="display: none" />
							</c:if>
						</legend>
					</c:if>

					<div class="row form-group">
						<label class="col-md-3 control-label" for="eventName">Event
							Name</label> &nbsp; &nbsp;
						<div class="col-md-3">
							<form:input id="eventName" class="form-control" type="text"
								path="eventName" placeholder="Name of the event"
								disabled="${edit}" />
						</div>
					</div>

					<div class="row form-group">
						<label class="col-md-3 control-label" for="eventLocation">Event
							Location</label> &nbsp; &nbsp;
						<div class="col-md-3">
							<form:select class="form-control" path="eventLocation"
								disabled="${edit}">
								<form:option value="NONE" label="--- Select a location ---" />
								<form:options items="${locations}" />
							</form:select>
						</div>
					</div>

					<div class="row form-group">
						<label class="col-md-3 control-label" for="eventPlannedDate">Planned
							date</label> &nbsp; &nbsp;
						<div class="col-md-3 date">
							<div class="input-group input-append date" id="datePicker">
								<form:input id="eventPlannedDate" type="text"
									class="form-control" path="eventPlannedDate"
									placeholder="Event date" disabled="${edit}" />
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

					<div class="row form-group">
						<label class="col-md-3 control-label" for="targetAudience">Target
							Audience</label> &nbsp; &nbsp;
						<div class="col-md-3">
							<form:input class="form-control" type="text" id="targetAudience"
								path="targetAudience" placeholder="Target Audience"
								disabled="${edit}" />
						</div>
					</div>

					<div class="row form-group">
						<label class="col-md-3 control-label" for="maxParticipants">Maximum
							participants</label> &nbsp; &nbsp;
						<div class="col-md-3">
							<form:input class="form-control" type="text"
								path="maxParticipants" id="maxParticipants"
								placeholder="Number of maximum participants" disabled="${edit}" />
						</div>
					</div>

					<c:if test="${not edit}">
						<div class="row center">
							<input class="btn btn-primary btn-lg" type="submit" value="Add" />
						</div>
					</c:if>

				</fieldset>
			</form:form>
		</div>

		<c:if test="${not empty event.eventStatus}">
			<form:form class="form-horizontal well" modelAttribute="event">
				<fieldset>
					<legend> Other details </legend>
					<div class="forParticipants">
						<label class="col-md-3 control-label" for="forParticipants">
							Link for Registration </label> &nbsp; &nbsp;
						<div class="col-md-8">
							<input class="form-control" type="text" id="forParticipants"
								value="http://localhost:8080/eventPlanner/participant/event${event.eventId}/register"
								readonly="readonly">
						</div>
					</div>
				</fieldset>
			</form:form>

			<form:form class="form-horizontal well"
				modelAttribute="eventOrganiser">
				<fieldset>
					<legend>
						Event Organisers <a
							href="<c:url value='/event${event.eventId}/addOrganisers' />">
							<button type="button" class="btn btn-link pull-right">
								Add Organisers</button>
						</a> &nbsp; &nbsp;
					</legend>
					<div class="organisersOfThatEvent">
						<ul>
							<c:forEach items="${event.associatedOrganisers}"
								var="eventOrganiser">
								<li><a
									href="<c:url value='/edit-${eventOrganiser.eventOrganiserId}-eventOrganiser' />">
										${eventOrganiser.organiserName} </a> &nbsp; &nbsp; <c:if
										test="${eventOrganiser.category ne 'All Event'}">
										<a href="<c:url value='/event${event.eventId}/removeOrganiser${eventOrganiser.eventOrganiserId}' />"> remove </a>
									</c:if></li>
							</c:forEach>
						</ul>
					</div>
				</fieldset>
			</form:form>
		</c:if>

	</div>
</body>
</html>
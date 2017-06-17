<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:choose>
		<c:when test="${edit}">
			Management - ${event.eventName} 
		</c:when>
		<c:otherwise>
			Management - Add Event
		</c:otherwise>
	</c:choose></title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

<!-- Bootstrap Datepicker -->
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<!-- style sheet -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/eventPlannerStyle.css"/> ">

<!-- javascripts -->
<script src="<c:url value="/resources/js/script.js"/>"></script>
<script src="<c:url value="/resources/js/action.js"/>"></script>
<script>
	$(document).ready(function() {
		$('#datePicker').datepicker({
			changeMonth : true,
			changeYear : true,
			format : 'dd-mm-yyyy',
			startDate : '-0d',
			todayHighlight : true
		});
	});
</script>
</head>
<body>
	<div class="dark-blue-header"></div>
	<div class="light-blue-header"></div>
	<div class="full-nav-bg"></div>

	<div class="container">

		<div class="header">
			<div class="heading">Technology NZ</div>
		</div>

		<div class="navigation">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs justify-content-center" role="tablist">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/' />">Home </a></li>
				<c:if test="${isEventManager}">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#eventManager" role="tab"> Management
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/admin' />">Administration</a></li>
				</c:if>
				<c:if test="${isEventOrganiser}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/organiser${eventOrganiserId}' />">Operation</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/registration' />">Registration</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Gallery</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Contact Us</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane fade show active" id="eventManager"
					role="tabpanel">

					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<c:url value='/manager' />">Management</a></li>
						<li class="breadcrumb-item active"><c:choose>
								<c:when test="${edit}">
									${event.eventName} 
									<c:if test="${not empty event.eventStatus}">
										<small> (${event.eventStatus}) </small>
									</c:if>
								</c:when>
								<c:otherwise>
									Add Event
								</c:otherwise>
							</c:choose></li>
					</ol>

					<div id="eventSetUpForm">
						<form:form class="card card-block bg-faded" method="POST"
							modelAttribute="event">
							<fieldset>
								<c:if test="${edit}">
									<legend>
										${event.eventName}

										<c:choose>
											<c:when test="${canDelete}">
												<button class="btn btn-success btn-circle"
													name="eventStatus" value="eventStatus"
													onclick="this.value='start'">Start</button>
											</c:when>
											<c:when test="${canChangeStatus}">
												<button class="btn btn-warning btn-circle"
													name="eventStatus" value="eventStatus"
													onclick="this.value='finish'">Finish</button>
												<button class="btn btn-danger btn-circle" name="eventStatus"
													value="eventStatus" onclick="this.value='drop'">Drop</button>
											</c:when>
										</c:choose>

										<c:if test="${edit}">
											<c:if test="${canDelete}">
												<a href="<c:url value='/delete-${event.eventId}-event' />">
													<button type="button" class="btn btn-link float-right">
														Delete</button>
												</a> &nbsp; &nbsp;
								
												<div id="sep" class="float-right">|</div>
											</c:if>

											<c:if test="${canChangeStatus}">
												<button id="editBtn" type="button"
													class="btn btn-link float-right"
													onClick="editEventToUpdate()">Edit</button>
											</c:if>

											<input id="updateBtn" class="btn btn-link float-right"
												type="submit" value="Update" style="display: none" />
										</c:if>
									</legend>
									<div class="separator"></div>
								</c:if>

								<div class="row form-group">
									<label class="col-md-3 control-label" for="eventName">Event
										Name</label> &nbsp; &nbsp;
									<div class="col-md-3">
										<form:input id="eventName"
											class="form-control form-control-sm" type="text"
											path="eventName" disabled="${edit}" />
									</div>
								</div>

								<div class="row form-group">
									<label class="col-md-3 control-label" for="eventDescription">Description
									</label> &nbsp; &nbsp;
									<div class="col-md-3">
										<form:textarea id="eventDescription"
											class="form-control form-control-sm" type="text"
											path="eventDescription" disabled="${edit}" />
									</div>
								</div>

								<div class="row form-group">
									<label class="col-md-3 control-label" for="eventLocation">Event
										Location</label> &nbsp; &nbsp;
									<div class="col-md-3">
										<form:select class="form-control form-control-sm"
											path="eventLocation" disabled="${edit}">
											<form:option value="NONE" label="--- Select a location ---" />
											<form:options items="${locations}" />
										</form:select>
									</div>
								</div>

								<div class="row form-group">
									<label class="col-md-3 control-label" for="eventBranch">Event
										Branch(es)</label> &nbsp; &nbsp;
									<div class="col-md-3">
										<form:select class="form-control form-control-sm"
											path="eventBranch" multiple="multiple" disabled="${edit}">
											<form:option value="170 FSW" label="170FSW" />
											<form:option value="Tory Street" label="Tory Street" />
											<form:option value="Vivian Street" label="Vivian Street" />
										</form:select>
									</div>
								</div>

								<div class="row form-group">
									<label class="col-md-3 control-label" for="eventPlannedDate">Planned
										date</label> &nbsp; &nbsp;
									<div class="col-md-3 date">
										<div class="input-group input-append date" id="datePicker">
											<form:input id="eventPlannedDate" type="text"
												class="form-control form-control-sm" path="eventPlannedDate"
												disabled="${edit}" />
											<span class="input-group-addon add-on"><span
												class="glyphicon glyphicon-calendar"></span></span>
										</div>
										<!-- 
								<form:input class="date-picker form-control form-control-sm" type="text"
									path="eventPlannedDate" data-date-format='yyyy-mm-dd'
									placeholder="Event date" />
								 -->
									</div>
								</div>

								<div class="row form-group">
									<label class="col-md-3 control-label" for="targetAudience">Target
										Audience</label> &nbsp; &nbsp;
									<div class="col-md-3">
										<form:input class="form-control form-control-sm" type="text"
											id="targetAudience" path="targetAudience" disabled="${edit}" />
									</div>
								</div>

								<div class="row form-group">
									<label class="col-md-3 control-label" for="maxParticipants">Maximum
										participants</label> &nbsp; &nbsp;
									<div class="col-md-3">
										<form:input class="form-control form-control-sm" type="text"
											path="maxParticipants" id="maxParticipants"
											disabled="${edit}" />
									</div>
								</div>

								<c:if test="${not edit}">
									<div class="row center">
										<input class="btn btn-primary btn-sm" type="submit"
											value="Add" />
									</div>
								</c:if>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
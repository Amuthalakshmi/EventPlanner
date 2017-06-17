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
<title>${event.eventName}-AddActivity</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

<!-- style sheet -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/eventPlannerStyle.css"/> ">

<!-- javascripts -->
<script src="<c:url value="/resources/js/script.js"/>"></script>
<script src="<c:url value="/resources/js/action.js"/>"></script>

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
					href="<c:url value='/' />">Home</a></li>
				<c:if test="${eventManager != null}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='' />">Management </a></li>
				</c:if>
				<c:if test="${eventOrganiser != null}">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#addActivity" role="tab"> Operation </a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/registration' />">Registration</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Gallery</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Contact Us</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade show active" id="addActivity"
					role="tabpanel">
					<div class='page-header'>
						<h4 style="display: inline-block">
							<c:choose>
								<c:when test="${edit}">
						${event.eventName} <small> - ${activity.activityName} </small>
								</c:when>
								<c:otherwise>
						${event.eventName} <small>- Add Activity </small>
								</c:otherwise>
							</c:choose>
						</h4>
						<div class="seperator"></div>
					</div>

					<div id="ActivityForm">
						<form:form class="card card-block bg-faded" method="POST"
							modelAttribute="activity">

							<c:if test="${edit}">
								<legend>
									Activity details
									<c:if test="${edit}">
										<button id="activityEditBtn" type="button"
											class="btn btn-link pull-right"
											onClick="editActivityToUpdate()">Edit</button>

										<input id="activityUpdateBtn" class="btn btn-link pull-right"
											type="submit" value="Update" style="display: none" />
									</c:if>
								</legend>
							</c:if>

							<div class="row form-group">
								<label class="control-label col-md-2" for="activityName">Name
								</label>
								<div class="col-md-4">
									<form:input id="activityName"
										class="form-control form-control-sm" type="text"
										path="activityName" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-2" for="activityDetail">Description</label>
								<div class="col-md-4">
									<form:textarea id="activityDetail"
										class="form-control form-control-sm" type="text"
										path="activityDetails" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-2" for="minAge">Min
									Age</label>
								<div class="col-md-4">
									<form:input id="minAge" class="form-control form-control-sm"
										type="text" path="minAge" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-2" for="maxAge">Max
									Age</label>
								<div class="col-md-4">
									<form:input id="maxAge" class="form-control form-control-sm"
										type="text" path="maxAge" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-2" for="activityLocation">Activity
									Location</label>
								<div class="col-md-4">
									<form:input id="activityLocation"
										class="form-control form-control-sm" type="text"
										path="activityLocation" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-2" for="homeLocation">Home
									Location</label>
								<div class="col-md-4">
									<form:input id="homeLocation"
										class="form-control form-control-sm" type="text"
										path="homeLocation" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-2" for="startTime">Start
									Time</label>
								<div class="col-md-4">
									<form:input id="startTime" class="form-control form-control-sm"
										type="text" path="startTime" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-2" for="endTime">End
									Time</label>
								<div class="col-md-4">
									<form:input id="endTime" class="form-control form-control-sm"
										type="text" path="endTime" disabled="${edit}" />
								</div>
							</div>

							<c:if test="${not edit}">
								<div class="row center">
									<button class="btn btn-primary">Add Activity</button>
								</div>
							</c:if>

						</form:form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
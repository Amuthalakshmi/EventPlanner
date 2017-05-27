<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${event.eventName}-AddActivity</title>
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

<!-- style sheet -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/eventPlannerStyle.css"/> ">
<!-- javascripts -->
<script src="<c:url value="/resources/js/action.js"/>">
	
</script>


</head>
<body>
	<div class="container">

		<div class='page-header'>
			<h1 style="display: inline-block">
				<c:choose>
					<c:when test="${edit}">
						${event.eventName} <small> - ${activity.activityName} </small>
					</c:when>
					<c:otherwise>
						${event.eventName} <small>- Add Activity </small>
					</c:otherwise>
				</c:choose>
			</h1>
		</div>

		<div id="ActivityForm">
			<form:form class="form-horizontal well" method="POST"
				modelAttribute="activity">

				<c:if test="${edit}">
					<legend>
						Activity details
						<c:if test="${edit}">
							<button id="activityEditBtn" type="button"
								class="btn btn-link pull-right" onClick="editActivityToUpdate()">
								Edit</button>

							<input id="activityUpdateBtn" class="btn btn-link pull-right"
								type="submit" value="Update" style="display: none" />
						</c:if>
					</legend>
				</c:if>

				<div class="row form-group">
					<label class="control-label col-md-3" for="activityName">Name
						of the activity</label>
					<div class="col-md-4">
						<form:input id="activityName" class="form-control" type="text"
							path="activityName" placeholder="Name of the activvity"
							disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="activityDetail">Description</label>
					<div class="col-md-4">
						<form:textarea id="activityDetail" class="form-control"
							type="text" path="activityDetails"
							placeholder="Description of the activity" disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="minAge">Min Age</label>
					<div class="col-md-4">
						<form:textarea id="minAge" class="form-control" type="text"
							path="minAge" placeholder="Minimun Age" disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="maxAge">Max Age</label>
					<div class="col-md-4">
						<form:textarea id="maxAge" class="form-control" type="text"
							path="maxAge" placeholder="Maximum Age" disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="activityLocation">Activity
						Location</label>
					<div class="col-md-4">
						<form:textarea id="activityLocation" class="form-control"
							type="text" path="activityLocation"
							placeholder="Location of the activity" disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="homeLocation">Home
						Location</label>
					<div class="col-md-4">
						<form:textarea id="homeLocation" class="form-control" type="text"
							path="homeLocation" placeholder="Location for gathering Kids"
							disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="startTime">Start
						Time</label>
					<div class="col-md-4">
						<form:textarea id="startTime" class="form-control" type="text"
							path="startTime" placeholder="Start time" disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="endTime">End 
						Time</label>
					<div class="col-md-4">
						<form:textarea id="endTime" class="form-control" type="text"
							path="endTime" placeholder="End time" disabled="${edit}" />
					</div>
				</div>

				<c:if test="${not edit}">
					<div class="row center">
						<button class="btn btn-primary btn-lg">Add Activity</button>
					</div>
				</c:if>

			</form:form>

		</div>

	</div>
</body>
</html>
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
						${event.eventName} - ${task.taskName}
		</c:when>
		<c:otherwise>
						${event.eventName} - Add task
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
			<div class="nav-top">
				<ul>
					<li><a href="<c:url value='' />">Contact us</a></li>
				</ul>
			</div>
		</div>

		<div class="navigation">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs justify-content-center" role="tablist">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/' />">Home </a></li>
				<c:if test="${isEventManager}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/manager' />">Management</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/admin' />">Administration</a></li>
				</c:if>
				<c:if test="${isEventOrganiser}">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#eventOrganiser" role="tab"> Operation
					</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/registration' />">Registration</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Gallery</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane fade show active" id="eventOrganiser"
					role="tabpanel">

					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<c:url value='/organiser${eventOrganiserId}' />">Operation</a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value='/organiser${eventOrganiserId}/plan/event${event.eventId}' />">${event.eventName}</a></li>
						<li class="breadcrumb-item active"><c:choose>
								<c:when test="${edit}">
									${task.taskName}
								</c:when>
								<c:otherwise>
									Add task
								</c:otherwise>
							</c:choose></li>
					</ol>

					<div id="TaskForm">
						<form:form class="card card-block bg-faded" method="POST"
							modelAttribute="task">
							<c:if test="${edit}">
								<legend>
									Task details
									<c:if test="${edit}">
										<button id="taskEditBtn" type="button"
											class="btn btn-link float-right" onClick="editTaskToUpdate()">
											Edit</button>

										<input id="taskUpdateBtn" class="btn btn-link float-right"
											type="submit" value="Update" style="display: none" />

										<div class="separator"></div>
									</c:if>
								</legend>
							</c:if>

							<div class="row form-group">
								<label class="control-label col-md-3" for="taskName">Name
									of the task</label>
								<div class="col-md-4">
									<form:input id="taskName" class="form-control form-control-sm"
										type="text" path="taskName" disabled="${edit}" />
								</div>
							</div>

							<div class="row form-group">
								<label class="control-label col-md-3" for="taskName">Description</label>
								<div class="col-md-4">
									<form:textarea id="taskName"
										class="form-control form-control-sm" type="text"
										path="taskDescription" disabled="${edit}" />
								</div>
							</div>

							<c:if test="${not edit}">
								<div class="row center">
									<button class="btn btn-primary">Add Task</button>
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
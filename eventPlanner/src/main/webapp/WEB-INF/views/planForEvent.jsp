<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Planning for ${event.eventName}</title>
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
<script src="<c:url value="/resources/js/action.js"/>"></script>
</head>
<body>
	<div class="container">
		<div class="tasks">
			<div class='page-header'>
				<h1>
					${event.eventName} <small>- Event planning</small>
				</h1>
			</div>

			<div class="Tasks">
				<div class="row">
					<h4>Tasks</h4>
				</div>
				<div class="horizontal TaskList">
					<c:choose>
						<c:when test="${not empty event.associatedTasks}">
							<div>
								<c:if test="${not empty startedTasks}">
									<h3>
										Started Tasks <span class="badge badge-default badge-pill">${fn:length(startedTasks)}</span>
									</h3>
									<ul class="list-group">
										<c:forEach items="${startedTasks}" var="task">
											<li class="list-group-item list-group-item-action"><a
												href="<c:url value='/eo-${eventOrganiserId}/update/task-${task.taskId}'/>">
													${task.taskName} </a> &nbsp; &nbsp; ( <a
												href="<c:url value='/eo-${eoId}/task-${task.taskId}' />">
													edit</a> )</li>
										</c:forEach>
									</ul>
								</c:if>
								<c:if test="${not empty openTasks}">
									<h3>
										Open Tasks <span class="badge badge-default badge-pill">${fn:length(openTasks)}</span>
									</h3>
									<ul class="list-group">
										<c:forEach items="${openTasks}" var="task">
											<li class="list-group-item list-group-item-action"><a
												href="<c:url value='/eo-${eventOrganiserId}/update/task-${task.taskId}'/>">
													${task.taskName} </a> &nbsp; &nbsp; ( <a
												href="<c:url value='/eo-${eoId}/task-${task.taskId}' />">
													edit</a> &nbsp; / &nbsp; <a
												href="<c:url value='/eo-${eoId}/delete-task${task.taskId}' />">
													delete</a> )</li>
										</c:forEach>
									</ul>
								</c:if>
								<c:if test="${not empty closedTasks}">
									<h3>
										Closed Tasks <span class="badge badge-default badge-pill">${fn:length(closedTasks)}</span>
									</h3>
									<ul class="list-group">
										<c:forEach items="${closedTasks}" var="task">
											<li class="list-group-item list-group-item-action"><a
												href="<c:url value='/eo-${eventOrganiserId}/update/task-${task.taskId}'/>">
													${task.taskName} </a> &nbsp; &nbsp; ( <a
												href="<c:url value='/eo-${eoId}/task-${task.taskId}' />">
													edit</a> )</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</c:when>
						<c:otherwise>
					There are no tasks
				</c:otherwise>
					</c:choose>
				</div>

				<div class="row col-md-2">
					<a
						href="<c:url value='/eo-${eoId}/event-${event.eventId}/newTask' />">
						<button type="button" class="btn btn-primary btn-sm">New
							Task</button>
					</a>
				</div>
			</div>
			<br>
			<div class="Activities">
				<div class="row">
					<h4>Activities</h4>
				</div>
				<div class="horizontal ActivityList">
					<c:choose>
						<c:when test="${not empty event.associatedActivities}">
							<div>
								<c:forEach items="${event.associatedActivities}" var="activity">
						${activity.activityName}
						${activity.activityDetails}
						${activity.minAge}
						${activity.maxAge}
						${activity.activityLocation}
						${activity.homeLocation}
						<joda:format value="${activity.startTime}" pattern="HH:mm"/>
						<joda:format value="${activity.endTime}" pattern="HH:mm"/>
						</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
					There are no Activities
					</c:otherwise>
					</c:choose>
				</div>

				<div class="row col-md-2">
					<a
						href="<c:url value='/eo-${eoId}/event-${event.eventId}/newActivity' />">
						<button type="button" class="btn btn-primary btn-sm">New
							Activity</button>
					</a>
				</div>
			</div>

		</div>
	</div>
</body>
</html>

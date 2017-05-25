<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events List</title>
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
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>All Events</h1>
		</div>

		<div class="row ">
			<c:if test="${not empty success}"> ${success} </c:if>
		</div>

		<div class="horizontal EventList">
			<c:choose>
				<c:when test="${not empty events}">
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<tr>
								<th>Name</th>
								<th>Location</th>
								<th>Planned Date</th>
								<th>Status</th>
							</tr>
							<c:forEach items="${events}" var="event">
								<tr class="data">
									<td><a
										href="<c:url value='/event-${event.eventId}' />">
											${event.eventName} </a></td>
									<td>${event.eventLocation}</td>
									<td><joda:format value="${event.eventPlannedDate}" pattern="dd-MM-yyyy"/></td>
									<td>${event.eventStatus }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					There are no Events
				</c:otherwise>
			</c:choose>
		</div>

		<div class="row">
			<a href="<c:url value='/newEvent' />">
				<button type="button" class="btn btn-primary btn-sm">New
					Event</button>
			</a>
		</div>
		
	</div>

</body>
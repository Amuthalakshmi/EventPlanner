<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Event Organisers</title>
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
		<div class="page-header">
			<h1>${event.eventName}
				<small> - Add Event Specific Organisers </small>
			</h1>
		</div>

		<div class="horizontal EventOrganiserList">
			<c:if test="${not empty eventSpecificOrganisers}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<tr>
							<th>LAN ID</th>
							<th>Name</th>
							<th>Options</th>
						</tr>
						<c:forEach items="${eventSpecificOrganisers}" var="eventOrganiser">
							<tr class="data">
								<td><a
									href="<c:url value='/edit-${eventOrganiser.eventOrganiserId}-eventOrganiser' />">
										${eventOrganiser.LANId} </a></td>
								<td>${eventOrganiser.organiserName}</td>
								<td><a
									href="<c:url value='/event${event.eventId}/add${eventOrganiser.eventOrganiserId}' />">Add</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>

		<div>
			<a href="<c:url value='/event-${event.eventId}' />"><input
				class="btn btn-primary btn-sm" value="Added Organisers" /></a>
		</div>

	</div>
</body>
</html>

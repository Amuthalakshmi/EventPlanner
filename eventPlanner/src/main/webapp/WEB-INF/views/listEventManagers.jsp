<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Managers List</title>
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
			<h1>Event Planner - Admin - Event Managers</h1>
		</div>

		<div class="row ">
			<c:if test="${not empty success}"> ${success} </c:if>
		</div>

		<div class="horizontal EventManagerList">
			<c:choose>
				<c:when test="${not empty eventManagers}">
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<tr>
								<th>LAN ID</th>
								<th>Name</th>
								<th>Options</th>
							</tr>
							<c:forEach items="${eventManagers}" var="eventManager">
								<tr class="data">
									<td>${eventManager.LANID}</td>
									<td>${eventManager.eventManagerName}</td>
									<td><a
										href="<c:url value='/edit-${eventManager.eventManagerId}-eventManager' />">
											Edit </a> / <a
										href="<c:url value='/delete-${eventManager.eventManagerId}-eventManager' />">
											Delete </a></td>



								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					There are no Event managers
				</c:otherwise>
			</c:choose>
		</div>


		<div class="row ">
			<a href="<c:url value='/newEventManager' />"><input
				class="btn btn-primary btn-sm" value="Add Event Manager" /></a>
		</div>

	</div>
</body>
</html>

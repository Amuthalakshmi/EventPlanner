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
					<div class="row">
						<div class="col-md-2">Employee ID</div>
						<div class="col-md-2">User Name</div>
						<div class="col-md-2">Options</div>
					</div>
					<c:forEach items="${eventManagers}" var="eventManager">
						<div class="row">
							<div class="col-md-2">${eventManager.employeeId}</div>
							<div class="col-md-2">${eventManager.userName}</div>
							<div class="col-md-2">
								<a
									href="<c:url value='/edit-${eventManager.eventManagerId}-eventManager' />">
									Edit </a>
							</div>
						</div>
					</c:forEach>
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

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${event.eventName}-AddTask</title>
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
						${event.eventName} <small> - ${task.taskName} </small>
					</c:when>
					<c:otherwise>
						${event.eventName} <small>- Add task </small>
					</c:otherwise>
				</c:choose>
			</h1>
		</div>

		<div id="TaskForm">
			<form:form class="form-horizontal well" method="POST"
				modelAttribute="task">

				<c:if test="${edit}">
					<legend>
						Task details
						<c:if test="${edit}">
							<button id="taskEditBtn" type="button"
								class="btn btn-link pull-right" onClick="editTaskToUpdate()">
								Edit</button>

							<input id="taskUpdateBtn" class="btn btn-link pull-right"
								type="submit" value="Update" style="display: none" />
						</c:if>
					</legend>
				</c:if>

				<div class="row form-group">
					<label class="control-label col-md-3" for="taskName">Name
						of the task</label>
					<div class="col-md-4">
						<form:input id="taskName" class="form-control" type="text"
							path="taskName" placeholder="Name of the task" disabled="${edit}" />
					</div>
				</div>

				<div class="row form-group">
					<label class="control-label col-md-3" for="taskName">Description</label>
					<div class="col-md-4">
						<form:textarea id="taskName" class="form-control" type="text"
							path="taskDescription" placeholder="Description of the task"
							disabled="${edit}" />
					</div>
				</div>

				<c:if test="${not edit}">
					<div class="row center">
						<button class="btn btn-primary btn-lg">Add Task</button>
					</div>
				</c:if>

			</form:form>

		</div>

	</div>
</body>
</html>
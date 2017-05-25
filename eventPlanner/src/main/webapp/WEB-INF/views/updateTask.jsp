<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				${event.eventName} <small> - ${task.taskName} </small>
			</h1>
		</div>

		<div id="TaskStatusForm">

			<form:form class="form-horizontal well" method="POST"
				modelAttribute="task">

				<c:if test="${edit}">
					<legend> Task details </legend>
				</c:if>

				<div class="row form-group">
					<label class="col-md-3">Description</label>
					<div class="col-md-4">${task.taskDescription}</div>
				</div>

				<c:if test="${not empty taskBlog}">
					<div class="row form-group">
						<label class="col-md-3">Updates</label>
						<div class="col-md-4">${taskBlog}</div>
					</div>
				</c:if>

				<c:if test="${task.taskStatus ne 'Close'}">
					<div class="row form-group">
						<label class="control-label col-md-3" for="taskStatus">Status</label>
						<div class="col-md-4">
							<form:select id="taskStatus" class="form-control"
								path="taskStatus">
								<form:option value="Open" label="Open" />
								<form:option value="Started" label="Started" />
								<form:option value="Close" label="Close" />
							</form:select>
						</div>
					</div>

					<div class="row form-group">
						<label class="control-label col-md-3" for="taskBlog">Update
							on progress</label>
						<div class="col-md-4">
							<form:textarea id="taskBlog" class="form-control" type="text"
								path="taskBlog"
								placeholder="A short description on the task status as and when there is an update"
								value="" />
						</div>
					</div>

					<div class="row center">
						<button class="btn btn-primary btn-lg">Update</button>
					</div>
				</c:if>


			</form:form>

		</div>

	</div>
</body>
</html>
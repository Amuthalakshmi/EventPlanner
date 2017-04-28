<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add/Update Event Manager</title>
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

<!--  BootStral Form Validator -->
<script
	src="http://formvalidation.io/bundles/0a3b9034e109d88d72f83c9e6c9d13b7.js"></script>

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
			<h1>Add Event Manager</h1>
		</div>

		<div id="eventManagerForm">
			<form:form class="form-horizontal" method="POST"
				modelAttribute="eventManager">
				<fieldset>
					<div class="eventManagerDetails">
						<div class="row form-group center">
							<label class="col-md-2 control-label" for="employeeId">Employee
								ID</label> &nbsp; &nbsp;
							<div class="col-md-2">
								<form:input id="employeeId" class="form-control" type="text"
									path="employeeId" placeholder="Employee Id" />
							</div>
						</div>

						<div class="row form-group center">
							<label class="col-md-2 control-label" for="userName">User
								Name / LAN ID </label> &nbsp; &nbsp;
							<div class="col-md-2">
								<form:input id="userName" class="form-control" type="text"
									path="userName" placeholder="User Name/LAN ID" />
							</div>
						</div>


						<div class="row right">
							<c:choose>
								<c:when test="${edit}">
									<input class="btn btn-primary btn-lg" type="submit"
										value="Update" />
								</c:when>
								<c:otherwise>
									<input class="btn btn-primary btn-lg" type="submit" value="Add" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</body>
</html>

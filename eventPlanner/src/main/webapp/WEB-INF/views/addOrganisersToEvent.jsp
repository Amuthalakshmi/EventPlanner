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
<title>${event.eventName}- Add event specific organisers</title>
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
			<div class="heading">Technology NZ</div>
		</div>

		<div class="navigation">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs justify-content-center" role="tablist">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/' />">Home </a></li>
				<c:if test="${isEventManager}">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#eventManager" role="tab"> Management
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/admin' />">Administration</a></li>
				</c:if>
				<c:if test="${isEventOrganiser}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/organiser${eventOrganiserId}' />">Operation</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/registration' />">Registration</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Gallery</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Contact Us</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane fade show active" id="eventManager"
					role="tabpanel">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<c:url value='/manager' />">Management</a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value='/event-${event.eventId}' />">${event.eventName}</a></li>
						<li class="breadcrumb-item active">Add event specific
							organisers</li>
					</ol>

					<div class="horizontal EventOrganiserList">
						<c:if test="${not empty eventSpecificOrganisers}">
							<div class="table-responsive">
								<table class="table table-striped table-bordered">
									<tr>
										<th>LAN ID</th>
										<th>Name</th>
										<th>Options</th>
									</tr>
									<c:forEach items="${eventSpecificOrganisers}"
										var="eventOrganiser">
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
			</div>
		</div>





	</div>
</body>
</html>

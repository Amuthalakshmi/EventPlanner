<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="container">

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
							<th>Branch</th>
							<th>Planned Date</th>
							<th>Status</th>
						</tr>
						<c:forEach items="${events}" var="event">
							<tr class="data">
								<td><a href="<c:url value='/event-${event.eventId}' />">
										${event.eventName} </a></td>
								<td>${event.eventLocation}</td>
								<td>${event.eventBranch}</td>
								<td><joda:format value="${event.eventPlannedDate}"
										pattern="dd-MM-yyyy" /></td>
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

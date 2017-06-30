<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="container">

	<div class="horizontal EventOrganiserList">
		<c:choose>
			<c:when test="${not empty eventOrganisers}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<tr>
							<th>LAN ID</th>
							<th>Name</th>
							<th>Options</th>
						</tr>
						<c:forEach items="${eventOrganisers}" var="eventOrganiser">
							<tr class="data">
								<td>${eventOrganiser.LANId}</td>
								<td>${eventOrganiser.organiserName}</td>
								<td><a
									href="<c:url value='/edit-${eventOrganiser.eventOrganiserId}-eventOrganiser' />">
										Edit </a> / <a
									href="<c:url value='/delete-${eventOrganiser.eventOrganiserId}-eventOrganiser' />">
										Delete </a></td>



							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:otherwise>
					There are no Event Organisers
				</c:otherwise>
		</c:choose>
	</div>


	<div class="row ">
		<a href="<c:url value='/newEventOrganiser' />">
			<button class="btn btn-primary btn-sm" type="button">Add
				Event Organiser</button>
		</a>
	</div>

</div>

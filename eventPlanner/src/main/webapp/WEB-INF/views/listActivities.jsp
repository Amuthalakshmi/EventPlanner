<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="container">
	<div class="Activities">
		<div class="horizontal ActivityList">
			<c:choose>
				<c:when test="${not empty event.associatedActivities}">
					<div>

						<ul class="list-group">
							<c:forEach items="${event.associatedActivities}" var="activity">
								<li class="list-group-item list-group-item-action"><a
									href="<c:url value='/eo-${eoId}/event-${activity.event.eventId}/activity-${activity.activityId}'/>">
										${activity.activityName} </a></li>
							</c:forEach>
						</ul>

					</div>
				</c:when>
				<c:otherwise>
					There are no Activities
				</c:otherwise>
			</c:choose>
		</div>

		<div class="row col-md-2">
			<a
				href="<c:url value='/eo-${eoId}/event-${event.eventId}/newActivity' />">
				<button type="button" class="btn btn-primary btn-sm">New
					Activity</button>
			</a>
		</div>
	</div>
</div>

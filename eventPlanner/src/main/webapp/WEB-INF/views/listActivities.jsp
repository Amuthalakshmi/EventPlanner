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
						<c:forEach items="${event.associatedActivities}" var="activity">
																${activity.activityName}
																${activity.activityDetails}
																${activity.minAge}
																${activity.maxAge}
																${activity.activityLocation}
																${activity.homeLocation}
																<joda:format value="${activity.startTime}"
								pattern="HH:mm" />
							<joda:format value="${activity.endTime}" pattern="HH:mm" />
						</c:forEach>
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

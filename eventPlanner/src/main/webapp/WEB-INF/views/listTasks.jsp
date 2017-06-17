<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="container">

	<div class="horizontal TaskList">
		<c:choose>
			<c:when test="${not empty event.associatedTasks}">
				<div>
					<c:if test="${not empty startedTasks}">
						<h6>
							Started Tasks <span class="badge badge-default badge-pill">${fn:length(startedTasks)}</span>
						</h6>
						<ul class="list-group">
							<c:forEach items="${startedTasks}" var="task">
								<li class="list-group-item list-group-item-action"><a
									href="<c:url value='/eo-${eventOrganiserId}/update/task-${task.taskId}'/>">
										${task.taskName} </a> &nbsp; &nbsp; ( <a
									href="<c:url value='/eo-${eoId}/task-${task.taskId}' />">
										edit</a> )</li>
							</c:forEach>
						</ul>
					</c:if>
					
					<c:if test="${not empty openTasks}">
						<h6>
							Open Tasks <span class="badge badge-default badge-pill">${fn:length(openTasks)}</span>
						</h6>
						<ul class="list-group">
							<c:forEach items="${openTasks}" var="task">
								<li class="list-group-item list-group-item-action"><a
									href="<c:url value='/eo-${eventOrganiserId}/update/task-${task.taskId}'/>">
										${task.taskName} </a> &nbsp; &nbsp; ( <a
									href="<c:url value='/eo-${eoId}/task-${task.taskId}' />">
										edit</a> &nbsp; / &nbsp; <a
									href="<c:url value='/eo-${eoId}/delete-task${task.taskId}' />">
										delete</a> )</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${not empty closedTasks}">
						<h6>
							Closed Tasks <span class="badge badge-default badge-pill">${fn:length(closedTasks)}</span>
						</h6>
						<ul class="list-group">
							<c:forEach items="${closedTasks}" var="task">
								<li class="list-group-item list-group-item-action"><a
									href="<c:url value='/eo-${eventOrganiserId}/update/task-${task.taskId}'/>">
										${task.taskName} </a> &nbsp; &nbsp; </li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</c:when>
			<c:otherwise>
				There are no tasks
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="row col-md-2">
		<a href="<c:url value='/eo-${eoId}/event-${event.eventId}/newTask' />">
			<button type="button" class="btn btn-primary btn-sm">New
				Task</button>
		</a>
	</div>
</div>



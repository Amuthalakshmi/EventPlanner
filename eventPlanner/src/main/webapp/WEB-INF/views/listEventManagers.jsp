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

	<div class="horizontal EventManagerList">
		<c:choose>
			<c:when test="${not empty eventManagers}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<tr>
							<th>LAN ID</th>
							<th>Name</th>
							<th>Options</th>
						</tr>
						<c:forEach items="${eventManagers}" var="eventManager">
							<tr class="data">
								<td>${eventManager.LANID}</td>
								<td>${eventManager.eventManagerName}</td>
								<td><a
									href="<c:url value='/edit-${eventManager.eventManagerId}-eventManager' />">
										Edit </a> / <a
									href="<c:url value='/delete-${eventManager.eventManagerId}-eventManager' />">
										Delete </a></td>



							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:otherwise>
					There are no Event managers
				</c:otherwise>
		</c:choose>
	</div>


	<div class="row ">
		<a href="<c:url value='/newEventManager' />">
			<button class="btn btn-primary btn-sm" type="button">Add
				Event Manager</button>
		</a>
	</div>

</div>
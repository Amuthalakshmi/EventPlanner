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
<title><c:if test="${not empty event.eventName}"> ${event.eventName} - </c:if>
	Registration</title>
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
			<div class="nav-top">
				<ul>
					<li><a href="<c:url value='' />">Contact us</a></li>
				</ul>
			</div>
		</div>

		<div class="navigation">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs justify-content-center" role="tablist">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/' />">Home</a></li>
				<c:if test="${isEventManager}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/manager' />">Management </a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/admin' />">Administration</a></li>
				</c:if>
				<c:if test="${isEventOrganiser}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/organiser${eventOrganiserId}' />">Operation</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link active"
					data-toggle="tab" href="#registration" role="tab"> Registration
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='' />">Gallery</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane fade show active" id="registration"
					role="tabpanel">

					<c:choose>
						<c:when test="${showForm}">
							<div id="ParticipantForm" class="card card-block bg-faded">
								<form:form method="POST" modelAttribute="participant">
									<fieldset>
										<legend>
											Personal Details
											<c:if test="${edit}">
												<button id="participantEditBtn" type="button"
													class="btn btn-link pull-right"
													onClick="editParticipantToUpdate()">Edit</button>

												<input id="participantUpdateBtn"
													class="btn btn-link pull-right" type="submit"
													value="Update" style="display: none" />
											</c:if>
										</legend>
										<div class="separator"></div>
										<div id="personaldetailsform" class="personaldetails">
											<div class="row form-group">
												<label class="control-label col-md-3" for="LANId">LAN Id</label>
												<div class="col-md-4">
													<form:input id="LANId"
														class="form-control form-control-sm" type="text"
														path="LANId" disabled="${edit}" />
												</div>
											</div>

											<div class="row form-group">
												<label class="control-label col-md-3" for="location">Location</label>
												<div class="col-md-4">
													<form:select id="location"
														class="form-control form-control-sm" path="location"
														disabled="${edit}">
														<form:option value="" label="-- Select a location --" />
														<form:option value="170FSW" label="170FSW" />
														<form:option value="ToryStreet" label="Tory Street" />
														<form:option value="VivianStreet" label="Vivian Street" />
													</form:select>
												</div>
											</div>

											<div class="row form-group">
												<label class="control-label col-md-3" for="level">Level</label>
												<div class="col-md-4">
													<form:select id="level"
														class="form-control form-control-sm" path="level"
														disabled="${edit}">
														<form:option value=""
															label="-- Select the level you are in --" />
														<form:option value="Level 1" label="Level 1" />
														<form:option value="Level 2" label="Level 2" />
														<form:option value="Level 3" label="Level 3" />
														<form:option value="Level 4" label="Level 4" />
														<form:option value="Level 5" label="Level 5" />
														<form:option value="Level 6" label="Level 6" />
														<form:option value="Level 7" label="Level 7" />
													</form:select>
												</div>
											</div>

											<div class="row form-group">
												<label class="control-label col-md-3">Number of kids</label>
												<div class="col-md-4">
													<form:select class="form-control form-control-sm"
														path="numberOfChildren" onchange="showDiv(this)"
														disabled="${edit}">
														<form:option value="1" label="1" />
														<form:option value="2" label="2" />
														<form:option value="3" label="3" />
														<form:option value="4" label="4" />
													</form:select>
												</div>
											</div>
										</div>

										<div class="kidsdetails card card-block">

											<legend>Kid's Details</legend>

											<div class="foreachkid" id="foreachkid">
												<c:forEach items="${participant.children}" varStatus="i">

													<div class="child card card-block bg-faded"
														id="child${i.index + 1}">
														<legend>Child ${i.index + 1}</legend>
														<div class="separator"></div>

														<div class="row form-group">
															<label class="control-label col-md-3" for="childName">Full
																name of the child</label>
															<div class="col-md-4">
																<form:input id="childName"
																	class="form-control form-control-sm" type="text"
																	path="children[${i.index}].childName"
																	disabled="${edit}" />
															</div>
														</div>

														<div class="row form-group">
															<label class="control-label col-md-3" for="childAge">
																Age </label>
															<div class="col-md-4">
																<form:input id="childAge"
																	class="form-control form-control-sm" type="text"
																	path="children[${i.index}].childAge" disabled="${edit}" />
															</div>
														</div>


														<div class="row form-group">
															<label class="control-label col-md-3">Gender </label>
															<div class="col-md-4">
																<div class="btn-group" data-toggle="buttons">
																	<label class="btn btn-secondary btn-sm"> <form:radiobutton
																			path="children[${i.index}].childGender" value="male"
																			autocomplete="off" disabled="${edit}" /> male
																	</label> <label class="btn btn-secondary btn-sm"> <form:radiobutton
																			path="children[${i.index}].childGender"
																			value="female" autocomplete="off" disabled="${edit}" />
																		female
																	</label> <label class="btn btn-secondary btn-sm"> <form:radiobutton
																			path="children[${i.index}].childGender" value="other"
																			autocomplete="off" disabled="${edit}" /> other
																	</label>
																</div>
															</div>
														</div>

														<div class="row form-group">
															<label class="control-label col-md-3">Dietary
																Requirement </label>
															<div id="dietReq" class="col-md-4">
																<div class="btn-group" data-toggle="buttons">
																	<label class="btn btn-secondary btn-sm"> <form:radiobutton
																			path="children[${i.index}].hasDietRequirement"
																			value="yes" autocomplete="off" disabled="${edit}" />
																		Yes
																	</label> <label class="btn btn-secondary btn-sm"> <form:radiobutton
																			path="children[${i.index}].hasDietRequirement"
																			value="no" autocomplete="off" disabled="${edit}" />
																		No
																	</label>
																</div>
															</div>
														</div>

														<div id="child${i.index + 1}foodpreference"
															style="display: none">
															<div class="row form-group">
																<label class="control-label col-md-3">Food
																	Preference</label>
																<div class="col-md-4">
																	<form:select class="form-control form-control-sm"
																		path="children[${i.index}].childFoodPreference"
																		disabled="${edit}">
																		<form:option value="" label="-- Choose an option --" />
																		<form:option value="Vegan" label="Vegan" />
																		<form:option value="Vegetarian" label="Vegetarian" />
																		<form:option value="Non-Vegetarian"
																			label="Non Vegetarian" />
																		<form:option value="Glutenfree" label="Gluten free" />
																		<form:option value="others" label="others specify" />
																	</form:select>
																</div>
															</div>

															<div class="row form-group">
																<label class="control-label col-md-3">Allergic </label>
																<div class="col-md-4">
																	<div class="btn-group" data-toggle="buttons">
																		<label class="btn btn-secondary btn-sm"> <form:radiobutton
																				path="children[${i.index}].isChildAllergic"
																				value="yes" autocomplete="off" disabled="${edit}" />
																			Yes
																		</label> <label class="btn btn-secondary btn-sm"> <form:radiobutton
																				path="children[${i.index}].isChildAllergic"
																				value="no" autocomplete="off" disabled="${edit}" />
																			No
																		</label>
																	</div>

																</div>
															</div>

															<div class="row form-group">
																<label class="control-label col-md-3">Details </label>
																<div class="col-md-4">
																	<form:textarea class="form-control form-control-sm"
																		path="children[${i.index}].otherDetails"
																		disabled="${edit}" />
																</div>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>

										<c:if test="${not edit}">
											<div class="row center">
												<button class="btn btn-primary">Complete
													registration</button>
											</div>
										</c:if>
									</fieldset>
								</form:form>
							</div>
						</c:when>
						<c:otherwise>
							<div class="row">
								<h4 class="col-md-6">${errormsg}</h4>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="tab-pane fade" id="gallery" role="tabpanel">GALLERY</div>
			</div>
		</div>
	</div>


</body>
</html>
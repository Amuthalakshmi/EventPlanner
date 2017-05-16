<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration <c:if test="${not empty event.eventName}"> 
		- ${event.eventName}
	</c:if>
</title>
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

<script src="<c:url value="/resources/js/action.js"/>">
	
</script>

</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>
				<c:if test="${not empty event.eventName}"> ${event.eventName} - </c:if>
				Registration
			</h1>
		</div>

		<c:choose>
			<c:when test="${showForm}">
				<div id="ParticipantForm">
					<form:form class="form-horizontal well" method="POST"
						modelAttribute="participant">
						<fieldset>
							<legend>
								Personal Details
								<c:if test="${edit}">
									<button id="participantEditBtn" type="button"
										class="btn btn-link pull-right"
										onClick="editParticipantToUpdate()">Edit</button>

									<input id="participantUpdateBtn"
										class="btn btn-link pull-right" type="submit" value="Update"
										style="display: none" />
								</c:if>
							</legend>
							<div id="personaldetailsform" class="personaldetails">
								<div class="row form-group">
									<label class="control-label col-md-3" for="userName">User
										Name</label>
									<div class="col-md-4">
										<form:input id="userName" class="form-control" type="text"
											path="userName" placeholder="Enter your User ID/LAN ID"
											disabled="${edit}" />
									</div>
								</div>

								<div class="row form-group">
									<label class="control-label col-md-3" for="location">Location</label>
									<div class="col-md-4">
										<form:select id="location" class="form-control"
											path="location" disabled="${edit}">
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
										<form:select id="level" class="form-control" path="level"
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
										<form:select class="form-control" path="numberOfChildren"
											onchange="showDiv(this)" disabled="${edit}">
											<form:option value="1" label="1" />
											<form:option value="2" label="2" />
											<form:option value="3" label="3" />
											<form:option value="4" label="4" />
										</form:select>
									</div>
								</div>
							</div>


							<div class="kidsdetails">
								<h3>Kid's Details</h3>

								<div class="foreachkid" id="foreachkid">
									<c:forEach items="${participant.children}" varStatus="i">
										<div class="child" id="child${i.index + 1}">
											<div class="row">
												<h4 class="col-md-2">Child ${i.index + 1}</h4>
											</div>

											${child.childName}

											<div class="row form-group">
												<label class="control-label col-md-3" for="childName">Full
													name of the child</label>
												<div class="col-md-4">
													<form:input id="childName" class="form-control" type="text" 
														path="children[${i.index}].childName" placeholder="Name of the child"
														disabled="${edit}" />
												</div>
											</div>

											<div class="row form-group">
												<label class="control-label col-md-3" for="childAge">
													Age </label>
												<div class="col-md-4">
													<form:input id="childAge" class="form-control" type="text"
														path="children[${i.index}].childAge" placeholder="Age of the child"
														disabled="${edit}" />
												</div>
											</div>


											<div class="row form-group">
												<label class="control-label col-md-3">Gender </label>
												<div class="col-md-4">
													<form:radiobutton path="children[${i.index}].childGender" value="male"
														disabled="${edit}" />
													male <br />
													<form:radiobutton path="children[${i.index}].childGender"
														value="female" disabled="${edit}" />
													female <br />
													<form:radiobutton path="children[${i.index}].childGender"
														value="prefer not to disclose" disabled="${edit}" />
													prefer not to disclose
												</div>
											</div>

											<div class="row form-group">
												<label class="control-label col-md-3">Dietary
													Requirement </label>
												<div class="col-md-4">
													<form:radiobutton path="children[${i.index}].hasDietRequirement"
														value="yes" onclick="foodprefer(this)" disabled="${edit}" />
													Yes &nbsp;&nbsp;
													<form:radiobutton path="children[${i.index}].hasDietRequirement"
														onclick="foodprefer(this)" value="no" disabled="${edit}" />
													No
												</div>
											</div>

											<div id="child${i.index + 1}foodpreference"
												style="display: none">
												<div class="row form-group">
													<label class="control-label col-md-3">Food
														Preference</label>
													<div class="col-md-4">
														<form:select class="form-control"
															path="children[${i.index}].childFoodPreference" disabled="${edit}">
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
														<form:radiobutton path="children[${i.index}].isChildAllergic"
															value="yes" disabled="${edit}" />
														Yes &nbsp;&nbsp;
														<form:radiobutton path="children[${i.index}].isChildAllergic"
															value="no" disabled="${edit}" />
														No
													</div>
												</div>

												<div class="row form-group">
													<label class="control-label col-md-3">Details </label>
													<div class="col-md-4">
														<form:textarea class="form-control"
															path="children[${i.index}].otherDetails" placeholder="Please specify"
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
									<button class="btn btn-primary btn-lg">Complete
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
</body>
</html>
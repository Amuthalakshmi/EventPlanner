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
							<legend>Personal Details</legend>
							<div class="personaldetails">
								<div class="row form-group">
									<label class="control-label col-md-2" for="userName">User
										Name</label>
									<div class="col-md-3">
										<form:input id="userName" class="form-control" type="text"
											path="userName" placeholder="Enter your User ID/LAN ID" />
									</div>
								</div>

								<div class="row form-group">
									<label class="control-label col-md-2" for="location">Location</label>
									<div class="col-md-3">
										<form:select id="location" class="form-control"
											path="location">
											<form:option value="" label="-- Select a location --" />
											<form:option value="170FSW" label="170FSW" />
											<form:option value="ToryStreet" label="Tory Street" />
											<form:option value="VivianStreet" label="Vivian Street" />
										</form:select>
									</div>
								</div>

								<div class="row form-group">
									<label class="control-label col-md-2">Level</label>
									<div class="col-md-3">
										<select class="form-control" name="level">
											<option value="">-- Select the level you are in --</option>
											<option value="ground">Ground</option>
											<option value="1">Level 1</option>
											<option value="2">Level 2</option>
											<option value="3">Level 3</option>
											<option value="4">Level 4</option>
											<option value="5">Level 5</option>
											<option value="6">Level 6</option>
										</select>
									</div>
								</div>

								<div class="row form-group">
									<label class="control-label col-md-2">Number of kids</label>
									<div class="col-md-3">
										<form:select class="form-control" path="numberOfChildren"
											onchange="showDiv(this)">
											<form:option value="1" label="1" />
											<form:option value="2" label="2" />
											<form:option value="3" label="3" />
											<form:option value="4" label="4" />
										</form:select>
									</div>
								</div>
							</div>

							<div class="kidsdetails">
								<form:form class="form-horizontal well" method="POST"
									modelAttribute="childListWrapper">
									<fieldset>
										<legend>Kid's Details</legend>

										<div class="foreachkid" id="foreachkid">
											<div class="child" id="child1">
												<c:forEach items="${childListWrapper.childList}"
													varStatus="i">

													<div class="row">
														<h4 class="col-md-2">Child ${i.index + 1}</h4>
													</div>

													<div class="row">
														<div class="pull-left col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4" for="childName">Name
																	of the child</label>
																<div class="col-md-6">
																	<form:input id="childName" class="form-control"
																		type="text" path="childList[${i.index}].childName"
																		placeholder="Name of the child" />
																</div>
															</div>

															<div class="form-group">
																<label class="control-label col-md-4" for="childAge">
																	Age </label>
																<div class="col-md-6">
																	<form:input id="childAge" class="form-control"
																		type="text" path="childList[${i.index}].childAge"
																		placeholder="Age of the child" />
																</div>
															</div>


															<div class="form-group">
																<label class="control-label col-md-4">Gender </label>
																<div class="col-md-6">
																	<form:radiobutton
																		path="childList[${i.index}].childGender" value="male" />
																	male <br />
																	<form:radiobutton
																		path="childList[${i.index}].childGender"
																		value="female" />
																	female <br />
																	<form:radiobutton
																		path="childList[${i.index}].childGender"
																		value="prefer not to disclose" />
																	prefer not to disclose
																</div>
															</div>
														</div>

														<div class="pull-right col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4">Dietary
																	Requirement </label>
																<div class="col-md-7">
																	<form:select class="form-control"
																		path="childList[${i.index}].childDietaryRequirement">
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

															<div class="form-group">
																<label class="control-label col-md-4">Allergic </label>
																<div class="col-md-7">
																	<form:radiobutton
																		path="childList[${i.index}].isChildAllergic"
																		value="yes" />
																	Yes &nbsp;&nbsp;
																	<form:radiobutton
																		path="childList[${i.index}].isChildAllergic"
																		value="no" />
																	No
																</div>
															</div>

															<div class="form-group">
																<label class="control-label col-md-4">Details </label>
																<div class="col-md-7">
																	<form:textarea class="form-control"
																		path="childList[${i.index}].otherDetails"
																		placeholder="Please specify" />
																</div>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
									</fieldset>
								</form:form>
							</div>

							<div class="row center">
								<button class="btn btn-primary btn-lg">Complete
									registration</button>
							</div>
						</fieldset>
					</form:form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<h4 class="col-md-2">${errormsg}</h4>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
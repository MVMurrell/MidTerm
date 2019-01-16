<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
  
<link rel="stylesheet" type="text/css" href="viewProject.css">
<title>View Project</title>
</head>
<body>
	<%@include file="navBar.jsp"%>

	<br>
	<br>
	<br>
	<br>

	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-4">
			<div class="projectDisplay">
				<c:if test="${project.active }">
					<h4 class="open">Project is Open</h4>
				</c:if>
				<c:if test="${not project.active }">
					<h4 class="closed">Project is Closed</h4>
				</c:if>
	
				<strong>Project name: </strong>${project.title} <br> <strong>Project
					Description: </strong>${project.description} <br> <strong>Category:
				</strong>
				<c:forEach items="${project.categories}" var="cat" varStatus="loop">
			  				 	${cat.name}
			  				 	<c:if test="${!loop.last}">,</c:if>
				</c:forEach>
				<br> 
				<strong>Owner Name: </strong>${project.owner.firstName}
				${project.owner.lastName} 
				<div>
					<form action="viewProfile.do" method="post">
						<input type="hidden" name="viewId" value="${project.owner.userid}">
						<input type="submit" value="View Profile" class="btn btn-primary">
					</form>
				</div>
				<br> 
				<strong>Address: </strong>${project.address }
				<br> 
				<strong>Contact Phone: </strong> ${project.owner.phone }
				<br>
				<strong>Contact Email: </strong> ${project.owner.user.email }
				<br>
				
				<strong>StartDate: </strong>${project.startDate} 
				<br>
				<strong>EndDate: </strong>${project.endDate} 
				<br> 
				<strong>Time: </strong>${project.time} <br>
				<c:if
					test="${(project.volunteersNeeded - fn:length(project.volunteers)) > 0}">
					<strong>Volunteers still needed: </strong>
			  					${project.volunteersNeeded - fn:length(project.volunteers) }
			  				</c:if>
				<c:if
					test="${(project.volunteersNeeded - fn:length(project.volunteers)) <= 0}">
					<strong>Volunteer:</strong>
			  					Volunteer Goal Met
			  				</c:if>
				<br>
				<c:if test="${!inList && project.active}">
	
					<form action="joinProject.do" method="post">
						<label for="hours">Add Volunteer: </label>
						 <input type="hidden"
							name="userId" id="userId" value="${user.id}"> 
						<input type="hidden" name="projectId" id="projectId"
							value="${project.id}"> 
						<input type="number" name="hours"
							id="hours" placeholder="pledged hours" required> 
						<input type="submit" class="btn btn-primary" value="Join Project">
	
					</form>
				</c:if>
			</div>
			<div class="commentBox">
				<c:if test="${project.owner.userid == user.id or user.role == 'admin' }">
					<form action="editProject.do" method="GET">
						<input name="projectId" value="${project.id}" type="hidden">
						<input type="submit" class="btn btn-primary" value="Edit Project" />
					</form>
				</c:if>
				<c:if test="${not empty project.comments }">
					<h3>Comments:</h3>
					<ul>
						<c:forEach items="${project.comments }" var="comment">
							<c:if test="${comment.active || user.role == 'admin'}">
								<li>
									<div class="commentDisplay">
										${comment.comment }<br>
										- ${comment.user.volunteer.firstName }<br>
										${comment.date }, 
										${comment.time }
										<c:if test="${ !comment.active && user.role == 'admin'}">
											<div class="commentButton">
												<form action="reactivateComment.do" method="POST">
													<input name="commentId" value="${comment.id }" type="hidden">
													<input type="submit" class="btn btn-primary" value="Reactivate Comment" />
												</form>
											</div>
										</c:if>
										<c:if test="${comment.active && user.id == project.owner.userid || user.id == comment.user.id || user.role == 'admin'}">
											<div class="commentButton">
												<form action="removeComment.do" method="POST">
													<input name="commentId" value="${comment.id }" type="hidden">
													<input type="submit" class="btn btn-primary" value="Remove Comment" />
												</form>
											</div>
										</c:if>
									</div>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<br>
				<c:if test="${not empty user }">
					<form action="addComment.do" method="POST">
						<textarea name="comment" placeholder="Add comment" ></textarea>
						<br>
						<input name="projectId" value="${project.id }" type="hidden">
						<input name="userId" value="${user.id }" type="hidden">
						<input type="submit" class="btn btn-primary" value="Add Comment" />
					</form>
				</c:if>
			</div>
			
			
		</div>
		<div class="col-sm-3">
			<h3>Volunteers:</h3>
			<div>
				<ul>
					<c:forEach items="${project.volunteers}" var="volunteer">
						<li>
							<div class="bubbles">
							
							
								<strong>${volunteer.firstName } ${volunteer.lastName } </strong>
								<br>
								<c:if test="${project.owner.userid == user.id }">
										<strong>Contact Phone: </strong>${volunteer.phone}
										<br>
										<strong>Contact Email: </strong>${volunteer.user.email}
									 
								</c:if>
								<div>
									<form action="viewProfile.do" method="post">
										<input type="hidden" name="viewId" value="${volunteer.userid}">
										<input type="submit" value="View Profile" class="btn btn-primary">
									</form>
								</div>
								<c:forEach items="${pvList}" var="pv">
									<c:if test="${volunteer.userid == pv.volunteer.userid}">
										<strong>Pledged Hours: </strong>${pv.hoursPledged}
						  				<br>
										<strong>Actual Hours: </strong>${pv.hoursActual }
						  			<!-- 	<br> -->
										<c:if test="${volunteer.userid == user.id or project.owner.userid == user.id or user.role == 'admin' }">
											<form action="submitHours.do" method="post">
												<label for="hoursActual">Edit Hours: </label> <input
													type="hidden" name="userId" id="userId"
													value="${volunteer.userid}"> <input type="hidden"
													name="projectId" id="projectId" value="${project.id}">
												<input type="number" name="hours" id="hours" min="0"
													value="${pv.hoursPledged}"> <input type="submit"
													class="btn btn-primary" value="Submit Hours">
											</form>
											<br>
											<form action="removeVolunteer.do" method="post">
												<input type="hidden" name="userId" id="userId"
													value="${volunteer.userid}"> <input type="hidden"
													name="projectId" id="projectId" value="${project.id}">
												<input type="submit" class="btn btn-primary"
													value="Remove Volunteer">
				
											</form>
										</c:if>
									</c:if>
								</c:forEach>
								
								<br>
							</div>
						</li>
					</c:forEach>
					
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
		<div id="map-canvas"></div>
			<input type="hidden" id="address"
			   	   value="${project.address.street} ${project.address.city} ${project.address.state.name}">
	</div>
		
	

	<!-- put map here -->





	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDu3z8H8wEptTmzLK1unAnBejz0QmTc_h8&callback=initMap">
		
	</script>
	<script src="JS/googleMaps.js"></script>
</body>
</html>
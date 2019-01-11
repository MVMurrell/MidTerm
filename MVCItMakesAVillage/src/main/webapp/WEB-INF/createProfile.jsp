<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>Volunteer Site - Create Profile</title>
</head>
<body>
	<header> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-4" >
				<h4>Create Profile</h4>
				<form action="createProfile.do" method="POST">
				<div class="form-group">
					<label id="id">Id:</label> 
					<input type="hidden" id="id" name="id"
						   value=${user.id }></input>
				</div>
				<div class="form-group">
					<label id="firstName">First Name:</label> 
					<input type="text" id="John"
						   name="firstName" value="First Name"></input>
				</div>
				<div class="form-group">
					<label id="lastName">Last Name:</label> 
					<input type="text" id="Smith"
						   name="lastName" value="Last Name"></input>
				</div>
				<div class="form-group">
					<label id="dob">Birth date:</label> 
					<input type="date" id="dob"
						   name="dob"></input>
				</div>
				<div class="form-group">
					<label id="phone">Phone Number:</label> 
					<input type="text" id="phone"
						   name="phone" value="555-555-5555"></input>
				</div>
				<div class="form-group">
					<label id="about">About me:</label> 
					<input type="text" id="about"
						   name="about" value="Tell us about yourself..."></input>
				</div>
				<div class="form-group">
					<label id="pictureURL">Profile Image:</label> 
					<input type="text" id="pictureURL"
						   name="pictureURL" value="Please enter a valid image URL"></input>
				</div>
				<input type="submit" class="btn btn-primary" value="Submit" />
				</form>
			</div>
		</div>
	
	
	
	
	
	
	
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
</body>
</html>
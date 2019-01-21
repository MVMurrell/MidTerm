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
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous"> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="profile.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">
<title>Volunteer Site - About </title>
</head>
<body>
	<header> 
	<%@include file="navBar.jsp"%>
		<br>
		<br>
		<br>
		<br>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
			</div>
			<div class="col-sm-6">
			<h3 id="about">About</h3>
			<br>
			<p>Welcome to VolunteerUp! We believe that change starts locally, in our neighborhoods and communities, and that we can do more together than we can do on our own. We wanted to find a way to get together with other people in our community who want to make a positive impact, and empower us to get stuff done. Please browse the projects other users have planned and executed, and if you haven't yet, join our site, create an account, and plan or join your first project!</p>
			<br>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
			</div>
			<div class="col-sm-6">
			<p>We created this website as our midterm project for Skill Distillery's 16 week Java Web Development course. Please visit our github repository for more information on our project: <a href="https://github.com/SkillDistillery/SD18/tree/master/MidtermGroupProject" ><strong>Repository</strong></a></p>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<br>
				<br>
				<h3 id="about">Created By:</h3>
				<br>
				<br>
				<br>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-4" >
			<img src="https://i.pinimg.com/736x/70/24/d5/7024d50af95eaf03f3ac86ef17d8febc.jpg" width="250" height="250">
			<br>
			<strong>Jimmy Fruchterman</strong>
			<br>
			<text>Scrumbum, doesn't use control space enough. "Why not just make another JSP?"</text>
			</div>
			<div class="col-sm-4">
			<img src="https://i.pinimg.com/originals/f4/a9/1c/f4a91c2267d1657a1d2dc9a5dcc1e464.jpg" width="250" height="250">
			<br>
			<strong>Braiden Miller</strong>
			<br>
			<text>DBA, "Can't we do just one more stretch goal?" </text>
			</div>
			<div class="col-sm-4">
			<img src="http://www.comedycentral.co.uk/sites/default/files/styles/image-w-520-h-520-scale/public/cc_uk/galleries/large/2017/01/10/ross_finds_out_rachel_pregnant.jpg?itok=gYlbc7Cn" width="250" height="250">
			<br>
			<strong>Michael Murrell</strong>
			<br>
			<text>Repo depot, chill beats. Straight edge ninja. </text>
			</div>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
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
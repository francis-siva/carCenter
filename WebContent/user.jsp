<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setAttribute("pageTitle", "Account Management"); %>    
<!DOCTYPE html>
<html>
	<head>
		<title>User Page</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./static/customStyle.css" />
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />		
	</head>
	<body>
		<div class="container-fluid">
			<jsp:include page="./header.jsp" />
			<jsp:include page="./navMenu.jsp" />
				<div class="jumbotron jumbotron-fluid">
					<div class="container">
						<% String login = (String) session.getAttribute("login"); login = (login != null) ? login : "User";%>
					    <h1 class="display-4">Welcome <%= login %></h1>
						<p class="lead">
							Latest activity.<br />
							Member since 2017
						</p>
				  	</div>
				</div>
				<div class="mx-auto" style="width: 20em;">
					<button type="button" class="btn btn-secondary btn-lg btn-block btn_user_option">My Deals</button>
					<button type="button" class="btn btn-secondary btn-lg btn-block btn_user_option">My Account</button>
				</div>
		</div>
	<jsp:include page="./footer.jsp" />
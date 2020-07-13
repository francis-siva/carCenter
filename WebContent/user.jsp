<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
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
			<div class="row">
				<div class="col-6 px-auto">
					<h1>Account Management</h1>
				</div>
				<div class="col-3 pt-2 px-2">
					<span class="badge badge-pill badge-light float-right">
						<% //LocalTime time = LocalTime.now(); out.println(time.format(DateTimeFormatter.ofPattern("HH:mm")));
						LocalDateTime localDateTime = LocalDateTime.now(); out.println(localDateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));%>
					</span>
				</div>
				<div class="col-3 pl-5">
					<!--<a href="/signIn?state=logOff" title="Quit" class="float-right">-->
					<a href="<%= request.getContextPath() %>/signIn?state=logOff" title="Quit" class="float-right">
						<svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-power" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						  <path fill-rule="evenodd" d="M5.578 4.437a5 5 0 1 0 4.922.044l.5-.866a6 6 0 1 1-5.908-.053l.486.875z"/>
						  <path fill-rule="evenodd" d="M7.5 8V1h1v7h-1z"/>
						</svg>
					</a>
				</div>			
			</div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<% 	boolean isConnected = false;
	
	/* Verification if a session exist */
	if(session.getAttribute("isConnected") != null) {
		isConnected = (boolean) session.getAttribute("isConnected");
	}
	
	/* If session is here, then send the profile HomePage */
   	if(isConnected) {System.out.println("pathT: "+ request.getContextPath());
		request.getRequestDispatcher("/signIn").forward(request, response);
   	}
   	else {
%>
		<!DOCTYPE html>
		<html>
			<head>
				<title>Home Page - carCenter</title>
				<meta charset="UTF-8" />
				<link rel="stylesheet" href="./static/customStyle.css" />
				<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />		
			</head>
			<body>
				<div class="container-fluid">
					<header>
						<h1>Sign in to CarCenter</h1>
					</header>			
					<div class="mx-auto" style="width: 200px;">
						<form action="./signIn" method="post" >
							<label for="login">Login :
								<input type="text" name="login" id="login" placeholder="Your login" autofocus class="form-control" />
							</label>
							<br />
							<label for="pwd">Password :
								<input type="password" name="pwd" id="pwd" placeholder="Your password" class="form-control" />
							</label>
							<br />
							<input type="submit" class="btn btn-outline-info" name="Valid" value="Sign in" />
						</form>
						<div>
							<a href="./signUp.jsp">Sign up</a>
						</div>
					</div>
				</div>
			<jsp:include page="./footer.jsp" />
  <%}%>
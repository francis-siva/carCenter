<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Register Page - carCenter</title>
		<meta charset="UTF-8" />
		<link rel="stylesheet" href="./static/customStyle.css" />		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />		
	</head>
	<body>
		<div class="container-fluid">
			<h1>Sign up to CarCenter</h1>
				<div class="mx-auto" style="width: 200px;">
					<form action="signUp" method="post" >
						<label for="login">Login :
							<input type="text" name="login" id="login" placeholder="Enter your login" autofocus class="form-control" />
						</label>
						<br />
						<label for="pwd">Password :
							<input type="password" name="pwd" id="pwd" placeholder="Enter your secret code" class="form-control" />
						</label>
						<br />
						
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" name="userProfile" id="userProfile1" value="Buyer" checked class="custom-control-input" />
						  <label for="userProfile1" class="custom-control-label">
						    Buyer
						  </label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" name="userProfile" id="userProfile2" value="Dealer" class="custom-control-input" />
						  <label for="userProfile2" class="custom-control-label">
						    Dealer
						  </label>
						</div>
						<br />
						<input type="submit" class="btn btn-outline-primary" name="Register" value="Create account" />
					</form>
					<div>
						<a href="./index.jsp">Sign in</a>
					</div>
				</div>
		</div>
	<jsp:include page="./footer.jsp" />
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
			<header>
				<h1>Sign up to CarCenter</h1>
			</header>
			<%
			String nameValue = "", loginValue = "", userProfile = "", userProfile1 = "", userProfile2 = "";
			
			if(request.getAttribute("userWarningMsg") != null) {				
				String warningMsg = (String) request.getAttribute("userWarningMsg");
				
				String name = (String) request.getParameter("name");				
				if(name != null && name.trim().length() > 0) { nameValue = "value=\""+ name +"\"";}
				
				String login = (String) request.getParameter("login");
				if(login != null && login.trim().length() > 0) { loginValue = "value=\""+ login +"\"";}
				
				userProfile = (String) request.getParameter("userProfile");				
			%>
			<div>
				<div class="font-italic alert alert-warning alert-dismissible fade show" role="alert">
			  		<%= warningMsg %>
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
			</div>
			<%
			}
			%>
				<div class="mx-auto" style="width: 200px;">
					<form action="./signUp" method="post" >
						<label for="name">Name :
							<input type="text" name="name" id="name" placeholder="Enter your name" <%= nameValue %> autofocus class="form-control" />
						</label>
						<br />
						<label for="login">Login :
							<input type="text" name="login" id="login" placeholder="Enter your login" <%= loginValue %> class="form-control" />
						</label>
						<br />
						<label for="pwd">Password :
							<input type="password" name="pwd" id="pwd" placeholder="Enter your secret code" class="form-control" />
						</label>
						<br />
						
						<%
						//Radio button value checked depending user's previous selection
						if(userProfile != null && userProfile.trim().length() > 0) {
							if("Buyer".equals(userProfile)) { userProfile1 = "checked";}
						%>
								<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" name="userProfile" id="userProfile1" value="Buyer" <%= userProfile1 %> class="custom-control-input" />
								  <label for="userProfile1" class="custom-control-label">
								    Buyer
								  </label>
								</div>
						
							<% if("Dealer".equals(userProfile)) { userProfile2 = "checked";} %>
								<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" name="userProfile" id="userProfile2" value="Dealer" <%= userProfile2 %> class="custom-control-input" />
								  <label for="userProfile2" class="custom-control-label">
								    Dealer
								  </label>
								</div>
						<%
						}
						//Default value checked (Buyer) on radio button
						else {
						%>
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
						<%
						}%>
						<br />
						<input type="submit" class="btn btn-outline-primary" name="Register" value="Create account" />
					</form>
					<div>
						<a href="./index.jsp">Sign in</a>
					</div>
				</div>
		</div>
	<jsp:include page="./footer.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model.User" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Admin Page - carCenter</title>
		<meta charset="UTF-8" />
		<link rel="stylesheet" href="./static/customStyle.css" />		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />		
	</head>
	<body>
		<div class="container-fluid">
			<h1>Account Management</h1>
				<jsp:include page="./navMenu.jsp" />
				<div class="mx-auto" style="width: 200px;">
					<h2>Members' list</h2>
					<%					
						User[] users = (User[]) request.getAttribute("user");
						for(int i = 0; i < users.length; i++) { %>
							<p><%out.println(i + ") " + users[i].getName() + " [" + users[i].getProfilType() + "]");%></p>
					<%	}%>
				</div>
		</div>
	<jsp:include page="./footer.jsp" />
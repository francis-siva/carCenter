<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model.User" %>
<% 	User[] users = (User[]) session.getAttribute("user");
	request.setAttribute("pageTitle", "Admin Management"); %>
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
			<jsp:include page="./header.jsp" />
			<jsp:include page="./navMenu.jsp" />
				<h2>Members' list</h2>
				<table class="table table-striped table-bordered">
				  	<thead>
			    		<tr>
					      	<th scope="col">#</th>
					      	<th scope="col">User</th>
					      	<th scope="col">Profile</th>
				    	</tr>
				  	</thead>
				  	<tbody>
				<%	for(int i = 0; i < users.length; i++) { %>
						<tr>
					      <th scope="row"><%= i%>)</th>
					      <td><%= users[i].getName().toUpperCase()%></td>
					      <td><%= users[i].getProfileType()%></td>
						</tr>						      
				<%	}%>
					</tbody>
				</table>
		</div>
	<jsp:include page="./footer.jsp" />
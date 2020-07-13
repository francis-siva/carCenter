<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setAttribute("pageTitle", "Our Store"); %>
<!DOCTYPE html>
<html>
	<head>
		<title>CarStore Page - carCenter</title>
		<meta charset="UTF-8" />
		<link rel="stylesheet" href="./static/customStyle.css" />
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />		
	</head>
	<body>
		<div class="container-fluid">
			<jsp:include page="./header.jsp" />
			<jsp:include page="./navMenu.jsp" />
				<table class="table table-hover">
					<thead>
						<tr>
						    <th scope="col">#</th>
						    <th scope="col">Car</th>
						    <th scope="col">Model</th>
						    <th scope="col">Detail</th>						    
						</tr>
					</thead>
					<tbody>
				  		<tr>
						    <th scope="row">1</th>
   						    <td>@mdo</td>
						    <td>Mercedes-Benz SLS AMG</td>
						    <td>Description1 Owner</td>
				  		</tr>
				  		<tr>
						    <th scope="row">2</th>
   						    <td>@fat</td>
						    <td>FERRARI 360 Modena</td>
						    <td>Description2 Owner</td>
					  	</tr>
					  	<tr>
						    <th scope="row">3</th>
						    <td>@fat</td>
						    <td>Maserati GranTurismo</td>
						    <td>Description3 Owner</td>
					  	</tr>
					  	<tr>
						    <th scope="row">4</th>
						    <td>@pic</td>
						    <td>Toyota Supra</td>
						    <td>Description3 Owner</td>
					  	</tr>
					  	<tr>
						    <th scope="row">5</th>
						    <td>@pic</td>
						    <td>Nissan Skyline GT-R R34</td>
						    <td>Description3 Owner</td>
					  	</tr>
					  	<tr>
						    <th scope="row">6</th>
						    <td>@pic</td>
						    <td>Mazda RX7</td>
						    <td>Description3 Owner</td>
					  	</tr>
					</tbody>
				</table>
		</div>
	<jsp:include page="./footer.jsp" />
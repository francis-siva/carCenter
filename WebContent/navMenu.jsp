<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  boolean isAdmin = (session.getAttribute("isAdmin") != null &&
	Boolean.TRUE.equals((boolean)session.getAttribute("isAdmin"))); %>    
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="#">NavMenu</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <a class="nav-item nav-link active" href="./user.jsp">Home <% if(!isAdmin) { %><span class="sr-only">(current)</span><% } %></a>
      <% if(isAdmin) { %>
	      <a class="nav-item nav-link" href="./admin.jsp">Admin <span class="sr-only">(current)</span></a>
      <% } %>
	      <a class="nav-item nav-link" href="./store.jsp">Store</a>
	      <a class="nav-item nav-link" href="#">Pricing</a>
	      <a class="nav-item nav-link" href="#">Upcoming-Plans</a>
	    </div>
	  </div>
	</nav>
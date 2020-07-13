<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
<% 	String pageTitle = (String) request.getAttribute("pageTitle");
	//LocalTime time = LocalTime.now(); out.println(time.format(DateTimeFormatter.ofPattern("HH:mm")));
	LocalDateTime localDateTime = LocalDateTime.now();%>
<header>
	<div class="row">					
		<div class="col-6 px-auto">
			<h1><%= pageTitle %></h1>
		</div>
		<div class="col-3 pt-2 px-2">
			<span class="badge badge-pill badge-light float-right">
				<%= localDateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))%>
			</span>
		</div>
		<div class="col-3 pl-5">
			<a href="<%= request.getContextPath() %>/signIn?state=logOff" title="Quit" class="float-right">
				<svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-power" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
				  <path fill-rule="evenodd" d="M5.578 4.437a5 5 0 1 0 4.922.044l.5-.866a6 6 0 1 1-5.908-.053l.486.875z"/>
				  <path fill-rule="evenodd" d="M7.5 8V1h1v7h-1z"/>
				</svg>
			</a>
		</div>			
	</div>
</header>
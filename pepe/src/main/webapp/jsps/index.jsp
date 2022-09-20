<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.util.List" %>

<% 
List<String> deps = (List<String>) request.getAttribute("departamentos");

for(String dep: deps){
	%>
	<div class="dep">
		<%= dep %>
	</div>		
<% } %>

HOLA
</body>
</html>
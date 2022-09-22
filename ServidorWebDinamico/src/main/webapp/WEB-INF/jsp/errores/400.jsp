<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Error</title>
		<style>
			#main{
				width: 100%;
				display: flex;
				justify-content: center;
				align-content: center;
			}
			
			#contenedor-error{
				width : 500px;
				display: grid;
				grid-template-rows : auto 1fr;
				
			}
			
			
		
		</style>
	</head>
<body>
	
	<section id="main">
		<div id="contenedor-error">
			<h1>Error controlado:</h1>
			<p>Motivo: <%= request.getAttribute("motivoDeError")%></p>
		</div>
	</section>
	
	
	

</body>
</html>
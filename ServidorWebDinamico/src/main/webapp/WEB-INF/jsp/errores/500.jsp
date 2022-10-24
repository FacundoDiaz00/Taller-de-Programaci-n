<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>   
		<title>Error</title>
		<style>

			#contenedor{
				min-height: calc(100vh - 141.5px);
				overflow:hidden;
			}
			
			#contenedor-error{
				z-index: 2;
				width : 100%;
				display: grid;
				background: white;
				padding: 10px 0 0 10px;
				grid-template-rows : auto 1fr;
				
			}
			
			
			
			
		
		</style>
	</head>
<body>
	
	<jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
	
	<section id="contenedor" style="background-image: url('img/BG500.gif'); background-size: cover;">
		<div id="contenedor-error">
			<h1>Error inesperado:</h1>
		</div>

		
	</section>
	
	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/> 
	

</body>
</html>
<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- List<String> departamentos
- List<String> categorias
- List<DTActividadTuristica> actividades (ya deben estar filtradas)
- List<DTPaquete> paquetes (ya deben estar filtradas)[Falta filtrar por ambos criterios]

 --%>

<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="css/index.css">

	
</head>
<body style="margin:0;padding:0;height:100%;">

    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

    <!-- <section id="contenedor"> -->
	<div>
		<h1 style="text-align: center;justify-content: center; display: flex;margin-bottom: 40%;margin:10%;margin-top:20%">Bienvenido a TurismoUY!</h1>
	</div>



    <!-- </section> -->
    <div style="bottom: 0;position: fixed; width: 100%">
		<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
	</div>
</body>
</html>
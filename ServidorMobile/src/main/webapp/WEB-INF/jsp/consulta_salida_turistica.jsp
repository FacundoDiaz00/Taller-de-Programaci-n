
<%@page import="utils.Utile"%>
<%@page import="publicar.usuarioturisticasservice.DtTurista"%>
<%@page import="publicar.actividadesturisticasservice.DtSalidaTuristicaDetalle"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>

	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>  	    
    <link rel="stylesheet" href="css/consulta_de_salida_turistica.css">
    
</head>
<body>
<main>

    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

    <section id="contenedor">

		<%
		p
		 DtSalidaTuristicaDetalle infoSalida = (DtSalidaTuristicaDetalle)request.getAttribute("datosSalida"
		%>

        <div id="titulo">
            <h1>Consulta de Salida turística</h1>
        </div>

        <div id="info-salida">

			<h2><%=t( infoSalida.getNombre(%></h2>
            <h6>Creado el <%=t( infoSalida.getFechaAltaStr(%></h6>
            <div id="info-general-imagen">                             
			    <img src="<%=t(Utile.obtenerUrlParaImagen(infoSalida.getImg(%>" class="img-fluid rounded-start paquetes"  style="margin: 10px" alt="">
            </div>



            <div id="resto-de-la-info-actividad">


                <div class="div-doble" id="FechaYhoraSalida">
                    <h5 class="label">Fecha y hora de partida: </h5>
                    <p><%=infoSalida.getFechaHoraSalidaStr()%> </p>
                </div>
                <div class="div-doble" id="Costo">
                    <h5 class="label">Capacidad de turistas: </h5>
                    <p><%= infoSalida.getCantMaxTuristas() %></p>
                </div>
                <div class="div-doble" id="Cuidad">
                    <h5 class="label">Lugar: </h5>
                    <p><%= infoSalida.getLugarSalida() %></p>
                </div>

            </div>

        </div>
        
        

    </section>
</main>


 <jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>

</body>
</html>
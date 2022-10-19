
<%@page import="logica.datatypes.DTSalidaTuristicaDetalle"%>
<%@page import="logica.datatypes.DTSalidaTuristica"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>

	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>  	    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consulta_de_salida_turistica.css">
    
</head>
<body>
<main>

    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>


    <section id="contenedor">
        <jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>

		<% DTSalidaTuristicaDetalle infoSalida = (DTSalidaTuristicaDetalle)request.getAttribute("datosSalida");%>

        <div id="titulo">
            <h1>Consulta de Salida turistica</h1>
        </div>

        <div id="info-salida">


            <div id="info-general-imagen">            
                 <% 
		            String pathSalida = "";
					if (infoSalida.getImg() == null) {
						pathSalida += "/noFoto.png";
					} else {
						pathSalida += infoSalida.getImg().getPath();
					}							
					%>
				    <img src="${pageContext.request.contextPath}/img<%=pathSalida%>" class="img-fluid rounded-start paquetes"  style="margin: 10px" alt="">
            </div>

            <div id="info">
                <h2><%= infoSalida.getNombre() %></h2>
                <h6>Creado el <%= infoSalida.getFechaAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ")) %></h6>
                <h5 id="label-acciones-relacionadas">Acciones relacionadas:</h5>
                <ul>
                    <li><a href="InscribiseASalida?id=<%=infoSalida.getNombre()%>">Inscribirse a la salida</a></li>
                </ul>
            </div>

            <div id="resto-de-la-info-actividad">


                <div class="div-doble" id="FechaYhoraSalida">
                    <h5 class="label">Fecha y hora de partida: </h5>
                    <p><%=infoSalida.getFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ' a las ' HH:mm"))%> </p>
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
        
        <div id="contenedor-actividades">
        
        	<div class="card" id="contenedor-actividad" style="max-width: 800px;">
	        	<h2 class="card-title">Actividad</h2>
	        	<div class="card mb-3 card-actividad" style="max-width: 800px;">
	                    <div class="row g-0">
	                        <div class="col-md-4 img-contain">
	                        	<% 
		            			String path = "";
								if (infoSalida.getDtActividad().getImg() == null) {
									path += "/noFoto.png";
								} else {
									path += infoSalida.getDtActividad().getImg().getPath();
								}							
								%>
	                            <img src="${pageContext.request.contextPath}/img<%=path%>" class="img-fluid rounded-start">
	                            <!-- Falta el manejo de foto de la verdadero paquete-->
	                        </div>
	                        <div class="col-md-8">
	                            <div class="card-body card-actividad">
	                            	<div>
	                            		<h5 class="card-title"><%= infoSalida.getDtActividad().getNombre()%> </h5>
	                                <p class="card-text descripcion-paquete"><%= infoSalida.getDtActividad().getDescripcion() %> </p>
	                            	</div>                            
	                                <div class="botonera">
	                                    <a href="ConsultaActividad?id=<%=infoSalida.getDtActividad().getNombre()%>" class="btn btn-primary">Ver mas</a>
	                                </div>
	
	                            </div>
	                        </div>
	                    </div>
	                </div>
        	</div>
        </div>
        

    </section>

    <footer id="sticky-footer" class="py-4 bg-dark text-white-50">
        <div class="container text-center">
        <span class="" style="font-size: 25px; color: Grey;">
          <i class="fas fa-route"></i>
        </span>
            <small>Copyright &copy; Grupo 16: Valentina Alaniz, Facundo Diaz, Agustín Martínez, Daniel Padrón y Agustín Recoba</small>
        </div>
    </footer>
</main>


 <jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>

</body>
</html>
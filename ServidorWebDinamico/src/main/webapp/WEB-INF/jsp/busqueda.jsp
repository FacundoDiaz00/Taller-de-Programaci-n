<%@page import="utils.Utile"%>
<%@page import="publicar.usuarioturisticasservice.DtProveedor"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>
<%@page import="publicar.usuarioturisticasservice.DtTurista"%>

<%@page import="publicar.maestroservices.DtActividadTuristica"%>
<%@page import="publicar.maestroservices.DtResultadoBusqueda"%>

<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="css/consulta_de_usuario.css">
    <title>Turismo UY</title>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
	<section id="contenedor">
		<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
		<div id="contenedor-items">
			
            <!--Actividades-->

            <div class="card" id="contenedor-actividades-turisticas">
                <div class="header-card-with-button" style="margin-left: 30px">
                	<h2 class="card-title header_usuarios">Busqueda:</h2>
            	</div>
                
                <div>
					<%
					DtResultadoBusqueda res = (DtResultadoBusqueda)request.getAttribute("resultadosBusqueda");
					for(DtActividadTuristica actividad: res.getActividades()){
					%>
						<div class="card mb-3" style="max-width: 800px;">
				                    <div class="row g-0">
				                        <div class="col-md-4 img-contain">
<%-- 				                            <img src="<%=Utile.obtenerUrlParaImagen(actividad.getImg())%>" class="img-fluid rounded-start">
 --%>				                            <!--  Falta el manejo de foto de la verdadera actividad -->
				                        </div>
				                        <div class="col-md-8">
				                            <div class="card-body">
				                            <div  style="justify-content: space-between; display: flex">
				                                  <h5 class="card-title"><%=actividad.getNombre()%></h5>
				                                   <%-- <%
				                                   if(session.getAttribute("usuarioLogeado") != null && session.getAttribute("usuarioLogeado") instanceof DtTurista ){ 
				                                   			                                	String idDepartamento = (String)request.getAttribute("idDepartamento");
				                                   			                                	if(actividadesFav.get(actividad)){
				                                   %>
						                                	<a href="index?marcarComoFav=<%=true%>&nomAct=<%=actividad.getNombre()%>&idDepartamento=<%=idDepartamento%>" ><i class="fa-solid fa-star fa-2x" style="color: #ffc700"></i></a>
						                                
						                                <%
						                                						                                } else {
						                                						                                %>
						                                
						                                	<a href="index?marcarComoFav=<%=true%>&nomAct=<%=actividad.getNombre()%>&idDepartamento=<%=idDepartamento%>"><i class="fa-solid fa-star fa-2x" style="color: #CCD1D1"></i></a>
						                                	
						                                <%
						                                							                                } 
						                                							                                			                                	}
						                                							                                %> --%>
					                                	
				                            </div>        	
				                                <p class="card-text descripcion-actividad"><%=actividad.getDescripcion()%></p>
				                               
				                                
				                                <div class="botonera">
				                                    <a href="ConsultaActividad?id=<%=actividad.getNombre()%>" class="btn btn-primary">Ver más</a>
				                                </div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
					<%} %>
                </div>
                	
                

            </div>


    </section>
	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
	
</body>
</html>
<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- List<String> departamentos
- List<String> categorias
- List<DTActividadTuristica> actividades (ya deben estar filtradas)
- List<DTPaquete> paquetes (ya deben estar filtradas)[Falta filtrar por ambos criterios]

 --%>


<%@page import="logica.datatypes.DTPaquete"%>
<%@page import="logica.datatypes.DTActividadTuristica"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">

	
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

    <section id="contenedor">
		
	<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
		
        <div id="contenedor-items">

            <!--Actividades-->

            <div class="card" id="contenedor-actividades-turisticas">
                <h2 class="card-title">Actividades</h2>
                
                	<% 
					List<DTActividadTuristica> actividades = (List<DTActividadTuristica>) request.getAttribute("actividades");
					
					for(DTActividadTuristica actividad: actividades){
						%>
							<div class="card mb-3" style="max-width: 800px;">
			                    <div class="row g-0">
			                        <div class="col-md-4 img-contain">
			                            <img src="${pageContext.request.contextPath}/img/noFoto.png" class="img-fluid rounded-start">
			                            <!-- Falta el manejo de foto de la verdadera actividad -->
			                        </div>
			                        <div class="col-md-8">
			                            <div class="card-body">
			                                <h5 class="card-title"><%= actividad.getNombre() %></h5>
			                                <p class="card-text descripcion-actividad"><%= actividad.getDescripcion() %></p>
			                                <div class="botonera">
			                                    <a href="ConsultaActividadServlet?id=<%=actividad.getNombre()%>" class="btn btn-primary">Ver mas</a>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
						
					<% } %>
                

            </div>


            <!--Paquetes-->

            <div class="card" id="contenedor-paquetes">
                <h2 class="card-title">Paquetes</h2>

			<% 
				List<DTPaquete> paquetes = (List<DTPaquete>) request.getAttribute("paquetes");
					
				for(DTPaquete pack: paquetes){
			%>


                <div class="card mb-3" style="max-width: 800px;">
                    <div class="row g-0">
                        <div class="col-md-4 img-contain">
                            <img src="${pageContext.request.contextPath}/img/noFoto.png" class="img-fluid rounded-start">
                            <!-- Falta el manejo de foto de la verdadero paquete-->
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title"><%= pack.getNombre()%> </h5>
                                <p class="card-text descripcion-paquete"><%= pack.getDescrpicion()%> </p>
                                <div class="botonera">
                                    <a href="ConsultaPaqueteServlet?id=<%=pack.getNombre()%>" class="btn btn-primary">Ver mas</a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                
           <% } %>

            </div>
        </div>



    </section>

    <jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
</body>
</html>
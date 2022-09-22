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

		<% 
			String idCategoriaMarcada = (String) request.getAttribute("idCategoria");
			String idDepartamentoMarcada = (String) request.getAttribute("idDepartamento");
		%>
		
		

        <div id="menu-lateral">
        
            <!--Acciones generales-->
            <div class="card list-group" id="opciones">
                <h5 class="card-title">Acciones</h5>
                <a href="consulta_de_usuario.html" class="list-group-item">Ver usuarios</a>
                <a href="alta_de_actividad_turistica.html" class="list-group-item">Crear Actividad turistica</a>
            </div>

            <!--Categoroias-->
            <div class="card list-group" id="filtro-categoria">
                <h5 class="card-title">Categorias</h5>
                <%
                	List<String> categoiras = (List<String>) request.getAttribute("categorias"); 
                    for(String cat : categoiras){
                    	if( idCategoriaMarcada != null && idCategoriaMarcada.equals(cat) ){   	
                %>
                			<a href="index?idCategoria=<%= cat %>" class="list-group-item active"><%= cat %></a>	
                
                <% 		} else { %>
                			<a href="index?idCategoria=<%= cat %>" class="list-group-item"><%= cat %></a>	
                
                <% 		}
                    } %>
            </div>

            <!--Departametos-->
            <div class="card list-group" id="filtro-departamentos">
                <h5 class="card-title">Departamentos</h5>
               
					<% 
					List<String> departamentos = (List<String>) request.getAttribute("departamentos");
					
					for(String dep: departamentos){
						if( idDepartamentoMarcada != null && idDepartamentoMarcada.equals(dep) ){ 
						%>
							<a href="index?idDepartamento=<%= dep %>" class="list-group-item active"><%= dep %></a>	
					  <%} else {%>
					  		<a href="index?idDepartamento=<%= dep %>" class="list-group-item"><%= dep %></a>	
				<%		}
					}	%>              
                
            </div>
        </div>

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
                                    <a href="consulta_de_paquete.html" class="btn btn-primary">Ver mas</a>
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
<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- List<String> departamentos
- List<String> categorias
- List<DTActividadTuristica> actividades (ya deben estar filtradas)
- List<DTPaquete> paquetes (ya deben estar filtradas)[Falta filtrar por ambos criterios]

 --%>

<%@page import="publicar.usuarioturisticasservice.DtPaquete"%>
<%@page import="utils.Utiles"%>

<%@page import="publicar.actividadesturisticasservice.DtActividadTuristica"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>

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
	<% 
				String idCategoriaMarcada = (String) request.getAttribute("idCategoria");
				String idDepartamentoMarcada = (String) request.getAttribute("idDepartamento");
	%>
<%-- 	<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/> --%>
		<div class="container btn-group " style="display: flex;justify-content: space-around;margin-top:10px;margin-bottom:10px">
			<div class="dropdown">
			  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
			    Departamentos
			  </button>
			  
			  <ul class="dropdown-menu">
			  <% 
						List<String> departamentos = (List<String>) request.getAttribute("departamentos");
						
						for(String dep: departamentos){
							if( idDepartamentoMarcada != null && idDepartamentoMarcada.equals(dep) ){ 
							%>
								<li><a href="ConsultaActividad?idDepartamento=<%= dep %>" class="  active dropdown-item"><%= dep %></a>	</li>
						  <%} else {%>
						  		<li><a href="ConsultaActividad?idDepartamento=<%= dep %>" class=" dropdown-item"><%= dep %></a></li>
					<%		}
						}	%>
			  </ul>
			</div>
			<div class="dropdown">
			  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
			    Categorías
			  </button>
			  
			  <ul class="dropdown-menu">
			  <% 
						List<String> categorias = (List<String>) request.getAttribute("categorias");
						
						for(String cat: categorias){
							if( idCategoriaMarcada != null && idCategoriaMarcada.equals(cat) ){ 
							%>
								<li><a href="index?idCategoria=<%= cat %>" class="  active dropdown-item"><%= cat %></a>	</li>
						  <%} else {%>
						  		<li><a href="index?idCategoria=<%= cat %>" class=" dropdown-item"><%= cat %></a></li>
					<%		}
						}	%>
			  </ul>
			</div>
	
		</div>
		
		
        <div id="contenedor-items">
            <!--Actividades-->

            <div class="card"> <!-- style="display: flex;justify-content: center;" -->
                <h2 class="card-title">Actividades</h2>
                
                	<% 
					List<DtActividadTuristica> actividades = (List<DtActividadTuristica>) request.getAttribute("actividades");
					
                	if (actividades.isEmpty()) {%>
                		<span>No hay actividades para mostrar aquí.</span>
               		<% } else {               	
						for(DtActividadTuristica actividad: actividades){
							%>
							<div class="card mb-3" style="max-width: 800px;">
			                    <div class="row g-0" style="margin: 20px">
			                        <div class="col-md-4 img-contain">
			                        	<% 
				            			String path = "";
										if (actividad.getImg() == null) {
											path += "/noFoto.png";
										} else {
											path += actividad.getImg().getPath();
										}							
										%>
			                            <img src="<%=Utiles.obtenerUrlParaImagen(actividad.getImg())%>" class="img-fluid rounded">
			                            <!--  Falta el manejo de foto de la verdadera actividad -->
			                        </div>
			                        <div class="col-md-8">
			                            <div class="card-body">
			                                <h5 class="card-title"><%= actividad.getNombre() %></h5>
			                                <p class="card-text descripcion-actividad"><%= actividad.getDescripcion() %></p>
			                                <div class="botonera">
			                                    <a href="ConsultaActividad?id=<%=actividad.getNombre()%>&listar=false" class="btn btn-primary">Ver más</a>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
						
					<% }
                	}							
					%>
                

            </div>
        </div>



    <!-- </section> -->
    <div style="bottom: 0;position: fixed;">
		<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
	</div>
</body>
</html>
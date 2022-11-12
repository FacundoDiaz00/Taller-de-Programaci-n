<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- List<String> departamentos
- List<String> categorias
- List<DTActividadTuristica> actividades (ya deben estar filtradas)
- List<DTPaquete> paquetes (ya deben estar filtradas)[Falta filtrar por ambos criterios]

 --%>


<%@page import="publicar.actividadesturisticasservice.DtSalidaTuristica"%>
<%@page import="utils.Utiles"%>

<%@page import="publicar.actividadesturisticasservice.DtMapActividadSalidaTuristicaCollection"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>

<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="css/listar_salidas.css">

	
</head>
<body style="">

    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
		
        <div id="contenedor-items">


            <div class="card" style="margin: 4px"> <!-- style="display: flex;justify-content: center;" -->
                <h2 class="card-title">Salidas</h2>
                
                	<% 
                	DtMapActividadSalidaTuristicaCollection datoSalida = (DtMapActividadSalidaTuristicaCollection) request.getAttribute("datosSalida");
					
                	if (datoSalida.getMapSalidas() == null || datoSalida.getMapSalidas().getEntry().size() == 0 ) {%>
                		<span>No hay salidas para mostrar aquí.</span>
               		<% } else {
               			
               			for(DtMapActividadSalidaTuristicaCollection.MapSalidas.Entry entrySalida : datoSalida.getMapSalidas().getEntry()){
               				
               				if(entrySalida.getValue() == null || entrySalida.getValue().getSalidas() == null) continue;
               				
               				
               				%>
               				
               				<h4>Actividad: <%= entrySalida.getKey() %></h3>
               				
               				<%
							for(DtSalidaTuristica salida: entrySalida.getValue().getSalidas()){
								%>
								<div class="card mb-3" style="max-width: 800px;margin: 5px">
				                    <div class="row g-0" style="margin: 10px">
				                        <div class="col-md-4 img-contain">
				                        	<% 
					            			String path = "";
											if (salida.getImg() == null) {
												path += "/noFoto.png";
											} else {
												path += salida.getImg().getPath();
											}							
											%>
				                            <img src="<%=Utiles.obtenerUrlParaImagen(salida.getImg())%>" class="img-fluid rounded">
				                            <!--  Falta el manejo de foto de la verdadera actividad -->
				                        </div>
				                        <div class="col-md-8">
				                            <div class="card-body">
				                                <h5 class="card-title"><%= salida.getNombre() %></h5>

				                                <div class="botonera">
				                                    <a href="ConsultaSalida?id=<%=salida.getNombre()%>&listar=false" class="btn btn-primary">Ver más</a>
				                                </div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
							
						<% }
               			}
                	}							
					%>
                

            </div>
        </div>



    <!-- </section> -->
    <div style="bottom: 0 ">
		<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
	</div>
</body>
</html>
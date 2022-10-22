<%-- 

# ATRIBUTOS QUE PRECISA LA PÁGINA



 --%>
<%@page import="logica.datatypes.DTProveedor"%>
<%@page import="logica.datatypes.DTPaquete"%>
<%@page import="logica.datatypes.DTSalidaTuristica"%>
<%@page import="logica.datatypes.DTActividadTuristicaDetalle"%>
<%@page import="logica.datatypes.DTActividadTuristica"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>   
    <link rel="stylesheet" href="css/consulta_actividad_turistica.css">

    
</head>
<body>
<main>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

    <section id="contenedor">

        <jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>

        <div id="titulo">
            <h1>Consulta de actividad</h1>
        </div>


        <div id="info-actividad">
        
        
        	<% DTActividadTuristicaDetalle datosActividad = (DTActividadTuristicaDetalle) request.getAttribute("datosActividad"); %>



            <div id="info-general-imagen">
                <% 
        		String path = "";
				if (datosActividad.getImg() == null) {
					path += "/noFoto.png";
				} else {
					path += datosActividad.getImg().getPath();
				}							
				%>
                <img src="img<%=path%>" alt="">
            </div>

            <div id="info">
                <h2><%= datosActividad.getNombre() %></h2>
                <h6>Creado el <%= datosActividad.getFechaAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ")) %></h6>
                
                <% 
                boolean proveedorLogueado = false;
        		try {
	        		DTProveedor tur = (DTProveedor) session.getAttribute("usuarioLogeado");
	        		proveedorLogueado = tur != null;
        		} catch (Exception e) {
        			// nada
        		}
        		// Muestro el boton si soy turista
        		if (proveedorLogueado) { %>		            
	                <h5 id="label-acciones-relacionadas">Acciones relacionadas:</h5>
	                <ul>
	                    <li><a href="AltaDeSalida?id=<%=datosActividad.getNombre()%>">Crear una salida turística</a></li>
	                </ul>	                
        		<% } %>
                
            </div>

            <div id="resto-de-la-info-actividad">
                <div id="descripcion">
                    <h5 class="">Descripción</h5>
                    <p><%= datosActividad.getDescripcion() %>a</p>
                </div>



                <div class="div-doble" id="Duracion">
                    <h5 class="">Duración: </h5>
                    <p><%= datosActividad.getDuracion() %> horas</p>
                </div>
                <div class="div-doble" id="Costo">
                    <h5 class="">Costo: </h5>
                    <p><%= datosActividad.getCostoPorTurista() %>$</p>
                </div>
                <div class="div-doble" id="Cuidad">
                    <h5 class="">Cuidad: </h5>
                    <p><%= datosActividad.getCuidad() %></p>
                </div>
                <div class="div-doble" id="Departamento">
                    <h5 class="">Departamento: </h5>
                    <p><%= datosActividad.getDepartamento() %></p>
                </div>

                <div id="categorias">
                    <h5 class="">Categorías:</h5>
                    <ul>
                        <% if(datosActividad.getCategorias() != null ){
                        		for(String cat : datosActividad.getCategorias() ) { %>
                        			 <li> <%=cat %></li>
                        <% 		}
                        
                            } else {
                        %>
							<span>Sin categorías</span>                        
                        <%
                        	}%>
                       
                    </ul>
                </div>
            </div>

        </div>

        <div id="contenedor-salidas-paquetes">
            <div id="contenedor-items">
                <div class="card" id="contenedor-salidas-turisticas">
                    <h2 class="card-title">Salidas Turísticas:</h2>

					<%
					
					for(DTSalidaTuristica salida : datosActividad.getSalidas().values()){
					
					%>


                    <div class="card mb-3" style="max-width: 800px;">
                        <div class="row g-0">
                            <div class="col-md-4 img-contain">
                                 <% 
		            			String pathSalida = "";
								if (salida.getImg() == null) {
									pathSalida += "/noFoto.png";
								} else {
									pathSalida += salida.getImg().getPath();
								}							
								%>
				                <img src="img<%=pathSalida%>" class="img-fluid rounded-start"  style="margin: 10px" alt="">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <div class="salidaInfo">
                                        <h5 class="card-title"><%=salida.getNombre() %></h5>
                                        <div><strong>Lugar salida: </strong><%=salida.getLugarSalida() %> </div>
                                        <div><strong>Fecha y hora de partida: </strong><%=salida.getFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ' a las ' HH:mm")) %> </div>
                                        <div><strong>Capacidad de turistas: </strong><%=salida.getCantMaxTuristas()%></div>
                                        <div><strong>Fecha de creación: </strong><%=salida.getFechaAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy ")) %></div>

                                    </div>


                                    <div class="botonera">
                                        <a href="ConsultaSalida?id=<%=salida.getNombre()%>" class="btn btn-primary">Ver más</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <%
					}
                    %>
                    
                    
                </div>

                <div class="card" id="contenedor-paquetes">
                
                
                    <h2 class="card-title">Paquetes:</h2>

					<%
					
					for(DTPaquete pack: datosActividad.getPaquetes().values()){
					
					%>

                    <div class="card mb-3" style="max-width: 800px; margin-right: 20px; margin-top: 15px">
                        <div class="row g-0">
                            <div class="col-md-4 img-contain">

                           
                                <% 
		            			String pathPack = "";
								if (pack.getImg() == null) {
									pathPack += "/noFoto.png";
								} else {
									pathPack += pack.getImg().getPath();
								}							
								%>
				                <img src="img<%=pathPack%>" class="img-fluid rounded-start paquetes"  style="margin: 10px" alt="">
                                
                            </div>
                            <div class="col-md-8">
                                <div class="card-body cards">
                                	<div>
	                                	<h5 class="card-title"><%= pack.getNombre() %></h5>
	                                    <p><%= pack.getDescrpicion() %></p>
                                	</div>
                                    
                                    <div class="botonera">
                                        <a href="ConsultaPaquete?id=<%=pack.getNombre()%>" class="btn btn-primary">Ver más</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <%
					}
                    %>
                    
                    
                </div>
            </div>
        </div>

    </section>

 <jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>    
    <%if(request.getAttribute("exito") != null){ %>
	    <script>
	    	generarMensaje('success', "Operacion completada" , "Se ha realizado un alta de salida satisfactoriamente" , 500);
	    </script>
    <%} %>
 

</main>


</body>
</html>
<%-- 

# ATRIBUTOS QUE PRECISA LA PÁGINA



 --%>
 <%@page import="utils.Utile"%>
<%@page import="publicar.actividadesturisticasservice.DtActividadTuristicaDetalle"%>
<%@page import="publicar.actividadesturisticasservice.EstadoActividadTuristica"%>
<%@page import="publicar.usuarioturisticasservice.DtProveedor"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>
<%@page import="publicar.actividadesturisticasservice.DtPaquete"%>
<%@page import="publicar.actividadesturisticasservice.DtSalidaTuristica"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>   
    <link rel="stylesheet" href="css/consulta_actividad_turistica.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    
</head>
<body>
<main>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

    <section id="contenedor">


        <div id="titulo">
            <h1 style="">Consulta de actividad</h1>
        </div>


        <div id="info-actividad">
        
        
        	<% DtActividadTuristicaDetalle datosActividad = (DtActividadTuristicaDetalle) request.getAttribute("datosActividad"); %>


			<h2><%= datosActividad.getNombre() %></h2>
			 <h6>Creado el <%= datosActividad.getFechaAltaStr() %></h6>
            <div id="info-general-imagen">
                <img src="<%=Utile.obtenerUrlParaImagen(datosActividad.getImg())%>" alt="">
            </div>

            <div id="info">
      
                
               
                <% 
                boolean proveedorLogueado = false;
        		try {
	        		DtProveedor tur = (DtProveedor) session.getAttribute("usuarioLogeado");
	        		proveedorLogueado = tur != null;
        		} catch (Exception e) {
        			// nada
        		}
        		// Muestro el boton si soy turista
        		if (proveedorLogueado && datosActividad.getEstado() == EstadoActividadTuristica.ACEPTADA) { %>		            
	                <h5 id="label-acciones-relacionadas">Acciones relacionadas:</h5>
	                <ul>
	                    <li><a href="AltaDeSalida?id=<%=datosActividad.getNombre()%>">Crear una salida turística</a></li>
	                </ul>	                
        		<% } %>
                
            </div>

            <div id="resto-de-la-info-actividad">
                <div id="descripcion">
                    <h5 class="">Descripción</h5>
                    <p><%= datosActividad.getDescripcion() %></p>
                </div>

                <div class="div-doble" id="Duracion">
                    <h5 class="">Estado: </h5>
                    <% if(datosActividad.getEstado() == EstadoActividadTuristica.ACEPTADA){%>
                    	<p>Confirmada</p>
                    <%} else if(datosActividad.getEstado() == EstadoActividadTuristica.AGREGADA){%>
                    	<p>Agregada sin confirmar</p>
                    <%} else {%>
                    	<p>Rechazada</p>
                    <%}%>
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
                    <h2 class="card-title" style="justify-content: center; display: flex; margin-top:5px;">Salidas Turísticas:</h2>

					<div style="padding:20px">
						<%
						
						if(datosActividad.getSalidas() == null || datosActividad.getSalidas().getEntry().size() == 0){
						%>
							<span style="margin-left: 10px">Sin informacion</span>
						<%
						}else{
						
							for(publicar.actividadesturisticasservice.DtActividadTuristicaDetalle.Salidas.Entry entrySalida : datosActividad.getSalidas().getEntry()){
								DtSalidaTuristica salida = entrySalida.getValue();
							%>
							<div style="margin-top: 5px;margin-bottom: 5px">
									<a href="ConsultaSalida?id=<%=salida.getNombre()%>&listar=false"><%=salida.getNombre() %></a> 
							</div>
		                    <%
							}
						}
		                %>
					</div>
                    
                    
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
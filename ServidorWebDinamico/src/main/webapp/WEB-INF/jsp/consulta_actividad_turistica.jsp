<%-- 

# ATRIBUTOS QUE PRECISA LA PÁGINA



 --%>
 <%@page import="utils.Utiles"%>
<%@page import="publicar.actividadesturisticasservice.DtActividadTuristicaDetalle"%>
<%@page import="publicar.actividadesturisticasservice.EstadoActividadTuristica"%>
<%@page import="publicar.usuarioturisticasservice.DtProveedor"%>
<%@page import="publicar.usuarioturisticasservice.DtUsuario"%>
<%@page import="publicar.actividadesturisticasservice.DtPaquete"%>
<%@page import="publicar.actividadesturisticasservice.DtSalidaTuristica"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="publicar.usuarioturisticasservice.DtTurista" %>
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
        
        
        	<% DtActividadTuristicaDetalle datosActividad = (DtActividadTuristicaDetalle) request.getAttribute("datosActividad"); %>



            <div id="info-general-imagen">
                <img src="<%=Utiles.obtenerUrlParaImagen(datosActividad.getImg())%>" alt="">
            </div>

            <div id="info">
            	<div>
            		<h2><%= datosActividad.getNombre() %></h2>
            		<% 
            		int cantFavoritos = (int) request.getAttribute("cantFavoritos");
					 if(session.getAttribute("usuarioLogeado") != null && session.getAttribute("usuarioLogeado") instanceof DtTurista ){ 
						boolean esFavoritaActividad = (boolean)request.getAttribute("esFavoritaActividad");
                     	String idDepartamento = (String)request.getAttribute("idDepartamento");
                     	if(esFavoritaActividad){
         
                      %>
                      	<a href="ConsultaActividad?marcarComoFav=<%=true%>&id=<%=datosActividad.getNombre()%>" ><i class="fa-solid fa-star fa-2x" style="color: yellow"></i></a>
                      
                      <%} else { %>
                      
                      	<a href="ConsultaActividad?marcarComoFav=<%=true%>&id=<%=datosActividad.getNombre()%>"><i class="fa-regular fa-star fa-2x"></i></a>
                      	
                      <% }
                     	
                     	}%>
                    <p>favorito de <%=cantFavoritos%> persona/s </p>
            	</div>
                
                <h6>Creado el <%= datosActividad.getFechaAltaStr() %></h6>
                <% 
                boolean proveedorLogueado = false;
        		try {
	        		DtProveedor tur = (DtProveedor) session.getAttribute("usuarioLogeado");
	        		proveedorLogueado = tur != null;
        		} catch (Exception e) {
        			// nada
        		}
        		// Muestro el boton si soy proveedor
        		if (proveedorLogueado && datosActividad.getEstado() == EstadoActividadTuristica.ACEPTADA) { %>		            
	                <h5 id="label-acciones-relacionadas">Acciones relacionadas:</h5>
	                <ul>
<%-- 	                    <li><a href="AltaDeSalida?id=<%=datosActividad.getNombre()%>">Crear una salida turística</a></li>
	                    <%
	                    	boolean sePuedeFinalizar = (boolean)request.getAttribute("sePuedeFinalizar");
							if(sePuedeFinalizar){
							%>
	                    <li><a href="ConsultaActividad?finalizar=<%=true%>" class="btn btn-primary">Finalizar</a></li>
	                  <%}%> --%>
	                </ul>	                
        		<% } %>
                
            </div>

            <div id="resto-de-la-info-actividad">
                <div id="descripcion">
                    <h5 class="">Descripción</h5>
                    <p><%= datosActividad.getDescripcion() %>a</p>
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
                <%if(datosActividad.getUrlVideo() != null && datosActividad.toString().length() > 0 ){ 
                	String url = (String)request.getAttribute("url");%>
                <%=url %>
                <%} %>
            </div>

        </div>

        <div id="contenedor-salidas-paquetes">
            <div id="contenedor-items">
                <div class="card" id="contenedor-salidas-turisticas">
                    <h2 class="card-title">Salidas Turísticas:</h2>

					<%
					
					if(datosActividad.getSalidas() == null || datosActividad.getSalidas().getEntry().size() == 0){
					%>
						<span style="margin-left: 10px">Sin informacion</span>
					<%
					}else{
					
						for(publicar.actividadesturisticasservice.DtActividadTuristicaDetalle.Salidas.Entry entrySalida : datosActividad.getSalidas().getEntry()){
							DtSalidaTuristica salida = entrySalida.getValue();
						%>
	
	
	                    <div class="card mb-3" style="max-width: 800px;">
	                        <div class="row g-0">
	                            <div class="col-md-4 img-contain">
	                                 
					                <img src="<%=Utiles.obtenerUrlParaImagen(salida.getImg())%>" class="img-fluid rounded-start"  style="margin: 10px" alt="">
	                            </div>
	                            <div class="col-md-8">
	                                <div class="card-body">
	                                    <div class="salidaInfo">
	                                        <h5 class="card-title"><%=salida.getNombre() %></h5>
	                                        <div><strong>Lugar salida: </strong><%=salida.getLugarSalida() %> </div>
	                                        <div><strong>Fecha y hora de partida: </strong><%=salida.getFechaHoraSalidaStr() %> </div>
	                                        <div><strong>Capacidad de turistas: </strong><%=salida.getCantMaxTuristas()%></div>
	                                        <div><strong>Fecha de creación: </strong><%=salida.getFechaAltaStr() %></div>
	
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
					}
	                    %>
                    
                    
                </div>

                <div class="card" id="contenedor-paquetes">
                
                
                    <h2 class="card-title">Paquetes:</h2>

					<%
					if(datosActividad.getPaquetes() == null || datosActividad.getPaquetes().getEntry().size() == 0){%>	
						<span style="margin-left: 10px">Sin informacion</span>
					<%} else {%>
						<%for(publicar.actividadesturisticasservice.DtActividadTuristicaDetalle.Paquetes.Entry entryPack: datosActividad.getPaquetes().getEntry()){
							DtPaquete pack = entryPack.getValue();
						%>
	
	                    <div class="card mb-3" style="max-width: 800px; margin-right: 20px; margin-top: 15px">
	                        <div class="row g-0">
	                            <div class="col-md-4 img-contain">
	
	  
					                <img src="<%=Utiles.obtenerUrlParaImagen(pack.getImg())%>" class="img-fluid rounded-start paquetes"  style="margin: 10px" alt="">
	                                
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
					
					<%}%>
					
                    
                    
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
    
    
 <%if(request.getAttribute("motivoDeError") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('error', "Error al finalizar actividad turística" , mensajeError , 200);
    </script>
    <%} %>
    
    
    <% if( (Boolean)request.getAttribute("exito") == Boolean.TRUE){ %>
    <script>
    
	    setTimeout(() => {
	        Swal.fire({
	            icon: "success",
	            title: "Éxito",
	            text: "La actividad turistica ha sido finalizada con éxito",
	            confirmButtonText: 'Entendido'  
	        }).then((res) => {
	        	window.location.href = "index";
	        })
	    }, 200)
    
	</script>
	<%} %>


 

</main>


</body>
</html>
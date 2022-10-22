<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA
	DTUsuario "usr"

 --%>
<%@page import="logica.datatypes.DTInscripcion"%>
<%@page import="logica.datatypes.DTUsuario"%>
<%@page import="logica.datatypes.DTCompra"%>

<%@page import="logica.datatypes.DTProveedorDetalle"%>
<%@page import="logica.datatypes.DTTuristaDetalle"%>
<%@page import="logica.datatypes.DTActividadTuristicaDetalle"%>
<%@page import="logica.datatypes.DTActividadTuristica"%>

<%@page import="logica.datatypes.DTSalidaTuristica"%>
<%@page import="logica.datatypes.DTTuristaDetallePrivado"%>
<%@page import="logica.datatypes.DTProveedorDetallePrivado"%>
<%@page import="logica.datatypes.DTProveedorDetallePrivado"%>
<%@page import="logica.datatypes.EstadoActividadTuristica"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%@ page import="java.util.List" %>

 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consulta_de_usuario.css">
    <title>Turismo UY</title>
</head>
<body>
	<%
	DTUsuario usr = (DTUsuario)request.getAttribute("usuario");
	%>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
	<section id="contenedor">
		<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
		
		<%
		DTUsuario usuario = (DTUsuario)session.getAttribute("usuarioLogeado");   
		if(usr!= null){%>
			<div id="contenedor-items">
				
				<%
				String imgpath;
				if(usr.getImg() != null){
					imgpath = "/img" + usr.getImg().getPath();
				}else{
					imgpath = "/img/noFoto.png";
				}
				%>
				
            			<div class="card mb-3" style="max-width: 540px;">
						  	<div class="row g-0">
						    	<div class="col-md-4">
						    		<img src="${pageContext.request.contextPath}<%=imgpath%>" class="img-fluid rounded-start">
						    	</div>
						    	<div class="col-md-8">
						      		<div class="card-body">
						    			<h5 class="card-title"><%= usr.getNombre()%> <%= usr.getApellido()%></h5>
						        		<p class="card-text"><small class="text-muted"><%= usr.getNickname()%> / <%= usr.getCorreo()%></small></p>
						      		</div>	
						    	</div>
						  	</div>
						</div>
						
	            <ul class="nav nav-tabs" id="myTab" role="tablist">
	                <li class="nav-item" role="presentation">
	                    <button class="nav-link active" id="boton-general" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Perfil</button>
	                </li>			
				    <%
				    boolean esProveedor = usr instanceof DTProveedorDetalle;
				    if(!esProveedor){
				        DTTuristaDetalle tur = (DTTuristaDetalle) usr;
				    %>
				    	 
						<!--"Si es turista se muestra la información de las salidas a las que se inscribió."-->
			            <li class="nav-item" role="presentation">
			                <button class="nav-link active" id="boton-salidas" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Inscripciones a salidas</button>
			            </li>
			            <% 

	                    if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname() == usr.getNickname()){ %>
			               <li class="nav-item" role="presentation">
				                <button class="nav-link active" id="boton-paquetes" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Compras de paquetes</button>
				            </li>
	                    <% }%>		
				    <%}else{
				        DTProveedorDetalle prv = (DTProveedorDetalle) usr;
				    %>
						<!--"Si es proveedor/a se muestra información de las actividades turísticas que ofrece (en estado “Confirmada”) y salidas asociadas."-->
			            <li class="nav-item" role="presentation">
			                <button class="nav-link active" id="boton-actividades" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Actividades ofrecidas</button>
			            </li>
			            
			            <li class="nav-item" role="presentation">
			                <button class="nav-link active" id="boton-salidasprov" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Salidas que se proveen</button>
			            </li>
				    <%}%>
		    	</ul>
		    	<div class="tab-content" id="myTabContent">
		    		<div class="tab-pane fade show active cardPerfil" id="boton-general-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">

	                    <div class="card-body cards">
	                        <h5 class="card-title"><%= usr.getNombre()%> <%= usr.getApellido()%></h5>
	                        <p class="text"><b><%= usr.getNombre()%> / <%= usr.getNickname()%></b></p>
	                        <p class="card-text"><b>Nickname:</b> <%= usr.getNickname()%></p>
	                        <p class="card-text"><b>Nombre: </b><%= usr.getNombre()%></p>
	                        <p class="card-text"><b>Apellido:</b> <%= usr.getApellido()%></p>
	                        <p class="card-text"><b>Email:</b> <%= usr.getCorreo()%></p>
	                        <p class="card-text"><b>Fecha de Nacimiento:</b> <%= usr.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))%></p>
	
	                    </div>
	                </div><!-- Cierra perfil -->
	                <%
					if(!esProveedor){						
            			DTTuristaDetalle tur = (DTTuristaDetalle) usr;
            			if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname() == usr.getNickname()){	
            				DTTuristaDetallePrivado turpriv = (DTTuristaDetallePrivado) usr;
            		%>
            			
            			<div class="tab-pane fade cardPaquetes" id="boton-paquetes-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="1">

		                    <div class="card-body cards">
		                        <h5 class="card-title"></h5>
								<%
		            				
		            				for(DTCompra cmp : turpriv.getCompras()){%>
		            					<div>
						 					<div class="card mb-3" style="max-width: 800px;">
							                    <div class="row g-0">

							                        <div class="col-md-4 img-contain">
							                        	<% 
								            			String path = "";
														if (cmp.getPaquete().getImg() == null) {
															path += "/noFoto.png";
														} else {
															path += cmp.getPaquete().getImg().getPath();
														}							
														%>
							                            <img src="${pageContext.request.contextPath}/img<%=path%>" class="img-fluid rounded-start">
							                            <!-- Falta el manejo de foto de la verdadero paquete-->
							                        </div>
							                        <div class="col-md-8">
							                            <div class="card-body">
							                                <h5 class="card-title"><%= cmp.getPaquete().getNombre()%> </h5>
							                                <p class="card-text"><b>Cantidad turistas:</b> <%= cmp.getCantTuristas()%></p>
							                                <p class="card-text"><b>Fecha de compra:</b> <%= cmp.getFechaCompra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))%></p>
							                                <p class="card-text"><b>Fecha de vencimiento:</b> <%= cmp.getVencimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))%></p>
							                                <p class="card-text"><b>Costo:</b> <%= cmp.getCosto()%></p>
							                                <div class="botonera">
							                                    <a href="ConsultaPaquete?id=<%=cmp.getPaquete().getNombre()%>" class="btn btn-primary">Ver mas</a>
							                                </div>
							
							                            </div>
							                        </div>
							                    </div>

							                </div>
					                    </div>
					                
		            				<%} %>
									
		                    </div>	
		                </div>
		                	
		                <div class="tab-pane fade cardSalidas" id="boton-salidas-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="2">
	            			<%
	            			for(DTInscripcion insc: turpriv.getDTInscripciones()){
	            			%>	
	                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
	                                <div class="row g-0">
	                                    <div class="col-md-4 img-contain">
	                                    
	                                    	<% 
					            			String pathImagen = "";
											if (insc.getSalidaTuristica().getImg() == null) {
												pathImagen += "/noFoto.png";
											} else {
												pathImagen += insc.getSalidaTuristica().getImg().getPath();
											}							
											%>
							                <img src="${pageContext.request.contextPath}/img<%=pathImagen%>" alt="" class="img-fluid rounded-start imagenSalidas">
	                                    
	                                    </div>
	                                    <div class="col-md-8">
	                                        <div class="card-body cards">
	                                             <h5 class="card-title"><%=insc.getSalidaTuristica().getNombre()%></h5>
	                                             <p class="card-text"><b>Cantidad turistas:</b> <%= insc.getCantidadTuristas()%></p>
	                                             <p class="card-text"><b>Costo de la inscripción:</b> <%= insc.getCosto()%></p> 
	                                             <p class="card-text"><b>Fecha de inscripción:</b> <%= insc.getFechaInscripcion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%></p>
	                                             <%if(insc.getCompra() != null) {%>
		                                             <p class="card-text"><b>Tipo de compra:</b> Con paquete</p>
	                                             	 <p class="card-text"><b>Comprada con paquete:</b> <%= insc.getCompra().getPaquete().getNombre()%></p>
	                                             
	                                             <%} else {%>
													<p class="card-text"><b>Tipo de compra:</b> General</p>                                             
	                                             <%} %>
	                                            <div class="botonera">
	                                                <a href="ConsultaSalida?id=<%=insc.getSalidaTuristica().getNombre() %>" class="btn btn-primary">Ver mas</a>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	            			<%}%>
	            		</div>
            		
            		
            		
            			<%} else {%>
            				
	            			<div class="tab-pane fade" id="boton-salidas-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="1">
	            			<%
	            			for(DTSalidaTuristica sal: tur.getInscripciones()){
	            			%>	
	                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
	                                <div class="row g-0">
	                                    <div class="col-md-4 img-contain">
	                                    
	                                    	<% 
					            			String pathImagen = "";
											if (sal.getImg() == null) {
												pathImagen += "/noFoto.png";
											} else {
												pathImagen += sal.getImg().getPath();
											}							
											%>
							                <img src="${pageContext.request.contextPath}/img<%=pathImagen%>" alt="" class="img-fluid rounded-start imagenSalidas">
	                                    
	                                    </div>
	                                    <div class="col-md-8">
	                                        <div class="card-body cards">
	                                            <h5 class="card-title"><%=sal.getNombre()%></h5>
	                                            
	                                            <div class="botonera">
	                                                <a href="ConsultaSalida?id=<%=sal.getNombre() %>" class="btn btn-primary">Ver más</a>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	            			<%}%>
	            			</div>
            			
            			
            			<%}%>
            		
						
            			
            			
            			
            		<%}
	                
	                
	                
	                if(esProveedor){
            			DTProveedorDetalle prv = (DTProveedorDetalle) usr;
            		%>
            			<div class="tab-pane fade" id="boton-actividades-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="1">
            			<h4>Actividades Confirmadas:</h4>
            			<%if(!prv.getActividades().isEmpty()){ %>
	            			<% for(DTActividadTuristicaDetalle act: prv.getActividades()) {%>	
							
			                <div class="card mb-3" style="max-width: 850px;">
			                    <div class="row g-0">
			                        <div class="col-md-4 img-contain">
			              
			                        	<% 
				            			String pathImagen = "";
										if (act.getImg() == null) {
											pathImagen += "/noFoto.png";
										} else {
											pathImagen += act.getImg().getPath();
										}							
										%>
						                <img src="${pageContext.request.contextPath}/img<%=pathImagen%>" alt="" class="img-fluid rounded-start imagen">
			                        </div>
			                        <div class="col-md-8">
			                            <div class="card-body">
			                                <h5 class="card-title"><%=act.getNombre()%></h5>
			                                <p class="card-text descripcion-actividad"><%=act.getDescripcion()%></p>
			                                <div class="botonera">
			                            		<a href="ConsultaActividad?id=<%=act.getNombre()%>" class="btn btn-primary">Ver más</a>
			                            	</div>
			                                
			                            	<div id="salidas" style=";margin-left: 10px">
			                            		<% if(! act.getSalidas().isEmpty()) {%>
				                            		<h6>Salidas:</h6>
				                            		<% for(DTSalidaTuristica sal: act.getSalidas().values()) {%>	
				                            			<li><a href="ConsultaSalida?id=<%=sal.getNombre() %>"><%=sal.getNombre() %></a></li>
													<% } %>
												<% } else {%>
													<h6>Todavía no hay salidas creadas.</h6>
												<% } %>
			                            	</div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
							<% } %>
            			<%}else{ %>
            				<p>No hay información.</p>
            				
            			<%} %>
						<%
						if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname() == usr.getNickname()){%>
            				<% DTProveedorDetallePrivado prvPriv = (DTProveedorDetallePrivado) usr;%>
    						<h4>Actividades agregadas sin confirmar:</h4>            				
            				<%if(!prvPriv.getActividadesNoConfirmadas().get(EstadoActividadTuristica.AGREGADA).isEmpty()){ %>
		        				<% for(DTActividadTuristica acti: prvPriv.getActividadesNoConfirmadas().get(EstadoActividadTuristica.AGREGADA)){%>
		        					<div class="card mb-3" style="max-width: 850px;">
					                    <div class="row g-0">
					                        <div class="col-md-4 img-contain">
					              
					                        	<% 
						            			String pathImagen = "";
												if (acti.getImg() == null) {
													pathImagen += "/noFoto.png";
												} else {
													pathImagen += acti.getImg().getPath();
												}							
												%>
								                <img src="${pageContext.request.contextPath}/img/<%=pathImagen%>" alt="" class="img-fluid rounded-start imagen">
					                        </div>
					                        <div class="col-md-8">
					                            <div class="card-body">
					                                <h5 class="card-title"><%=acti.getNombre()%></h5>
					                                <p class="card-text descripcion-actividad"><%=acti.getDescripcion()%></p>
					                                <div class="botonera">
					                            		<a href="ConsultaActividad?id=<%=acti.getNombre()%>" class="btn btn-primary">Ver más</a>
					                            	</div>
					                            </div>
					                        </div>
					                    </div>
					                </div>
		        				<%} %>
        				<%}else{ %>
            				<p>No hay información.</p>

            			<%} %>
            			<h4>Actividades Rechazadas:</h4>
        				<%if(!prvPriv.getActividadesNoConfirmadas().get(EstadoActividadTuristica.RECHAZADA).isEmpty()){ %>
		        			<% for(DTActividadTuristica acti: prvPriv.getActividadesNoConfirmadas().get(EstadoActividadTuristica.RECHAZADA)){%>
		        					<div class="card mb-3" style="max-width: 850px;">
					                    <div class="row g-0">
					                        <div class="col-md-4 img-contain">
					              
					                        	<% 
						            			String pathImagen = "";
												if (acti.getImg() == null) {
													pathImagen += "/noFoto.png";
												} else {
													pathImagen += acti.getImg().getPath();
												}							
												%>
								                <img src="${pageContext.request.contextPath}/img/<%=pathImagen%>" alt="" class="img-fluid rounded-start imagen">
					                        </div>
					                        <div class="col-md-8">
					                            <div class="card-body">
					                                <h5 class="card-title"><%=acti.getNombre()%></h5>
					                                <p class="card-text descripcion-actividad"><%=acti.getDescripcion()%></p>
					                                <div class="botonera">
					                            		<a href="ConsultaActividad?id=<%=acti.getNombre()%>" class="btn btn-primary">Ver más</a>
					                            	</div>
					                            </div>
					                        </div>
					                    </div>
					                </div>
		        				<%} %>
        				<%}else{ %>
            				<p>No hay información.</p>
            			<%} %>

						<%} %>
            			</div>
            			<div class="tab-pane fade" id="boton-salidasprov-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="3">
            				<% for(DTActividadTuristicaDetalle act: prv.getActividades()) {%>	
								<h4>Actividad: <%=act.getNombre() %></h4>
								<% for(DTSalidaTuristica sal: act.getSalidas().values()) {%>
									<div class="card mb-3 imagenSalidas" style="max-width: 800px;">
		                                <div class="row g-0">
		                                    <div class="col-md-4 img-contain">
		                                        <div id="info-general-imagen">            
								                 	<% 
										            String pathSalida = "";
													if (sal.getImg() == null) {
														pathSalida += "/noFoto.png";
													} else {
														pathSalida += sal.getImg().getPath();
													}							
													%>
												    <img src="${pageContext.request.contextPath}/img<%=pathSalida%>" class="img-fluid rounded-start paquetes"  style="margin: 10px" alt="">
												</div>
		                                    </div>
		                                    <div class="col-md-8">
		                                        <div class="card-body cards">
		                                            <h5 class="card-title"><%= sal.getNombre() %></h5>
 		                                            <p>Actividad: <%=sal.getActividad() %></p> 
		                                            <div class="botonera">
				                                        <a href="ConsultaSalida?id=<%=sal.getNombre()%>" class="btn btn-primary">Ver más</a>
				                                    </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
								<% } %>
							<% } %>
            			</div>
					<%}%>
				</div><!-- cierra tabcontent -->
				
		    </div><!-- cierra ContenedorItems -->
		<%}%>
    </section>
	    <jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
	
 
	<script src="${pageContext.request.contextPath}/js/perfil_de_usuario.js"></script>
	<script src="${pageContext.request.contextPath}/js/popUp_modificar_usuario.js"></script>
    <script>
        $(document).ready(function(){
            $(".btn").click(function(){
                $("#myModal").modal('show');
            });
            $(".close").click(function(){
                $("#myModal").modal('hide');
            });
        });
    </script>

</body>
</html>
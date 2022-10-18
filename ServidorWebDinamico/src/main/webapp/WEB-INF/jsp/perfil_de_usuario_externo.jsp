<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA
	DTUsuario "usr"

 --%>
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
				        System.out.println("esTurista");
				        DTTuristaDetalle tur = (DTTuristaDetalle) usr;
				    %>
				    	 
						<!--"Si es turista se muestra la información de las salidas a las que se inscribió."-->
			            <li class="nav-item" role="presentation">
			                <button class="nav-link active" id="boton-salidas" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Salidas</button>
			            </li>
			            <% 

	                    if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname() == usr.getNickname()){ %>
			               <li class="nav-item" role="presentation">
				                <button class="nav-link active" id="boton-paquetes" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Paquetes</button>
				            </li>
	                    <% }%>		
				    <%}else{
				        System.out.println("esProveedor");
				        DTProveedorDetalle prv = (DTProveedorDetalle) usr;
				    %>
						<!--"Si es proveedor/a se muestra información de las actividades turísticas que ofrece (en estado “Confirmada”) y salidas asociadas."-->
			            <li class="nav-item" role="presentation">
			                <button class="nav-link active" id="boton-actividades" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Actividades</button>
			            </li>
			            
			            <li class="nav-item" role="presentation">
			                <button class="nav-link active" id="boton-salidasprov" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Salidas</button>
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
						System.out.println("esTurista");
            			DTTuristaDetalle tur = (DTTuristaDetalle) usr;
            	
            		%>
						<div class="tab-pane fade" id="boton-salidas-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="1">
            			<%
            			for(String sal: tur.getInscripciones()){
            			%>	
                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
                                <div class="row g-0">
                                    <div class="col-md-4 img-contain">
                                    
                                    	<% 
				            			String pathImagen = "";
										if (/*sal.getImg() == null*/ true) {
											pathImagen += "noFoto.png";
										//} else {
										//	pathImagen += sal.getImg().getPath();
										}							
										%>
						                <img src="${pageContext.request.contextPath}/img<%=pathImagen%>" alt="" class="img-fluid rounded-start imagenSalidas">
                                    
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body cards">
                                            <h5 class="card-title"><%=sal%></h5>
                                            <div class="botonera">
                                                <a href="consulta_de_salida_turistica.html" class="btn btn-primary">Ver mas</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
            			<%}%>
            			</div>
            			
            			
            			<div class="tab-pane fade cardPerfil" id="boton-paquetes-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="2">

		                    <div class="card-body cards">
		                        <h5 class="card-title"></h5>
								<%
								if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname() == usr.getNickname()){
		            				DTTuristaDetallePrivado turpriv = (DTTuristaDetallePrivado) usr;
		            				for(DTCompra cmp : turpriv.getCompras()){%>
		            					</div>
					                        <div class="col-md-8">
					                            <div class="card-body">
					                                <h5 class="card-title"><%= cmp.getPaquete()%> </h5>
					                                <p class="card-text descripcion-paquete"></p>
					                                <div class="botonera">
					                                    <a href="ConsultaPaquete?id=" class="btn btn-primary">Ver mas</a>
					                                </div>
					
					                            </div>
					                        </div>
					                    </div>
					                </div>
		            				<%} %>
								<%} %>
								
	                    	</div>	
	                	</div>
            		<%}
	                
	                
	                
	                if(esProveedor){
            			System.out.println("esProveedor");
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
											pathImagen += "noFoto.png";
										} else {
											pathImagen += act.getImg().getPath();
										}							
										%>
						                <img src="${pageContext.request.contextPath}/img/<%=pathImagen%>" alt="" class="img-fluid rounded-start imagen">
			                        </div>
			                        <div class="col-md-8">
			                            <div class="card-body">
			                                <h5 class="card-title"><%=act.getNombre()%></h5>
			                                <p class="card-text descripcion-actividad"><%=act.getDescripcion()%></p>
			                                <div class="botonera">
			                            		<a href="ConsultaActividad?id=<%=act.getNombre()%>" class="btn btn-primary">Ver más</a>
			                            	</div>
			                                
			                            	<div id="salidas" style=";margin-left: 10px">
			                            		<h6>Salidas:</h6>
			                            		<% for(DTSalidaTuristica sal: act.getSalidas().values()) {%>	
													<p><%=sal.getNombre() %></p>
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
													pathImagen += "noFoto.png";
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
													pathImagen += "noFoto.png";
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
            				<%-- for(List<DTActividadTuristica> ListAct: prvPriv.getActividadesNoConfirmadas().values()){
            					for(DTActividadTuristica acti:ListAct){%>
	            					<div class="card mb-3" style="max-width: 850px;">
					                    <div class="row g-0">
					                        <div class="col-md-4 img-contain">
					              
					                        	<% 
						            			String pathImagen = "";
												if (acti.getImg() == null) {
													pathImagen += "noFoto.png";
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
	            			<%} %> --%>
						<%} %>
            			</div>
            			<div class="tab-pane fade" id="boton-salidasprov-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="3">
            				<% for(DTActividadTuristicaDetalle act: prv.getActividades()) {%>	
								
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
				                                        <a href="ConsultaSalida?id=<%=sal.getNombre()%>" class="btn btn-primary">Ver mas</a>
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
	
 

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
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
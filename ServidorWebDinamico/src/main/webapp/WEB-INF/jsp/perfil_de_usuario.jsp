<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA
	DtUsuario "usr"

 --%>

<%@page import="utils.Utiles"%>
<%@page import="java.time.LocalDate"%>
<%@page import="publicar.usuarioturisticasservice.DtActividadTuristicaDetalle.Salidas.Entry"%>
<%@page import="publicar.usuarioturisticasservice.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>

 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="css/consulta_de_usuario.css">
    <title>Turismo UY</title>
</head>
<body>
	<%
	DtUsuario usr = (DtUsuario)request.getAttribute("usuario");
	%>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
	<section id="contenedor">
		<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
		
		<%
		DtUsuario usuario = (DtUsuario)session.getAttribute("usuarioLogeado");   
		if(usr!= null){%>
			<div id="contenedor-items">
				
				
            			<div class="card mb-3" style="max-width: 540px;">
						  	<div class="row g-0">
						    	<div class="col-md-4">
						    		<img src="<%=Utiles.obtenerUrlParaImagen(usr.getImg())%>" class="img-fluid rounded-start">
						    	</div>
						    	<div class="col-md-8">
						      		<div class="card-body">
						    			<h5 class="card-title"><%= usr.getNombre()%> <%= usr.getApellido()%></h5>
						        		<p class="card-text"><small class="text-muted"><%= usr.getNickname()%> / <%= usr.getCorreo()%></small></p>
						        		<% 
						        		System.out.println("usuario que visita = " + usuario.getNickname() + " usuario visitado: " + usr.getNickname());
						        		if(!usr.getNickname().equals(usuario.getNickname())){
						        			boolean seSiguenUsuarios = (boolean)request.getAttribute("seSiguenUsuarios");
						        			if(seSiguenUsuarios){ %>
						        				<a href="ConsultaDeUsuario?id=<%=usr.getNickname()%>&listar=false&seguir=<%=true%>" class="btn btn-danger"><i class="fa-solid fa-user-minus"></i></a>
						        			<%} else{%>
						        				<a href="ConsultaDeUsuario?id=<%=usr.getNickname()%>&listar=false&seguir=<%=true%>" class="btn btn-primary"><i class="fa-solid fa-user-plus"></i></a>
						        			<%} 
						        		}%>
						      		</div>	
						    	</div>
						  	</div>
						</div>
						
	            <ul class="nav nav-tabs" id="myTab" role="tablist">
	                <li class="nav-item" role="presentation">
	                    <button class="nav-link active" id="boton-general" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Perfil</button>
	                </li>			
				    <%
				    boolean esProveedor = usr instanceof DtProveedorDetalle;
				    if(!esProveedor){
				        DtTuristaDetalle tur = (DtTuristaDetalle) usr;
				    %>
				    	 
						<!--"Si es turista se muestra la información de las salidas a las que se inscribió."-->
			            <li class="nav-item" role="presentation">
			                <button class="nav-link" id="boton-salidas" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Inscripciones a salidas</button>
			            </li>
			            
			            
			            
			            <% 

	                    if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname().equals(usr.getNickname()) ){ %>
			               <li class="nav-item" role="presentation">
				                <button class="nav-link" id="boton-paquetes" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Compras de paquetes</button>
				            </li>
	                    <% }%>		
				    <%}else{
				        DtProveedorDetalle prv = (DtProveedorDetalle) usr;
				    %>
						<!--"Si es proveedor/a se muestra información de las actividades turísticas que ofrece (en estado “Confirmada”) y salidas asociadas."-->
			            <li class="nav-item" role="presentation">
			                <button class="nav-link" id="boton-actividades" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Actividades ofrecidas</button>
			            </li>
			            
			            <li class="nav-item" role="presentation">
			                <button class="nav-link" id="boton-salidasprov" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Salidas que se proveen</button>
			            </li>
				    <%}%>
				    <li class="nav-item" role="presentation">
	                    <button class="nav-link" id="boton-seguidos" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Seguidos</button>
	                </li>	
				    
				    <li class="nav-item" role="presentation">
	                    <button class="nav-link" id="boton-seguidores" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Seguidores</button>
	                </li>	
		    	</ul>
		    	<div class="tab-content" id="myTabContent">
		    		<div class="tab-pane fade show cardPerfil" id="boton-general-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">

	                    <div class="card-body cards">
	                        <h5 class="card-title"><%= usr.getNombre()%> <%= usr.getApellido()%></h5>
	                        <p class="text"><b><%= usr.getNombre()%> / <%= usr.getNickname()%></b></p>
	                        <p class="card-text"><b>Tipo de usuario:</b> <%= usr instanceof DtProveedor ? "Proveedor" : "Turista"%></p>
	                        <p class="card-text"><b>Nickname:</b> <%= usr.getNickname()%></p>
	                        <p class="card-text"><b>Nombre: </b><%= usr.getNombre()%></p>
	                        <p class="card-text"><b>Apellido:</b> <%= usr.getApellido()%></p>
	                        <p class="card-text"><b>Email:</b> <%= usr.getCorreo()%></p>
	                        <p class="card-text"><b>Fecha de Nacimiento:</b> <%=usr.getFechaNacStr()%></p>
	                        <% if(usr instanceof DtProveedor){ 
	                        	DtProveedor provData = (DtProveedor) usr;
	                        %>
	                        	<p class="card-text"><b>Descripcion:</b> <%= provData.getDescrpicionGeneral()%></p>
	                        	<p class="card-text"><b>Url:</b> <%= provData.getLink() == null ? "Sin informacion" : provData.getLink()%></p>
	                        
	                        <% }else if(usr instanceof DtTurista){ 
	                        	DtTurista turiData = (DtTurista) usr;
	                        %>
	                        <p class="card-text"><b>Nacionalidad:</b> <%= turiData.getNacionalidad()%></p>
	                      
							<%}%>
							
							<% if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname().equals(usr.getNickname())) { %>
								<jsp:include page="/WEB-INF/jsp/modificar_usuario.jsp"/>
							<% } %>	

	                    </div>
	                </div><!-- Cierra perfil -->
	                <%
					if(!esProveedor){						
            			DtTuristaDetalle tur = (DtTuristaDetalle) usr;
            			if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname().equals(usr.getNickname())){	
            				DtTuristaDetallePrivado turpriv = (DtTuristaDetallePrivado) usr;
            		%>
            			
            			<div class="tab-pane fade cardPaquetes" id="boton-paquetes-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="1">

		                    <div class="card-body cards">
		                        <h5 class="card-title"></h5>
								<%if(turpriv.getCompras().size() == 0){%>
									
									<span>Sin informacion</span>
								
		            			<%}else{	
		            					
		            				for(DtCompra cmp : turpriv.getCompras()){%>
		            					<div>
						 					<div class="card mb-3" style="max-width: 800px;">
							                    <div class="row g-0">

							                        <div class="col-md-4 img-contain">
							                            <img src="<%=Utiles.obtenerUrlParaImagen(cmp.getPaquete().getImg())%>" class="img-fluid rounded-start">
							                            <!-- Falta el manejo de foto de la verdadero paquete-->
							                        </div>
							                        <div class="col-md-8">
							                            <div class="card-body">
							                                <h5 class="card-title"><%= cmp.getPaquete().getNombre()%> </h5>
							                                <p class="card-text"><b>Cantidad turistas:</b> <%= cmp.getCantTuristas()%></p>
							                                <p class="card-text"><b>Fecha de compra:</b> <%= cmp.getFechaCompraStr()%></p>
							                                <p class="card-text"><b>Fecha de vencimiento:</b> <%= cmp.getVencimientoStr()%></p>
							                                <p class="card-text"><b>Costo:</b> <%= cmp.getCosto()%>$</p>
							                                <div class="botonera">
							                                    <a href="ConsultaPaquete?id=<%=cmp.getPaquete().getNombre()%>" class="btn btn-primary">Ver mas</a>
							                                </div>
							
							                            </div>
							                        </div>
							                    </div>

							                </div>
					                    </div>
					                
		            				<%} 
		            			}%>
									
		                    </div>	
		                </div>
		                	
		                <div class="tab-pane fade cardSalidas" id="boton-salidas-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="2">
		                
		                	<%if(turpriv.getDtInscripciones().size() == 0){%>
									
									<span>Sin informacion</span>
								
		            		<%}else{	
		               
		            			for(DtInscripcion insc: turpriv.getDtInscripciones()){
		            			%>	
		                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
		                                <div class="row g-0">
		                                    <div class="col-md-4 img-contain">		                                   
								                <img src="<%=Utiles.obtenerUrlParaImagen(insc.getSalida().getImg())%>" alt="" class="img-fluid rounded-start imagenSalidas">
		                                    
		                                    </div>
		                                    <div class="col-md-8">
		                                        <div class="card-body cards">
		                                             <h5 class="card-title"><%=insc.getSalida().getNombre()%></h5>
		                                             <p class="card-text"><b>Cantidad turistas:</b> <%= insc.getCantidadTuristas()%></p>
		                                             <p class="card-text"><b>Costo de la inscripción:</b> <%= insc.getCosto()%>$</p> 
		                                             <p class="card-text"><b>Fecha de inscripción:</b> <%= insc.getFechaInscripcionStr()%></p>
		                                             <%if(insc.getCompra() != null) {%>
			                                             <p class="card-text"><b>Tipo de compra:</b> Con paquete</p>
		                                             	 <p class="card-text"><b>Comprada con paquete:</b> <%= insc.getCompra().getPaquete().getNombre()%></p>
		                                             
		                                             <%} else {%>
														<p class="card-text"><b>Tipo de compra:</b> General</p>                                             
		                                             <%} %>
		                                            <div class="botonera">
		                                                <a href="ConsultaSalida?id=<%=insc.getSalida().getNombre() %>" class="btn btn-primary">Ver mas</a>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		            			<%}
		            		}%>
	            		</div>
            		
            		
            		
            			<%} else {%>
            				
	            			<div class="tab-pane fade" id="boton-salidas-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="1">
	            			
		            			<%if(tur.getInscripcionesSalidas().size() == 0){%>
										
										<span>Sin informacion</span>
									
			            		<%}else{
			            			for(DtSalidaTuristica sal: tur.getInscripcionesSalidas()){
			            			%>	
			                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
			                                <div class="row g-0">
			                                    <div class="col-md-4 img-contain">
			                                    
									                <img src="<%=Utiles.obtenerUrlParaImagen(sal.getImg())%>" alt="" class="img-fluid rounded-start imagenSalidas">
			                                    
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
			            			<%}
			            		}%>
	            			</div>
            			
            			
            			<%}%>
            		
						
            			
            			
            			
            		<%}
	                
	                
	                
	                if(esProveedor){
            			DtProveedorDetalle prv = (DtProveedorDetalle) usr;
            		%>
            			<div class="tab-pane fade" id="boton-actividades-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="1">
            			<h4>Actividades Confirmadas:</h4>
            			<%if(!prv.getActividades().isEmpty()){ %>
	            			<% for(DtActividadTuristicaDetalle act: prv.getActividades()) {
	            			
	            				if(act.getEstado() == publicar.usuarioturisticasservice.EstadoActividadTuristica.ACEPTADA){%>	
							
			                <div class="card mb-3" style="max-width: 850px;">
			                    <div class="row g-0">
			                        <div class="col-md-4 img-contain">
			              
						                <img src="<%=Utiles.obtenerUrlParaImagen(act.getImg())%>" alt="" class="img-fluid rounded-start imagen">
			                        </div>
			                        <div class="col-md-8">
			                            <div class="card-body">
			                            	<div style="display: grid;grid-template-columns: auto 177px;">
			                            		<div>
			                            		    <h5 class="card-title"><%=act.getNombre()%></h5>
				                                	<p class="card-text descripcion-actividad"><%=act.getDescripcion()%></p>
			                            		</div>
			                                	<a href="ConsultaDeUsuario?id=listar=false&idAct=<%= act.getNombre()%>&finalizar=<%=true%>" class="btn btn-danger" style="height: 40px" >Finalizar Actividad <i class="fa-solid fa-ban"></i></a>
			                                	
			                            	</div>
			                                <div class="botonera">
			                            		<a href="ConsultaActividad?id=<%=act.getNombre()%>&actividadDeProveedor=<%=true%>" class="btn btn-primary">Ver más</a>
			                            	</div>
			                                
			                            	<div id="salidas" style=";margin-left: 10px">
			                            		<% if(act != null && act.getSalidas() != null &&  act.getSalidas().getEntry() != null && !act.getSalidas().getEntry().isEmpty()) { %>
				                            		<h6>Salidas:</h6>
				                            		<% for(Entry entr: act.getSalidas().getEntry()) {
				                            			DtSalidaTuristica sal = entr.getValue();
				                            		%>	
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
							<% }
	            				}%>
            			<%}else{ %>
            				<p>No hay información.</p>
            			<%}

						if(session.getAttribute("usuarioLogeado") != null && usuario.getNickname().equals(usr.getNickname())){
            				DtProveedorDetallePrivado prvPriv = (DtProveedorDetallePrivado) usr;
            				
            				Map<EstadoActividadTuristica, List<DtActividadTuristica>> map = new HashMap<>();
            				
            				map.put(EstadoActividadTuristica.AGREGADA, prvPriv.getActividadesAgregadas());
            				map.put(EstadoActividadTuristica.FINALIZADA, prvPriv.getActividadesFinalizadas());
            				map.put(EstadoActividadTuristica.RECHAZADA, prvPriv.getActividadesRechazadas());
            				
            				if (prvPriv.getActividadesAgregadas().size() + prvPriv.getActividadesFinalizadas().size() + prvPriv.getActividadesRechazadas().size() > 0) {
	            				%>
	           					<h4>Actividades no confirmadas:</h4>
	        				<% }
	        				for(EstadoActividadTuristica estado: map.keySet()) {
	        						List<DtActividadTuristica> actividades = map.get(estado);
	        						
	        						if (actividades.size() <= 0)
	        							continue;
	        						
	        						String estadoAct = "";
		        					switch (estado) {
		        					case AGREGADA:
		        						estadoAct = "agregadas";
		        						break;
		        					case FINALIZADA:
		        						estadoAct = "finalizadas";
		        						break;
		        					case RECHAZADA:
		        						estadoAct = "rechazadas";
		        						break;
		        					default:
		        						break;
		        					}
		        					
		        					%> <h5>Actividades <%=estadoAct%>:</h5> <%
		        					for (DtActividadTuristica acti : actividades) { %>
			        					<div class="card mb-3" style="max-width: 850px;">
						                    <div class="row g-0">
						                    	<div class="col-md-4 img-contain">
									                <img src="<%=Utiles.obtenerUrlParaImagen(acti.getImg())%>" alt="" class="img-fluid rounded-start imagen">
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
		        					<% } %>
        					<% } %>
						<% } %>
            			</div>
            			<div class="tab-pane fade" id="boton-salidasprov-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="3">
            			
            				<%if( prv.getActividades().size() == 0){%>										
								<span>Sin informacion</span>									
			            	<%}else{            			
	            				 for(DtActividadTuristicaDetalle act: prv.getActividades()) {%>	
									<%if(act != null && act.getSalidas() != null && act.getSalidas().getEntry() != null && !act.getSalidas().getEntry().isEmpty()){ %>
										<h4>Actividad: <%=act.getNombre() %></h4>
									
									<% for(Entry entrie: act.getSalidas().getEntry()) {
										DtSalidaTuristica sal = entrie.getValue();
									%>
										<div class="card mb-3 imagenSalidas" style="max-width: 800px;">
			                                <div class="row g-0">
			                                    <div class="col-md-4 img-contain">
			                                        <div id="info-general-imagen">            
													    <img src="<%=Utiles.obtenerUrlParaImagen(sal.getImg())%>" class="img-fluid rounded-start paquetes"  style="margin: 10px" alt="">
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
									<% } } %>
								<% } %>
							<% } %>
            			</div>
					<%}%>
					
				<div class="tab-pane fade" id="boton-seguidos-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="4">
           			<jsp:include page="/WEB-INF/jsp/listarSeguidos.jsp"/>
           		</div>
           		
           		<div class="tab-pane fade" id="boton-seguidores-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="5">
           			<jsp:include page="/WEB-INF/jsp/listarSeguidores.jsp"/>
           		</div>
				
					
				</div><!-- cierra tabcontent -->
				
				
		    </div><!-- cierra ContenedorItems -->
		<%}%>
    </section>
	    <jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
	
 
	<script src="js/perfil_de_usuario.js"></script>
	<script src="js/popUp_modificar_usuario.js"></script>
    <script>
        $(document).ready(function(){
            $(".botonModificar").click(function(){
                $("#myModal").modal('show');
            });
            $(".close").click(function(){
                $("#myModal").modal('hide');
            });
        });
    </script>
    
     <%if(request.getAttribute("motivoDeError") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('error', "Ha ocurrido un error al seguir usuario" , mensajeError , 200);
    </script>
    <%} %>
    

    
  

</body>
</html>
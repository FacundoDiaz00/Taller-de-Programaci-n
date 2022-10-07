<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA
	DTUsuario "usr"

 --%>
<%@page import="logica.datatypes.DTUsuario"%>
<%@page import="logica.datatypes.DTProveedorDetalle"%>
<%@page import="logica.datatypes.DTTuristaDetalle"%>
<%@page import="logica.datatypes.DTActividadTuristicaDetalle"%>
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
		<%if(usr!= null){%>
			<div id="contenedor-items">
				<%
				String imgpath;
				if(usr.getImg() != null){
					imgpath = "/img" + usr.getImg().getPath();
				}else{
					imgpath = "/img/noFoto.png";
				}								                        
				%>

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
				    <%}else{
				        System.out.println("esProveedor");
				        DTProveedorDetalle prv = (DTProveedorDetalle) usr;
				    %>
						<!--"Si es proveedor/a se muestra información de las actividades turísticas que ofrece (en estado “Confirmada”) y salidas asociadas."-->
			            <li class="nav-item" role="presentation">
			                <button class="nav-link active" id="boton-actividades" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Actividades</button>
			            </li>
				    <%}%>
		    	</ul>
		    	<div class="tab-content" id="myTabContent">
		    		<div class="tab-pane fade show active cardPerfil" id="boton-general-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
	                    <div >
	                    	<%
					        	//String imgpath;
					            if(usr.getImg() != null){
						        	imgpath = "/img" + usr.getImg().getPath();
								}else{
					            	imgpath = "/img/noFoto.png";
								}								                        
							%>
	                        <img src="${pageContext.request.contextPath}<%=imgpath%>" class="img-fluid rounded-start">
	                    </div>
	                    <div class="card-body cards">
	                        <h5 class="card-title"><%= usr.getNombre()%> <%= usr.getApellido()%></h5>
	                        <p class="text"><b><%= usr.getNombre()%> / <%= usr.getNickname()%></b></p>
	                        <p class="card-text"><b>Nickname: <%= usr.getNickname()%></b></p>
	                        <p class="card-text"><b>Nombre: </b><%= usr.getNombre()%></p>
	                        <p class="card-text"><b>Apellido:</b> <%= usr.getApellido()%></p>
	                        <p class="card-text"><b>Email: <%= usr.getCorreo()%></b></p>
	                        <p class="card-text"><b>Fecha de Nacimiento:</b> <%= usr.getFechaNac()%></p>
	
	                    </div>
	                </div><!-- Cierra perfil -->
	                <%
					if(!esProveedor){
						System.out.println("esTurista");
            			DTTuristaDetalle tur = (DTTuristaDetalle) usr;
            		%>
						<div class="tab-pane fade" id="boton-salidas-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="1">
            			<div class="card mb-3" style="max-width: 540px;">
						  <div class="row g-0">
						    <div class="col-md-4">
						    <img src="${pageContext.request.contextPath}<%=imgpath%>" class="img-fluid rounded-start">
						    </div>
						    <div class="col-md-8">
						      <div class="card-body">
						    	<h5 class="card-title"><%= usr.getNombre()%> <%= usr.getApellido()%></h5>
						        <p class="card-text"><small class="text-muted"><%= usr.getNickname()%></small></p>
						      </div>	
						    </div>
						  </div>
						</div>
            			<%
            			for(String sal: tur.getInscripciones()){
            			%>	
                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
                                <div class="row g-0">
                                    <div class="col-md-4 img-contain">
                                        <img src="../img/salida1.png" class="img-fluid rounded-start imagenSalidas"> <!-- TODO: imagenes de salidas -->
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
            		<%}else{
            			System.out.println("esProveedor");
            			DTProveedorDetalle prv = (DTProveedorDetalle) usr;
            		%>
            			<div class="tab-pane fade" id="boton-actividades-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="1">
            			<div class="card mb-3" style="max-width: 540px;">
						  <div class="row g-0">
						    <div class="col-md-4">
						    <img src="${pageContext.request.contextPath}<%=imgpath%>" class="img-fluid rounded-start">
						    </div>
						    <div class="col-md-8">
						      <div class="card-body">
						    	<h5 class="card-title"><%= usr.getNombre()%> <%= usr.getApellido()%></h5>
						        <p class="card-text"><small class="text-muted"><%= usr.getNickname()%></small></p>
						      </div>	
						    </div>
						  </div>
						</div>
            			<%
            			for(DTActividadTuristicaDetalle act: prv.getActividades()){
            				System.out.println(act.getNombre());
            			%>	
                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
                                <div class="row g-0">
                                    <div class="col-md-4 img-contain">
                                        <img src="../img/salida1.png" class="img-fluid rounded-start imagenSalidas"> <!-- TODO: imagenes de actividad -->
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body cards">
                                            <h5 class="card-title"><%=act.getNombre()%></h5>
                                            <h5>AAAAAAAAAAa</h5>
                                            <div class="botonera">
                                                <a href="consulta_de_salida_turistica.html" class="btn btn-primary">Ver mas</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
            			<%}%>
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
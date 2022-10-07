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
		<div id="contenedor-items">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="boton-general" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Perfil</button>
                </li>
                <!--"Si es turista se muestra la información de las salidas a las que se inscribió."-->
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="boton-salidas" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Salidas</button>
                </li>
                <!--"Si es proveedor/a se muestra información de las actividades turísticas que ofrece (en estado “Confirmada”) y salidas asociadas."-->
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="boton-paquetes" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Actividades</button>
                </li>

            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active cardPerfil" id="boton-general-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
                    <div >
                    	<%
				        	String imgpath;
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
                </div>
				<%if(usr!= null){%>
					<%
					boolean esProveedor = usr instanceof DTProveedorDetalle;
					if(!esProveedor){
						System.out.println("esTurista");
            			DTTuristaDetalle tur = (DTTuristaDetalle) usr;
            		%>
						<div class="tab-pane fade" id="boton-salidas-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="1">
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
            			<div class="tab-pane fade" id="boton-salidas-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="2">
            			<%
            			for(DTActividadTuristicaDetalle act: prv.getActividades()){
            				System.out.println(act.getNombre());
            			%>	
                            <div class="card mb-3 imagenSalidas" style="max-width: 800px;">
                                <div class="row g-0">
                                    <div class="col-md-4 img-contain">
                                        <img src="../img/salida1.png" class="img-fluid rounded-start imagenSalidas"> <!-- TODO: imagenes de salidas -->
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
				<%}%>
                
            	
       
                	
                <!-- TODO: la parte de paquetes requiere sesion iniciada -->
                <!-- <div class="tab-pane fade" id="boton-paquetes-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="2">
                        <div class="card mb-3" style="max-width: 800px; margin-right: 20px; margin-top: 15px">
                            <div class="row g-0">
                                <div class="col-md-4 img-contain">
                                    <img src="../img/pack1.png" class="img-fluid rounded-start paquetes">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body cards">
                                        <h5 class="card-title">Disfrutar Rocha</h5>
                                        <p class="card-text descripcion-paquete">Actividades para hacer en familia y disfrutar arte y gastronomía.</p>
                                        <div class="botonera">
                                            <a href="consulta_de_paquete.html" class="btn btn-primary">Ver mas</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="card mb-3" style="max-width: 800px;margin-right: 20px">
                            <div class="row g-0">
                                <div class="col-md-4 img-contain">
                                    <img src="../img/pack2.png" class="img-fluid rounded-start paquetes">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body cards">

                                        <h5 class="card-title">Un día en Colonia</h5>
                                        <p class="card-text descripcion-paquete">Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros</p>
                                        <div class="botonera">
                                            <a href="#" class="btn btn-primary">Ver mas</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div> -->
            </div>
        </div>

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
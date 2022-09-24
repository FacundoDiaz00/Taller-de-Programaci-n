<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- DTUsuario "usuarioLogeado" (opcional)
- DTPaqueteDetalles paquete

 --%>


<%@page import="logica.datatypes.DTActividadTuristica"%>
<%@page import="logica.datatypes.DTPaqueteDetalles"%>
<%@page import="logica.datatypes.Imagen"%>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consulta_de_paquete.css">
    
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
    
<main>
    <% 
	DTPaqueteDetalles paquete = (DTPaqueteDetalles) request.getAttribute("paquete");
    Map<String, DTActividadTuristica> actividadesPaquete = paquete.getActividades();
	
	%>


    <section id="contenedor">

		<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
		
	
        <div id="titulo">
            <h1>Consulta de Paquete </h1>
        </div>
        

	        <div id="info-paquete">
	
	
	            <div id="info-general-imagen">
	            			<% 
	            			String path = "";
							if (paquete.getImg() == null) {
								path += "noFoto.png";
							} else {
								path += paquete.getImg().getPath();
							}							
							%>
			                <img src="${pageContext.request.contextPath}/img/<%=path%>" alt="">
	            </div>
	
	            <div id="info">
	                <h2><%=paquete.getNombre()%></h2>
	                <h6>Creado el <%=paquete.getFechaRegistro()%></h6>
	
	            </div>
	
	            <div id="resto-de-la-info-actividad">
	
	
	                <div class="div-doble" id="validesPaquete">
	                    <h5 class="label">Validez del paquete: </h5>
	                    <p><%=paquete.getValidez() %> dias</p>
	                </div>
	                <div class="div-doble" id="descuento">
	                    <h5 class="label">Descuento: </h5>
	                    <p><%=paquete.getDescuento()%>%</p>
	                </div>
	                <div class="div-doble" id="descripcion">
	                    <h5 class="label">Descripcion: </h5>
	                    <p><%=paquete.getDescrpicion()%></p>
	                </div>
	
	            </div>
	
	        </div>
	
	        <div id="actividades-compra-turisticas">
            <div class="card" id="contenedor-compra">
                <div class="header-card-with-button">
                    <h2 class="card-title">Compra paquete</h2>
                </div>
                <div class="row g-0">
                    <form action="" id="form-comprar">
                        <div class="contenedorinput mb-3">
                            <span class="input-label">Cantidad turistas</span> </span>
                            <input id="input-cantidad-turistas" type="number" required class="form-control" placeholder="Ingrese la cantidad de turistas para la compra" aria-label="" aria-describedby="basic-addon1">
                        </div>
                        <div id="div-comprar">
                            <input type="submit" id="comprar-boton" class="btn btn-success" value="Comprar">
                        </div>

                    </form>
                </div>
            </div>

            <div class="card" id="contenedor-actividades-turisticas">
                <div class="header-card-with-button">
                    <h2 class="card-title">Actividades</h2>
                </div>

				<% for(DTActividadTuristica act: actividadesPaquete.values()) {%>	
				
	                <div class="card mb-3" style="max-width: 800px;">
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
	                                <a href="ConsultaActividadServlet?id=<%=act.getNombre()%>" class="btn btn-primary">Ver más</a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
					
				<% } %>


               
            </div>
        </div>
		
    </section>
</main>


<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>

</body>
</html>

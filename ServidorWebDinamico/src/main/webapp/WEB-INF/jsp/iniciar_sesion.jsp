
<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- List<String> departamentos
- List<String> categorias
- List<DTActividadTuristica> actividades (ya deben estar filtradas)
- List<DTPaquete> paquetes (ya deben estar filtradas)[Falta filtrar por ambos criterios]

 --%>


<%@page import="logica.datatypes.DTUsuario"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iniciar_sesion.css">

	
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

	
<section id="contenedor">
        <div>
        </div>
    <div id="centerRow">
        
          <form class="card" id="form-iniciar-sesion" name="iniciarSesionForm" method="post" action="${pageContext.request.contextPath}/IniciarSesion">
                <div class="card-body">
                    <h5 class="card-title">Iniciar Sesión</h5>

                    <span class="input-label">Identificador:<span class="obligatorio"> *</span></span>
			        <div class="input-group mb-3 contenedorinput">
			        
			            <select class="form-select" aria-label="Default select example">
			                <option selected>Email</option>
			                <option value="2">Nickname</option>
			            </select>
			            <% String email = (String)request.getAttribute("email"); %>
                        <input id="input-correo-electronico" 
                         	   type="email" 
                         	   required 
                         	   name="email" 
                         	   class="form-control" 
                         	   placeholder="Ingrese un correo" 
                         	   aria-label="Correo electronico" 
                         	   aria-describedby="basic-addon1"
                         	   value="<%= email %>">
			        </div>

                    <div class="contenedorinput mb-3">
                    <% String password =(String)request.getAttribute("password"); %>
                        <span class="input-label">Contraseña:<span class="obligatorio"> *</span></span>
                        <input id="input-contrasenia" 
                        	   type="password" 
                        	   required name="password" 
                        	   class="form-control" 
                        	   placeholder="Ingrese una contraseña" 
                        	   aria-label="Contraseña" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= password %>"
                        	   >
                    </div>
                   
		        <div id="botones">
		            <a href="index.html" class="btn btn-danger btn-lg btn-block">Cancelar</a>
		            <input type="submit" id="aceptar-boton" class="btn btn-success btn-lg btn-block" value="Aceptar">
		        </div>

                </div>
            </form>
    </div>
</section>

 	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>


<script src="../js/jquery-3.3.1.slim.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
<script src="../js/sweetalert2.all.min.js"></script>
<script src="../js/comportamientoComun.js"></script>

</body>
</html>
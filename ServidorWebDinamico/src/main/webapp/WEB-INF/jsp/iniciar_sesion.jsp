
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
        <h2 id="title">Inicio de sesión</h2>
        <div id="icon">
            <i class="fa-solid fa-user fa-6x fa-fw"></i>
        </div>
        <span class="input-label">Identificador:<span class="obligatorio"> *</span></span>
        <div class="input-group mb-3">
            <select class="form-select" aria-label="Default select example">
                <option selected>Email</option>
                <option value="2">Nickname</option>
            </select>
            <input type="text" required class="form-control" aria-label="Text input with dropdown button">
        </div>
        <div class="contenedorinput mb-3">
            <span class="input-label">Contraseña:<span class="obligatorio"> *</span></span>
            <input id="input-contraseña" type="password" required class="form-control" placeholder="Ingrese una contraseña" aria-label="Contraseña" aria-describedby="basic-addon1">
        </div>
        <!--todo: Centrar botones-->
        <div id="botones">
            <a href="index.html" class="btn btn-danger btn-lg btn-block">Cancelar</a>
            <input type="submit" id="aceptar-boton" class="btn btn-success btn-lg btn-block" value="Aceptar">
        </div>

    </div>
</section>
<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
    <div class="container text-center">
        <span class="" style="font-size: 25px; color: Grey;">
          <i class="fas fa-route"></i>
        </span>
        <small>Copyright &copy; Grupo 16: Valentina Alaniz, Facundo Diaz, Agustín Martínez, Daniel Padrón y Agustín Recoba</small>
    </div>
</footer>


<script src="../js/jquery-3.3.1.slim.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
<script src="../js/sweetalert2.all.min.js"></script>
<script src="../js/comportamientoComun.js"></script>

</body>
</html>
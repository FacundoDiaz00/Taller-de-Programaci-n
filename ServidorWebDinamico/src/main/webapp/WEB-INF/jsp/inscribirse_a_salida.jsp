
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inscribirse_a_salida.css">

	
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

<section id="contenedor">
    <div id="menu-lateral">

        <!--Acciones generales-->
        <div class="card list-group" id="opciones">
            <h5 class="card-title">Acciones</h5>
            <a href="consulta_de_usuario.html" class="list-group-item">Ver usuarios</a>
            <a href="alta_de_actividad_turistica.html" class="list-group-item">Crear Actividad turistica</a>
        </div>

        <!--Categoroias-->
        <div class="card list-group" id="filtro-categoria">
            <h5 class="card-title">Categorias</h5>
            <a href="index.html" class="list-group-item">Gastronomía</a>
        </div>

        <!--Departametos-->
        <div class="card list-group" id="filtro-departamentos">
            <h5 class="card-title">Departamentos</h5>
            <a href="index.html" class="list-group-item">Rocha</a>
        </div>
    </div>
    <div id="contenedorForm">
        <form class="card" id="form-inscribirse-a-salida" style="margin: 10px">
            <div class="card-body">
                <h5 class="card-title" id ="tituloPagina">Inscrbirse a Salida.</h5>

                <div class="contenedorinput mb-3">
                    <span class="input-label">Cantidad De Turistas:<span class="obligatorio"> *</span></span>
                    <input id="input-cantTur" type="number" required class="form-control" placeholder="Cantidad de turistas a Inscribir" aria-label="Cantidad de Turistas" aria-describedby="basic-addon1">
                </div>


                <div class="contenedorinput mb-3">
                    <span class="input-label">Forma de Pago:<span class="obligatorio"> *</span></span>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="formaPago" id="checkGeneral" checked>
                        <label class="form-check-label" for="checkGeneral">
                            General
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="formaPago" id="checkPaquete">
                        <label class="form-check-label" for="checkPaquete">
                            Por Paquete
                        </label>
                    </div>
                </div>
                <div class="contenedorinput mb-3 eliminarElemento" id="comboPaquetes">
                    <div class="form-group">
                        <label>Paquete:</label>
                        <select class="combobox input-large form-control" name="normal">
                            <option value="" selected="selected">Disfrutar Rocha</option>
                        </select>
                    </div>
                </div>
                <div id="botonera">
                    <a href="index.html" class="btn btn-danger ">Cancelar</a>
                    <input type="submit" id="aceptar-boton" class="btn btn-success" value="Aceptar">
                </div>


            </div>
        </form>
    </div>
    <div id="contenedorDer">
        <div class="card" style="width: 18rem;">
            <img src="../img/salida2.png" class="card-img-top" alt="..." style="margin: 10px;width: 267px;">
            <div class="card-body">
                <h4 class="card-title">Degusta Setiembre</h4>
                <div class="div-doble" id="FechaYhoraSalida">
                    <h5 class="label">Fecha y hora de partida: </h5>
                    <p>03/09/2022 17:00</p>
                </div>
                <div class="div-doble" id="Costo">
                    <h5 class="label">Capacidad de turistas: </h5>
                    <p>20</p>
                </div>
                <div class="div-doble" id="Cuidad">
                    <h5 class="label">Lugar: </h5>
                    <p>Sociedad Agropecuaria de Rocha</p>
                </div>
            </div>
        </div>
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
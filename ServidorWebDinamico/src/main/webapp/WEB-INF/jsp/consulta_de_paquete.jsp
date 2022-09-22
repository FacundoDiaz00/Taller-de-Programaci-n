<%-- 

# ATTRIBUTOS QUE PRECISA LA PÁGINA

- DTUsuario "usuarioLogeado" (opcional)
- DTPaqueteDetalles paquete

 --%>


<%@page import="logica.datatypes.DTPaquete"%>
<%@page import="logica.datatypes.DTActividadTuristica"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="/WEB-INF/css/consulta_de_paquete.css">
    
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>
    
<main>
    


    <section id="contenedor">

		<jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
		
		<div id='parte_izquierda'>
		
		
	
        <div id="titulo">
            <h1>Consulta de Paquete </h1>
        

        <div id="info-paquete">


            <div id="info-general-imagen">
                <img src="../img/pack1.png" alt="">
            </div>

            <div id="info">
                <h2>Disfrutar Rocha</h2>
                <h6>Creado el 10/08/2022</h6>

            </div>

            <div id="resto-de-la-info-actividad">


                <div class="div-doble" id="validesPaquete">
                    <h5 class="label">Valides paquete: </h5>
                    <p>20 dias</p>
                </div>
                <div class="div-doble" id="descuento">
                    <h5 class="label">Descuento: </h5>
                    <p>20%</p>
                </div>
                <div class="div-doble" id="descripcion">
                    <h5 class="label">Descripcion: </h5>
                    <p>Actividades para hacer en familia y disfrutar arte y gastronomÃ­a</p>
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
                            <span class="input-label">Cantidad turistas</span></span>
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

                <div class="card mb-3" style="max-width: 800px;">
                    <div class="row g-0">
                        <div class="col-md-4 img-contain">
                            <img src="../img/actividad1.png" class="img-fluid rounded-start imagen">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">Degusta</h5>
                                <p class="card-text descripcion-actividad">Festival gastronÃ³mico de productos locales en Rocha</p>
                                <a href="INSERTAR_SERVLET_A_CONSULTAR" class="btn btn-primary">Ver mas</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card mb-3" style="max-width: 800px;">
                    <div class="row g-0">
                        <div class="col-md-4 img-contain">
                            <img src="../img/actividad2.png" class="img-fluid rounded-start imagen">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">Teatro con Sabores</h5>
                                <p class="card-text descripcion-actividad">En el mes aniversario del Club Deportivo UniÃ³n de Rocha te invitamos a una merienda deliciosa.</p>
                                <a href="INSERTAR_SERVLET_A_CONSULTAR" class="btn btn-primary">Ver mas</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		</div>
    </section>
</main>


<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>

</body>
</html>

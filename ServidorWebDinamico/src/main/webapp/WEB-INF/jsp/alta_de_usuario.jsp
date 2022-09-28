<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>     
    <link rel="stylesheet" href="../css/alta_de_usuario.css">

    <title>Turismo UY</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

    <section id="contenedor">
        <jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
        <div id="contenedorForm">
            <form class="card" id="form-alta-usuario">
                <div class="card-body">
                    <h5 class="card-title">Registrar Usuario</h5>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Nickname:<span class="obligatorio"> *</span></span>
                        <input id="input-nickname" type="text" required class="form-control" placeholder="Ingrese un nickname" aria-label="Nickname" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Nombre:<span class="obligatorio"> *</span></span>
                        <input id="input-nombre" type="text" required class="form-control" placeholder="Ingrese un nombre" aria-label="Nombre" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Apellido:<span class="obligatorio"> *</span></span>
                        <input id="input-apellido" type="text" required class="form-control" placeholder="Ingrese un apellido" aria-label="Apellido" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Contraseña:<span class="obligatorio"> *</span></span>
                        <input id="input-contraseña" type="password" required class="form-control" placeholder="Ingrese una contraseña" aria-label="Contraseña" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Confirmacion contraseña:<span class="obligatorio"> *</span></span>
                        <input id="input-confirmacion-contraseña" type="password" required class="form-control" placeholder="Vuelva a ingresar la contraseña" aria-label="Nickname" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Correo electronico:<span class="obligatorio"> *</span></span>
                        <input id="input-correo-electronico" type="email" required class="form-control" placeholder="Ingrese un correo" aria-label="Correo electronico" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Fecha de nacimiento:<span class="obligatorio"> *</span></span>
                        <input id="input-fecha-nacimiento" type="date" required class="form-control" placeholder="Fecha nacimiento" aria-label="Fecha nacimiento" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Imagen:</span>
                        <input id="input-imagen" type="file" accept="image/*"  class="form-control" placeholder="Imagen" aria-label="Imagen" aria-describedby="basic-addon1">
                    </div>

                    <div  class="contenedorinput mb-3">
                        <span class="input-label">Tipo de usuario<span class="obligatorio"> *</span></span>
                        <div id="tipoUsuarioRadioButtonContainer">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="tipoUsuario" id="checkTipoProveedor"  checked>
                                <label class="form-check-label" for="checkTipoProveedor">
                                    Proveedor
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="tipoUsuario" id="checkTipoTurista" >
                                <label class="form-check-label" for="checkTipoTurista">
                                    Turista
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="contenedorinput mb-3 eliminarElemento " id="nacionalidad-field">
                        <span class="input-label">Nacionalidad:<span class="obligatorio"> *</span></span>
                        <input id="input-nacionalidad" type="hidden" required class="form-control" placeholder="Ingrese una nacionalidad" aria-label="Nacionalidad" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3" id="descripcion-general-field">
                        <span class="input-label">Descripcion general:<span class="obligatorio"> *</span></span>
                        <input id="input-descripcionGeneral" type="text" required class="form-control" placeholder="Ingrese una descripcion general" aria-label="Descripcion general" aria-describedby="basic-addon1">
                    </div>

                    <div class="contenedorinput mb-3" id="link-sito-web-field">
                        <span class="input-label">Link sito web:</span>
                        <input id="input-link" type="text" class="form-control" placeholder="ingrese un link al sitio web" aria-label="Link" aria-describedby="basic-addon1">
                    </div>



                    <div id="botonera">
                        <a href="index.html" class="btn btn-danger">Cancelar</a>
                        <input type="submit" id="aceptar-boton" class="btn btn-success" value="Aceptar">
                    </div>

                </div>
            </form>
        </div>
        <div></div>
    </section>



 	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
    <script src="../js/alta_usuario.js"></script>


</body>
</html>
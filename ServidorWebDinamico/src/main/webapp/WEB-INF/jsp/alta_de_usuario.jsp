<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>     
    <link rel="stylesheet" href="css/alta_de_usuario.css">

    <title>Turismo UY</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

	<%! public String getTextWithoutNull(String value){
			return value == null ? "" : value;
		}
	%>
	

    <section id="contenedor">
        <jsp:include page="/WEB-INF/jsp/templates/menuLateral.jsp"/>
        <div id="contenedorForm">
            <form class="card" id="form-alta-usuario" name="altaUsuarioForm" enctype="multipart/form-data" method="post" action="AltaDeUsuario">
                <div class="card-body">
                    <h5 class="card-title">Registrar Usuario</h5>

                    <div class="contenedorinput mb-3">
                    	<% String nickname = getTextWithoutNull((String)request.getAttribute("nickname")); %>
                        <span class="input-label">Nickname:<span class="obligatorio"> *</span></span>
                        <input id="input-nickname" 
                        	   type="text" 
                        	   required 
                        	   name="nickname" 
                        	   class="form-control" 
                        	   placeholder="Ingrese un nickname" 
                        	   aria-label="Nickname" 
                        	   aria-describedby="basic-addon1" 
                        	   value="<%= nickname %>">
                    </div>

					<div style="text-align: right; margin-bottom: 3px"  id="ajaxGetUserServletResponseNickname"></div>
                    

                    <div class="contenedorinput mb-3">
                    	<% String nombre = getTextWithoutNull((String)request.getAttribute("nombre")); %>
                        <span class="input-label">Nombre:<span class="obligatorio"> *</span></span>
                        <input id="input-nombre" 
                        	   type="text" 
                        	   required 
                        	   name="nombre" 
                        	   class="form-control" 
                        	   placeholder="Ingrese un nombre" 
                        	   aria-label="Nombre" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= nombre %>">
                    </div>

                    <div class="contenedorinput mb-3">
                    	<% String apellido = getTextWithoutNull((String)request.getAttribute("apellido")); %>
                        <span class="input-label">Apellido:<span class="obligatorio"> *</span></span>
                        <input id="input-apellido" 
                        	   type="text" 
                        	   required 
                        	   name="apellido" 
                        	   class="form-control" 
                        	   placeholder="Ingrese un apellido" 
                        	   aria-label="Apellido" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= apellido %>"
                        	   >
                    </div>

                    <div class="contenedorinput mb-3">
                    <% String password = getTextWithoutNull((String)request.getAttribute("password")); %>
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

                    <div class="contenedorinput mb-3">
                        <span class="input-label">Confirmación contraseña:<span class="obligatorio"> *</span></span>
                        <input id="input-confirmacion-contrasenia" 
                        	   type="password" 
                        	   required 
                        	   class="form-control" 
                        	   placeholder="Vuelva a ingresar la contraseña" 
                        	   aria-label="Nickname" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= password %>">
                    </div>

                    <div class="contenedorinput mb-3">
                    	<% String email = getTextWithoutNull((String)request.getAttribute("email")); %>
                        <span class="input-label">Correo electrónico:<span class="obligatorio"> *</span></span>
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

					<div style="text-align: right; margin-bottom: 3px" id="ajaxGetUserServletResponseCorreo"></div>

                    <div class="contenedorinput mb-3">
                    	<% String fechaNac = getTextWithoutNull((String)request.getAttribute("fechaNac")); %>
                        <span class="input-label">Fecha de nacimiento:<span class="obligatorio"> *</span></span>
                        <input id="input-fecha-nacimiento" 
                        	   type="date" 
                        	   required 
                        	   name="fechaNac" 
                        	   class="form-control" 
                        	   placeholder="Fecha nacimiento" 
                        	   aria-label="Fecha nacimiento" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= fechaNac %>">
                    </div>

                    <div class="contenedorinput mb-3">

                        <span class="input-label">Imagen:</span>
                        <input id="input-imagen" 
                        	   type="file" 
                        	   accept="image/*" 
                        	   name="img" 
                        	   class="form-control" 
                        	   placeholder="Imagen" 
                        	   aria-label="Imagen" 
                        	   aria-describedby="basic-addon1">
                    </div>

                    <div  class="contenedorinput mb-3">
                    	<% String tipoUsuario = (String)request.getAttribute("tipoUsuario");
                    		if(tipoUsuario == null){
                    			tipoUsuario = "proveedor";
                    		}
                    	
                    	%>
                    	
                    	<script>
                    		
                    	
                    	</script>
                    	
                        <span class="input-label">Tipo de usuario<span class="obligatorio"> *</span></span>                        
                        
                        <div id="tipoUsuarioRadioButtonContainer">
                            <div class="form-check">
                                <input class="form-check-input" 
                                	   name="tipoUsuario" 
                                	   type="radio" 
                                	   id="checkTipoProveedor" 
                                	   value="proveedor"
                                	   <%= tipoUsuario.equals("proveedor") ? "checked" : ""%>  
                                	   >
                                <label class="form-check-label" for="checkTipoProveedor">
                                    Proveedor
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" 
                                	   name="tipoUsuario" 
                                	   type="radio" 
                                	   id="checkTipoTurista" 
                                	   value="turista"                                	 
                                	   <%= tipoUsuario.equals("turista") ? "checked" : ""%>>
                                <label class="form-check-label" for="checkTipoTurista">
                                    Turista
                                </label>
                            </div>
                        </div>
                                                 
                        
                    </div>

                    <div class="contenedorinput mb-3 <%= tipoUsuario.equals("turista") ? "" : "eliminarElemento" %>" id="nacionalidad-field">
                    	<% String nacionalidad = getTextWithoutNull((String)request.getAttribute("nacionalidad")); %>
                        <span class="input-label">Nacionalidad:<span class="obligatorio"> *</span></span>
                        <input id="input-nacionalidad" 
                        	   type= "<%= tipoUsuario.equals("turista") ? "text" : "hidden"%>" 
                        	   name="nacionalidad" 
                        	   required 
                        	   class="form-control" 
                        	   placeholder="Ingrese una nacionalidad" 
                        	   aria-label="Nacionalidad" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= nacionalidad %>">
                    </div>

                    <div class="contenedorinput mb-3 <%= tipoUsuario.equals("proveedor") ? "" : "eliminarElemento" %>" id="descripcion-general-field">
                    	<% String descripcionGeneral = getTextWithoutNull((String)request.getAttribute("descripcionGeneral")); %>
                        <span class="input-label">Descripción general:<span class="obligatorio"> *</span></span>
                        <input id="input-descripcionGeneral" 
                        	   type="<%= tipoUsuario.equals("proveedor") ? "text" : "hidden"%>" 
                        	   name="descripcionGeneral" 
                        	   required 
                        	   class="form-control" 
                        	   placeholder="Ingrese una descripcion general" 
                        	   aria-label="Descripcion general" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= descripcionGeneral %>">
                    </div>

                    <div class="contenedorinput mb-3 <%= tipoUsuario.equals("proveedor") ? "" : "eliminarElemento"%>" id="link-sito-web-field">
                    	<% String link = getTextWithoutNull((String)request.getAttribute("link")); %>
                        <span class="input-label">Link sito web:</span>
                        <input id="input-link" 
                        	   type="<%= tipoUsuario.equals("proveedor") ? "text" : "hidden"%>"  
                        	   name="link" 
                        	   class="form-control" 
                        	   placeholder="ingrese un link al sitio web" 
                        	   aria-label="Link" 
                        	   aria-describedby="basic-addon1"
                        	   value="<%= link %>">
                    </div>



                    <div id="botonera">
                        <a href="index" class="btn btn-danger">Cancelar</a>
                        <input type="submit" id="aceptar-boton" class="btn btn-success" value="Aceptar">
                    </div>

                </div>
            </form>
        </div>
        
    </section>



 	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp"/>
    <script src="js/alta_usuario.js"></script>
    
    <%if(request.getAttribute("motivoDeError") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('error', "Error al crear el usuario" , mensajeError , 200);
    </script>
    <%} %>
    
    
    <% if( (Boolean)request.getAttribute("exito") == Boolean.TRUE){ %>
    <script>
    
	    setTimeout(() => {
	        Swal.fire({
	            icon: "success",
	            title: "Usuario creado con exito",
	            text: "Su usuario ha sido creado con éxito. Al cerrar este popup sea redirigido a la ventana de inicio de sección con el fin que se autentifique y pueda comenzar a usar su perfil. ",
	            confirmButtonText: 'Entendido'  
	        }).then((res) => {
	        	window.location.href = "IniciarSesion";
	        })
	    }, 200)
    
	</script>
	<%} %>
	
	<script>
		$('#input-nickname')[0].addEventListener("focusout", function() {
			console.log('entrè a coso de ajax')
			$.ajax({
				url : 'UsuarioYaRegistradoServlet',
				data : {
					nickname : $('#input-nickname').val()
				},
				success : function(responseText) {
					$('#ajaxGetUserServletResponseNickname').text(responseText);
				}
			});

		});

		$('#input-correo-electronico')[0].addEventListener("focusout", function() {
			console.log('entrè a coso de ajax')
			$.ajax({
				url : 'UsuarioYaRegistradoServlet',
				data : {
					correo : $('#input-correo-electronico').val()
				},
				success : function(responseText) {
					$('#ajaxGetUserServletResponseCorreo').text(responseText);
				}
			});

		});
	</script>
</body>
</html>
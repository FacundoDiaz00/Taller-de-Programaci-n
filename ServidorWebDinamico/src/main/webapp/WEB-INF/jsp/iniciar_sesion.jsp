
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/templates/commonHead.jsp"/>
    <link rel="stylesheet" href="css/iniciar_sesion.css">

	
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/templates/navBar.jsp"/>

	
<section id="contenedor">
        <div>
        </div>
    <div id="centerRow">
        
          <form class="card" id="form-iniciar-sesion" name="iniciarSesionForm" method="post" action="IniciarSesion">
                <div class="card-body">
                    <h5 class="card-title">Iniciar Sesión</h5>
                    

                    <span class="input-label">Identificador:<span class="obligatorio"> *</span></span>
			        <div class="input-group mb-3 contenedorinput">
			   
			        
			            <select id="idForm" name="idForm" class="form-select" aria-label="Default select example">
			                <option selected value="1">Email</option>
			                <option value="2">Nickname</option>
			            </select>
			            
			            <% String email = "";%>
			            
                        <input id="input-correo-electronico" 
                         	   type="email" 
                         	   name="email" 
                         	   class="form-control" 
                         	   placeholder="Ingrese un correo" 
                         	   aria-label="Correo electronico" 
                         	   aria-describedby="basic-addon1"
                         	   value="<%=email%>">
                        	
                      	<% String nickname = "";%>
                      	<input id="input-nickname" 
                          	   type="hidden" 
                          	   name="nickname" 
                          	   class="form-control" 
                          	   placeholder="Ingrese un nickname" 
                          	   aria-label="nickname" 
                          	   aria-describedby="basic-addon1"
                          	   value="<%=nickname%>">
			        </div>

                    <div class="contenedorinput mb-3">
                    <% String password = ""; %>
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

<script>
        $(function(){
        $('#idForm').bind('change', function () {
        var valor = $(this).val(); // get selected value
        console.log("cambia el idForm: ", valor);
       	nickname = null;
    	email = null;
        if(valor == 1){
        	console.log("entre a 1");
        	$("#input-nickname")[0].type = "hidden";
        	$("#input-correo-electronico")[0].type = "email";
        }else{
        	$("#input-correo-electronico")[0].type = "hidden";
        	$("#input-nickname")[0].type = "text";
        }
         });
       });
</script>

    <script src="js/alta_usuario.js"></script>
    
    <%if(request.getAttribute("motivoDeError") != null){ %>
    
    <script>
    	const mensajeError = "<%= (String) request.getAttribute("motivoDeError")%>"
    	generarMensaje('error', "Error al iniciar sesión" , mensajeError , 200);
    </script>
    <%} %>


</body>
</html>